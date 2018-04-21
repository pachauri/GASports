package com.db;

import org.springframework.data.annotation.Id;

import java.io.Serializable;

/**
 * @author vipul pachauri
 */
public class ProductDetails implements Serializable {

    private String uid;

    private String name;

    private String price;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
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

    @Override
    public String toString() {
        return "ProductDetails{" +
                "uid='" + uid + '\'' +
                ", name='" + name + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}
