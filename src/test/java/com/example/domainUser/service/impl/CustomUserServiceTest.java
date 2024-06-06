package com.example.domainUser.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.domainUser.model.UserMapperEntity;
import com.example.repository.UserMapperRepository;

@ExtendWith(MockitoExtension.class)
class CustomUserServiceTest {

    @Mock
    private UserMapperRepository userMapperRepository;

    @InjectMocks
    private CustomUserService customUserService;

    @Test
    void testGetUserByUsername() {
        // モックの設定
        String loginId = "testUser";
        UserMapperEntity expectedUser = new UserMapperEntity(/* ユーザー情報を設定 */);
        when(userMapperRepository.getLoginUser(loginId)).thenReturn(expectedUser);
        
        // テスト対象メソッドの実行
        UserMapperEntity actualUser = customUserService.getUserByUsername(loginId);
        
        // 期待値と実際の値の比較
        assertEquals(expectedUser, actualUser);
        
        // モックのメソッドが正しく呼ばれたか検証
        verify(userMapperRepository, times(1)).getLoginUser(loginId);
    }

}
