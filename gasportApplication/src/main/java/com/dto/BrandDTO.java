package com.dto;

import org.hibernate.validator.constraints.NotEmpty;

import java.util.List;

/**
 * @author vipul pachauri
 */
public class BrandDTO {

    private String uid;

    private String name;

    @NotEmpty
    private List<ProductDTO> products;

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

    public List<ProductDTO> getProducts() {
        return products;
    }

    public void setProducts(List<ProductDTO> products) {
        this.products = products;
    }
}
