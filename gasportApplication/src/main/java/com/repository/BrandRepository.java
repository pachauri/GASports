package com.repository;

import com.db.Brand;
import com.db.Category;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @author vipul pachauri
 */
@Repository
public interface BrandRepository extends MongoRepository<Brand,Long> {

    @Query(value = "{ 'name' : ?0, 'subCategories.name' : ?1, 'subCategories.brandList.name' : ?2 }", fields = "{ 'subCategories.name' : 1,'subCategories.brandList.name' : 2 }")
    Category findByName(String categoryName, String subcategoryName, String brandName);
}
