package com.example.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.domainUser.model.PaidAppEntity;

@Mapper
public interface PaidAppRepository {
	
	/**有給申請登録*/
	public int insertPaidApp(PaidAppEntity paidapp);
	
	/**有給申請取得(Admin)*/
	public List<PaidAppEntity>findMany(PaidAppEntity paid);
	
	/**有給申請取得初期表示（ユーザー）*/
	public List<PaidAppEntity>getUserPaidRequest(PaidAppEntity paid);

	/**有給申請取得検索後（ユーザー）*/
	public List<PaidAppEntity>selectUserPaidRequests(PaidAppEntity paid);
	
	/**有給申請取得（1件）*/
	public PaidAppEntity findOne(int paidAppId);
	
	/**申請ステータス更新(承認)*/
	public  void updateStaApproval(PaidAppEntity paid);

	/**申請ステータス更新(差し戻し)*/
	public  void updateStaRemand(
			@Param("paidAppId")int paidAppId);
	
	/**申請ステータス更新(差し戻し)*/
	public  void updateStaRemove(
			@Param("paidAppId")int paidAppId);
	
}
