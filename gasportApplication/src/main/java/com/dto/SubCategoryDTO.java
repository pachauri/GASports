package com.dto;

import org.hibernate.validator.constraints.NotEmpty;

import java.util.List;

/**
 * @author vipul pachauri
 */
public class SubCategoryDTO {

    @NotEmpty
    private String categoryName;

    private List<String> subcategoryNames;

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public List<String> getSubcategoryNames() {
        return subcategoryNames;
    }

    public void setSubcategoryNames(List<String> subcategoryNames) {
        this.subcategoryNames = subcategoryNames;
    }

    @Override
    public String toString() {
        return "SubCategoryDTO{" +
                "categoryName='" + categoryName + '\'' +
                ", subcategoryNames=" + subcategoryNames +
                '}';
    }
}
