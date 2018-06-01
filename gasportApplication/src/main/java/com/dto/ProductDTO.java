package com.dto;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author vipul pachauri
 */
public class ProductDTO {

    @NotEmpty
    private String categoryName;

    @NotEmpty
    private String subCategoryName;

    @NotEmpty
    private String brandName;

    @NotEmpty
    private List<ProductDetailsDTO> productDetails;

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getSubCategoryName() {
        return subCategoryName;
    }

    public void setSubCategoryName(String subCategoryName) {
        this.subCategoryName = subCategoryName;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public List<ProductDetailsDTO> getProductDetails() {
        return productDetails;
    }

    public void setProductDetails(List<ProductDetailsDTO> productDetails) {
        this.productDetails = productDetails;
    }


    @Override
    public String toString() {
        return "ProductDTO{" +
                "categoryName='" + categoryName + '\'' +
                ", subCategoryName='" + subCategoryName + '\'' +
                ", brandName='" + brandName + '\'' +
                ", productDetails=" + productDetails +
                '}';
    }
}
