package com.example.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.domainUser.model.PaidAppEntity;

@Mapper
public interface PaidAppRepository {
	
	/**有給申請登録*/
	public int insertPaidApp(PaidAppEntity paidapp);
	
	/**有給申請取得*/
	public List<PaidAppEntity>findMany();
	
	/**有給申請取得（1件）*/
	public PaidAppEntity findOne(int paidAppId);
	


}
