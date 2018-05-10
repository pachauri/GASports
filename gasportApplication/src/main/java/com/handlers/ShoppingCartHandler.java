package com.handlers;

import com.Mappings;
import com.db.CartItem;
import com.db.ShoppingCart;
import com.response.APIResponse;
import com.service.ShoppingCartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author vipul pachauri
 */
@RestController
public class ShoppingCartHandler {

    private static Logger logger = LoggerFactory.getLogger(ShoppingCartHandler.class);

    @Autowired
    private ShoppingCartService shoppingCartService;

    @PostMapping(value= Mappings.CART_ADD_PRODUCT)
    public APIResponse cartAddProduct(@RequestBody ShoppingCart shoppingCart){
        logger.info("Adding product in cart.");
        return shoppingCartService.addProductInCart(shoppingCart);
    }

    @DeleteMapping(value= Mappings.CART_REMOVE_PRODUCT)
    public APIResponse cartRemoveProduct(@RequestBody CartItem cartItem){
        logger.info("Removing product in cart.");
        return shoppingCartService.removeProductfromCart(cartItem);
    }

    @PutMapping(value= Mappings.CART_PRODUCT_COUNT_UPDATE)
    public APIResponse cartUpdateProductCount(@RequestBody CartItem cartItem){
        logger.info("Incrementing product count of cart Item [{}]",cartItem);
        return shoppingCartService.cartUpdateProductCount(cartItem);
    }


}
