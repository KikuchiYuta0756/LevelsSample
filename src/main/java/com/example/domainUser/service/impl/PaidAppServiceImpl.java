package com.example.domainUser.service.impl;

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
	/**有給申請取得*/
	@Override
	public List<PaidAppEntity> getPaidRequests(){
		return paidapprepository.findMany();		
	}
	
	/**有給申請取得(１件)*/
	@Override
	public PaidAppEntity getPaidAppOne(int paidAppId) {
		return paidapprepository.findOne(paidAppId);	
	}
	
	/**申請ステータス更新*/
	@Override
	public void updateRequestStaOne(int paidAppId){
		paidapprepository.updateRequestSta(paidAppId);
	}


}
