package com.dto;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author vipul pachauri
 */
public class ProductDetailsDTO {

    private String price;

    private String name;

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ProductDetailsDTO{" +
                "price='" + price + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
