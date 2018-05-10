package com.db;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author vipul pachauri
 */
@Document(collection = "invalidtoken")
public class InvalidJWTToken {

    private String token;

    public InvalidJWTToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
