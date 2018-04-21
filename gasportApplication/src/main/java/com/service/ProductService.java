package com.service;

import com.db.SubCategory;
import com.dto.*;
import com.response.APIResponse;

/**
 * @author vipul pachauri
 */
public interface ProductService {

    APIResponse addCategory(ProductParentDTO productParentDTO);

    APIResponse addSubCategory(CategoryDTO categoryDTO);

    APIResponse addBrand(SubCategoryDTO subCategoryDTO);

   APIResponse addProductDetails(BrandDTO brandDTO);
}
