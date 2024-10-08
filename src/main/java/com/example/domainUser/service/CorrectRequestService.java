package com.example.domainUser.service;

import java.util.Date;
import java.util.List;

import com.example.domainUser.model.CorrectRequestEntity;
import com.example.domainUser.model.RequestStatesEntity;

public interface CorrectRequestService {
	
	/**修正申請の登録*/
	public void correctRequestCreate(CorrectRequestEntity correct);

	/**勤怠修正申請取得(ADMIN)*/
	public List<CorrectRequestEntity> getCorrectRequests(CorrectRequestEntity correct);


	/**修正申請一覧初期表示取得（ユーザー）*/
	public List<CorrectRequestEntity> getUserCorrectRequests(CorrectRequestEntity correct);

	/**有給申請取得検索後(ユーザー）*/
	public List<CorrectRequestEntity> selectUserCorrectRequests(CorrectRequestEntity correct);
	
	//修正申請取得（1件）
	public CorrectRequestEntity getCorrectRequestOne(int correctRequestId);
	
	/**申請ステータス更新(承認済み)*/
	public void updateRequestStaApproval(CorrectRequestEntity correct);

	/**申請ステータス更新(差し戻し)*/
	public void updateRequestStaRemand(CorrectRequestEntity correct);
	
	/**申請ステータス更新(却下)*/
	public void updateRequestStaRemove(CorrectRequestEntity correct);
	
	/**申請ステータス更新(却下)*/
	public void updateUserRequestStaRemove(int correctRequestId);	

	/**申請ステータス更新(再提出)*/
	public void updateRequestStaSubmission(
			int correctRequestId,
			Date correctDate,
			String correctStartTime,
			String correctCloseTime,
			String correctRestTime,
			String correctReason
			);
	
	/**申請ステータスの取得*/
	public List<RequestStatesEntity> getAllRequestStates();
		
}
