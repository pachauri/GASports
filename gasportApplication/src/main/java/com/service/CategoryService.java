package com.service;

import com.db.Category;
import com.dto.CategoryDTO;

import java.util.List;

/**
 * @author vipul pachauri
 */
public interface CategoryService {

    List<Category> createCategories(CategoryDTO categories);
}
