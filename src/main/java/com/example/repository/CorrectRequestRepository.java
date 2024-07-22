package com.example.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.domainUser.model.CorrectRequestEntity;
import com.example.domainUser.model.RequestStatesEntity;

@Mapper
public interface CorrectRequestRepository {
	
	/**修正申請の登録*/
	public int insertOne(CorrectRequestEntity correct);

	
	//勤怠修正申請取得
	public List<CorrectRequestEntity>findMany(CorrectRequestEntity correct);
	
	//修正申請取得（１件）
	public CorrectRequestEntity findOne(int correctRequestId);
	
	/**申請ステータス更新(承認)*/
	public  void updateStaApproval(
			@Param("correctRequestId")int correctRequestId);
	
	/**申請ステータス更新(差し戻し)*/
	public  void updateStaRemand(
			@Param("correctRequestId")int correctRequestId);
	
	/**申請ステータス更新(差し戻し)*/
	public  void updateStaRemove(
			@Param("correctRequestId")int correctRequestId);

	//勤怠修正申請取得（ユーザー）
	public List<CorrectRequestEntity>userFindMany(String correctLoginId);
	
	/**申請ステータスの取得*/
	public List<RequestStatesEntity>findAllRequestStates();

}
