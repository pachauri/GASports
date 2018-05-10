package com.utils;

import com.ExceptionHandler.GASportsException;
import com.db.ApplicationUser;
import com.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Date;
import java.util.UUID;

import static com.Mappings.*;
import static com.constants.GASportConstant.EXPIRATION_TIME;
import static com.constants.GASportConstant.SECRET;

/**
 * @author vipul pachauri
 */
public class GASportsUtils {

    private static Logger logger = LoggerFactory.getLogger(GASportsUtils.class);

    @Autowired
    private  UserRepository userRepository;

    public static String createToken(String username, Claims claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
    }

    public static String[] whiteListURL(){
        return new String[]{
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
