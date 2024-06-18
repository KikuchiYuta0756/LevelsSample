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
    public void loadUserByUsername_UserExists_ShouldReturnUserDetails() {
        // Arrange
        UserMapperEntity userMapperEntity = new UserMapperEntity();
        userMapperEntity.setLoginId("testuser");
        
        when(customUserService.getUserByUsername("testuser")).thenReturn(userMapperEntity);

        // Act
        UserDetails userDetails = customUserDetailsService.loadUserByUsername("testuser");

        // Assert
        assertEquals("testuser", userDetails.getUsername());
    }
    
    @Test
    public void loadUserByUsername_UserDoesNotExist_ShouldThrowException() {
        // Arrange
        when(customUserService.getUserByUsername("nonexistentuser")).thenReturn(null);

        // Act & Assert
        UsernameNotFoundException exception = assertThrows(UsernameNotFoundException.class, () -> {
            customUserDetailsService.loadUserByUsername("nonexistentuser");
        });

        assertEquals("User not found: nonexistentuser", exception.getMessage());
    }
}
