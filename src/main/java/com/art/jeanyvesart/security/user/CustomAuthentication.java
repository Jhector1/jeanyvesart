package com.art.jeanyvesart.security.user;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;


public class CustomAuthentication extends UsernamePasswordAuthenticationToken {

    //private String customDetail; // Custom detail you want to associate
    //private boolean authenticated=false;
    //jean

    public CustomAuthentication(Object principal, Object credentials) {
        super(principal, credentials);

    }

    public CustomAuthentication(
            Object principal,
            Object credentials,
            Collection<? extends GrantedAuthority> authorities) {
        super(principal, credentials, authorities);

    }

//    @Override
//    public Object getDetails() {
//        return userDetails;
//    }



}
