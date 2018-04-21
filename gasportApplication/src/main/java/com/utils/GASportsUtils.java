package com.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.UUID;

import static com.Mappings.ADMIN_LOGIN_URL;
import static com.Mappings.LOGIN_URL;
import static com.Mappings.SIGN_UP_URL;
import static com.constants.SecurityConstant.EXPIRATION_TIME;
import static com.constants.SecurityConstant.SECRET;

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
        String[] urls = {ADMIN_LOGIN_URL,LOGIN_URL,SIGN_UP_URL};
        return urls;
    }

    public static String getUid(){
        return UUID.randomUUID().toString();
    }
}
