package com.serviceImpl;


import com.ExceptionHandler.GASportsException;
import com.db.ApplicationUser;
import com.repository.UserRepository;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static java.util.Collections.emptyList;

/**
 * @author vipul pachauri
 */
@Service
public class UserServiceImpl implements UserDetailsService {

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);


    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("loadUserByUsername [{}]",username);
        ApplicationUser applicationUser = userRepository.findByUsername(username);
        if (applicationUser == null || StringUtils.isBlank(applicationUser.getUsername())) {
            logger.info("User not found by username [{}]",username);
            throw new GASportsException("User not found by username.");
        }
        logger.info("User found by username [{}]",username);
        return new User(applicationUser.getUsername(), applicationUser.getPassword(), emptyList());
    }
}
