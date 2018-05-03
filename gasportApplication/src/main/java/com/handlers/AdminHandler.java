package com.handlers;

import com.Mappings;
import com.constants.GASportConstant;
import com.dto.*;
import com.response.APIResponse;
import com.response.ErrorResponse;
import com.service.ProductService;
import enums.ErrorCodes;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import static com.Mappings.ADD_CATEGORY;

/**
 * @author vipul pachauri
 */
@RestController
public class AdminHandler {

    private final Logger logger = LoggerFactory.getLogger(AdminHandler.class);

    @Autowired
    private ProductService productService;

    @PostMapping(ADD_CATEGORY)
    public APIResponse addCategory(@RequestBody CategoryDTO categoryDTO){
        logger.info("Adding Product category [{}]",categoryDTO);
        if (categoryDTO == null || CollectionUtils.isEmpty(categoryDTO.getCategoryNames())) {
            logger.error("Error in adding category for [{}]", categoryDTO);
            return new APIResponse(GASportConstant.FAILURE, new ErrorResponse(ErrorCodes.ERROR_ADDING_CATEGORY.getResponseCode(), ErrorCodes.ERROR_ADDING_CATEGORY.getResponseMessage(), "Invalid category details."));
        }
        return productService.addOrUpdateCategory(null,categoryDTO);
    }

    @PostMapping(Mappings.ADD_SUB_CATEGORY)
    public APIResponse addSubCategory(@RequestBody SubCategoryDTO subCategoryDTO){
        logger.info("Adding Product Subcategory [{}]",subCategoryDTO);
        if (subCategoryDTO == null || StringUtils.isBlank(subCategoryDTO.getCategoryName()) || CollectionUtils.isEmpty(subCategoryDTO.getSubcategoryNames())) {
            logger.error("Error in adding sub category for [{}]", subCategoryDTO);
            return new APIResponse(GASportConstant.FAILURE, new ErrorResponse(ErrorCodes.ERROR_ADDING_SUB_CATEGORY.getResponseCode(), ErrorCodes.ERROR_ADDING_SUB_CATEGORY.getResponseMessage(), "Invalid sub category details."));
        }
        return productService.addOrUpdateSubCategory(null,subCategoryDTO);
    }

    @PostMapping(Mappings.ADD_BRAND)
    public APIResponse addBrand(@RequestBody BrandDTO brandDTO){
        logger.info("Adding Product brand [{}]",brandDTO);
        if (brandDTO == null || StringUtils.isBlank(brandDTO.getCategoryName())
                || StringUtils.isBlank(brandDTO.getSubCategoryName()) || CollectionUtils.isEmpty(brandDTO.getBrandNames())) {
            logger.error("Error in adding brand for [{}]", brandDTO);
            return new APIResponse(GASportConstant.FAILURE, new ErrorResponse(ErrorCodes.ERROR_ADDING_BRAND.getResponseCode(), ErrorCodes.ERROR_ADDING_BRAND.getResponseMessage(), "Invalid brand details."));
        }
        return productService.addOrUpdateBrand(null,brandDTO);
    }

    @PostMapping(Mappings.ADD_PRODUCT_DETAILS)
    public APIResponse addProductDetails(@RequestBody ProductDTO productDTO){
        logger.info("Adding Product Details [{}]",productDTO);
        if (productDTO == null || StringUtils.isBlank(productDTO.getCategoryName())
                || StringUtils.isBlank(productDTO.getSubCategoryName()) || StringUtils.isBlank(productDTO.getBrandName()) || CollectionUtils.isEmpty(productDTO.getProductDetails())) {
            return new APIResponse(GASportConstant.FAILURE, new ErrorResponse(ErrorCodes.ERROR_ADDING_PRODUCT_DETAILS.getResponseCode(), ErrorCodes.ERROR_ADDING_PRODUCT_DETAILS.getResponseMessage(), "Invalid product details."));
        }
        return productService.addOrUpdateProductDetails(null,productDTO);
    }

    @PutMapping(Mappings.UPDATE_CATEGORY)
    public APIResponse updateCategory(@PathVariable String oldCategoryName, @RequestBody CategoryDTO categoryDTO){
        logger.info("Updating Product category [{}]",categoryDTO);
        if (StringUtils.isBlank(oldCategoryName) || categoryDTO == null || CollectionUtils.isEmpty(categoryDTO.getCategoryNames())) {
            logger.error("Error in updating category for [{}]", categoryDTO);
            return new APIResponse(GASportConstant.FAILURE, new ErrorResponse(ErrorCodes.ERROR_UPDATING_CATEGORY.getResponseCode(), ErrorCodes.ERROR_UPDATING_CATEGORY.getResponseMessage(), "Invalid category details."));
        }
        return productService.addOrUpdateCategory(oldCategoryName,categoryDTO);
    }

    @PutMapping(Mappings.UPDATE_SUB_CATEGORY)
    public APIResponse updateSubCategory(@PathVariable String oldSubCategoryName, @RequestBody SubCategoryDTO subCategoryDTO){
        logger.info("Updating Product Subcategory [{}]",subCategoryDTO);
        if (StringUtils.isBlank(oldSubCategoryName) || subCategoryDTO == null || CollectionUtils.isEmpty(subCategoryDTO.getSubcategoryNames())) {
            logger.error("Error in updating category for [{}]", subCategoryDTO);
            return new APIResponse(GASportConstant.FAILURE, new ErrorResponse(ErrorCodes.ERROR_UPDATING_CATEGORY.getResponseCode(), ErrorCodes.ERROR_UPDATING_CATEGORY.getResponseMessage(), "Invalid sub category details."));
        }
        return productService.addOrUpdateSubCategory(oldSubCategoryName,subCategoryDTO);
    }

    @PutMapping(Mappings.UPDATE_BRAND)
    public APIResponse updateBrand(@PathVariable String oldBrandName, @RequestBody BrandDTO brandDTO){
        logger.info("Updating Product brand [{}]",brandDTO);
        if (StringUtils.isBlank(oldBrandName) || brandDTO == null || CollectionUtils.isEmpty(brandDTO.getBrandNames())) {
            logger.error("Error in updating brand for [{}]", brandDTO);
            return new APIResponse(GASportConstant.FAILURE, new ErrorResponse(ErrorCodes.ERROR_UPDATING_BRAND.getResponseCode(), ErrorCodes.ERROR_UPDATING_BRAND.getResponseMessage(), "Invalid brand details."));

        }
        return productService.addOrUpdateBrand(oldBrandName,brandDTO);
    }

    @PutMapping(Mappings.UPDATE_PRODUCT_DETAILS)
    public APIResponse updateProductDetails(@PathVariable String oldProductName, @RequestBody ProductDTO productDTO){
        logger.info("Updating Product Details [{}]",productDTO);
        if (StringUtils.isBlank(oldProductName) || productDTO == null || CollectionUtils.isEmpty(productDTO.getProductDetails())) {
            logger.error("Error in updating product details for [{}]", productDTO);
            return new APIResponse(GASportConstant.FAILURE, new ErrorResponse(ErrorCodes.ERROR_UPDATING_PRODUCT_DETAILS.getResponseCode(), ErrorCodes.ERROR_UPDATING_PRODUCT_DETAILS.getResponseMessage(), "Invalid product details."));

        }
        return productService.addOrUpdateProductDetails(oldProductName,productDTO);
    }


}
