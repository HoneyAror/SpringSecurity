package com.example.firstapp;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.naming.AuthenticationException;
import java.util.Arrays;

@Component  //customize our own authentication provider
  public class MyAuthenticationProvider implements AuthenticationProvider{

    @Override
    public Authentication authenticate(Authentication authentication) {
        String username=authentication.getName();
        String password=authentication.getCredentials().toString();
        if("honey".equals(username) && "arora".equals(password)){
            return new UsernamePasswordAuthenticationToken(username,password, Arrays.asList());
        }
        else{
            throw new BadCredentialsException("INVALID LOGIN");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
