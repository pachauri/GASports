package com.service;

import com.db.Category;
import com.db.ProductDetails;
import com.dto.ProductDTO;

import java.util.List;

/**
 * @author vipul pachauri
 */
public interface ProductDetailsService {

    List<ProductDetails> createProductDetails(ProductDTO productDTO, Category category);
}
