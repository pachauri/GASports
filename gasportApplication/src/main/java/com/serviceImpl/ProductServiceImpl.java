package com.serviceImpl;

import com.ExceptionHandler.GASportsException;
import com.db.Category;
import com.db.ProductDetails;
import com.dto.BrandDTO;
import com.dto.CategoryDTO;
import com.dto.ProductDTO;
import com.dto.SubCategoryDTO;
import com.repository.CategoryRepository;
import com.repository.SubCategoryRepository;
import com.response.APIResponse;
import com.service.*;
import enums.ErrorCodes;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;

/**
 * @author vipul pachauri
 */
@Service
public class ProductServiceImpl implements ProductService {

    private final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private SubCategoryRepository subCategoryRepository;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private SubCategoryService subCategoryService;

    @Autowired
    private BrandService brandService;

    @Autowired
    private ProductDetailsService productDetailsService;


    @Override
    public APIResponse addOrUpdateCategory(String oldCategoryName, CategoryDTO categoryDTO) {
        if(StringUtils.isBlank(oldCategoryName)){
            return categoryService.addCategory(categoryDTO);
        }
        return categoryService.updateCategory(oldCategoryName, categoryDTO);
    }

    @Override
    public APIResponse addOrUpdateSubCategory(String oldSubCategoryName, SubCategoryDTO subCategoryDTO) {
        APIResponse apiResponse = categoryService.getCategoryByName(subCategoryDTO.getCategoryName());
        if(apiResponse.getErrorResponse() != null){
            return apiResponse;
        }
        if(StringUtils.isBlank(oldSubCategoryName)){
            return subCategoryService.addSubCategories(subCategoryDTO,(Category) apiResponse.getData());
        }
        return subCategoryService.updateSubCategory(oldSubCategoryName,subCategoryDTO,(Category) apiResponse.getData());
    }

    @Override
    public APIResponse addOrUpdateBrand(String oldBrandName, BrandDTO brandDTO) {

        APIResponse apiResponse = categoryService.getCategoryByName(brandDTO.getCategoryName());
        if(apiResponse.getErrorResponse() != null){
            return apiResponse;
        }

        if(StringUtils.isBlank(oldBrandName)){
            return brandService.createBrands(brandDTO, (Category) apiResponse.getData());
        }
        return brandService.updateBrands(oldBrandName,brandDTO, (Category) apiResponse.getData());


    }

    @Override
    public APIResponse addOrUpdateProductDetails(String oldProductName, ProductDTO productDTO) {

        APIResponse apiResponse = categoryService.getCategoryByName(productDTO.getCategoryName());
        if(apiResponse.getErrorResponse() != null){
            return apiResponse;
        }

        if(StringUtils.isBlank(oldProductName)){
            logger.info("Adding Product details.");
            return productDetailsService.addProductDetails(productDTO, (Category) apiResponse.getData());
        }
        logger.info("Updating product details.");
        return productDetailsService.updateProductDetails(oldProductName,productDTO, (Category) apiResponse.getData());
    }


    public ProductDetails getProductDetailsFromList(List<ProductDetails> productDetails, String productName) {
        if(CollectionUtils.isEmpty(productDetails)){
            logger.error("Product details don't exist for [{}]", productName);
            throw new GASportsException(ErrorCodes.ERROR_PRODUCT_NOT_FOUND.getResponseMessage());
        }
        ProductDetails foundProduct;
        Optional<ProductDetails> productDetailsOptional = productDetails.stream().filter(product -> product.getName().equalsIgnoreCase(productName)).findFirst();
        if (productDetailsOptional.isPresent()) {
            logger.info("Product found by name [{}]", productName);
            foundProduct = productDetailsOptional.get();
            return foundProduct;
        } else {
            logger.error("Product doesn't exist by name [{}]", productName);
            throw new GASportsException(ErrorCodes.ERROR_PRODUCT_NOT_FOUND.getResponseMessage());
        }
    }

}
