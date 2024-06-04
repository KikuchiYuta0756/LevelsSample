package com.example.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import com.example.application.service.UserApplicationService;
import com.example.domainUser.model.DepartmentEntity;
import com.example.domainUser.model.RoleEntity;
import com.example.domainUser.model.UserMapperEntity;
import com.example.domainUser.service.UserService;
import com.example.domainUser.service.WorkTimeService;
import com.example.form.UserCreateForm;

@ExtendWith(MockitoExtension.class)
public class UserCreateControllerTest {
	
    @InjectMocks
    private UserCreateController controller;

    @Mock
    private UserApplicationService userApplicationService;

    @Mock
    private UserService userService;

    @Mock
    private Model model;

    @Mock
    private ModelMapper modelMapper;
    
    @Mock
	private WorkTimeService workTimeService;

    @Test
    public void testGetUserCreate() {
        // Setup
    	UserCreateForm form = new UserCreateForm();
        List<DepartmentEntity> departmentList = Arrays.asList(new DepartmentEntity(), new DepartmentEntity());
        List<RoleEntity> roleList = Arrays.asList(new RoleEntity(), new RoleEntity());
        Map<String, Integer> validationMap = new HashMap<>();
        validationMap.put("key1", 1);
        validationMap.put("key2", 2);
        Map<String, Integer> authorityMap = new HashMap<>();
        authorityMap.put("key3", 3);
        authorityMap.put("key4", 4);

        when(userService.getAllDepartment()).thenReturn(departmentList);
        when(userService.getAllRole()).thenReturn(roleList);
        when(userApplicationService.getValidationMap()).thenReturn(validationMap);
        when(userApplicationService.getAuthorityMap()).thenReturn(authorityMap);

        // Exercise
        String viewName = controller.getUserCreate(model, form);

        // Verify
        verify(userService,times(1)).getAllDepartment(); 
        verify(userService,times(1)).getAllRole(); 
        verify(userApplicationService,times(1)).getValidationMap(); 
        verify(userApplicationService,times(1)).getAuthorityMap(); 
        verify(model).addAttribute("departmentList", departmentList);
        verify(model).addAttribute("roleList", roleList);
        verify(model).addAttribute("validationMap", validationMap);
        verify(model).addAttribute("authorityMap", authorityMap);
        assertEquals("admin/create", viewName);
    }

    @Test
    public void testPostUserCreate_WithValidInput() {
        // 有効な入力データを作成
    	BindingResult bindingResult = mock(BindingResult.class);
        UserCreateForm validForm = new UserCreateForm();
        validForm.setLoginId("testuser");
        validForm.setPassword("password");
        UserMapperEntity user = new UserMapperEntity();
        
        // モックの設定
        when(bindingResult.hasErrors()).thenReturn(false);
        when(modelMapper.map(validForm, UserMapperEntity.class)).thenReturn(user);
        
        // メソッドの実行
        String result = controller.postUserCreate(model, validForm, bindingResult);
        
        // 結果の検証
        verify(modelMapper).map(validForm, UserMapperEntity.class);
        assertEquals("redirect:/admin/list", result);
    }

    @Test
    public void testPostUserCreate_WithInvalidInput() {
        // 無効な入力データを作成（例えば、空のログインID）
    	BindingResult bindingResult = mock(BindingResult.class);
        UserCreateForm invalidForm = new UserCreateForm();
        invalidForm.setLoginId("");
        
        when(bindingResult.hasErrors()).thenReturn(true);
        
        // メソッドの実行
        String result = controller.postUserCreate(model, invalidForm, bindingResult);
        
        // 結果の検証
        assertEquals("admin/create", result); // ユーザー登録画面に戻るはず
    }
    
    

//    @Test
//    public void testPostUserCreate_NoErrors() {
//        // Setup
//        String loginId = "testLoginId";
//        UserCreateForm form = new UserCreateForm();
//        form.setPassword("");
//        UserMapperEntity user = new UserMapperEntity();
//        BindingResult bindingResult = mock(BindingResult.class);
//
//        
//        when(bindingResult.hasErrors()).thenReturn(false);
//        when(modelMapper.map(form, UserMapperEntity.class)).thenReturn(user);
//        when(user.getLoginId()).thenReturn(loginId);
//
//        // Test
//        String result = controller.postUserCreate(model, form, bindingResult);
//
//        // Verify
//        verify(userService, times(1)).userCreate(user);
//        verify(userService, times(1)).userPaidCreate(user);
//        verify(workTimeService, times(1)).userWorkTimeCreate(loginId);
//        verify(workTimeService, times(1)).userWorkTimeTotalCreate(loginId);
//        verify(modelMapper).map(form, UserMapperEntity.class);
//        assertEquals("redirect:/admin/list", result);
//    }
}
