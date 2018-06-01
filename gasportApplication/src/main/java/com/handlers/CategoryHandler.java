package com.handlers;

import com.response.APIResponse;
import com.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import static com.Mappings.DELETE_CATEGORY;
import static com.Mappings.GET_CATEGORIES;
import static com.Mappings.GET_CATEGORY;

/**
 * @author vipul pachauri
 */
@RestController
public class CategoryHandler {

    private final Logger logger = LoggerFactory.getLogger(CategoryHandler.class);

    @Autowired
    private CategoryService categoryService;

    @GetMapping(value = GET_CATEGORY)
    public APIResponse getCategory(@PathVariable String categoryName){
        logger.info("getCategory call started.");
        return categoryService.getCategoryByName(categoryName);
    }

    @GetMapping(value = GET_CATEGORIES)
    public APIResponse getAllCategories(){
        logger.info("getAllCategories call started.");
        return categoryService.getAllCategories();
    }

    @DeleteMapping(value = DELETE_CATEGORY)
    public APIResponse delCategory(@PathVariable String categoryName){
        logger.info("delCategory call started.");
        return categoryService.deleteCategory(categoryName);
    }
}
