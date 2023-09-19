package com.example.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import com.example.domainUser.model.UserMapperEntity;

@Mapper
public interface UserMapperRepository {
	
	/**ユーザー登録*/
	//public int insertOne(UserMapperEntity user);
	
	/**ユーザー取得*/
	public List<UserMapperEntity>findMany();
	
	/**ユーザー取得（1件）*/
	public UserMapperEntity findOne(String loginId);

}
