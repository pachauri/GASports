package com.service;

import com.dto.ShoppingCartDTO;
import com.response.APIResponse;

/**
 * @author vipul pachauri
 */
public interface ShoppingCartService {

    APIResponse addProductInCart(ShoppingCartDTO shoppingCart);
}
