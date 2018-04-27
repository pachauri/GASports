package com.serviceImpl;

import com.constants.GASportConstant;
import com.db.Brand;
import com.db.Category;
import com.db.ProductDetails;
import com.db.SubCategory;
import com.dto.BrandDTO;
import com.dto.CategoryDTO;
import com.dto.ProductDTO;
import com.dto.SubCategoryDTO;
import com.repository.CategoryRepository;
import com.repository.SubCategoryRepository;
import com.response.APIResponse;
import com.response.ErrorResponse;
import com.response.SuccessResponse;
import com.service.*;
import enums.ErrorCodes;
import enums.SuccessCodes;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
    public APIResponse addCategory(CategoryDTO categoryDTO) {
        if (categoryDTO == null || CollectionUtils.isEmpty(categoryDTO.getCategoryNames())) {
            logger.error("Error in adding category for [{}]", categoryDTO);
            return new APIResponse(GASportConstant.FAILURE, new ErrorResponse(ErrorCodes.ERROR_ADDING_CATEGORY.getResponseCode(), ErrorCodes.ERROR_ADDING_CATEGORY.getResponseMessage(), "Invalid category details."));
        }
        List<Category> categories = categoryService.createCategories(categoryDTO);
        if (categories == null) {
            return new APIResponse(GASportConstant.FAILURE, new ErrorResponse(ErrorCodes.ERROR_ADDING_CATEGORY.getResponseCode(), ErrorCodes.ERROR_ADDING_CATEGORY.getResponseMessage()));
        }
        logger.info("Saving categories [{}]", categories);
        categoryRepository.save(categories);
        return new APIResponse(GASportConstant.SUCCESS, new SuccessResponse(SuccessCodes.SUCCESS_ADDED_CATEGORY.getResponseCode(), SuccessCodes.SUCCESS_ADDED_CATEGORY.getResponseMessage()));
    }

    @Override
    public APIResponse addSubCategory(SubCategoryDTO subCategoryDTO) {
        if (subCategoryDTO == null || StringUtils.isBlank(subCategoryDTO.getCategoryName()) || CollectionUtils.isEmpty(subCategoryDTO.getSubcategoryNames())) {
            logger.error("Error in sub category for [{}]", subCategoryDTO);
            return new APIResponse(GASportConstant.FAILURE, new ErrorResponse(ErrorCodes.ERROR_ADDING_SUB_CATEGORY.getResponseCode(), ErrorCodes.ERROR_ADDING_SUB_CATEGORY.getResponseMessage(), "Invalid sub category details."));
        }

        //Find category by category id
        logger.info("Finding category by category name [{}]", subCategoryDTO.getCategoryName());
        Category category = categoryRepository.findProductCategoryByName(subCategoryDTO.getCategoryName());
        if (category == null) {
            logger.error("Error: Category not found by category name [{}]", subCategoryDTO.getCategoryName());
            return new APIResponse(GASportConstant.FAILURE, new ErrorResponse(ErrorCodes.ERROR_CATEGORY_NOT_FOUND.getResponseCode(), ErrorCodes.ERROR_CATEGORY_NOT_FOUND.getResponseMessage()));
        }
        logger.info("Category found by given category name [{}]", category.getName());

        List<SubCategory> subCategories = subCategoryService.createSubCategories(subCategoryDTO, category);
        if (subCategories == null) {
            return new APIResponse(GASportConstant.FAILURE, new ErrorResponse(ErrorCodes.ERROR_ADDING_SUB_CATEGORY.getResponseCode(), ErrorCodes.ERROR_ADDING_SUB_CATEGORY.getResponseMessage()));
        }
        category.setSubCategories(subCategories);
        categoryRepository.save(category);
        return new APIResponse(GASportConstant.SUCCESS, new SuccessResponse(SuccessCodes.SUCCESS_ADDED_SUB_CATEGORY.getResponseCode(), SuccessCodes.SUCCESS_ADDED_CATEGORY.getResponseMessage()));
    }

    @Override
    public APIResponse addBrand(BrandDTO brandDTO) {

        if (brandDTO == null || StringUtils.isBlank(brandDTO.getCategoryName())
                || StringUtils.isBlank(brandDTO.getSubCategoryName()) || CollectionUtils.isEmpty(brandDTO.getBrandNames())) {
            logger.error("Error in adding brand for [{}]", brandDTO);
            return new APIResponse(GASportConstant.FAILURE, new ErrorResponse(ErrorCodes.ERROR_ADDING_BRAND.getResponseCode(), ErrorCodes.ERROR_ADDING_BRAND.getResponseMessage(), "Invalid brand details."));
        }

        //Find category by category name
        logger.info("Finding category by category name [{}]", brandDTO.getCategoryName());
        Category category = categoryRepository.findProductCategoryByName(brandDTO.getCategoryName());
        if (category == null) {
            logger.error("Error: Category not found by category name [{}]", brandDTO.getCategoryName());
            return new APIResponse(GASportConstant.FAILURE, new ErrorResponse(ErrorCodes.ERROR_CATEGORY_NOT_FOUND.getResponseCode(), ErrorCodes.ERROR_CATEGORY_NOT_FOUND.getResponseMessage()));
        }
        logger.info("Category found by given category name [{}]", category.getName());

        List<Brand> brandList = brandService.createBrands(brandDTO, category);
        if (brandList == null) {
            return new APIResponse(GASportConstant.FAILURE, new ErrorResponse(ErrorCodes.ERROR_ADDING_BRAND.getResponseCode(), ErrorCodes.ERROR_ADDING_BRAND.getResponseMessage()));
        }
        categoryRepository.save(category);
        return new APIResponse(GASportConstant.SUCCESS, new SuccessResponse(SuccessCodes.SUCCESS_ADDED_BRAND.getResponseCode(), SuccessCodes.SUCCESS_ADDED_CATEGORY.getResponseMessage()));
    }

    @Override
    public APIResponse addProductDetails(ProductDTO productDTO) {
        if (productDTO == null || StringUtils.isBlank(productDTO.getCategoryName())
                || StringUtils.isBlank(productDTO.getSubCategoryName()) || StringUtils.isBlank(productDTO.getBrandName()) || CollectionUtils.isEmpty(productDTO.getProductDetails())) {
            return new APIResponse(GASportConstant.FAILURE, new ErrorResponse(ErrorCodes.ERROR_ADDING_PRODUCT_DETAILS.getResponseCode(), ErrorCodes.ERROR_ADDING_PRODUCT_DETAILS.getResponseMessage(), "Invalid product details."));
        }

        logger.info("Finding category by category name [{}]", productDTO.getCategoryName());
        Category category = categoryRepository.findProductCategoryByName(productDTO.getCategoryName());
        if (category == null) {
            logger.error("Error: Category not found by category name [{}]", productDTO.getCategoryName());
            return new APIResponse(GASportConstant.FAILURE, new ErrorResponse(ErrorCodes.ERROR_CATEGORY_NOT_FOUND.getResponseCode(), ErrorCodes.ERROR_CATEGORY_NOT_FOUND.getResponseMessage()));
        }

        List<ProductDetails> productDetails = productDetailsService.createProductDetails(productDTO, category);
        if (productDetails == null) {
            return new APIResponse(GASportConstant.FAILURE, new ErrorResponse(ErrorCodes.ERROR_ADDING_SUB_CATEGORY.getResponseCode(), ErrorCodes.ERROR_ADDING_SUB_CATEGORY.getResponseMessage()));
        }
        categoryRepository.save(category);
        return new APIResponse(GASportConstant.SUCCESS, new SuccessResponse(SuccessCodes.SUCCESS_ADDED_PRODUCT_DETAILS.getResponseCode(), SuccessCodes.SUCCESS_ADDED_CATEGORY.getResponseMessage()));
    }


    public ProductDetails getProductDetailsFromList(List<ProductDetails> productDetails, String productName) {
        ProductDetails foundProduct;
        Optional<ProductDetails> productDetailsOptional = productDetails.stream().filter(product -> product.getName().equalsIgnoreCase(productName)).findFirst();
        if (productDetailsOptional.isPresent()) {
            logger.info("Product found by name [{}]", productName);
            foundProduct = productDetailsOptional.get();
            return foundProduct;
        } else {
            logger.error("Product doesn't exist by name [{}]", productName);
            return null;
        }
    }
}
