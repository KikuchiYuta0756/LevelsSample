package com.example.domainUser.service;

import java.util.List;

import com.example.domainUser.model.UserMapperEntity;

public interface UserService {
	
	/**ユーザー登録*/
	//public void signup(UserMapperEntity user);

	/**ユーザー取得*/
	public List<UserMapperEntity> getUsers();
	
	/**ユーザー取得(１件)*/
	public UserMapperEntity getUserOne(String userId);
}