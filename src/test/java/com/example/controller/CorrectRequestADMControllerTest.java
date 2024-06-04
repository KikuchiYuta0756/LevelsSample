package com.example.controller;

import org.modelmapper.ModelMapper;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.springframework.validation.BindingResult;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;

import com.example.domainUser.model.CorrectRequestEntity;
import com.example.domainUser.model.UserMapperEntity;
import com.example.domainUser.service.CorrectRequestService;
import com.example.form.CorrectRequestForm;

@ExtendWith(MockitoExtension.class)
class CorrectRequestADMControllerTest {
	
    @InjectMocks
    private CorrectRequestADMController controller;

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
    void testGetCorrectRequestADM() {
        // テスト用フォームオブジェクトの作成
        CorrectRequestForm form = new CorrectRequestForm();

        // テスト対象メソッドの呼び出し
        String result = controller.getCorrectRequestADM(model, form);

        // 返された文字列の検証
        assertEquals("admin/correctRequestADM", result);

    }
	
	   @Test
	    void testPostCorrectRequestADM() {
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
	        String result = controller.postCorrectRequestADM(model, form, bindingResult);

	        // 返された文字列の検証
	        assertEquals("redirect:/admin/clockInListADM", result);

	        // モックのメソッドが呼び出されたか検証
	        verify(correctRequestService, times(1)).correctRequestCreate(any());
	    }
}
