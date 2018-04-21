package com.repository;

import com.db.ApplicationUser;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @author vipul pachauri
 */
@Repository
public interface UserRepository extends MongoRepository<ApplicationUser,Long> {

    @Query("{ 'username' : ?0 }")
    ApplicationUser findByUsername(String username);
}
