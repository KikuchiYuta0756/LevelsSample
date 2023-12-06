package com.example.domainUser.service;

import com.example.domainUser.model.WorkTimeEntity;

public interface WorkTimeService {
	
	/**勤怠時間の登録
	public void worktimeSignup(WorkTimeEntity wtime);*/

	/**出勤時間の登録*/
	public void worktimeSignup(WorkTimeEntity worktime);
	
	//退勤時間の登録
	public void worktimeupdate(WorkTimeEntity worktime);


	
}
