package com.db;

import org.springframework.data.annotation.Id;

import java.io.Serializable;

/**
 * @author vipul pachauri
 */
public class ProductDetails extends BaseProductInfo {

    private String name;

    private String price;

    public ProductDetails(String name, String price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

}
