package com.dto;

import org.hibernate.validator.constraints.NotEmpty;

import java.util.List;

/**
 * @author vipul pachauri
 */
public class BrandDTO {

    @NotEmpty
    private String categoryName;

    @NotEmpty
    private String subCategoryName;

    private List<String> brandNames;

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

    public List<String> getBrandNames() {
        return brandNames;
    }

    public void setBrandNames(List<String> brandNames) {
        this.brandNames = brandNames;
    }

    @Override
    public String toString() {
        return "BrandDTO{" +
                "categoryName='" + categoryName + '\'' +
                ", subCategoryName='" + subCategoryName + '\'' +
                ", brandNames=" + brandNames +
                '}';
    }
}
