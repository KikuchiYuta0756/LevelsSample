package com.example.domainUser.service.impl;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domainUser.model.PaidAppEntity;
import com.example.domainUser.model.UserMapperEntity;
import com.example.domainUser.model.WorkTimeEntity;
import com.example.domainUser.model.WorkTimeTotalEntity;
import com.example.domainUser.service.WorkTimeService;
import com.example.repository.WorkTimeMapperRepository;

@Service
public class WorkTimeServiceImpl implements WorkTimeService{
	
	@Autowired
	private WorkTimeMapperRepository worktimemapper;
	
	//出勤時間の登録
	@Override
	public void startTimeSignup(WorkTimeEntity worktime) {
		worktimemapper.insertStartTime(worktime);
	}

	//退勤時間の登録
	@Override
	public void closeTimeSignup(WorkTimeEntity worktime) {
		worktimemapper.insertCloseTime(worktime);
	}

	//残業時間の登録
	@Override
	public void overTimeSignup(WorkTimeEntity worktime) {
		worktimemapper.insertOverTime(worktime);
	}
	
	//勤怠一覧（月次）の取得
	@Override
	public List<WorkTimeEntity> getClockTimes(String loginId){
		return worktimemapper.findMany(loginId);
	}
	
	//勤怠一覧（月次）の取得（ユーザ毎）
	public List<WorkTimeEntity> getCorrectClockTimes(String loginId){
		return worktimemapper.findManyOne(loginId);
	}
	
	//選択された年月の勤怠一覧を表示する
	@Override
	public List<WorkTimeEntity> getSelectYearMonth(String loginId, String selectedYearMonth){
		return worktimemapper.findSelectYearMonth(loginId, selectedYearMonth);
	}

	//来月の勤怠一覧（月次）の取得
	@Override
	public List<WorkTimeEntity> getClockTimesNextMonth(){
		return worktimemapper.findManyNextMonth();
	}

	//勤怠（月次）の各合計を取得
	@Override
	public WorkTimeTotalEntity getworkTimesTotal(String loginId){
		return worktimemapper.totalWorkTime(loginId);
	}

	//選択した勤怠（月次）の各合計を取得
	@Override
	public WorkTimeTotalEntity getSelectWorkTimesTotal(String loginId, String selectedYearMonth){
		return worktimemapper.selectTotalWorkTime(loginId, selectedYearMonth);
	}
	
	//合計時間（勤怠）の更新
	@Override
	public void updateTotalWorkTime(String loginId) {
		worktimemapper.updateTotalWorkTime(loginId);
	}

	/**勤怠（日次）の取得(１件)*/
	public WorkTimeEntity getWorkTimeOne(String loginId, String workDate) {
		return worktimemapper.workTimeOne(loginId, workDate);
	}
	
	/**ユーザー更新（1件）*/
	@Override
	public void updateWorkTimeOne(
				String workDate,
				String loginId, 
				String startTime, 
				String closeTime, 
				LocalTime restTime
					)
	   {
		worktimemapper.updateWorkTimeOne(
				workDate,
				loginId, 
				startTime, 
				closeTime, 
				restTime 
				);
		}
	
	/**勤怠テーブルに新規ユーザの登録*/
	public void userWorkTimeCreate(String loginId) {
		worktimemapper.insertUserWorkTimeCreate(loginId);
	}

	//勤怠合計テーブルに新規ユーザの年月毎のレコードを作成する
	public void userWorkTimeTotalCreate(String loginId) {
		worktimemapper.insertUserWorkTimeTotalCreate(loginId);
	}

	//選択された年月の勤怠一覧を取得
	public List<WorkTimeEntity> getSelectCorrectYearMonth(String loginId, String selectedYearMonth){
		return worktimemapper.findSelectCorrectYearMonth(loginId, selectedYearMonth);
	}

	//承認された有給使用の日付更新
	public void updateWorkTimeRemarks(PaidAppEntity paid) {
		worktimemapper.updateWorkTimeRemarks(paid);
	}


	
}
