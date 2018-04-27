package com.serviceImpl;

import com.ExceptionHandler.GASportsException;
import com.db.Category;
import com.db.SubCategory;
import com.dto.SubCategoryDTO;
import com.repository.CategoryRepository;
import com.response.APIResponse;
import com.response.ErrorResponse;
import com.response.SuccessResponse;
import com.service.CategoryService;
import com.service.SubCategoryService;
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
    public List<SubCategory> createSubCategories(SubCategoryDTO subCategoryDTO, Category category) {
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
            return subCategoryList;
        } catch (Exception e) {
            logger.error("Error : createSubCategories [{}]", e.getMessage());
            throw new GASportsException(e.getMessage());
        }
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
        Optional<SubCategory> subCategoryOptional = subCategories.stream().filter(subCategory -> subCategory.getName().equalsIgnoreCase(subCategoryName)).findFirst();
        SubCategory foundSubCategory;
        if (subCategoryOptional.isPresent()) {
            foundSubCategory = subCategoryOptional.get();
            return foundSubCategory;
        } else {
            logger.error("SubCategory does not exist by name [{}]", subCategoryName);
            return null;
        }
    }
}
