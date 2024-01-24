package com.example.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.domainUser.model.CorrectRequestEntity;
import com.example.domainUser.model.UserMapperEntity;

@Mapper
public interface CorrectRequestRepository {
	
	/**修正申請の登録*/
	public int insertOne(CorrectRequestEntity correct);

	
	//勤怠修正申請取得
	public List<CorrectRequestEntity> findMany();
	
	//修正申請取得（１件）
	public CorrectRequestEntity findOne(int correctRequestId);

}
