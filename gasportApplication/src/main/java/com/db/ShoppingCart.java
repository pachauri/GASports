package com.db;

import java.util.List;

/**
 * @author vipul pachauri
 */
public class ShoppingCart {

    private List<ProductDetails> productDetails;

    public ShoppingCart() {
    }

    public ShoppingCart(List<ProductDetails> productDetails) {
        this.productDetails = productDetails;
    }

    public List<ProductDetails> getProductDetails() {
        return productDetails;
    }

    public void setProductDetails(List<ProductDetails> productDetails) {
        this.productDetails = productDetails;
    }

}
