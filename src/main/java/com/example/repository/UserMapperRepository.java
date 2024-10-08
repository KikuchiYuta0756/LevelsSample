package com.example.repository;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.domainUser.model.DepartmentEntity;
import com.example.domainUser.model.PaidAppEntity;
import com.example.domainUser.model.PaidEntity;
import com.example.domainUser.model.RoleEntity;
import com.example.domainUser.model.UserMapperEntity;


@Mapper
public interface UserMapperRepository {
	
	/**ユーザー登録*/
	public void insertOne(UserMapperEntity user);
	
	/**ユーザー取得（有効)*/
	public List<UserMapperEntity>findValidationUsers(UserMapperEntity user);

	/**ユーザー取得(無効)*/
	public List<UserMapperEntity>findNotValidationUsers(UserMapperEntity user);
	
	/**ユーザー取得（1件）*/
	public UserMapperEntity findOne(String loginId);
	
	/**ユーザー取得（1件）*/
	public UserMapperEntity findUserOne(String loginId);


	/**ログイン認証ユーザー取得（1件）*/
	public UserMapperEntity getLoginUser(String loginId);

	
	/**ユーザー取得（1件）*/
	public PaidAppEntity paidRequestFindOne(String loginId);

	
	/**ユーザー更新（1件）*/
	public void updateOne(
			@Param("loginId")String loginId,
			@Param("encryptPassword")String encryptPassword,
			@Param("userName")String userName,
			@Param("userNamekana")String userNamekana,
			@Param("mailAddress")String mailAddress,
	        @Param("departmentId")Integer departmentId,
	        @Param("roleId")Integer roleId,
	        @Param("validation")Integer validation,
	        @Param("authority")Integer authority,
            @Param("hire")Date hire);

	/**ログインユーザパスワード更新（1件）*/
	public void updatePasswordOne(
			@Param("loginId")String loginId,
			@Param("encryptPassword")String encryptPassword);
			
			
	/**ユーザー削除（1件）*/
	public int deleteOne(@Param("loginId")String loginId);
	
	/**部署の取得*/
	public List<DepartmentEntity>findAll();

	/**役職の取得*/
	public List<RoleEntity>findAll2();

	/**出退勤フラグの取得*/
	public UserMapperEntity findWorkFlg(String loginId);
	
	/**退勤フラグの活性化（1件）*/
	public void updateWorkFlgLeaving(String loginId);
	
	
	/**退勤フラグの活性化（1件）*/
	public void updateWorkFlgAttendance(String loginId);
	
	/**ユーザー登録*/
	public int insertUserPaidCreate(UserMapperEntity user);

	//初回の有給付与処理
	public void giveFirstPaidDays();

	//次回以降の有給付与処理
	public void updateGivePaidDays();

    //有給日数を取得
	public PaidEntity getPaidDays(String loginId);
	
	
}
