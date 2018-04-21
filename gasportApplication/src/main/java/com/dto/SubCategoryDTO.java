package com.dto;

import org.hibernate.validator.constraints.NotEmpty;

import java.util.List;

/**
 * @author vipul pachauri
 */
public class SubCategoryDTO {

    private String uid;

    private String name;

    @NotEmpty
    private List<BrandDTO> brands;

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

    public List<BrandDTO> getBrands() {
        return brands;
    }

    public void setBrands(List<BrandDTO> brands) {
        this.brands = brands;
    }

    @Override
    public String toString() {
        return "SubCategoryDTO{" +
                "uid='" + uid + '\'' +
                ", name='" + name + '\'' +
                ", brands=" + brands +
                '}';
    }
}
