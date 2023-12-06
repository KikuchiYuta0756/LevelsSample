package com.example.domainUser.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.example.domainUser.model.WorkTimeEntity;
import com.example.domainUser.service.WorkTimeService;
import com.example.repository.WorkTimeMapperRepository;

@Service
public class WorkTimeServiceImpl implements WorkTimeService{
	
	@Autowired
	private WorkTimeMapperRepository worktimemapper;
	
	
	
	/**勤怠時間の登録　旧
	//@Override
	public void worktimeSignup(WorkTimeEntity wtime) {
		worktimemapper.insertTime(wtime);
	}*/
	
	/**勤怠時間の登録　新*/
	@Override
	public void worktimeSignup(WorkTimeEntity worktime) {
		worktimemapper.insertStartTime(worktime);
	}


}
