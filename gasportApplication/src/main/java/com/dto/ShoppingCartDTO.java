package com.dto;

import java.util.List;

/**
 * @author vipul pachauri
 */
public class ShoppingCartDTO {

    private List<ProductDetailsDTO> productDetailsList;

    public List<ProductDetailsDTO> getProductDetailsList() {
        return productDetailsList;
    }

    public void setProductDetailsList(List<ProductDetailsDTO> productDetailsList) {
        this.productDetailsList = productDetailsList;
    }

    @Override
    public String toString() {
        return "ShoppingCartDTO{" +
                "productDetailsList=" + productDetailsList +
                '}';
    }
}
