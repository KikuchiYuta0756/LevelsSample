package com.example.domainUser.service;

import java.util.List;

import com.example.domainUser.model.UserMapperEntity;

public interface UserService {
	
	/**ユーザー登録*/
	public void userCreate(UserMapperEntity user);

	/**ユーザー取得*/
	public List<UserMapperEntity> getUsers();
	
	/**ユーザー取得(１件)*/
	public UserMapperEntity getUserOne(String loginId);
	
	/**ユーザー更新（1件）*/
	public void updateUserOne(String loignId, String password, String userName, String userNamekana, String mailAddress);
	
	/**ユーザー削除（1件）*/
	public void deleteUserOne(String loginId);
	
	/**部署の取得*/
	public List<UserMapperEntity> getAllDepartment();
	
	/**役職の取得*/
	//public List<UserMapperEntity> getAllRole();

}