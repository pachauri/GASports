package com.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

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
}
