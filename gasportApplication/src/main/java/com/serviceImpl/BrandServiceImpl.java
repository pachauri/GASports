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
import static enums.ErrorCodes.ERROR_CATEGORY_NOT_FOUND;
import static enums.ErrorCodes.ERROR_SUB_CATEGORY_NOT_FOUND;
import static enums.SuccessCodes.SUCCESS_FOUND_BRAND;
import static enums.SuccessCodes.SUCCESS_FOUND_CATEGORY;

/**
 * @author vipul pachauri
 */
@Service
public class BrandServiceImpl implements BrandService {

    private Logger logger = LoggerFactory.getLogger(BrandServiceImpl.class);

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private SubCategoryService subCategoryService;

    @Override
    public List<Brand> createBrands(BrandDTO brandDTO,Category category) {

        logger.info("Getting all sub categories of category name [{}]", category.getName());
        if (CollectionUtils.isEmpty(category.getSubCategories())) {
            logger.error("Error : createBrands() sub categories not found");
           return null;
        }
        SubCategory foundedSubCategory = subCategoryService.getSubCategoryFromList(category.getSubCategories(),brandDTO.getSubCategoryName());
        List<Brand> brandList = foundedSubCategory.getBrandList();
        List<String> brandNames = new ArrayList<String>();
        if (!CollectionUtils.isEmpty(brandList)){
            brandNames = getBrandNames(brandList);
        }else {
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
        if(StringUtils.isBlank(categoryName) || StringUtils.isBlank(subcategoryName) || StringUtils.isBlank(brandName)){
            logger.error("Error : getBrand categoryName, categoryName , brandName [{}] [{}] [{}] ",categoryName,subcategoryName,brandName);
            return new APIResponse(FAILURE,new ErrorResponse(ERROR_BRAND_NOT_FOUND.getResponseCode(),ERROR_BRAND_NOT_FOUND.getResponseMessage(),"Empty brand name."));
        }
        Category category = categoryRepository.findProductCategoryByName(categoryName);
        if (category == null){
            logger.error("Error : getBrand, category doesn't exist for subcategory and brand [{}] [{}]",subcategoryName,brandName);
            return new APIResponse(FAILURE,new ErrorResponse(ERROR_CATEGORY_NOT_FOUND.getResponseCode(),ERROR_CATEGORY_NOT_FOUND.getResponseMessage()));
        }

        if(CollectionUtils.isEmpty(category.getSubCategories())){
            logger.error("Error : getBrand, Subcategories don't exist for subcategory and brand [{}] [{}]",subcategoryName,brandName);
            return new APIResponse(FAILURE,new ErrorResponse(ERROR_CATEGORY_NOT_FOUND.getResponseCode(),ERROR_CATEGORY_NOT_FOUND.getResponseMessage()));
        }

        SubCategory subCategory = subCategoryService.getSubCategoryFromList(category.getSubCategories(),subcategoryName);
        if(subCategory == null){
            return new APIResponse(FAILURE,new ErrorResponse(ERROR_SUB_CATEGORY_NOT_FOUND.getResponseCode(),ERROR_SUB_CATEGORY_NOT_FOUND.getResponseMessage(),"Subcategory doesn't exist by given name."));
        }
        if(CollectionUtils.isEmpty(subCategory.getBrandList())){
            logger.error("Error : getBrand, Brands don't exist for subcategory and brand [{}] [{}]",subcategoryName,brandName);
            return new APIResponse(FAILURE,new ErrorResponse(ERROR_BRAND_NOT_FOUND.getResponseCode(),ERROR_BRAND_NOT_FOUND.getResponseMessage()));
        }
        Brand brand = getBrandsFromList(subCategory.getBrandList(),brandName);
        return new APIResponse(SUCCESS,new SuccessResponse(SUCCESS_FOUND_CATEGORY.getResponseCode(),SUCCESS_FOUND_CATEGORY.getResponseMessage()),brand);
    }

    @Override
    public APIResponse getBrands(String categoryName, String subcategoryName) {
        if(StringUtils.isBlank(categoryName) || StringUtils.isBlank(subcategoryName)){
            logger.error("Error : getBrand categoryName, categoryName  [{}] [{}] ",categoryName,subcategoryName);
            return new APIResponse(FAILURE,new ErrorResponse(ERROR_BRAND_NOT_FOUND.getResponseCode(),ERROR_BRAND_NOT_FOUND.getResponseMessage(),"Empty category or subcategory name."));
        }
        Category category = categoryRepository.findProductCategoryByName(categoryName);
        if (category == null){
            logger.error("Error : getBrand, category doesn't exist for subcategory  [{}]",subcategoryName);
            return new APIResponse(FAILURE,new ErrorResponse(ERROR_CATEGORY_NOT_FOUND.getResponseCode(),ERROR_CATEGORY_NOT_FOUND.getResponseMessage()));
        }

        if(CollectionUtils.isEmpty(category.getSubCategories())){
            logger.error("Error : getBrand, Subcategories don't exist for subcategory [{}]",subcategoryName);
            return new APIResponse(FAILURE,new ErrorResponse(ERROR_SUB_CATEGORY_NOT_FOUND.getResponseCode(),ERROR_SUB_CATEGORY_NOT_FOUND.getResponseMessage()));
        }

        SubCategory subCategory = subCategoryService.getSubCategoryFromList(category.getSubCategories(),subcategoryName);
        if(subCategory == null){
            return new APIResponse(FAILURE,new ErrorResponse(ERROR_SUB_CATEGORY_NOT_FOUND.getResponseCode(),ERROR_SUB_CATEGORY_NOT_FOUND.getResponseMessage(),"Subcategory doesn't exist by given name."));
        }
        return new APIResponse(SUCCESS,new SuccessResponse(SUCCESS_FOUND_BRAND.getResponseCode(),SUCCESS_FOUND_BRAND.getResponseMessage()),subCategory.getBrandList());
    }

    private List<String> getBrandNames(List<Brand> brandList) {
        return brandList.stream().map(brand -> brand.getName()).collect(Collectors.toList());
    }

    public Brand getBrandsFromList(List<Brand> brandList, String brandName) {
        Brand foundBrand = null;
        Optional<Brand> brandOptional = brandList.stream().filter(brand -> brand.getName().equalsIgnoreCase(brandName)).findFirst();
        if(brandOptional.isPresent()){
            foundBrand = brandOptional.get();
            return foundBrand;
        }else {
            logger.error("Brand does not exist by name [{}]",brandName);
            return null;
        }
    }
}
