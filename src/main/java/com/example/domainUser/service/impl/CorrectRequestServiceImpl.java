package com.example.domainUser.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domainUser.model.CorrectRequestEntity;
import com.example.domainUser.model.RequestStatesEntity;
import com.example.domainUser.service.CorrectRequestService;
import com.example.repository.CorrectRequestRepository;

@Service
public class CorrectRequestServiceImpl implements CorrectRequestService {
	
	@Autowired
	private CorrectRequestRepository correctrequestrepository;
	
	/**修正申請の登録*/
	public void correctRequestCreate(CorrectRequestEntity correct){
		correctrequestrepository.insertOne(correct);
	}
	
	//勤怠修正申請取得(ユーザー)
	@Override
	public List<CorrectRequestEntity>getCorrectRequests(CorrectRequestEntity correct){
		return correctrequestrepository.findMany(correct);
	}
	
	/**勤怠修正申請取得（ユーザー）*/
	@Override
	public List<CorrectRequestEntity> getUserCorrectRequests(String loginId, RequestStatesEntity requeststates){
		return correctrequestrepository.correctRequestFindMany(loginId, requeststates);
	};
	
	//修正申請取得（1件）
	@Override
	public CorrectRequestEntity getCorrectRequestOne(int correctRequestId) {
		return correctrequestrepository.findOne(correctRequestId);
	}
	
	/**申請ステータス更新(承認済み)*/
	@Override
	public void updateRequestStaApproval(int correctRequestId){
		correctrequestrepository.updateStaApproval(correctRequestId);
	}

	/**申請ステータス更新(差し戻し)*/
	@Override
	public void updateRequestStaRemand(int correctRequestId){
		correctrequestrepository.updateStaRemand(correctRequestId);
	}
	
	/**申請ステータス更新(却下)*/
	@Override
	public void updateRequestStaRemove(int correctRequestId){
		correctrequestrepository.updateStaRemove(correctRequestId);
	}

	/**申請ステータスの取得*/
	@Override
	public List<RequestStatesEntity> getAllRequestStates(){
		return correctrequestrepository.findAllRequestStates();
	}


}
