package com.example.domainUser.service.impl;

import java.util.Date;
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
	
	/**修正申請取得初期表示用（ユーザー）*/
	@Override
	public List<CorrectRequestEntity> getUserCorrectRequests(CorrectRequestEntity correct){
		return correctrequestrepository.getUserCorrectRequest(correct);
	};

	
	/**修正申請取得検索後（ユーザー）*/
	@Override
	public List<CorrectRequestEntity> selectUserCorrectRequests(CorrectRequestEntity correct){
		return correctrequestrepository.selectUserCorrectRequests(correct);
	};
	
	//修正申請取得（1件）
	@Override
	public CorrectRequestEntity getCorrectRequestOne(int correctRequestId) {
		return correctrequestrepository.findOne(correctRequestId);
	}
	
	/**申請ステータス更新(承認済み)*/
	@Override
	public void updateRequestStaApproval(CorrectRequestEntity correct){
		correctrequestrepository.updateStaApproval(correct);
	}

	/**申請ステータス更新(差し戻し)*/
	@Override
	public void updateRequestStaRemand(CorrectRequestEntity correct){
		correctrequestrepository.updateStaRemand(correct);
	}
	
	/**申請ステータス更新(却下)*/
	@Override
	public void updateRequestStaRemove(CorrectRequestEntity correct){
		correctrequestrepository.updateStaRemove(correct);
	}

	/**申請ステータス更新(自己却下)*/
	@Override
	public void updateUserRequestStaRemove(int correctRequestId){
		correctrequestrepository.updateUserStaRemove(correctRequestId);
	}

	/**申請ステータス更新(再提出)*/
	@Override
	public void updateRequestStaSubmission(
			int correctRequestId,
			Date correctDate,
			String correctStartTime,
			String correctCloseTime,
			String correctRestTime,
			String correctReason
			){
		correctrequestrepository.updateStaSubmission(
				correctRequestId,
				correctDate,
				correctStartTime,
				correctCloseTime,
				correctRestTime,
				correctReason
				);
	}	
	
	/**申請ステータスの取得*/
	@Override
	public List<RequestStatesEntity> getAllRequestStates(){
		return correctrequestrepository.findAllRequestStates();
	}


}
