package com.serviceImpl;

import com.ExceptionHandler.GASportsException;
import com.db.Brand;
import com.db.Category;
import com.db.SubCategory;
import com.dto.BrandDTO;
import com.repository.CategoryRepository;
import com.service.BrandService;
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
