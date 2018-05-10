package com.config;

import com.filter.JWTAuthorizationFilter;
import com.repository.TokenRepository;
import com.utils.GASportsUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author vipul pachauri
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private WebSecurityConfig webSecurityConfig;

    @Autowired
    public TokenRepository tokenRepository;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // Create the Authorization Filter
        JWTAuthorizationFilter authoFilter =
                new JWTAuthorizationFilter(
                        authenticationManager()
                );
        // Inject the configuration Class into the Filter
        authoFilter.setWebSecurityConfig(webSecurityConfig);

        http.cors().
                and().csrf().disable().authorizeRequests()
                .antMatchers(GASportsUtils.whiteListURL()).permitAll()
                .antMatchers("/ga-sports/admin/**").access("hasAuthority('ADMIN')")
                .antMatchers("/ga-sports/user/**").access("hasAuthority('USER')")
                .anyRequest().authenticated()
                .and()
                .addFilter(authoFilter)
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);

    }
}
