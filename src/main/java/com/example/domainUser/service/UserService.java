package com.example.domainUser.service;

import java.util.Date;
import java.util.List;

import com.example.domainUser.model.UserMapperEntity;
import com.example.domainUser.model.DepartmentEntity;
import com.example.domainUser.model.PaidAppEntity;
import com.example.domainUser.model.RoleEntity;

public interface UserService {
	
	/**ユーザー登録*/
	public void userCreate(UserMapperEntity user);

	/**ユーザー取得 */
	public List<UserMapperEntity> getUsers(UserMapperEntity user);
	
	/**ユーザー取得(１件)*/
	public UserMapperEntity getUserOne(String loginId);

	/**ログインユーザーの権限情報取得(１件)*/
	public UserMapperEntity getLoginUser(String loginId);
	
	
	
	/**ユーザー更新（1件）*/
	public void updateUserOne(
			String loignId, 
			String password, 
			String userName, 
			String userNamekana, 
			String mailAddress,
			Integer departmentId,
			Integer roleId,
			Integer validation,
			Integer authority,
			Date hire
			);
	
	/**ユーザー削除（1件）*/
	public void deleteUserOne(String loginId);
	
	/**部署の取得*/
	public List<DepartmentEntity> getAllDepartment();
	
	/**役職の取得*/
	public List<RoleEntity> getAllRole();
	
	
	/**打刻画面用　出退勤フラグの取得*/
	public UserMapperEntity getWorkFlg();
	
	/**打刻画面用　出退勤フラグの更新（退勤ボタンを活性化）*/
	public void getWorkFlgLeaving();
	
	/**打刻画面用　出退勤フラグの更新（出勤ボタンを活性化）*/
	public void getWorkFlgAttendance();

	

}