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
    public APIResponse addCategory(@RequestBody CategoryDTO categoryDTO){
        logger.info("Adding Product category [{}]",categoryDTO);
        return productService.addCategory(categoryDTO);
    }

    @PostMapping(Mappings.ADD_SUB_CATEGORY)
    public APIResponse addSubCategory(@RequestBody SubCategoryDTO subCategoryDTO){
        logger.info("Adding Product Subcategory [{}]",subCategoryDTO);
        return productService.addSubCategory(subCategoryDTO);
    }

    @PostMapping(Mappings.ADD_BRAND)
    public APIResponse addBrand(@RequestBody BrandDTO brandDTO){
        logger.info("Adding Product brand [{}]",brandDTO);
        return productService.addBrand(brandDTO);
    }

    @PostMapping(Mappings.ADD_PRODUCT_DETAILS)
    public APIResponse addProductDetails(@RequestBody ProductDTO productDTO){
        logger.info("Adding Product Details [{}]",productDTO);
        return productService.addProductDetails(productDTO);
    }


}
