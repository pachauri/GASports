package com.serviceImpl;

import com.db.Brand;
import com.db.Category;
import com.db.ProductDetails;
import com.db.SubCategory;
import com.dto.ProductDTO;
import com.dto.ProductDetailsDTO;
import com.service.BrandService;
import com.service.ProductDetailsService;
import com.service.SubCategoryService;
import com.utils.GASportsUtils;
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
public class ProductDetailsServiceImpl implements ProductDetailsService {

    private Logger logger = LoggerFactory.getLogger(ProductDetailsServiceImpl.class);

    @Autowired
    private SubCategoryService subCategoryService;

    @Autowired
    private BrandService brandService;

    @Override
    public List<ProductDetails> createProductDetails(ProductDTO productDTO, Category category) {
        if(CollectionUtils.isEmpty(category.getSubCategories())){
            logger.error("SubCategories don't exist for category name [{}]",productDTO.getCategoryName());
            return null;
        }
        SubCategory foundSubCategory = subCategoryService.getSubCategoryFromList(category.getSubCategories(),productDTO.getSubCategoryName());

        if(CollectionUtils.isEmpty(foundSubCategory.getBrandList())){
            logger.error("Brands don't exist for sub category name [{}]",productDTO.getSubCategoryName());
            return null;
        }

        Brand foundBrand = brandService.getBrandsFromList(foundSubCategory.getBrandList(),productDTO.getBrandName());
        List<ProductDetails> productDetailsList = foundBrand.getProductDetails();
        List<String> productNames = new ArrayList<String>();
        if(!CollectionUtils.isEmpty(productDetailsList)){
            productNames = productDetailsList.stream().map(productDetails -> productDetails.getName()).collect(Collectors.toList());
        }else {
            productDetailsList = new ArrayList<ProductDetails>();
        }
        for(ProductDetailsDTO productDetailsDTO : productDTO.getProductDetails()){
            if (CollectionUtils.isEmpty(productNames) || (!CollectionUtils.isEmpty(productNames) && !productNames.contains(productDetailsDTO.getName()))) {
                ProductDetails productDetails = new ProductDetails(productDetailsDTO.getName(),productDetailsDTO.getPrice());
                productDetailsList.add(productDetails);
            }
        }
        foundBrand.setProductDetails(productDetailsList);
        return productDetailsList;
    }


}
