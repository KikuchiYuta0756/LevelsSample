package com.example.domainUser.service;

import java.util.List;

import com.example.domainUser.model.UserMapperEntity;

public interface UserService {
	
	/**ユーザー登録*/
	//public void signup(UserMapperEntity user);

	/**ユーザー取得*/
	public List<UserMapperEntity> getUsers();
	
	/**ユーザー取得(１件)*/
	public UserMapperEntity getUserOne(String loginId);
	
	/**ユーザー更新（1件）*/
	public void updateUserOne(String loignId, String password, String userName);
	
	/**ユーザー削除（1件）*/
	public void deleteUserOne(String loginId);

}