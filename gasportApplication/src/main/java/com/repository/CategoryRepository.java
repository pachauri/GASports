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

    @Query("{ 'name' : ?0 }")
    Category findProductCategoryByName(String name);

    @Query(value = "{ 'name' : ?0, 'subCategories.name' : ?1 }", fields = "{ 'subCategories.name' : 1 }")
    Category findProductCategoryByCatNameAndSubCatName(String categoryName, String subCategoryName);

    @Query(value = "{ 'name' : ?0, 'subCategories.name' : ?1, 'subCategories.brandList.name' : ?2 }", fields = "{ 'subCategories.name' : 1,'subCategories.brandList.name' : 2 }")
    Category findCategoryByCatAndSubCatAndBrandName(String categoryName, String subcategoryName, String brandName);
}
