package com.example.domainUser.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domainUser.model.DepartmentEntity;
import com.example.domainUser.model.UserMapperEntity;
import com.example.domainUser.model.RoleEntity;
import com.example.domainUser.service.UserService;
import com.example.repository.UserMapperRepository;

@Service
public class UserServiceImpl implements UserService{	
	@Autowired
	private UserMapperRepository usermapper;
	
	/**ユーザー登録*/
	@Override
	public void userCreate(UserMapperEntity user) {
		user.setDepartmentId(1); //部署
		user.setRoleId(1); //ロール
		usermapper.insertOne(user);
	}
	
	/**ユーザー取得*/
	@Override
	public List<UserMapperEntity> getUsers(){
		return usermapper.findMany();
	}
	/**ユーザー取得(１件)*/
	@Override
	public UserMapperEntity getUserOne(String loginId) {
		return usermapper.findOne(loginId);
	}
	
	/**ユーザー更新（1件）*/
	@Override
	public void updateUserOne(String loginId, String password, String userName, String userNamekana, String mailAddress) {
		usermapper.updateOne(loginId, password, userName, userNamekana, mailAddress);
	}
	
	/**ユーザー削除（1件）*/
	@Override
	public void deleteUserOne(String loginId) {
		int count = usermapper.deleteOne(loginId);
	}
	
	/**部署の取得*/
	@Override
	public List<DepartmentEntity> getAllDepartment(){
	    return usermapper.findAll();
	}
	
	/**役職の取得*/
	@Override
	public List<RoleEntity> getAllRole(){
	    return usermapper.findAll2();
	}
}
