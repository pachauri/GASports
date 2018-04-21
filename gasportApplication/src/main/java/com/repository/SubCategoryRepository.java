package com.repository;

import com.db.SubCategory;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @author vipul pachauri
 */
@Repository
public interface SubCategoryRepository extends MongoRepository<SubCategory,Long> {

    @Query("{ 'subCategories.uid' : ?0 }")
    SubCategory findSubCategoryByUid(String uid);
}
