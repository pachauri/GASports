package com.dto;

import java.util.List;

/**
 * @author vipul pachauri
 */
public class ProductParentDTO {

    private List<CategoryDTO> categories;

    public List<CategoryDTO> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoryDTO> categories) {
        this.categories = categories;
    }

    @Override
    public String toString() {
        return "ProductParentDTO{" +
                "categories=" + categories +
                '}';
    }
}
