package com.example.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.springframework.validation.BindingResult;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;

import com.example.domainUser.service.PaidAppService;
import com.example.domainUser.service.UserService;
import com.example.domainUser.model.PaidEntity;
import com.example.domainUser.model.PaidAppEntity;
import com.example.form.PaidRequestForm;

@ExtendWith(MockitoExtension.class)
class PaidRequestADMControllerTest {

    @InjectMocks
    private PaidRequestADMController paidRequestADMController;
	
    @Mock
    private UserService userService;

    @Mock
    private PaidAppService paidAppService;

    @Mock
    private Model model;

    @Mock
    private Authentication authentication;

    @Mock
    private SecurityContext securityContext;
    
    @Mock
    private BindingResult bindingResult;    
    
    @Mock
    private ModelMapper modelMapper;    
    

    @Test
    void testGetPaidRequestADM() {
    	
    	String loginId = "testLoginId";
        PaidEntity paidEntity = new PaidEntity();
        paidEntity.setPaidDateNum("5"); // 仮の有給日数を設定
    	
        Authentication authentication = mock(Authentication.class);
        when(authentication.getName()).thenReturn(loginId);

        SecurityContext securityContext = mock(SecurityContext.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);
        // モックの動作を設定
        when(userService.getPaidDays(loginId)).thenReturn(paidEntity);

        // テスト対象メソッドの呼び出し
        String viewName = paidRequestADMController.getPaidRequestADM(model, new PaidRequestForm());

        // モックの呼び出しを検証
        assertEquals("admin/paidRequestADM", viewName);
        verify(userService, times(1)).getPaidDays(loginId);
        verify(model, times(1)).addAttribute(eq("paidDays"), eq("5")); // 仮の有給日数を確認
        verify(model, times(1)).addAttribute(eq("paidDays"), anyString()); // 有給日数がnullの場合の確認
    }
    
    @Test
    void testPostPaidRequestADM() {
    	//Setup
    	String paidloginId = "testLoginId";
        PaidAppEntity paidAppEntity = new PaidAppEntity();
        PaidRequestForm form = new PaidRequestForm();

        Authentication authentication = mock(Authentication.class);
        when(authentication.getName()).thenReturn(paidloginId);

        SecurityContext securityContext = mock(SecurityContext.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);
    	
        // 入力チェックが成功した場合
        when(bindingResult.hasErrors()).thenReturn(false);
        when(modelMapper.map(form, PaidAppEntity.class)).thenReturn(paidAppEntity);

        // テスト対象メソッドの呼び出し
        String viewName = paidRequestADMController.postPaidRequestADM(model, form, bindingResult);

        // モックの呼び出しを検証
        verify(paidAppService, times(1)).paidAppCreate(paidAppEntity); // paidAppServiceが1回呼び出されることを確認
        verify(model, never()).addAttribute(anyString(), any());       // エラーメッセージがモデルに追加されないことを確認
        assertEquals("redirect:/admin/clockInListADM", viewName);        // 適切なリダイレクトが行われることを確認
    }

}
