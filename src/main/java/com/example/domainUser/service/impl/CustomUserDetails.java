package com.example.domainUser.service.impl;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class CustomUserDetails extends User {
    private final int validation;

    public CustomUserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities, int validation) {
        super(username, password, authorities);
        this.validation = validation;
    }

    public int getValidation() {
        return validation;
    }
}
