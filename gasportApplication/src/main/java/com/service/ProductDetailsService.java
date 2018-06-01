package com.service;

import com.db.Category;
import com.dto.ProductDTO;
import com.response.APIResponse;

/**
 * @author vipul pachauri
 */
public interface ProductDetailsService {

    APIResponse getProductDetailsByName(String categoryName, String subcategoryName, String brandName, String productName);

    APIResponse getAllProductsDetails(String categoryName, String subcategoryName, String brandName);

    APIResponse addProductDetails(ProductDTO productDTO, Category data);

    APIResponse updateProductDetails(String oldProductName, ProductDTO productDTO, Category data);

    APIResponse deleteProduct(String categoryName, String subcategoryName, String brandName, String productName);
}
