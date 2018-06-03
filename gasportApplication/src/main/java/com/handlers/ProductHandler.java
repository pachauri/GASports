package com.handlers;

import com.response.APIResponse;
import com.service.ProductDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import static com.Mappings.*;

/**
 * @author vipul pachauri
 */
@RestController
public class ProductHandler {

    private final Logger logger = LoggerFactory.getLogger(SubCategoryHandler.class);

    @Autowired
    private ProductDetailsService productDetailsService;

    @GetMapping(value = GET_PRODUCT_DETAILS)
    public APIResponse getProductDetails(@PathVariable String categoryName,@PathVariable String subcategoryName,
                                   @PathVariable String brandName,@PathVariable String productName){
        logger.info("getProductDetails call started.");
        return productDetailsService.getProductDetailsByName(categoryName,subcategoryName,brandName,productName);
    }

    @GetMapping(value = GET_PRODUCTS_DETAILS)
    public APIResponse getProductsDetails(@PathVariable String categoryName,@PathVariable String subcategoryName,
                                   @PathVariable String brandName){
        logger.info("getProductsDetails call started.");
        return productDetailsService.getAllProductsDetails(categoryName,subcategoryName,brandName);
    }
}
