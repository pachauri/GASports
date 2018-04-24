package com.service;

import com.dto.*;
import com.response.APIResponse;

/**
 * @author vipul pachauri
 */
public interface ProductService {

    APIResponse addCategory(CategoryDTO categoryDTO);

    APIResponse addSubCategory(SubCategoryDTO subCategoryDTO);

    APIResponse addBrand(BrandDTO brandDTO);

    APIResponse addProductDetails(ProductDTO productDTO);
}
