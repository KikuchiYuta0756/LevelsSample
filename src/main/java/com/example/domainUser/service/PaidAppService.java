package com.example.domainUser.service;

import java.util.Date;
import java.util.List;

import com.example.domainUser.model.PaidAppEntity;

public interface PaidAppService {
	
	/**有給申請登録*/
	public void paidAppCreate(PaidAppEntity paidapp);
	
	/**有給申請取得(Admin)*/
	public List<PaidAppEntity> getPaidRequests(PaidAppEntity paid);
	
	/**有給申請一覧初期表示取得（ユーザー）*/
	public List<PaidAppEntity> getUserPaidRequests(PaidAppEntity paid);
	
	/**有給申請取得検索後(ユーザー）*/
	public List<PaidAppEntity> selectUserPaidRequests(PaidAppEntity paid);
	
	/**有給申請取得(１件)*/
	public PaidAppEntity getPaidAppOne(int paidAppId);
	
	/**申請ステータス更新(承認済み)*/
	public void updateRequestStaApproval(PaidAppEntity paid);

	/**申請ステータス更新(差し戻し)*/
	public void updateRequestStaRemand(PaidAppEntity paid);

	/**申請ステータス更新(却下)*/
	public void updateRequestStaRemove(PaidAppEntity paid);

	/**申請ステータス更新(自己却下)*/
	public void updateUserRequestStaRemove(int paidAppId);
	
	/**申請ステータス更新(再提出)*/
	public void updatePaidRequestStaSubmission(
			int paidAppId,
			Date paidRequestDateApp,
			String paidAppReason
			);	
}
