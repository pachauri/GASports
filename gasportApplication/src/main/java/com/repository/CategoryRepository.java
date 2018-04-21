package com.repository;

import com.db.Category;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @author vipul pachauri
 */
@Repository
public interface CategoryRepository extends MongoRepository<Category,Long>{

    @Query("{ 'subCategories.name' : ?0 }")
    Category findProductBySubCategory(String subCategoryName);

    @Query("{ 'category' : ?0 }")
    Category findProductByCategory(String category);

    @Query("{ 'uid' : ?0 }")
    Category findCategoryByCategoryUid(String uid);

    @Query("{ 'subCategories.uid' : ?0 }")
    Category findCategoryBySubCategoryUid(String uid);

    @Query("{ 'subCategories.brandList.uid' : ?0 }")
    Category findProductCategoryByBrandUid(String brandId);

//    @Query(value="{ 'subCategories.brandList.uid' : ?0 }",fields="{ 'subCategories.brandList' : 1}")
//    List<Brand> findProductCategoryByBrandUid(String brandId);
}
