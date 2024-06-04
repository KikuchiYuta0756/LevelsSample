package com.example.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
import org.springframework.validation.BindingResult;

import com.example.domainUser.model.CorrectRequestEntity;
import com.example.domainUser.service.CorrectRequestService;
import com.example.form.CorrectRequestForm;

@ExtendWith(MockitoExtension.class)
class CorrectRequestControllerTest {
	
    @InjectMocks
    private CorrectRequestController controller;

    @Mock
    private Model model;

    @Mock
    private CorrectRequestService correctRequestService;
    
    @Mock
    private ModelMapper modelMapper;    
    
    @Mock
    private CorrectRequestEntity correctRequestEntity;
    
    @Mock
    private BindingResult bindingResult;    


	@Test
    void testGetCorrectRequest() {
        // テスト用フォームオブジェクトの作成
        CorrectRequestForm form = new CorrectRequestForm();

        // テスト対象メソッドの呼び出し
        String result = controller.getCorrectRequest(model, form);

        // 返された文字列の検証
        assertEquals("user/correctRequest", result);

    }
	
	   @Test
	    void testPostCorrectRequest() {
		   String loginId = "testloginId";
	        // テスト用フォームオブジェクトの作成
	        CorrectRequestForm form = new CorrectRequestForm();
	        
	        Authentication authentication = mock(Authentication.class);
	        when(authentication.getName()).thenReturn(loginId);

	        SecurityContext securityContext = mock(SecurityContext.class);
	        when(securityContext.getAuthentication()).thenReturn(authentication);
	        SecurityContextHolder.setContext(securityContext);

	        // モックの動作設定
	        when(bindingResult.hasErrors()).thenReturn(false);
	        when(modelMapper.map(form, CorrectRequestEntity.class)).thenReturn(new CorrectRequestEntity());
	        when(authentication.getName()).thenReturn("mockedUsername");

	        // テスト対象メソッドの呼び出し
	        String result = controller.postCorrectRequest(model, form, bindingResult);

	        // 返された文字列の検証
	        assertEquals("redirect:/user/clockInList", result);

	        // モックのメソッドが呼び出されたか検証
	        verify(correctRequestService, times(1)).correctRequestCreate(any());
	    }
}
