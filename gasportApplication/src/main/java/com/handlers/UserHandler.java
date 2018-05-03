package com.handlers;

import com.ExceptionHandler.GASportsException;
import com.db.ApplicationUser;
import com.repository.UserRepository;
import com.response.APIResponse;
import com.utils.GASportsUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.Mappings.*;

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

    private AuthenticationManager authenticationManager;

    @PostMapping(SIGN_UP_URL)
    public APIResponse register(@RequestBody ApplicationUser user) {
        logger.info("User registration started. [{}]",user);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        logger.info("User registration successful. [{}]",user);
        return new APIResponse("User Registration Successful.",null,user);
    }


    @PostMapping({LOGIN_URL,ADMIN_LOGIN_URL})
    public APIResponse login(@RequestBody ApplicationUser applicationUser,HttpServletRequest req,
                             HttpServletResponse res) {
        logger.info("Login started. [{}]",applicationUser);

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

        String accessToken = GASportsUtils.parseToken(user.getUsername());
        return new APIResponse("Login Successful.", accessToken);

    }


}
