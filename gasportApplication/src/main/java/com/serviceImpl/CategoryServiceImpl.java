package com.serviceImpl;

import com.constants.GASportConstant;
import com.db.Category;
import com.dto.CategoryDTO;
import com.repository.CategoryRepository;
import com.response.APIResponse;
import com.response.ErrorResponse;
import com.response.SuccessResponse;
import com.service.CategoryService;
import com.utils.GASportsUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.constants.GASportConstant.FAILURE;
import static com.constants.GASportConstant.SUCCESS;
import static enums.ErrorCodes.ERROR_CATEGORY_NOT_FOUND;
import static enums.SuccessCodes.SUCCESS_FOUND_CATEGORY;

/**
 * @author vipul pachauri
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    private Logger logger = LoggerFactory.getLogger(CategoryServiceImpl.class);

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> createCategories(CategoryDTO categoryDTO) {

        try {
            //Find All categories
            logger.info("Getting all categories.");
            List<Category> categoryList = categoryRepository.findAll();
            List<String> catNames = new ArrayList<String>();
            if(!CollectionUtils.isEmpty(categoryList)){
                catNames = getCategoryNames(categoryList);
            }
            categoryList = new ArrayList<>();

            for (String name:categoryDTO.getCategoryNames()) {
                if(CollectionUtils.isEmpty(catNames) ||  (!CollectionUtils.isEmpty(catNames) && !catNames.contains(name))){
                    Category category = new Category(GASportsUtils.getUid(),name);
                    categoryList.add(category);
                }
            }
            return categoryList;
        }catch (Exception e){
            logger.error("Error : createCategories [{}]",e.getMessage());
        }
        return null;
    }

    @Override
    public APIResponse getCategoryByName(String name) {
        if(StringUtils.isBlank(name)){
            return new APIResponse(FAILURE,new ErrorResponse(ERROR_CATEGORY_NOT_FOUND.getResponseCode(),ERROR_CATEGORY_NOT_FOUND.getResponseMessage()));
        }
       Category category =  categoryRepository.findProductCategoryByName(name);
        if (category == null){
            return new APIResponse(FAILURE,new ErrorResponse());
        }
        return new APIResponse(SUCCESS,new SuccessResponse(SUCCESS_FOUND_CATEGORY.getResponseCode(),SUCCESS_FOUND_CATEGORY.getResponseMessage()),category);
    }

    @Override
    public APIResponse getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        if(CollectionUtils.isEmpty(categories)){
            return new APIResponse(FAILURE,new ErrorResponse(ERROR_CATEGORY_NOT_FOUND.getResponseCode(),ERROR_CATEGORY_NOT_FOUND.getResponseMessage()));
        }
        return new APIResponse(SUCCESS,new SuccessResponse(SUCCESS_FOUND_CATEGORY.getResponseCode(),SUCCESS_FOUND_CATEGORY.getResponseMessage()),categories);
    }

    private List<String> getCategoryNames(List<Category> categoryList) {
        return categoryList.stream().map(category -> category.getName()).collect(Collectors.toList());
    }
}
