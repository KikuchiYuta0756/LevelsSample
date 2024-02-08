package com.example.domainUser.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.example.domainUser.model.WorkTimeEntity;
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

	//労働時間の登録
	@Override
	public void genWorkTimeSignup(WorkTimeEntity worktime) {
		worktimemapper.insertGenWorkTime(worktime);
	}

	//実働時間の登録
	@Override
	public void actWorkTimeSignup(WorkTimeEntity worktime) {
		worktimemapper.insertActWorkTime(worktime);
	}

	//残業時間の登録
	@Override
	public void overTimeSignup(WorkTimeEntity worktime) {
		worktimemapper.insertOverTime(worktime);
	}
	
	//勤怠一覧（月次）の取得
	@Override
	public List<WorkTimeEntity> getClockTimes(){
		return worktimemapper.findMany();
	}
	
	//前月の勤怠一覧（月次）の取得
	@Override
	public List<WorkTimeEntity> getClockTimesLastMonth(){
		return worktimemapper.findManyLastMonth();
	}

	//来月の勤怠一覧（月次）の取得
	@Override
	public List<WorkTimeEntity> getClockTimesNextMonth(){
		return worktimemapper.findManyNextMonth();
	}

	//勤怠（月次）の各合計を取得
	@Override
	public WorkTimeEntity getClockTimesSum(){
		return worktimemapper.sumWorkTime();
	}

	
	
}
