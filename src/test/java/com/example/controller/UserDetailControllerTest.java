package com.example.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import com.example.application.service.UserApplicationService;
import com.example.domainUser.model.DepartmentEntity;
import com.example.domainUser.model.RoleEntity;
import com.example.domainUser.model.UserMapperEntity;
import com.example.domainUser.service.UserService;
import com.example.form.UserDetailForm;

@ExtendWith(MockitoExtension.class)
class UserDetailControllerTest {
	
    @InjectMocks
    private UserDetailController controller;
	
	@Mock
	private UserService userService;
	
    @Mock
    private Model model;
	
    @Mock
    private ModelMapper modelMapper;
    
    @Mock
    private UserApplicationService userApplicationService;

    @Test
    public void testGetUserDetail() {
    	        
        // テストデータの作成
        String loginId = "testId";
        UserMapperEntity user = mock(UserMapperEntity.class);
        UserDetailForm form = new UserDetailForm();
        // 他のテストデータの作成も同様に続ける
        
        // モックの設定
        when(userService.getUserOne(loginId)).thenReturn(user);
        
        List<DepartmentEntity> departmentList = new ArrayList<>();
        // 部署リストの追加も同様に続ける
        when(userService.getAllDepartment()).thenReturn(departmentList);
        
        List<RoleEntity> roleList = new ArrayList<>();
        // 役職リストの追加も同様に続ける
        when(userService.getAllRole()).thenReturn(roleList);
        
        Map<String, Integer> validationMap = new HashMap<>();
        // バリデーションマップの追加も同様に続ける
        when(userApplicationService.getValidationMap()).thenReturn(validationMap);
        
        Map<String, Integer> authorityMap = new HashMap<>();
        // 権限マップの追加も同様に続ける
        when(userApplicationService.getAuthorityMap()).thenReturn(authorityMap);
        when(modelMapper.map(user, UserDetailForm.class)).thenReturn(form);
        
        // Exercise
        String result = controller.getUserDetail(new UserDetailForm(), model, loginId);
        
        // Verify
        // 正しいビュー名を返すかどうかの確認
        assertEquals("admin/userDetail", result);
        
        // モデルに正しいデータが追加されているかの確認
        verify(model).addAttribute("departmentList", departmentList);
        verify(model).addAttribute("roleList", roleList);
        verify(model).addAttribute("validationMap", validationMap);
        verify(model).addAttribute("authorityMap", authorityMap);
        verify(model).addAttribute("userDetailForm", form);
        
        // ユーザーサービスのメソッドが正しく呼び出されているかの確認
        verify(userService).getUserOne(loginId);
        verify(userService).getAllDepartment();
        verify(userService).getAllRole();
        
        // ユーザーアプリケーションサービスのメソッドが正しく呼び出されているかの確認
        verify(userApplicationService).getValidationMap();
        verify(userApplicationService).getAuthorityMap();
    }

    @Test
    public void testUpdateUser() {
        // Setup
        UserDetailForm form = new UserDetailForm();
        // フォームにデータをセット
    	BindingResult bindingResult = mock(BindingResult.class);
        
        // Exercise
        String result = controller.updateUser(model, form, bindingResult);
        
        // Verify
        // userServiceのupdateUserOneメソッドが適切に呼び出されることを確認
        verify(userService).updateUserOne(
            form.getLoginId(),
            form.getPassword(),
            form.getUserName(),
            form.getUserNamekana(),
            form.getMailAddress(),
            form.getDepartmentId(),
            form.getRoleId(),
            form.getValidation(),
            form.getAuthorityFlg(),
            form.getHire()
        );
        
        // リダイレクト先が正しいことを確認
        assertEquals("redirect:/admin/list", result);
    }
    
    @Test
    public void testDeleteUser() {
        // Setup
        UserDetailForm form = new UserDetailForm();
        // フォームにデータをセット
        
        // Exercise
        String result = controller.deleteUser(form, model);
        
        // Verify
        // userServiceのdeleteUserOneメソッドが適切に呼び出されることを確認
        verify(userService).deleteUserOne(form.getLoginId());
        
        // リダイレクト先が正しいことを確認
        assertEquals("redirect:/admin/list", result);
    }

}
