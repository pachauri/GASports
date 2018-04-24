package com.dto;

import java.util.List;

/**
 * @author vipul pachauri
 */
public class CategoryDTO {

    private List<String> categoryNames;

    public List<String> getCategoryNames() {
        return categoryNames;
    }

    public void setCategoryNames(List<String> categoryNames) {
        this.categoryNames = categoryNames;
    }

    @Override
    public String toString() {
        return "CategoryDTO{" +
                "categoryNames=" + categoryNames +
                '}';
    }
}
