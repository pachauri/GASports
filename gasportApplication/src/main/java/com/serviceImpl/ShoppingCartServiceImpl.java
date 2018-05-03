package com.serviceImpl;

import com.db.ApplicationUser;
import com.db.ProductDetails;
import com.db.ShoppingCart;
import com.dto.ProductDetailsDTO;
import com.dto.ShoppingCartDTO;
import com.repository.UserRepository;
import com.response.APIResponse;
import com.response.SuccessResponse;
import com.service.ShoppingCartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.constants.GASportConstant.SUCCESS;

/**
 * @author vipul pachauri
 */
@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private static Logger logger = LoggerFactory.getLogger(ShoppingCartServiceImpl.class);

    @Autowired
    private UserRepository userRepository;


    @Override
    public APIResponse addProductInCart(ShoppingCartDTO shoppingCartDTO) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(auth.getPrincipal());
        ApplicationUser applicationUser = userRepository.findByUsername(auth.getPrincipal().toString());
        ShoppingCart shoppingCart = new ShoppingCart();

        List<ProductDetailsDTO>  productDetailsDTOS = shoppingCartDTO.getProductDetailsList();
        List<ProductDetails> productDetailsList = new ArrayList<>();
        for(ProductDetailsDTO productDetailsDTO : productDetailsDTOS){
            ProductDetails productDetails = new ProductDetails();
            productDetails.setName(productDetailsDTO.getName());
            productDetails.setPrice(productDetailsDTO.getPrice());
            productDetailsList.add(productDetails);
        }

        shoppingCart.setProductDetails(productDetailsList);
        applicationUser.setShoppingCart(shoppingCart);
        //userRepository.save(applicationUser);
        return new APIResponse(SUCCESS,new SuccessResponse("1","User Details"),applicationUser);
    }
}
