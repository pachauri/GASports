package com.handlers;

import com.response.APIResponse;
import com.service.BrandService;
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
public class BrandHandler {

    private final Logger logger = LoggerFactory.getLogger(BrandHandler.class);

    @Autowired
    private BrandService brandService;

    @GetMapping(value = GET_BRAND)
    public APIResponse getBrand(@PathVariable String categoryName, @PathVariable String subcategoryName, @PathVariable String brandName){
        logger.info("getBrand call started.");
        return brandService.getBrand(categoryName,subcategoryName,brandName);
    }

    @GetMapping(value = GET_BRANDS)
    public APIResponse getBrands(@PathVariable String categoryName, @PathVariable String subcategoryName){
        logger.info("getBrands call started.");
        return brandService.getBrands(categoryName,subcategoryName);
    }

    @DeleteMapping(value = DELETE_BRAND)
    public APIResponse delBrand(@PathVariable String categoryName, @PathVariable String subcategoryName, @PathVariable String brandName){
        logger.info("delBrand call started.");
        return brandService.deleteBrand(categoryName,subcategoryName,brandName);
    }
}
