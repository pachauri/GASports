package com.serviceImpl;

import com.ExceptionHandler.GASportsException;
import com.db.Category;
import com.db.SubCategory;
import com.dto.SubCategoryDTO;
import com.repository.CategoryRepository;
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
