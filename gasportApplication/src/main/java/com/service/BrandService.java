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


    Brand getBrandsFromList(List<Brand> brandList, String brandName);

    APIResponse createBrands(BrandDTO brandDTO, Category category);

    APIResponse updateBrands(String oldBrandName, BrandDTO brandDTO, Category category);

    APIResponse getBrand(String categoryName, String subcategoryName, String brandName);

    APIResponse getBrands(String categoryName, String subcategoryName);

}
