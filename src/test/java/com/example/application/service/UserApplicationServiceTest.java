package com.example.application.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserApplicationServiceTest {

	@Mock
    private UserApplicationService userApplicationService;
    
    
    @BeforeEach
    public void setUp() throws Exception {
        userApplicationService = new UserApplicationService();
    }
    
    @Test
    public void testGetValidationMap() {
        Map<String, Integer> validationMap = userApplicationService.getValidationMap();
        assertNotNull(validationMap);
        assertEquals(2, validationMap.size());
        assertEquals(Integer.valueOf(1), validationMap.get("有効"));
        assertEquals(Integer.valueOf(2), validationMap.get("無効"));
    }

    @Test
    public void testGetAuthorityMap() {
        Map<String, Integer> authorityMap = userApplicationService.getAuthorityMap();
        assertNotNull(authorityMap);
        assertEquals(2, authorityMap.size());
        assertEquals(Integer.valueOf(1), authorityMap.get("GENERAL"));
        assertEquals(Integer.valueOf(2), authorityMap.get("ADMIN"));
    }

}
