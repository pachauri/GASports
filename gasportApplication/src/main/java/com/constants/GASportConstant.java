package com.constants;

/**
 * @author vipul pachauri
 */
public class GASportConstant {

    public static final String SECRET = "SecretKeyToGenJWTs";
    public static final long EXPIRATION_TIME = 120000; // 2 min
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String AUTHORITIES = "roles";

    //Success & Failure
    public static final String SUCCESS = "GA01";
    public static final String FAILURE = "GA00";

    //Email
    public static final String SUBJECT = "GA Sport Greetings !!";
    public static final String BODY = "Congratulations ! You have successfully registered with GASports.";


    public static final int S3_SOCKET_TIMEOUT = 20 * 1000;
}
