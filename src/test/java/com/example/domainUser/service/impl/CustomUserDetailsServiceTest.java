package com.example.domainUser.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.example.domainUser.model.UserMapperEntity;

@ExtendWith(MockitoExtension.class)
class CustomUserDetailsServiceTest {
	
    @InjectMocks
    private CustomUserDetailsService customUserDetailsService;
    
    
    @Mock
    private CustomUserService customUserService;

    @Test
    public void testLoadUserByUsername_ExistingUser() {
        // モックの設定
        UserMapperEntity mockUser = new UserMapperEntity();
        mockUser.setLoginId("testuser");
        mockUser.setPassword("testpassword");

        when(customUserService.getUserByUsername("testuser")).thenReturn(mockUser);

        // テスト
        UserDetails userDetails = customUserDetailsService.loadUserByUsername("testuser");

        // 検証
        assertEquals("testuser", userDetails.getUsername());
        assertEquals("testpassword", userDetails.getPassword());
    }

    @Test
    public void testLoadUserByUsername_NonExistingUser() {
        // モックの設定
        when(customUserService.getUserByUsername("nonexistinguser")).thenReturn(null);

        // テストと例外のキャッチ
        UsernameNotFoundException exception = assertThrows(UsernameNotFoundException.class, () -> {
        	customUserDetailsService.loadUserByUsername("nonexistinguser");
        });

        // 検証
        assertEquals("User not found: nonexistinguser", exception.getMessage());
    }
}
