package com.dto;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author vipul pachauri
 */
public class ProductDetailsDTO {

    private String price;

    private String name;

    private List<String> imgEncodedUrlList;

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

    public List<String> getImgEncodedUrlList() {
        return imgEncodedUrlList;
    }

    public void setImgEncodedUrlList(List<String> imgEncodedUrlList) {
        this.imgEncodedUrlList = imgEncodedUrlList;
    }

    @Override
    public String toString() {
        return "ProductDetailsDTO{" +
                "price='" + price + '\'' +
                ", name='" + name + '\'' +
                ", imgEncodedUrlList=" + imgEncodedUrlList +
                '}';
    }
}
