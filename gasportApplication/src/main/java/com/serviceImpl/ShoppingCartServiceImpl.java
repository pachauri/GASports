package com.serviceImpl;

import com.ExceptionHandler.GASportsException;
import com.db.ApplicationUser;
import com.db.CartItem;
import com.db.ShoppingCart;
import com.repository.UserRepository;
import com.response.APIResponse;
import com.response.ErrorResponse;
import com.response.SuccessResponse;
import com.service.ShoppingCartService;
import enums.ErrorCodes;
import enums.SuccessCodes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;

import static com.constants.GASportConstant.FAILURE;
import static com.constants.GASportConstant.SUCCESS;

/**
 * @author vipul pachauri
 */
@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private static Logger logger = LoggerFactory.getLogger(ShoppingCartServiceImpl.class);

    private static final String CURRENT_USER = "currentUser";
    private static final String SHOPPING_CART = "shoppingCart";

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MongoTemplate mongoTemplate;


    @Override
    public APIResponse addProductInCart(ShoppingCart shoppingCart) {
        try {
            HashMap<String, Object> hashMap = getShoppingCart();
            ApplicationUser applicationUser = (ApplicationUser) hashMap.get(CURRENT_USER);
            ShoppingCart existShoppingCart = (ShoppingCart) hashMap.get(SHOPPING_CART);
            if (existShoppingCart != null) {
                logger.info("Shopping cart exists. Adding new cart items in shopping cart.");
                List<CartItem> cartItems = existShoppingCart.getCartItems();
                if (CollectionUtils.isEmpty(cartItems)) {
                    logger.error("Cart items does not exist.");
                    return new APIResponse(FAILURE, new ErrorResponse(ErrorCodes.ERROR_ADDING_PRODUCT_CART.getResponseCode(), ErrorCodes.ERROR_ADDING_PRODUCT_CART.getResponseMessage(), "Cart items does not exist."));
                }
                cartItems.addAll(shoppingCart.getCartItems());
                existShoppingCart.setCartItems(cartItems);
            } else {
                logger.info("Shopping cart does not exist. Creating new shopping cart.");
                existShoppingCart = ShoppingCart.buildShoppingCart(shoppingCart);
            }
            applicationUser.setShoppingCart(existShoppingCart);
            userRepository.save(applicationUser);
            logger.info("Product added successfully in cart. [{}]", existShoppingCart);
            return new APIResponse(SUCCESS, new SuccessResponse(SuccessCodes.SUCCESS_ADDED_PRODUCT_CART.getResponseCode(), SuccessCodes.SUCCESS_ADDED_PRODUCT_CART.getResponseMessage()), existShoppingCart);
        } catch (Exception e) {
            logger.error("Error : addProductInCart [{}]", e.getStackTrace());
            return new APIResponse(FAILURE, new ErrorResponse(ErrorCodes.ERROR_ADDING_PRODUCT_CART.getResponseCode(), ErrorCodes.ERROR_ADDING_PRODUCT_CART.getResponseMessage(), e.getMessage()));
        }

    }

    @Override
    public APIResponse removeProductfromCart(CartItem cartItem) {
        try {
            HashMap<String, Object> hashMap = getShoppingCart();
            ApplicationUser applicationUser = (ApplicationUser) hashMap.get(CURRENT_USER);
            ShoppingCart existShoppingCart = (ShoppingCart) hashMap.get(SHOPPING_CART);
            if (existShoppingCart == null) {
                logger.error("ERROR : removeProductfromCart shopping Cart [{}]", existShoppingCart);
                return new APIResponse(FAILURE, new ErrorResponse(ErrorCodes.ERROR_REMOVING_PRODUCT_CART.getResponseCode(), ErrorCodes.ERROR_ADDING_PRODUCT_CART.getResponseMessage(), "Empty shopping cart."));
            }
            List<CartItem> cartItems = existShoppingCart.getCartItems();
            if (CollectionUtils.isEmpty(cartItems)) {
                logger.error("Cart items does not exist.");
                return new APIResponse(FAILURE, new ErrorResponse(ErrorCodes.ERROR_REMOVING_PRODUCT_CART.getResponseCode(), ErrorCodes.ERROR_ADDING_PRODUCT_CART.getResponseMessage(), "Cart items does not exist."));
            }

            ListIterator<CartItem> listIterator = cartItems.listIterator();
            logger.info("Iterating over cart items to remove cart item [{}", cartItem);
            while (listIterator.hasNext()) {
                if (listIterator.next().getProductDetails().getName().equalsIgnoreCase(cartItem.getProductDetails().getName())) {
                    logger.info("Cart item found.Removing from cart items.");
                    listIterator.remove();
                }
            }
            existShoppingCart.setCartItems(cartItems);
            applicationUser.setShoppingCart(existShoppingCart);
            userRepository.save(applicationUser);
            logger.info("Product removed from cart successfully.");
            return new APIResponse(SUCCESS, new SuccessResponse(SuccessCodes.SUCCESS_REMOVED_PRODUCT_CART.getResponseCode(), SuccessCodes.SUCCESS_REMOVED_PRODUCT_CART.getResponseMessage()), existShoppingCart);
        } catch (Exception e) {
            logger.error("ERROR :removeProductfromCart, cause [{}] ", e);
            return new APIResponse(FAILURE, new ErrorResponse(ErrorCodes.ERROR_REMOVING_PRODUCT_CART.getResponseCode(), ErrorCodes.ERROR_ADDING_PRODUCT_CART.getResponseMessage(), e.getMessage()));
        }


    }

    @Override
    public APIResponse cartUpdateProductCount(CartItem cartItem) {
        try {
            HashMap<String, Object> hashMap = getShoppingCart();
            ApplicationUser applicationUser = (ApplicationUser) hashMap.get(CURRENT_USER);
            ShoppingCart existShoppingCart = (ShoppingCart) hashMap.get(SHOPPING_CART);
            if (existShoppingCart == null) {
                logger.error("ERROR : cartUpdateProductCount shopping Cart [{}]", existShoppingCart);
                return new APIResponse(FAILURE, new ErrorResponse(ErrorCodes.ERROR_UPDATING_PRODUCT_COUNT.getResponseCode(), ErrorCodes.ERROR_UPDATING_PRODUCT_COUNT.getResponseMessage(), "Empty shopping cart."));
            }
            List<CartItem> cartItems = existShoppingCart.getCartItems();
            if (CollectionUtils.isEmpty(cartItems)) {
                logger.error("Cart items does not exist.");
                return new APIResponse(FAILURE, new ErrorResponse(ErrorCodes.ERROR_UPDATING_PRODUCT_COUNT.getResponseCode(), ErrorCodes.ERROR_UPDATING_PRODUCT_COUNT.getResponseMessage(), "Cart items does not exist."));
            }

            logger.info("Finding cart item to increment product count. [{}", cartItem);
            for (CartItem item :cartItems) {
                if (item.getProductDetails().getName().equalsIgnoreCase(cartItem.getProductDetails().getName())) {
                    logger.info("Cart item found.Incrementing product count by one.");
                    item.setQuantity(cartItem.getQuantity());
                    break;
                }
            }

            existShoppingCart.setCartItems(cartItems);
            applicationUser.setShoppingCart(existShoppingCart);
            userRepository.save(applicationUser);
            logger.info("Product removed from cart successfully.");
            return new APIResponse(SUCCESS, new SuccessResponse(SuccessCodes.SUCCESS_UPDATED_PRODUCT_COUNT.getResponseCode(), SuccessCodes.SUCCESS_UPDATED_PRODUCT_COUNT.getResponseMessage()), existShoppingCart);
        } catch (Exception e) {
            logger.error("ERROR :removeProductfromCart, cause [{}] ", e);
            return new APIResponse(FAILURE, new ErrorResponse(ErrorCodes.ERROR_UPDATING_PRODUCT_COUNT.getResponseCode(), ErrorCodes.ERROR_UPDATING_PRODUCT_COUNT.getResponseMessage(), e.getMessage()));
        }
    }


    private HashMap<String, Object> getShoppingCart() {
        logger.info("Finding current logged in user.");
        try{
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if(auth.isAuthenticated()){
                ApplicationUser applicationUser = userRepository.findByUsername(auth.getPrincipal().toString());
                if(applicationUser == null){
                    throw new GASportsException("User not Found");
                }
                logger.info("Logged in user. [{}]", applicationUser);
                logger.info("Finding existing shopping cart.");
                HashMap<String, Object> hashMap = new HashMap<>();
                hashMap.put(CURRENT_USER, applicationUser);
                hashMap.put(SHOPPING_CART, applicationUser.getShoppingCart());
                return hashMap;
            }
        }catch (Exception e){
            logger.error("Error : getCurrentUser() [{}]",e);
        }
        throw new GASportsException("Error in authorising user.");
    }
}
