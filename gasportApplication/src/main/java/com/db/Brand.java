package com.db;

import java.util.List;

/**
 * @author vipul pachauri
 */
public class Brand extends BaseProductInfo<Brand> {

    private String name;

    private List<ProductDetails> productDetails;

    public Brand(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ProductDetails> getProductDetails() {
        return productDetails;
    }

    public void setProductDetails(List<ProductDetails> productDetails) {
        this.productDetails = productDetails;
    }

    @Override
    public String toString() {
        return "Brand{" +
                "name='" + name + '\'' +
                ", productDetails=" + productDetails +
                '}';
    }

}
