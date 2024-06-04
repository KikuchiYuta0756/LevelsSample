package com.example.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
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

import com.example.domainUser.model.UserMapperEntity;
import com.example.domainUser.service.UserService;
import com.example.form.UserDetailForm;

@ExtendWith(MockitoExtension.class)
class PasswordChangeControllerTest {
	
        @InjectMocks
        private PasswordChangeController controller;
        
	    @Mock
	    private UserService userService;

	    @Mock
	    private Model model;

	    @Mock
	    private ModelMapper modelMapper;
	    
	    @Test
	    public void testGetBeforePasswordChange_ReturnsCorrectView() {
	    	
	    	String loginId = "user";
	        UserMapperEntity loginUser = new UserMapperEntity();
	        UserDetailForm form = new UserDetailForm();   //モックのModelオブジェクトを作成

	        // モックの設定
	        Authentication auth = mock(Authentication.class);
	        SecurityContextHolder.getContext().setAuthentication(auth);
	        when(auth.getName()).thenReturn(loginId); // モックの認証情報を設定

	        UserMapperEntity user = new UserMapperEntity();
	        user.setLoginId(loginId);
	        when(userService.getUserOne(loginId)).thenReturn(loginUser);

	        // テスト実行
	        String result = controller.getBeforePasswordChange(form, model);

	        // 検証
	        assertEquals("common/afterPasswordChange", result);
	        verify(userService, times(1)).getUserOne(loginId);
	    }

	    @Test
	    public void testPostAfterPasswordChange_PasswordUpdatedSuccessfully() {
	        // モックの設定
	        UserDetailForm form = new UserDetailForm();
	        form.setLoginId("user");
	        form.setPassword("newPassword");

	        // テスト実行
	        String result = controller.postAfterPasswordChange(form, model);

	        // 検証
	        assertEquals("common/TopPage", result);
	        verify(userService, times(1)).updatePasswordOne("user", "newPassword");
	    }

}
