package com.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.UUID;

import static com.Mappings.*;
import static com.constants.GASportConstant.EXPIRATION_TIME;
import static com.constants.GASportConstant.SECRET;

/**
 * @author vipul pachauri
 */
public class GASportsUtils {

    public static String parseToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
    }

    public static String[] getExcludedURL(){
        return new String[]{
                ADMIN_LOGIN_URL,
                LOGIN_URL,
                SIGN_UP_URL,
                GET_CATEGORY,
                GET_CATEGORIES,
                GET_SUB_CATEGORY,
                GET_SUB_CATEGORIES,
                GET_BRAND,
                GET_BRANDS,
                GET_PRODUCT,
                GET_PRODUCTS,
                "/v2/api-docs",
                "/configuration/ui",
                "/swagger-resources",
                "/configuration/security",
                "/swagger-ui.html",
                "/webjars/**",
                "/swagger-resources/configuration/ui",
                "/swagger-ui.html"};
    }

    public static String getUid(){
        return UUID.randomUUID().toString();
    }

}
