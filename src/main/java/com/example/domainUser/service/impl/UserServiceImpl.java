package com.example.domainUser.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domainUser.model.UserMapperEntity;
import com.example.domainUser.service.UserService;
import com.example.repository.UserMapperRepository;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserMapperRepository usermapper;
	
	/**ユーザー登録*/
	@Override
	public void signup(UserMapperEntity user) {
		user.setDepartmentId(1);//部署
		user.setRole("ROLE_GENERAL");//ロール
		usermapper.insertOne(user);
	}
}
