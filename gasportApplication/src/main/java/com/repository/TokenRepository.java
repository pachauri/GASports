package com.repository;

import com.db.InvalidJWTToken;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @author vipul pachauri
 */
@Repository
public interface TokenRepository extends MongoRepository<InvalidJWTToken,Long> {

    @Query("{ 'token' : ?0 }")
    InvalidJWTToken getToken(String token);
}
