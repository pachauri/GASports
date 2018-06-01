package com.db;


import java.sql.Timestamp;
import java.util.List;

/**
 * @author vipul pachauri
 */
public class SubCategory extends BaseProductInfo<SubCategory> {

    private String name;

    private List<Brand> brandList;

    public SubCategory(String name) {
        super(new Timestamp(System.currentTimeMillis()).getTime(),new Timestamp(System.currentTimeMillis()).getTime());
        this.name = name;
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
                "name='" + name + '\'' +
                ", brandList=" + brandList +
                '}';
    }
}
