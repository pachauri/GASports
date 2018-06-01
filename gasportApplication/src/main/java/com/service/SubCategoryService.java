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

    SubCategory getSubCategoryFromList(List<SubCategory> subCategories, String subCategoryName);

    APIResponse updateSubCategory(String oldSubCategoryName, SubCategoryDTO subCategoryDTO, Category category);

    APIResponse addSubCategories(SubCategoryDTO subCategoryDTO, Category category);

    APIResponse getSubCategoryByName(String categoryName, String subCategoryName);

    APIResponse getSubCategories(String categoryName);

    APIResponse deleteSubCategory(String categoryName, String subcategoryName);
}
