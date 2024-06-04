package com.example.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import com.example.domainUser.model.UserMapperEntity;
import com.example.domainUser.service.UserService;
import com.example.form.UserListForm;

@ExtendWith(MockitoExtension.class)
class UserListControllerTest {
	
    @InjectMocks
    private UserListController controller;

    @Mock
    private UserService userService;

    @Mock
    private Model model;
	
    @Mock
    private ModelMapper modelMapper;


    @Test
    public void testGetUserList() {
        // モックの振る舞いを設定
        UserListForm form = new UserListForm(); // formを適切に初期化する
        UserMapperEntity userEntity = new UserMapperEntity(); // userEntityを適切に初期化する
        when(modelMapper.map(form, UserMapperEntity.class)).thenReturn(userEntity);

        List<UserMapperEntity> userList = new ArrayList<>(); // テスト用のユーザーリストを作成する
        when(userService.getUsers(userEntity)).thenReturn(userList);

        // メソッドを実行
        String viewName = controller.getUserList(model, form);

        // 検証
        verify(modelMapper).map(form, UserMapperEntity.class);
        verify(userService).getUsers(userEntity);
        verify(model).addAttribute("userList", userList);
        // 期待されるビュー名を検証する（実際のビュー名に応じて変更する）
        assertEquals("admin/list", viewName); 
    }

    
    @Test
    public void testPostUserList() {
        // モックの振る舞いを設定
        UserListForm form = new UserListForm(); // formを適切に初期化する
        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.hasErrors()).thenReturn(false);

        UserMapperEntity userEntity = new UserMapperEntity(); // userEntityを適切に初期化する
        when(modelMapper.map(form, UserMapperEntity.class)).thenReturn(userEntity);

        List<UserMapperEntity> userList = new ArrayList<>(); // テスト用のユーザーリストを作成する
        when(userService.getUsers(userEntity)).thenReturn(userList);

        // テスト実行
        String viewName = controller.postUserList(form, model, bindingResult);

        // 検証
        verify(bindingResult).hasErrors();
        verify(modelMapper).map(form, UserMapperEntity.class);
        verify(userService).getUsers(userEntity);
        verify(model).addAttribute("userList", userList);
        assertEquals("admin/list", viewName); // ユーザー一覧画面を表示することを検証
    }

}
