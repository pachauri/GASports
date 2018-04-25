package com.service;

import com.db.Category;
import com.db.SubCategory;
import com.dto.SubCategoryDTO;
import com.response.APIResponse;

import java.util.List;

/**
 * @author vipul pachauri
 */
public interface SubCategoryService {

    List<SubCategory> createSubCategories(SubCategoryDTO subCategoryDTO, Category category);

    SubCategory getSubCategoryFromList(List<SubCategory> subCategories, String subCategoryName);

    APIResponse getSubCategoryByName(String categoryName, String subCategoryName);

    APIResponse getSubCategories(String categoryName);
}
