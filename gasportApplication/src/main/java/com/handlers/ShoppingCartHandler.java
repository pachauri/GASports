package com.handlers;

import com.Mappings;
import com.db.ShoppingCart;
import com.dto.ShoppingCartDTO;
import com.response.APIResponse;
import com.service.ShoppingCartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author vipul pachauri
 */
@RestController
public class ShoppingCartHandler {

    private static Logger logger = LoggerFactory.getLogger(ShoppingCartHandler.class);

    @Autowired
    private ShoppingCartService shoppingCartService;

    @PostMapping(value= Mappings.CART_ADD_PRODUCT)
    public APIResponse cartAddProduct(@RequestBody ShoppingCartDTO shoppingCartDTO){
        logger.info("Adding product in cart.");
        return shoppingCartService.addProductInCart(shoppingCartDTO);
    }

    @PostMapping(value= Mappings.CART_REMOVE_PRODUCT)
    public APIResponse cartremoveProduct(){
        logger.info("Removing product in cart.");
        return  null;
    }
}
