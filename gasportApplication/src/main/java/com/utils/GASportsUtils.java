package com.utils;

import com.db.BaseProductInfo;
import com.db.Category;
import com.db.SubCategory;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.Mappings.*;
import static com.constants.GASportConstant.EXPIRATION_TIME;
import static com.constants.GASportConstant.SECRET;

/**
 * @author vipul pachauri
 */
public class GASportsUtils {

    public static String parseToken(String username) {
        String token = Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
        return token;
    }

    public static String[] getExcludedURL(){
        String[] urls = {
                ADMIN_LOGIN_URL,
                LOGIN_URL,
                SIGN_UP_URL,
                GET_CATEGORY,
                GET_CATEGORIES,
                GET_SUB_CATEGORY,
                GET_SUB_CATEGORIES,
                GET_BRAND,
                "/v2/api-docs",
                "/configuration/ui",
                "/swagger-resources",
                "/configuration/security",
                "/swagger-ui.html",
                "/webjars/**",
                "/swagger-resources/configuration/ui",
                "/swagger-ui.html"};
        return urls;
    }

    public static String getUid(){
        return UUID.randomUUID().toString();
    }

}
