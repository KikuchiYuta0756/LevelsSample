package com.example.domainUser.service;

import java.util.Date;
import java.util.List;

import com.example.domainUser.model.DepartmentEntity;
import com.example.domainUser.model.PaidEntity;
import com.example.domainUser.model.RequestStatesEntity;
import com.example.domainUser.model.RoleEntity;
import com.example.domainUser.model.UserMapperEntity;

public interface UserService {
	
	/**ユーザー登録*/
	public void userCreate(UserMapperEntity user);

	/**ユーザー取得（有効) */
	public List<UserMapperEntity> findValidationUsers(UserMapperEntity user);

	/**ユーザー取得（無効) */
	public List<UserMapperEntity> findNotValidationUsers(UserMapperEntity user);
	
	/**ユーザー取得(１件：パスワードなし)*/
	public UserMapperEntity getUserOne(String loginId);	

	/**ユーザー取得(１件：パスワードあり)*/
	public UserMapperEntity getFindUserOne(String loginId);	
		
	
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

	/**ログインユーザパスワード更新（1件）*/
	public void updatePasswordOne(
			String loignId, 
			String password);

	
	/**ユーザー削除（1件）*/
	public void deleteUserOne(String loginId);
	
	/**部署の取得*/
	public List<DepartmentEntity> getAllDepartment();
	
	/**役職の取得*/
	public List<RoleEntity> getAllRole();
		
	
	/**打刻画面用　出退勤フラグの取得*/
	public UserMapperEntity getWorkFlg(String loginId);
	
	/**打刻画面用　出退勤フラグの更新（退勤ボタンを活性化）*/
	public void getWorkFlgLeaving(String loginId);
	
	/**打刻画面用　出退勤フラグの更新（出勤ボタンを活性化）*/
	public void getWorkFlgAttendance(String loginId);

	/**有給テーブルに新規ログインIDを作成*/
	public void userPaidCreate(UserMapperEntity user);
	
	/**初回の従業員に有給を付与する*/
	public void giveFirstPaidDays();
	
	/**次回以降の従業員に有給を付与する*/
	public void updateGivePaidDays();
	
	//有給日数を取得
    public PaidEntity getPaidDays(String loginId);
}