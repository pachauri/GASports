package com.db;

import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.List;

/**
 * @author vipul pachauri
 */
public class Brand implements Serializable {

    private String uid;

    private String name;

    private List<ProductDetails> productDetails;

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

    public List<ProductDetails> getProductDetails() {
        return productDetails;
    }

    public void setProductDetails(List<ProductDetails> productDetails) {
        this.productDetails = productDetails;
    }

    @Override
    public String toString() {
        return "Brand{" +
                "uid='" + uid + '\'' +
                ", name='" + name + '\'' +
                ", productDetails=" + productDetails +
                '}';
    }
}
