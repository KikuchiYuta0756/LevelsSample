package com.example.domainUser.service;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;

import com.example.domainUser.model.UserMapperEntity;
import com.example.domainUser.model.WorkTimeEntity;
import com.example.domainUser.model.WorkTimeTotalEntity;

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
	public List<WorkTimeEntity> getClockTimes(String loginId);
	
	//勤怠一覧（月次）の取得（ユーザー毎）
	public List<WorkTimeEntity> getCorrectClockTimes(String loginId);

	
	//選択された年月の勤怠一覧を取得
	public List<WorkTimeEntity> getSelectYearMonth(String loginId, String selectedYearMonth);


	//来月の勤怠一覧（月次）の取得
	public List<WorkTimeEntity> getClockTimesNextMonth();

	//勤怠（月次）の各合計を取得
	public WorkTimeTotalEntity getworkTimesTotal(String loginId);

	//選択した勤怠（月次）の各合計を取得
	public WorkTimeTotalEntity getSelectWorkTimesTotal(String loginId, String selectedYearMonth);

	
	//合計時間（勤怠）の更新
	public void updateTotalWorkTime(String loginId);

	/**勤怠情報の取得(１件)*/
	public WorkTimeEntity getWorkTimeOne(String loginId, String workDate);
	
	/**勤怠情報の更新（1件）*/
	public void updateWorkTimeOne(
			String workDate,
			String loginId, 
			String startTime, 
			String closeTime, 
			LocalTime restTime
			);


	
}
