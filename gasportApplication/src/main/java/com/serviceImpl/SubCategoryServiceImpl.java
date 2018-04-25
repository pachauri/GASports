package com.serviceImpl;

import com.ExceptionHandler.GASportsException;
import com.db.Category;
import com.db.SubCategory;
import com.dto.SubCategoryDTO;
import com.repository.CategoryRepository;
import com.response.APIResponse;
import com.response.ErrorResponse;
import com.response.SuccessResponse;
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
import static enums.ErrorCodes.ERROR_CATEGORY_NOT_FOUND;
import static enums.ErrorCodes.ERROR_SUB_CATEGORY_NOT_FOUND;
import static enums.SuccessCodes.SUCCESS_FOUND_CATEGORY;
import static enums.SuccessCodes.SUCCESS_FOUND_SUB_CATEGORY;

/**
 * @author vipul pachauri
 */
@Service
public class SubCategoryServiceImpl implements SubCategoryService {

    private Logger logger = LoggerFactory.getLogger(SubCategoryServiceImpl.class);

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<SubCategory> createSubCategories(SubCategoryDTO subCategoryDTO,Category category) {
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
            logger.error("Error : createSubCategories [{}]",e.getMessage());
            throw new GASportsException(e.getMessage());
        }
    }


    @Override
    public APIResponse getSubCategoryByName(String categoryName, String subCategoryName) {
        if(StringUtils.isBlank(categoryName) || StringUtils.isBlank(subCategoryName)){
            logger.error("Error : getSubCategoryByName [{}]",subCategoryName);
            return new APIResponse(FAILURE,new ErrorResponse(ERROR_SUB_CATEGORY_NOT_FOUND.getResponseCode(),ERROR_SUB_CATEGORY_NOT_FOUND.getResponseMessage()));
        }
        Category category = categoryRepository.findProductCategoryByCatNameAndSubCatName(categoryName,subCategoryName);
        if (category == null){
            logger.error("Error : getSubCategoryByName  Category doesn't exist for subcategory [{}]",subCategoryName);
            return new APIResponse(FAILURE,new ErrorResponse(ERROR_CATEGORY_NOT_FOUND.getResponseCode(),ERROR_CATEGORY_NOT_FOUND.getResponseMessage()));
        }

        SubCategory subCategory = getSubCategoryFromList(category.getSubCategories(),subCategoryName);
        if(subCategory == null){
            return new APIResponse(FAILURE,new ErrorResponse(ERROR_SUB_CATEGORY_NOT_FOUND.getResponseCode(),ERROR_SUB_CATEGORY_NOT_FOUND.getResponseMessage(),"Subcategory doesn't exist by given name."));
        }
        return new APIResponse(SUCCESS,new SuccessResponse(SUCCESS_FOUND_CATEGORY.getResponseCode(),SUCCESS_FOUND_CATEGORY.getResponseMessage()),subCategory);
    }

    @Override
    public APIResponse getSubCategories(String categoryName) {
        Category category = categoryRepository.findProductCategoryByName(categoryName);
        if(category == null){
            logger.error("Error : getSubCategories , Category doesn't exist.");
            return new APIResponse(FAILURE,new ErrorResponse(ERROR_CATEGORY_NOT_FOUND.getResponseCode(),ERROR_CATEGORY_NOT_FOUND.getResponseMessage()));
        }
        List<SubCategory> subCategories = category.getSubCategories();
        if(CollectionUtils.isEmpty(subCategories)){
            logger.error("Error : getSubCategories , Categories don't exist for category. [{}] ",category.getName());
            return new APIResponse(FAILURE,new ErrorResponse(ERROR_SUB_CATEGORY_NOT_FOUND.getResponseCode(),ERROR_SUB_CATEGORY_NOT_FOUND.getResponseMessage()));
        }
        return new APIResponse(SUCCESS,new SuccessResponse(SUCCESS_FOUND_SUB_CATEGORY.getResponseCode(),SUCCESS_FOUND_SUB_CATEGORY.getResponseMessage()),subCategories);
    }

    private List<String> getSubCategoryNames(List<SubCategory> subCategoryList) {
        return subCategoryList.stream().map(subCategory -> subCategory.getName()).collect(Collectors.toList());
    }

    public SubCategory getSubCategoryFromList(List<SubCategory> subCategories, String subCategoryName) {
        Optional<SubCategory> subCategoryOptional = subCategories.stream().filter(subCategory -> subCategory.getName().equalsIgnoreCase(subCategoryName)).findFirst();
        SubCategory foundSubCategory = null;
        if (subCategoryOptional.isPresent()) {
            foundSubCategory = subCategoryOptional.get();
            return foundSubCategory;
        } else {
            logger.error("SubCategory does not exist by name [{}]", subCategoryName);
            return null;
        }
    }
}
