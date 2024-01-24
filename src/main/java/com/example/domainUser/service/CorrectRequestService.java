package com.example.domainUser.service;

import java.util.List;

import com.example.domainUser.model.CorrectRequestEntity;
import com.example.domainUser.model.UserMapperEntity;

public interface CorrectRequestService {
	
	/**修正申請の登録*/
	public void correctRequestCreate(CorrectRequestEntity correct);

	/**勤怠修正申請取得*/
	public List<CorrectRequestEntity> getCorrectRequests();
	
	//修正申請取得（1件）
	public CorrectRequestEntity getCorrectRequestOne(int correctRequestId);

}