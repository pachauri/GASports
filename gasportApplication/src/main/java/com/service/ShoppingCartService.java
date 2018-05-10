package com.service;

import com.db.CartItem;
import com.db.ShoppingCart;
import com.response.APIResponse;

/**
 * @author vipul pachauri
 */
public interface ShoppingCartService {

    APIResponse addProductInCart(ShoppingCart shoppingCart);

    APIResponse removeProductfromCart(CartItem cartItem);

    APIResponse cartUpdateProductCount(CartItem cartItem);

}
