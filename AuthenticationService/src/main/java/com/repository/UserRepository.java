package com.repository;

import com.db.ApplicationUser;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

/**
 * @author vipul pachauri
 */
public interface UserRepository extends MongoRepository<ApplicationUser,Long> {

    @Query("{ 'username' : ?0 }")
    ApplicationUser findByUsername(String username);
}
