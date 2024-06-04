package com.example.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;

import com.example.domainUser.model.UserMapperEntity;
import com.example.domainUser.service.UserService;
import com.example.form.UserDetailForm;

@ExtendWith(MockitoExtension.class)
class TopPageControllerTest {
	
    @InjectMocks
    private TopPageController controller;

    @Mock
    private UserService userService;
    
    @Mock
    private Model model;   
    
    @Test
    public void testGetDivisionLogin_AdminAuthority_RedirectToAdminClockInADM() {
    	
    	String userloginId = "admin";
        // モックの設定
        Authentication authentication = mock(Authentication.class);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        when(authentication.getName()).thenReturn(userloginId); // モックの認証情報を設定

        UserMapperEntity user = new UserMapperEntity();
        user.setAuthorityFlg(2);
        when(userService.getUserOne(userloginId)).thenReturn(user);

        // テスト実行
        String result = controller.getDivisionLogin(new UserDetailForm(), mock(Model.class));

        // 検証
        verify(userService, times(1)).getUserOne(userloginId);
        assertEquals("redirect:/admin/clockInADM", result);
    }

    @Test
    public void testGetDivisionLogin_NonAdminAuthority_RedirectToUserClockIn() {
    	
    	String userloginId = "user";
        // モックの設定
        Authentication authentication = mock(Authentication.class);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        when(authentication.getName()).thenReturn(userloginId); // モックの認証情報を設定

        UserMapperEntity user = new UserMapperEntity();
        user.setAuthorityFlg(1);
        when(userService.getUserOne(userloginId)).thenReturn(user);

        // テスト実行
        String result = controller.getDivisionLogin(new UserDetailForm(), mock(Model.class));

        // 検証
        verify(userService, times(1)).getUserOne(userloginId);
        assertEquals("redirect:/user/clockIn", result);
    }    
}
