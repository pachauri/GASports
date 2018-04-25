package com.handlers;

import com.response.APIResponse;
import com.service.SubCategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import static com.Mappings.*;

/**
 * @author vipul pachauri
 */
@RestController
public class SubCategoryHandler {

    private Logger logger = LoggerFactory.getLogger(SubCategoryHandler.class);

    @Autowired
    private SubCategoryService subCategoryService;

    @GetMapping(value = GET_SUB_CATEGORY)
    public APIResponse getSubCategory(@PathVariable String categoryName, @PathVariable String subcategoryName){
        return subCategoryService.getSubCategoryByName(categoryName,subcategoryName);
    }

    @GetMapping(value = GET_SUB_CATEGORIES)
    public APIResponse getSubCategories(@PathVariable String categoryName){
        return subCategoryService.getSubCategories(categoryName);
    }
}