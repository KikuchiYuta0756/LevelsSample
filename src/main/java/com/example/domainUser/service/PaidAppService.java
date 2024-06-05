package com.example.domainUser.service;

import java.util.List;

import com.example.domainUser.model.PaidAppEntity;

public interface PaidAppService {
	
	/**有給申請登録*/
	public void paidAppCreate(PaidAppEntity paidapp);
	
	/**有給申請取得*/
	public List<PaidAppEntity> getPaidRequests(PaidAppEntity paid);
	
	/**有給申請取得(１件)*/
	public PaidAppEntity getPaidAppOne(int paidAppId);
	
	/**申請ステータス更新*/
	public void updateRequestStaApproval(int paidAppId);

	/**申請ステータス更新*/
	public void updateRequestStaRemand(int paidAppId);

	/**有給申請取得（ユーザー）*/
	public List<PaidAppEntity> getUserPaidRequests(String paidLoginId);

}
