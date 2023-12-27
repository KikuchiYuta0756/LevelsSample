package com.example.domainUser.service;

import java.util.List;

import com.example.domainUser.model.WorkTimeEntity;

public interface WorkTimeService {
	
	/**勤怠時間の登録
	public void worktimeSignup(WorkTimeEntity wtime);*/

	/**出勤時間の登録*/
	public void startTimeSignup(WorkTimeEntity worktime);
	
	//退勤時間の登録
	public void closeTimeSignup(WorkTimeEntity worktime);

	//労働時間の登録
	public void genWorkTimeSignup(WorkTimeEntity worktime);

	//実働時間の登録
	public void actWorkTimeSignup(WorkTimeEntity worktime);

	//残業時間の登録
	public void overTimeSignup(WorkTimeEntity worktime);
	
	//勤怠一覧（月次）の取得
	public List<WorkTimeEntity> getClockTimes();
	
	
}
