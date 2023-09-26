package com.example.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.domainUser.model.UserMapperEntity;

@Mapper
public interface UserMapperRepository {
	
	/**ユーザー登録*/
	//public int insertOne(UserMapperEntity user);
	
	/**ユーザー取得*/
	public List<UserMapperEntity>findMany();
	
	/**ユーザー取得（1件）*/
	public UserMapperEntity findOne(String loginId);

	/**ユーザー更新（1件）*/
	public void updateOne(@Param("loginId")String loginId,
			@Param("password")String password,
			@Param("userName")String userName);
	
	/**ユーザー削除（1件）*/
	public int deleteOne(@Param("loginId")String loginId);

}
