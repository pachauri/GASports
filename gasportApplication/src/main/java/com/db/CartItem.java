package com.db;

import io.swagger.models.auth.In;

/**
 * @author vipul pachauri
 */
public class CartItem {

    private Integer quantity;

    private ProductDetails productDetails;

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public ProductDetails getProductDetails() {
        return productDetails;
    }

    public void setProductDetails(ProductDetails productDetails) {
        this.productDetails = productDetails;
    }
}
