package com.serviceImpl;

import com.db.Category;
import com.dto.CategoryDTO;
import com.repository.CategoryRepository;
import com.service.CategoryService;
import com.utils.GASportsUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    private List<String> getCategoryNames(List<Category> categoryList) {
        return categoryList.stream().map(category -> category.getName()).collect(Collectors.toList());
    }
}
