package com.service;

import com.dto.CategoryDTO;
import com.response.APIResponse;

/**
 * @author vipul pachauri
 */
public interface CategoryService {

    APIResponse getCategoryByName(String id);

    APIResponse getAllCategories();

    APIResponse addCategory(CategoryDTO categoryDTO);

    APIResponse updateCategory(String categoryName, CategoryDTO categoryDTO);
}
