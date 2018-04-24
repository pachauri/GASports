package com.repository;

import com.db.Category;
import com.db.SubCategory;
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

   @Query(value="{ 'subCategories.name' : ?0 }")
   SubCategory findSubtCategoryByName(String name);
}
