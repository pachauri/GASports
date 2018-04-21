package com.dto;

import org.hibernate.validator.constraints.NotEmpty;

import java.util.List;

/**
 * @author vipul pachauri
 */
public class CategoryDTO {

    private String uid;

    private String name;

    @NotEmpty
    private List<SubCategoryDTO> subCategories;

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

    public List<SubCategoryDTO> getSubCategories() {
        return subCategories;
    }

    public void setSubCategories(List<SubCategoryDTO> subCategories) {
        this.subCategories = subCategories;
    }

    @Override
    public String toString() {
        return "CategoryDTO{" +
                "uid='" + uid + '\'' +
                ", name='" + name + '\'' +
                ", subCategories=" + subCategories +
                '}';
    }
}
