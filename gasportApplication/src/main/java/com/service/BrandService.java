package com.service;

import com.db.Brand;
import com.db.Category;
import com.dto.BrandDTO;
import com.response.APIResponse;

import java.util.List;

/**
 * @author vipul pachauri
 */
public interface BrandService {

    List<Brand> createBrands(BrandDTO brandDTO, Category category);

    Brand getBrandsFromList(List<Brand> brandList, String brandName);

    APIResponse getBrand(String categoryName, String subcategoryName, String brandName);

    APIResponse getBrands(String subcategoryName);
}
