package com.serviceImpl;

import com.db.Brand;
import com.db.Category;
import com.db.SubCategory;
import com.dto.BrandDTO;
import com.repository.CategoryRepository;
import com.response.APIResponse;
import com.response.ErrorResponse;
import com.response.SuccessResponse;
import com.service.BrandService;
import com.service.SubCategoryService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.constants.GASportConstant.FAILURE;
import static com.constants.GASportConstant.SUCCESS;
import static enums.ErrorCodes.ERROR_BRAND_NOT_FOUND;
import static enums.ErrorCodes.ERROR_SUB_CATEGORY_NOT_FOUND;
import static enums.SuccessCodes.SUCCESS_FOUND_BRAND;
import static enums.SuccessCodes.SUCCESS_FOUND_CATEGORY;

/**
 * @author vipul pachauri
 */
@Service
class BrandServiceImpl implements BrandService {

    private final Logger logger = LoggerFactory.getLogger(BrandServiceImpl.class);

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private SubCategoryService subCategoryService;

    @Override
    public List<Brand> createBrands(BrandDTO brandDTO, Category category) {

        logger.info("Getting all sub categories of category name [{}]", category.getName());
        if (CollectionUtils.isEmpty(category.getSubCategories())) {
            logger.error("Error : createBrands() sub categories not found");
            return null;
        }
        SubCategory foundedSubCategory = subCategoryService.getSubCategoryFromList(category.getSubCategories(), brandDTO.getSubCategoryName());
        List<Brand> brandList = foundedSubCategory.getBrandList();
        List<String> brandNames = new ArrayList<>();
        if (!CollectionUtils.isEmpty(brandList)) {
            brandNames = getBrandNames(brandList);
        } else {
            brandList = new ArrayList<>();
        }

        for (String name : brandDTO.getBrandNames()) {
            if (CollectionUtils.isEmpty(brandNames) || (!CollectionUtils.isEmpty(brandNames) && !brandNames.contains(name))) {
                Brand brand = new Brand(name);
                brandList.add(brand);
            }
        }
        foundedSubCategory.setBrandList(brandList);
        return brandList;
    }


    @Override
    public APIResponse getBrand(String categoryName, String subcategoryName, String brandName) {
        APIResponse apiResponse = getBrands(categoryName, subcategoryName);
        if (apiResponse.getErrorResponse() != null) {
            return apiResponse;
        }
        //noinspection unchecked
        Brand brand = getBrandsFromList((List<Brand>) apiResponse.getData(), brandName);
        return new APIResponse(SUCCESS, new SuccessResponse(SUCCESS_FOUND_BRAND.getResponseCode(), SUCCESS_FOUND_BRAND.getResponseMessage()), brand);
    }

    @Override
    public APIResponse getBrands(String categoryName, String subcategoryName) {
        if (StringUtils.isBlank(categoryName) || StringUtils.isBlank(subcategoryName)) {
            logger.error("Error : getBrands categoryName, categoryName  [{}] [{}] ", categoryName, subcategoryName);
            return new APIResponse(FAILURE, new ErrorResponse(ERROR_BRAND_NOT_FOUND.getResponseCode(), ERROR_BRAND_NOT_FOUND.getResponseMessage(), "Empty category or subcategory name."));
        }

        APIResponse apiResponse = subCategoryService.getSubCategories(categoryName);
        if (apiResponse.getErrorResponse() != null) {
            return apiResponse;
        }

        //noinspection unchecked
        SubCategory subCategory = subCategoryService.getSubCategoryFromList((List<SubCategory>) apiResponse.getData(), subcategoryName);
        if (subCategory == null) {
            return new APIResponse(FAILURE, new ErrorResponse(ERROR_SUB_CATEGORY_NOT_FOUND.getResponseCode(), ERROR_SUB_CATEGORY_NOT_FOUND.getResponseMessage(), "Subcategory doesn't exist by given name."));
        }
        return new APIResponse(SUCCESS, new SuccessResponse(SUCCESS_FOUND_BRAND.getResponseCode(), SUCCESS_FOUND_BRAND.getResponseMessage()), subCategory.getBrandList());
    }

    private List<String> getBrandNames(List<Brand> brandList) {
        return brandList.stream().map(Brand::getName).collect(Collectors.toList());
    }

    public Brand getBrandsFromList(List<Brand> brandList, String brandName) {
        Brand foundBrand;
        Optional<Brand> brandOptional = brandList.stream().filter(brand -> brand.getName().equalsIgnoreCase(brandName)).findFirst();
        if (brandOptional.isPresent()) {
            foundBrand = brandOptional.get();
            return foundBrand;
        } else {
            logger.error("Brand does not exist by name [{}]", brandName);
            return null;
        }
    }
}
