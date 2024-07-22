package com.example.domainUser.service;

import java.util.List;

import com.example.domainUser.model.CorrectRequestEntity;
import com.example.domainUser.model.RequestStatesEntity;
import com.example.domainUser.model.UserMapperEntity;

public interface CorrectRequestService {
	
	/**修正申請の登録*/
	public void correctRequestCreate(CorrectRequestEntity correct);

	/**勤怠修正申請取得*/
	public List<CorrectRequestEntity> getCorrectRequests(CorrectRequestEntity correct);
	
	//修正申請取得（1件）
	public CorrectRequestEntity getCorrectRequestOne(int correctRequestId);
	
	/**申請ステータス更新(承認済み)*/
	public void updateRequestStaApproval(int correctRequestId);

	/**申請ステータス更新(差し戻し)*/
	public void updateRequestStaRemand(int correctRequestId);
	
	/**申請ステータス更新(差し戻し)*/
	public void updateRequestStaRemove(int correctRequestId);

	/**勤怠修正申請取得（ユーザー）*/
	public List<CorrectRequestEntity> getUserCorrectRequests(String correctLoginId);
	
	/**申請ステータスの取得*/
	public List<RequestStatesEntity> getAllRequestStates();
		
}
