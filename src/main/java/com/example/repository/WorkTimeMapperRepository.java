package com.example.repository;

import java.time.LocalTime;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.domainUser.model.WorkTimeEntity;
import com.example.domainUser.model.WorkTimeTotalEntity;



@Mapper
public interface WorkTimeMapperRepository {


	/**出勤時刻の登録*/
	public int insertStartTime(WorkTimeEntity worktime);
	
	/**退勤時刻の登録*/
	public int insertCloseTime(WorkTimeEntity worktime);


	/**残業時間の登録*/
	public int insertOverTime(WorkTimeEntity worktime);
	
	/**勤怠一覧（月次）の取得*/
	public  List<WorkTimeEntity> findMany(String loginId);

	/**勤怠一覧（月次）の取得（ユーザ毎）*/
	public  List<WorkTimeEntity> findManyOne(String loginId);
	
	
	
	//選択された年月の勤怠一覧を取得
	public  List<WorkTimeEntity> findSelectYearMonth(String loginId, String selectedYearMonth);

	/**来月の勤怠一覧（月次）の取得*/
	public  List<WorkTimeEntity> findManyNextMonth();

	/**勤怠一覧（月次）の取得*/
	public  WorkTimeTotalEntity totalWorkTime(String loginId);

	/**勤怠一覧（月次）の取得*/
	public  WorkTimeTotalEntity selectTotalWorkTime(String loginId, String selectedYearMonth);
	
	/**合計時間（勤怠）の更新*/
	public void updateTotalWorkTime(String loginId);
	
	/**勤怠（日次）の取得（1件）*/
	public  WorkTimeEntity workTimeOne(String loginId, String workDate);
	
	
	/**ユーザー更新（1件）*/
	public void updateWorkTimeOne(
			@Param("workDate")String workDate,
			@Param("loginId")String loginId,
			@Param("startTime")String startTime,
			@Param("closeTime")String closeTime,
			@Param("restTime")LocalTime restTime
	        );
	
	/**勤怠テーブルに新規ユーザの登録*/
	public void insertUserWorkTimeCreate(String loginId);

	/**勤怠テーブルに新規ユーザの登録*/
	public void insertUserWorkTimeTotalCreate(String loginId);

	//選択された年月の勤怠一覧を取得
	public  List<WorkTimeEntity> findSelectCorrectYearMonth(String loginId, String selectedYearMonth);
	
	
	
}
