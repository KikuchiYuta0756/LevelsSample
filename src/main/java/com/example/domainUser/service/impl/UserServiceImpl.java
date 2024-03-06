package com.example.domainUser.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.domainUser.model.DepartmentEntity;
import com.example.domainUser.model.PaidAppEntity;
import com.example.domainUser.model.UserMapperEntity;
import com.example.domainUser.model.RoleEntity;
import com.example.domainUser.service.UserService;
import com.example.repository.UserMapperRepository;

@Service
public class UserServiceImpl implements UserService{	
	@Autowired
	private UserMapperRepository usermapper;

	@Autowired
	private PasswordEncoder encoder;
	
	/**ユーザー登録*/
	@Override
	public void userCreate(UserMapperEntity user) {
		user.setDepartmentId(1); //部署
		user.setRoleId(1); //ロール
		
		//パスワード暗号化
		String rawPassword = user.getPassword();
		user.setPassword(encoder.encode(rawPassword));
		
		
		usermapper.insertOne(user);
	}
	
	/**ユーザー取得*/
	@Override
	public List<UserMapperEntity> getUsers(UserMapperEntity user){
		return usermapper.findMany(user);
	}
	/**ユーザー取得(１件)*/
	@Override
	public UserMapperEntity getUserOne(String loginId) {
		return usermapper.findOne(loginId);
	}	
	
	
	/**ユーザー更新（1件）*/
	@Override
	public void updateUserOne(
			String loginId, 
			String password, 
			String userName, 
			String userNamekana, 
			String mailAddress,
			Integer departmentId,
			Integer roleId,
			Integer validation,
			Integer authority,
			Date hire
			) 
	   {
		String encryptPassword = encoder.encode(password);
		usermapper.updateOne(
				loginId, 
				encryptPassword, 
				userName, 
				userNamekana, 
				mailAddress,
				departmentId,
				roleId,
				validation,
				authority,
				hire
				);
		
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
	
	/**打刻画面用　出退勤フラグの取得*/
	@Override
	public UserMapperEntity getWorkFlg() {
		return usermapper.findWorkFlg();
	}
	
	/**打刻画面用　出退勤フラグの更新（退勤ボタンを活性化）*/
	@Override
	public void getWorkFlgLeaving() {
	    usermapper.updateWorkFlgLeaving();
	}
	
	/**打刻画面用　出退勤フラグの更新（退勤ボタンを活性化）*/
	@Override
	public void getWorkFlgAttendance() {
		usermapper.updateWorkFlgAttendance();
	}



}
