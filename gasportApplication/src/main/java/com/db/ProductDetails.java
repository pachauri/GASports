package com.db;

import java.util.List;

/**
 * @author vipul pachauri
 */
public class ProductDetails extends BaseProductInfo<ProductDetails> {

    private String name;

    private String price;

    private List<String> imageUrlList;

    public ProductDetails() {
    }

    public ProductDetails(String name, String price,List<String> imageUrlList) {
        this.name = name;
        this.price = price;
        this.imageUrlList = imageUrlList;
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

    public List<String> getImageUrlList() {
        return imageUrlList;
    }

    public void setImageUrlList(List<String> imageUrlList) {
        this.imageUrlList = imageUrlList;
    }
}
