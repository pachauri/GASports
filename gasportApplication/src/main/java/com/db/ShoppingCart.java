package com.db;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author vipul pachauri
 */
public class ShoppingCart extends BaseProductInfo {

    private List<CartItem> cartItems;

    private double totalAmount;

    public ShoppingCart() {
    }

    public ShoppingCart(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public ShoppingCart setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
        return this;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public ShoppingCart setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
        return this;
    }

    public ShoppingCart setCreationDate() {
        super.setCreationDate();
        return this;
    }

    public ShoppingCart setModificationDate() {
        super.setModificationDate();
        return this;
    }

    public static ShoppingCart buildShoppingCart(ShoppingCart shoppingCart){
        return new ShoppingCart()
                .setCartItems(shoppingCart.getCartItems())
                .setTotalAmount(shoppingCart.totalAmount)
                .setCreationDate()
                .setModificationDate();

    }
}
