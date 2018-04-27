package com.service;

import com.db.ProductDetails;
import com.dto.*;
import com.response.APIResponse;

import java.util.List;

/**
 * @author vipul pachauri
 */
public interface ProductService {

    APIResponse addCategory(CategoryDTO categoryDTO);

    APIResponse addSubCategory(SubCategoryDTO subCategoryDTO);

    APIResponse addBrand(BrandDTO brandDTO);

    APIResponse addProductDetails(ProductDTO productDTO);

    ProductDetails getProductDetailsFromList(List<ProductDetails> productDetails, String productName);

}
