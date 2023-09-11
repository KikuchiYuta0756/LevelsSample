package com.example.repository;

import org.apache.ibatis.annotations.Mapper;
import com.example.domainUser.model.UserMapperEntity;

@Mapper
public interface UserMapperRepository {
	
	/**ユーザー登録*/
	public int insertOne(UserMapperEntity user);

}
