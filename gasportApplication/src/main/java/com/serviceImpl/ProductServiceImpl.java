package com.serviceImpl;

import com.ExceptionHandler.GASportsException;
import com.db.Brand;
import com.db.Category;
import com.db.ProductDetails;
import com.db.SubCategory;
import com.dto.*;
import com.repository.CategoryRepository;
import com.repository.SubCategoryRepository;
import com.response.APIResponse;
import com.service.ProductService;
import com.utils.GASportsUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author vipul pachauri
 */
@Service
public class ProductServiceImpl implements ProductService {

    private Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private SubCategoryRepository subCategoryRepository;

    @Override
    public APIResponse addCategory(ProductParentDTO productParentDTO) {
        if(productParentDTO == null || CollectionUtils.isEmpty(productParentDTO.getCategories())){
            throw new GASportsException("Can't add Empty Categories");
        }
        List<Category> categories = new ArrayList<Category>();
        for (CategoryDTO categoryDTO : productParentDTO.getCategories()) {
            Category category = new Category();
            category.setUid(GASportsUtils.getUid());
            category.setCategoryName(categoryDTO.getName());
            categories.add(category);
        }
        categoryRepository.save(categories);
        return new APIResponse("Category added successfully.",productParentDTO.getCategories());
    }

    @Override
    public APIResponse addSubCategory(CategoryDTO categoryDTO) {
        if(categoryDTO == null || CollectionUtils.isEmpty(categoryDTO.getSubCategories())){
            throw new GASportsException("Can't add Empty Sub Categories");
        }
        Category category =  categoryRepository.findCategoryByCategoryUid(categoryDTO.getUid());
        if(category == null){
            throw new GASportsException("Category Not Found by given categoryId");
        }
        List<SubCategory> subCategories = new ArrayList<SubCategory>();

            for (SubCategoryDTO dto : categoryDTO.getSubCategories()) {
                SubCategory subCategory = new SubCategory();
                subCategory.setUid(GASportsUtils.getUid());
                subCategory.setName(dto.getName());
                subCategories.add(subCategory);
            }

        category.setSubCategories(subCategories);
        categoryRepository.save(category);
        return new APIResponse("Subcategory added successfully",category.getSubCategories());
    }

    @Override
    public APIResponse addBrand(SubCategoryDTO subCategoryDTO) {

        if(subCategoryDTO == null || CollectionUtils.isEmpty(subCategoryDTO.getBrands())){
            throw new GASportsException("Can't add Empty Brands");
        }
        Category category = categoryRepository.findCategoryBySubCategoryUid(subCategoryDTO.getUid());

        if(category == null){
            throw new GASportsException("SubCategory Not Found by given subCategoryId");
        }
        List<SubCategory> subCategories = category.getSubCategories();
        SubCategory subCategory = null;
        //Create stream for subCategories

        Optional<SubCategory> optionalSubCategory = subCategories.stream().filter(s -> s.getUid().equals(subCategoryDTO.getUid())).findFirst();
        if(optionalSubCategory.isPresent()){
            subCategory = optionalSubCategory.get();
        }

        List<Brand> brandList = subCategory.getBrandList();

        if(CollectionUtils.isEmpty(brandList)){
            brandList = new ArrayList<Brand>();
        }

        for (BrandDTO dto : subCategoryDTO.getBrands()) {
            Brand brand = new Brand();
            brand.setUid(GASportsUtils.getUid());
            brand.setName(dto.getName());
            brandList.add(brand);
        }
        subCategory.setBrandList(brandList);
        category = categoryRepository.save(category);
        return new APIResponse("ProductParent Subcategory added successfully",category);
    }

    @Override
    public APIResponse addProductDetails(BrandDTO brandDTO) {
        if(brandDTO == null || CollectionUtils.isEmpty(brandDTO.getProducts())){
            throw new GASportsException("Can't add Empty Product Details");
        }

        Category category = categoryRepository.findProductCategoryByBrandUid(brandDTO.getUid());
        if(category == null){
            throw  new GASportsException("Brand not found by brandId.");
        }

        Optional<Brand> brandOptional = category.getSubCategories().stream()
                                                 .flatMap(subCategory -> subCategory.getBrandList().stream())
                                                  .filter(brand -> brand.getUid().equals(category.getUid())).findFirst();

        Brand brand = null;

        if(brandOptional.isPresent()){
            brand = brandOptional.get();
        }

        List<ProductDetails> productDetails = brand.getProductDetails();
        if(CollectionUtils.isEmpty(productDetails)){
            productDetails = new ArrayList<ProductDetails>();
        }

            for (ProductDTO productDTO : brandDTO.getProducts()) {
                ProductDetails producDetail = new ProductDetails();
                producDetail.setUid(GASportsUtils.getUid());
                producDetail.setName(productDTO.getName());
                producDetail.setPrice(productDTO.getPrice());
                productDetails.add(producDetail);
            }

        brand.setProductDetails(productDetails);
        categoryRepository.save(category);
        return new APIResponse("Product Details added successfully.",category);
    }


}
