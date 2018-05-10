package com.handlers;

import com.ExceptionHandler.GASportsException;
import com.db.ApplicationUser;
import com.db.Email;
import com.db.InvalidJWTToken;
import com.repository.TokenRepository;
import com.repository.UserRepository;
import com.response.APIResponse;
import com.response.ErrorResponse;
import com.response.SuccessResponse;
import com.service.EmailService;
import com.utils.GASportsUtils;
import enums.ErrorCodes;
import enums.SuccessCodes;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.apache.commons.lang.StringUtils;
import org.omg.PortableInterceptor.SUCCESSFUL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static com.Mappings.*;
import static com.constants.GASportConstant.*;
import static com.constants.GASportConstant.SECRET;
import static com.constants.GASportConstant.TOKEN_PREFIX;

/**
 * @author vipul pachauri
 */

@RestController
public class UserHandler {

    private final Logger logger = LoggerFactory.getLogger(UserHandler.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    private EmailService emailService;


    @PostMapping(SIGN_UP_URL)
    public APIResponse register(@RequestBody ApplicationUser user) {
        try {
            logger.info("User registration call started. [{}]",user);
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            userRepository.save(user);
            logger.info("User registration successful. [{}]",user);
            emailService.sendMail(user.getEmailId());
            return new APIResponse(SUCCESS,new SuccessResponse(SuccessCodes.SUCCEES_USER_REGISTRATION.getResponseCode(),SuccessCodes.SUCCEES_USER_REGISTRATION.getResponseMessage()),user);
        }catch (Exception e){
            logger.error("ERROR : register [{}]",e.getStackTrace());
            return new APIResponse(FAILURE,new ErrorResponse(ErrorCodes.ERROR_USER_REGISTRATION.getResponseCode(),ErrorCodes.ERROR_USER_REGISTRATION.getResponseMessage(),e.getMessage()));
        }

    }


    @PostMapping(LOGIN_URL)
    public APIResponse login(@RequestBody ApplicationUser applicationUser,HttpServletRequest req,
                             HttpServletResponse res) {
        logger.info("Login call started. [{}]",applicationUser);
        if(applicationUser == null || StringUtils.isBlank(applicationUser.getUsername()) || StringUtils.isBlank(applicationUser.getPassword())){
           throw new GASportsException("Invalid Username or password");
        }

        UserDetails user = userDetailsService.loadUserByUsername(applicationUser.getUsername());
        if (user == null) {
            throw new GASportsException("User not found by username.");
        }

        String encodedPassword = user.getPassword();
        String password = applicationUser.getPassword();
        boolean passwordMatched = bCryptPasswordEncoder.matches(password,encodedPassword);

        if(!passwordMatched){
            throw new GASportsException("Error in authentication | Invalid Password.");
        }

        Claims claims = Jwts.claims().setSubject(applicationUser.getUsername());
        claims.put(AUTHORITIES, user.getAuthorities());

        String accessToken = GASportsUtils.createToken(user.getUsername(),claims);
        return new APIResponse("Login Successful.", accessToken);

    }

    @PostMapping(LOGOUT_URL)
    public APIResponse logout(HttpServletRequest request, HttpServletResponse res) {
        HttpSession session= request.getSession(false);
        SecurityContextHolder.clearContext();
        if(session != null) {
            session.invalidate();
        }

        String token = request.getHeader(HEADER_STRING);
        if (token != null) {
            tokenRepository.save(new InvalidJWTToken(token));
        }
        return new APIResponse(SUCCESS,new SuccessResponse(SuccessCodes.SUCCESS_LOGOUT.getResponseCode(),SuccessCodes.SUCCESS_LOGOUT.getResponseMessage()));

    }

}
