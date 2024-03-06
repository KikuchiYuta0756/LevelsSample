package com.example.domainUser.service.impl;

import org.springframework.stereotype.Service;

import com.example.domainUser.model.UserMapperEntity;
import com.example.repository.UserMapperRepository;

@Service
public class CustomUserService {

	//UserMapperRepositoryをインスタンス化
    private final UserMapperRepository userMapperRepository;

    //UserMapperRepositoryの変数を初期化
    public CustomUserService(UserMapperRepository userMapperRepository) {
        this.userMapperRepository = userMapperRepository;
    }
    
    //ログイン認証ユーザ取得（１件）
    public UserMapperEntity getUserByUsername(String loginId) {
        return userMapperRepository.getLoginUser(loginId);
    }

}
