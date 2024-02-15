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

	//残業時間の登録
	public void overTimeSignup(WorkTimeEntity worktime);
	
	//勤怠一覧（月次）の取得
	public List<WorkTimeEntity> getClockTimes();
	
	//前月の勤怠一覧（月次）の取得
	public List<WorkTimeEntity> getClockTimesLastMonth();

	//来月の勤怠一覧（月次）の取得
	public List<WorkTimeEntity> getClockTimesNextMonth();

	//勤怠（月次）の各合計を取得
	public WorkTimeEntity getClockTimesSum();

	
}
