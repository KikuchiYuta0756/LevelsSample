package com.example.domainUser.service;

import java.util.Date;
import java.util.List;

import com.example.domainUser.model.PaidAppEntity;
import com.example.domainUser.model.UserMapperEntity;

public interface PaidAppService {
	
	/**有給申請登録*/
	public void paidAppCreate(PaidAppEntity paidapp);
	
	/**有給申請取得*/
	public List<PaidAppEntity> getPaidRequests();
	
	/**有給申請取得(１件)*/
	public PaidAppEntity getPaidAppOne(int paidAppId);
	
	/**申請ステータス更新*/
	public void updateRequestStaApproval(int paidAppId);

	/**申請ステータス更新*/
	public void updateRequestStaRemand(int paidAppId);


}
