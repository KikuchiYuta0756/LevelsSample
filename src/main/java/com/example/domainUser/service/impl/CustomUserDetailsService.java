package com.example.domainUser.service.impl;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.domainUser.model.UserMapperEntity;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	
    private final CustomUserService customUserService;

    public CustomUserDetailsService(CustomUserService customUserService) {
        this.customUserService = customUserService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserMapperEntity user = customUserService.getUserByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found: " + username);
        }

        return User.builder()
                .username(user.getLoginId())
                .password(user.getPassword())
                .build();
    }



}
