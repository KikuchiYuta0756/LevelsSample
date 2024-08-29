package com.example.domainUser.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domainUser.model.PaidAppEntity;
import com.example.domainUser.service.PaidAppService;
import com.example.repository.PaidAppRepository;

@Service
public class PaidAppServiceImpl implements PaidAppService {
	@Autowired
	private  PaidAppRepository paidapprepository;
	
	/**有給申請登録*/
	@Override
	public void paidAppCreate(PaidAppEntity paidapp) {
		paidapprepository.insertPaidApp(paidapp);
	}
	/**有給申請取得(Admin)*/
	@Override
	public List<PaidAppEntity> getPaidRequests(PaidAppEntity paid){
		return paidapprepository.findMany(paid);
	}
	
	/**有給申請取得初期表示用（ユーザー）*/
	@Override
	public List<PaidAppEntity> getUserPaidRequests(PaidAppEntity paid){
	return paidapprepository.getUserPaidRequest(paid);
	}
	
	/**有給申請取得検索後（ユーザー）*/
	@Override
	public List<PaidAppEntity> selectUserPaidRequests(PaidAppEntity paid){
	return paidapprepository.selectUserPaidRequests(paid);
	}	
		
	/**有給申請取得(１件)*/
	@Override
	public PaidAppEntity getPaidAppOne(int paidAppId) {
		return paidapprepository.findOne(paidAppId);	
	}
	
	/**申請ステータス更新(承認済み)*/
	@Override
	public void updateRequestStaApproval(PaidAppEntity paid){
		paidapprepository.updateStaApproval(paid);
	}

	/**申請ステータス更新(差し戻し)*/
	@Override
	public void updateRequestStaRemand(PaidAppEntity paid){
		paidapprepository.updateStaRemand(paid);
	}

	/**申請ステータス更新(却下)*/
	@Override
	public void updateRequestStaRemove(PaidAppEntity paid){
		paidapprepository.updateStaRemove(paid);
	}	

	/**申請ステータス更新(自己却下)*/
	@Override
	public void updateUserRequestStaRemove(int paidAppId){
		paidapprepository.updateUserStaRemove(paidAppId);
	}	
	
	/**申請ステータス更新(再提出)*/
	@Override
	public void updatePaidRequestStaSubmission(
			int paidAppId,
			Date paidRequestDateApp,
			String paidAppReason
			){
		paidapprepository.updatePaidStaSubmission(
				 paidAppId,
				 paidRequestDateApp,
				 paidAppReason
				);
	}	
	
}
