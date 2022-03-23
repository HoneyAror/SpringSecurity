package com.example.firstapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration //at run time this will be used for configuration by springboot
public class MySecurityConfig extends WebSecurityConfigurerAdapter {
    @Lazy
    @Autowired
    private PasswordEncoder passwordEncoder;


    @Autowired
    private MyAuthenticationProvider authenticationProvider;

//    @Override //it customize authenticationmanager
//     protected void configure (AuthenticationManagerBuilder auth) throws Exception{
//        InMemoryUserDetailsManager userdetailservice=new InMemoryUserDetailsManager();
//        UserDetails user= User.withUsername("honey").password(passwordEncoder.encode("arora")).authorities("read").build();
//        userdetailservice.createUser(user);
//        auth.userDetailsService(userdetailservice);
//    }

    @Override //it customize authenticationmanager
    protected void configure (AuthenticationManagerBuilder auth) throws Exception{
        auth.authenticationProvider(authenticationProvider);
    }

    @Override //customize what security we want to use(form based,http base)
    protected  void configure(HttpSecurity http) throws Exception{
        http.httpBasic();
        http.authorizeRequests().antMatchers("/hello").authenticated(); //all request shld  be authenticated thn only they cn access
          http.addFilterBefore(new MySecurityFilter(), BasicAuthenticationFilter.class);
    }

    @Bean
    public  BCryptPasswordEncoder passwordEncoder(){
       return new BCryptPasswordEncoder();
    }
}
