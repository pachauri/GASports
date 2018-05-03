package com.service;

import com.db.ProductDetails;
import com.dto.*;
import com.response.APIResponse;

import java.util.List;

/**
 * @author vipul pachauri
 */
public interface ProductService {

    APIResponse addOrUpdateCategory(String oldCategoryName, CategoryDTO categoryDTO);

    APIResponse addOrUpdateSubCategory(String oldSubCategoryName,SubCategoryDTO subCategoryDTO);

    APIResponse addOrUpdateBrand(String oldBrandName, BrandDTO brandDTO);

    APIResponse addOrUpdateProductDetails(String oldProductName, ProductDTO productDTO);

    ProductDetails getProductDetailsFromList(List<ProductDetails> productDetails, String productName);

}
