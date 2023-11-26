package com.example.repository;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.domainUser.model.UserMapperEntity;
import com.example.domainUser.model.DepartmentEntity;
import com.example.domainUser.model.RoleEntity;
//import com.example.domainUser.model.WorkTimeEntity;


@Mapper
public interface UserMapperRepository {
	
	/**ユーザー登録*/
	public int insertOne(UserMapperEntity user);
	
	/**出退勤時刻の登録*/
	//public int insertTime(WorkTimeEntity wtime);
	
	/**ユーザー取得*/
	public List<UserMapperEntity>findMany();
	
	/**ユーザー取得（1件）*/
	public UserMapperEntity findOne(String loginId);

	/**ユーザー更新（1件）*/
	public void updateOne(
			@Param("loginId")String loginId,
			@Param("password")String password,
			@Param("userName")String userName,
			@Param("userNamekana")String userNamekana,
			@Param("mailAddress")String mailAddress,
	        @Param("department")DepartmentEntity department,
	        @Param("role")RoleEntity role,
	        @Param("validation")Integer validation,
	        @Param("authority")Integer authority,
            @Param("hire")Date hire);
	
	/**ユーザー削除（1件）*/
	public int deleteOne(@Param("loginId")String loginId);
	
	/**部署の取得*/
	public List<DepartmentEntity>findAll();

	/**役職の取得*/
	public List<RoleEntity>findAll2();

	
}
