package com.handlers;

import com.Mappings;
import com.response.APIResponse;
import com.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import static com.Mappings.GET_CATEGORIES;
import static com.Mappings.GET_CATEGORY;

/**
 * @author vipul pachauri
 */
@RestController
public class CategoryHandler {

    private Logger logger = LoggerFactory.getLogger(CategoryHandler.class);

    @Autowired
    private CategoryService categoryService;

    @GetMapping(value = GET_CATEGORY)
    public APIResponse getCategory(@PathVariable String name){
        return categoryService.getCategoryByName(name);
    }

    @GetMapping(value = GET_CATEGORIES)
    public APIResponse getAllCategories(){
        return categoryService.getAllCategories();
    }
}