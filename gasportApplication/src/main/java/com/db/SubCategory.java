package com.db;


import java.io.Serializable;
import java.util.List;

/**
 * @author vipul pachauri
 */
public class SubCategory implements Serializable {

    private String uid;

    private String name;

    private List<Brand> brandList;

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

    public List<Brand> getBrandList() {
        return brandList;
    }

    public void setBrandList(List<Brand> brandList) {
        this.brandList = brandList;
    }

    @Override
    public String toString() {
        return "SubCategory{" +
                "uid='" + uid + '\'' +
                ", name='" + name + '\'' +
                ", brandList=" + brandList +
                '}';
    }
}
