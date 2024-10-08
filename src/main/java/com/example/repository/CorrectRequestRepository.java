package com.example.repository;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.domainUser.model.CorrectRequestEntity;
import com.example.domainUser.model.RequestStatesEntity;

@Mapper
public interface CorrectRequestRepository {
	
	/**修正申請の登録*/
	public int insertOne(CorrectRequestEntity correct);

	
	//勤怠修正申請一覧取得(Admin)
	public List<CorrectRequestEntity>findMany(CorrectRequestEntity correct);
	//修正申請取得初期表示（ユーザー）
	public List<CorrectRequestEntity>getUserCorrectRequest(CorrectRequestEntity correct);

	//修正申請取得検索後（ユーザー）
	public List<CorrectRequestEntity>selectUserCorrectRequests(CorrectRequestEntity correct);
	
	//申請の詳細取得（１件）
	public CorrectRequestEntity findOne(int correctRequestId);	
	
	/**申請ステータス更新(承認)*/
	public  void updateStaApproval(CorrectRequestEntity correct);
	
	/**申請ステータス更新(差し戻し)*/
	public  void updateStaRemand(CorrectRequestEntity correct);
	
	/**申請ステータス更新(却下)*/
	public  void updateStaRemove(CorrectRequestEntity correct);
	
	/**申請ステータス更新(却下)*/
	public  void updateUserStaRemove(int correctRequestId);	

	/**申請ステータス更新(再提出)*/
	public  void updateStaSubmission(
			@Param("correctRequestId")int correctRequestId,
			@Param("correctDate")Date correctDate,
			@Param("correctStartTime")String correctStartTime,
			@Param("correctCloseTime")String correctCloseTime,
			@Param("correctRestTime")String correctRestTime,
			@Param("correctReason")String correctReason
			
			);
	
	/**申請ステータスの取得*/
	public List<RequestStatesEntity>findAllRequestStates();

}
