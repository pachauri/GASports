package com.service;

import com.db.Category;
import com.db.ProductDetails;
import com.dto.ProductDTO;
import com.response.APIResponse;

import java.util.List;

/**
 * @author vipul pachauri
 */
public interface ProductDetailsService {

    List<ProductDetails> createProductDetails(ProductDTO productDTO, Category category);

    APIResponse getProductDetailsByName(String categoryName, String subcategoryName, String brandName, String productName);

    APIResponse getAllProductsDetails(String categoryName, String subcategoryName, String brandName);
}
