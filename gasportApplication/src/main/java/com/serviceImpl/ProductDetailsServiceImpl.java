package com.serviceImpl;

import com.db.Brand;
import com.db.Category;
import com.db.ProductDetails;
import com.db.SubCategory;
import com.dto.ProductDTO;
import com.dto.ProductDetailsDTO;
import com.response.APIResponse;
import com.response.ErrorResponse;
import com.response.SuccessResponse;
import com.service.BrandService;
import com.service.ProductDetailsService;
import com.service.ProductService;
import com.service.SubCategoryService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.constants.GASportConstant.FAILURE;
import static com.constants.GASportConstant.SUCCESS;
import static enums.ErrorCodes.ERROR_PRODUCT_NOT_FOUND;
import static enums.SuccessCodes.SUCCESS_FOUND_BRAND;
import static enums.SuccessCodes.SUCCESS_FOUND_PRODUCT;

/**
 * @author vipul pachauri
 */
@Service
public class ProductDetailsServiceImpl implements ProductDetailsService {

    private final Logger logger = LoggerFactory.getLogger(ProductDetailsServiceImpl.class);

    @Autowired
    private SubCategoryService subCategoryService;

    @Autowired
    private BrandService brandService;

    @Autowired
    private ProductService productService;

    @Override
    public List<ProductDetails> createProductDetails(ProductDTO productDTO, Category category) {
        if (CollectionUtils.isEmpty(category.getSubCategories())) {
            logger.error("SubCategories don't exist for category name [{}]", productDTO.getCategoryName());
            return null;
        }
        SubCategory foundSubCategory = subCategoryService.getSubCategoryFromList(category.getSubCategories(), productDTO.getSubCategoryName());

        if (CollectionUtils.isEmpty(foundSubCategory.getBrandList())) {
            logger.error("Brands don't exist for sub category name [{}]", productDTO.getSubCategoryName());
            return null;
        }

        Brand foundBrand = brandService.getBrandsFromList(foundSubCategory.getBrandList(), productDTO.getBrandName());
        List<ProductDetails> productDetailsList = foundBrand.getProductDetails();
        List<String> productNames = new ArrayList<>();
        if (!CollectionUtils.isEmpty(productDetailsList)) {
            productNames = productDetailsList.stream().map(ProductDetails::getName).collect(Collectors.toList());
        } else {
            productDetailsList = new ArrayList<>();
        }
        for (ProductDetailsDTO productDetailsDTO : productDTO.getProductDetails()) {
            if (CollectionUtils.isEmpty(productNames) || (!CollectionUtils.isEmpty(productNames) && !productNames.contains(productDetailsDTO.getName()))) {
                ProductDetails productDetails = new ProductDetails(productDetailsDTO.getName(), productDetailsDTO.getPrice());
                productDetailsList.add(productDetails);
            }
        }
        foundBrand.setProductDetails(productDetailsList);
        return productDetailsList;
    }

    @Override
    public APIResponse getProductDetailsByName(String categoryName, String subcategoryName, String brandName, String productName) {
        APIResponse apiResponse = getAllProductsDetails(categoryName, subcategoryName, brandName);
        if (apiResponse.getErrorResponse() != null) {
            return new APIResponse(FAILURE, new ErrorResponse(ERROR_PRODUCT_NOT_FOUND.getResponseCode(), ERROR_PRODUCT_NOT_FOUND.getResponseMessage()));
        }
        //noinspection unchecked
        ProductDetails productDetails = productService.getProductDetailsFromList((List<ProductDetails>) apiResponse.getData(), productName);
        return new APIResponse(SUCCESS, new SuccessResponse(SUCCESS_FOUND_PRODUCT.getResponseCode(), SUCCESS_FOUND_PRODUCT.getResponseMessage()), productDetails);
    }

    @Override
    public APIResponse getAllProductsDetails(String categoryName, String subcategoryName, String brandName) {
        if (StringUtils.isBlank(categoryName) || StringUtils.isBlank(subcategoryName)) {
            logger.error("Error : getAllProductsDetails categoryName, subcategoryName, brandName  [{}] [{}] [{}] ", categoryName, subcategoryName, brandName);
            return new APIResponse(FAILURE, new ErrorResponse(ERROR_PRODUCT_NOT_FOUND.getResponseCode(), ERROR_PRODUCT_NOT_FOUND.getResponseMessage(), "Empty category or subcategory name."));
        }

        APIResponse apiResponse = brandService.getBrand(categoryName, subcategoryName, brandName);
        if (apiResponse.getErrorResponse() != null) {
            return apiResponse;
        }
        Brand brand = (Brand) apiResponse.getData();
        if (CollectionUtils.isEmpty(brand.getProductDetails())) {
            logger.error("Error : getProductDetailsByName, Products don't exist for subcategory and brand [{}] [{}]", subcategoryName, brandName);
            return new APIResponse(FAILURE, new ErrorResponse(ERROR_PRODUCT_NOT_FOUND.getResponseCode(), ERROR_PRODUCT_NOT_FOUND.getResponseMessage()));
        }
        return new APIResponse(SUCCESS, new SuccessResponse(SUCCESS_FOUND_BRAND.getResponseCode(), SUCCESS_FOUND_BRAND.getResponseMessage()), brand.getProductDetails());
    }


}
