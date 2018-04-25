package com.handlers;

import com.response.APIResponse;
import com.service.BrandService;
import com.service.CategoryService;
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
public class BrandHandler {

    private Logger logger = LoggerFactory.getLogger(BrandHandler.class);

    @Autowired
    private BrandService brandService;

    @GetMapping(value = GET_BRAND)
    public APIResponse getBrand(@PathVariable String categoryName, @PathVariable String subcategoryName, @PathVariable String brandName){
        return brandService.getBrand(categoryName,subcategoryName,brandName);
    }

    @GetMapping(value = GET_BRANDS)
    public APIResponse getBrands(@PathVariable String subcategoryName){
        return brandService.getBrands(subcategoryName);
    }
}
