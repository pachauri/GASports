package com.serviceImpl;

import com.ExceptionHandler.GASportsException;
import com.constants.GASportConstant;
import com.db.Category;
import com.db.SubCategory;
import com.dto.SubCategoryDTO;
import com.repository.CategoryRepository;
import com.response.APIResponse;
import com.response.ErrorResponse;
import com.response.SuccessResponse;
import com.service.CategoryService;
import com.service.SubCategoryService;
import enums.ErrorCodes;
import enums.SuccessCodes;
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
import static enums.ErrorCodes.ERROR_SUB_CATEGORY_NOT_FOUND;
import static enums.SuccessCodes.SUCCESS_FOUND_CATEGORY;
import static enums.SuccessCodes.SUCCESS_FOUND_SUB_CATEGORY;

/**
 * @author vipul pachauri
 */
@Service
public class SubCategoryServiceImpl implements SubCategoryService {

    private final Logger logger = LoggerFactory.getLogger(SubCategoryServiceImpl.class);

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryService categoryService;

    @Override
    public APIResponse updateSubCategory(String oldSubCategoryName, SubCategoryDTO subCategoryDTO, Category category) {
        SubCategory subCategory = getSubCategoryFromList(category.getSubCategories(),oldSubCategoryName);
        subCategory.setName(subCategoryDTO.getSubcategoryNames().get(0));
        categoryRepository.save(category);
        return new APIResponse(GASportConstant.SUCCESS, new SuccessResponse(SuccessCodes.SUCCESS_UPDATED_SUB_CATEGORY.getResponseCode(), SuccessCodes.SUCCESS_UPDATED_SUB_CATEGORY.getResponseMessage()));

    }

    @Override
    public APIResponse addSubCategories(SubCategoryDTO subCategoryDTO, Category category) {

        try {
            //Get all sub categories of founded category
            logger.info("Getting all sub categories of category name [{}]", category.getName());
            List<SubCategory> subCategoryList = category.getSubCategories();
            List<String> subCatNames = new ArrayList<>();
            if (!CollectionUtils.isEmpty(subCategoryList)) {
                subCatNames = getSubCategoryNames(subCategoryList);
            }
            subCategoryList = new ArrayList<>();
            for (String name : subCategoryDTO.getSubcategoryNames()) {
                if (CollectionUtils.isEmpty(subCatNames) || (!CollectionUtils.isEmpty(subCatNames) && !subCatNames.contains(name))) {
                    SubCategory subCategory = new SubCategory(name);
                    subCategoryList.add(subCategory);
                }
            }
            if (CollectionUtils.isEmpty(subCategoryList)) {
                return new APIResponse(GASportConstant.FAILURE, new ErrorResponse(ErrorCodes.ERROR_ADDING_SUB_CATEGORY.getResponseCode(), ErrorCodes.ERROR_ADDING_SUB_CATEGORY.getResponseMessage()));
            }
            category.setSubCategories(subCategoryList);
            categoryRepository.save(category);
            return new APIResponse(GASportConstant.SUCCESS, new SuccessResponse(SuccessCodes.SUCCESS_ADDED_SUB_CATEGORY.getResponseCode(), SuccessCodes.SUCCESS_ADDED_CATEGORY.getResponseMessage()));
        } catch (Exception e) {
            logger.error("Error : createOrUpdateSubCategories [{}]", e.getMessage());
        }
        return new APIResponse(GASportConstant.FAILURE, new ErrorResponse(ErrorCodes.ERROR_ADDING_SUB_CATEGORY.getResponseCode(), ErrorCodes.ERROR_ADDING_SUB_CATEGORY.getResponseMessage()));
    }


    @Override
    public APIResponse getSubCategoryByName(String categoryName, String subCategoryName) {
        APIResponse apiResponse = getSubCategories(categoryName);
        if (apiResponse.getErrorResponse() != null) {
            return new APIResponse(FAILURE, new ErrorResponse(ERROR_SUB_CATEGORY_NOT_FOUND.getResponseCode(), ERROR_SUB_CATEGORY_NOT_FOUND.getResponseMessage(), "Subcategory doesn't exist by given name."));
        }

        //noinspection unchecked
        SubCategory subCategory = getSubCategoryFromList((List<SubCategory>) apiResponse.getData(), subCategoryName);
        if (subCategory == null) {
            return new APIResponse(FAILURE, new ErrorResponse(ERROR_SUB_CATEGORY_NOT_FOUND.getResponseCode(), ERROR_SUB_CATEGORY_NOT_FOUND.getResponseMessage(), "Subcategory doesn't exist by given name."));
        }
        logger.info("Subcategory found successfully. [{}]",subCategory.getName());
        return new APIResponse(SUCCESS, new SuccessResponse(SUCCESS_FOUND_CATEGORY.getResponseCode(), SUCCESS_FOUND_CATEGORY.getResponseMessage()), subCategory);
    }

    @Override
    public APIResponse getSubCategories(String categoryName) {
        APIResponse apiResponse = categoryService.getCategoryByName(categoryName);
        if (apiResponse.getErrorResponse() != null) {
            return apiResponse;
        }
        Category category = (Category) apiResponse.getData();
        List<SubCategory> subCategories = category.getSubCategories();
        if (CollectionUtils.isEmpty(subCategories)) {
            logger.error("Error : getSubCategories , Categories don't exist for category. [{}] ", category.getName());
            return new APIResponse(FAILURE, new ErrorResponse(ERROR_SUB_CATEGORY_NOT_FOUND.getResponseCode(), ERROR_SUB_CATEGORY_NOT_FOUND.getResponseMessage()));
        }

        return new APIResponse(SUCCESS, new SuccessResponse(SUCCESS_FOUND_SUB_CATEGORY.getResponseCode(), SUCCESS_FOUND_SUB_CATEGORY.getResponseMessage()), subCategories);
    }

    private List<String> getSubCategoryNames(List<SubCategory> subCategoryList) {
        return subCategoryList.stream().map(SubCategory::getName).collect(Collectors.toList());
    }

    public SubCategory getSubCategoryFromList(List<SubCategory> subCategories, String subCategoryName) {
        if(CollectionUtils.isEmpty(subCategories)){
            logger.error("SubCategories does not exist for [{}]", subCategoryName);
            throw new GASportsException(ErrorCodes.ERROR_SUB_CATEGORY_NOT_FOUND.getResponseMessage());
        }
        Optional<SubCategory> subCategoryOptional = subCategories.stream().filter(subCategory -> subCategory.getName().equalsIgnoreCase(subCategoryName)).findFirst();
        SubCategory foundSubCategory;
        if (subCategoryOptional.isPresent()) {
            foundSubCategory = subCategoryOptional.get();
            return foundSubCategory;
        } else {
            logger.error("SubCategory does not exist by name [{}]", subCategoryName);
            throw new GASportsException(ErrorCodes.ERROR_SUB_CATEGORY_NOT_FOUND.getResponseMessage());
        }
    }
}
