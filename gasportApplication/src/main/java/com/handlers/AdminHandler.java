package com.handlers;

import com.Mappings;
import com.db.Brand;
import com.dto.*;
import com.response.APIResponse;
import com.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.Mappings.ADD_CATEGORY;

/**
 * @author vipul pachauri
 */
@RestController
public class AdminHandler {

    private Logger logger = LoggerFactory.getLogger(AdminHandler.class);

    @Autowired
    private ProductService productService;

    @PostMapping(ADD_CATEGORY)
    public APIResponse addCategory(@RequestBody ProductParentDTO productParentDTO){
        logger.info("Adding ProductParent category [{}]",productParentDTO);
        return productService.addCategory(productParentDTO);
    }

    @PostMapping(Mappings.ADD_SUB_CATEGORY)
    public APIResponse addSubCategory(@RequestBody CategoryDTO categoryDTO){
        logger.info("Adding ProductParent Subcategory [{}]",categoryDTO);
        return productService.addSubCategory(categoryDTO);
    }

    @PostMapping(Mappings.ADD_BRAND)
    public APIResponse addBrand(@RequestBody SubCategoryDTO subCategoryDTO){
        logger.info("Adding ProductParent brand [{}]",subCategoryDTO);
        return productService.addBrand(subCategoryDTO);
    }

    @PostMapping(Mappings.ADD_PRODUCT_DETAILS)
    public APIResponse addProductDetails(@RequestBody BrandDTO brandDTO){
        logger.info("Adding ProductParent Details [{}]",brandDTO);
        return productService.addProductDetails(brandDTO);
    }


}
