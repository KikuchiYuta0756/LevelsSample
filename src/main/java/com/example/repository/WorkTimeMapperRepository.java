package com.example.repository;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.domainUser.model.WorkTimeEntity;
import com.example.domainUser.model.WorkTimeTotalEntity;



@Mapper
public interface WorkTimeMapperRepository {
	
	/**出退勤時刻の登録
	public int insertTime(WorkTimeEntity wtime);*/

	/**出勤時刻の登録*/
	public int insertStartTime(WorkTimeEntity worktime);
	
	/**退勤時刻の登録*/
	public int insertCloseTime(WorkTimeEntity worktime);


	/**残業時間の登録*/
	public int insertOverTime(WorkTimeEntity worktime);
	
	/**勤怠一覧（月次）の取得*/
	public  List<WorkTimeEntity> findMany();

	/**勤怠一覧（月次）の取得（ユーザ毎）*/
	public  List<WorkTimeEntity> findManyOne(String loginId);
	
	
	
	//選択された年月の勤怠一覧を取得
	public  List<WorkTimeEntity> findSelectYearMonth(String selectedYearMonth);

	/**来月の勤怠一覧（月次）の取得*/
	public  List<WorkTimeEntity> findManyNextMonth();

	/**勤怠一覧（月次）の取得*/
	public  WorkTimeTotalEntity totalWorkTime();

	/**勤怠一覧（月次）の取得*/
	public  WorkTimeTotalEntity selectTotalWorkTime(String selectedYearMonth);
	
	/**合計時間（勤怠）の更新*/
	public void updateTotalWorkTime();
	
	/**勤怠（日次）の取得（1件）*/
	public  WorkTimeEntity workTimeOne(String workDate);
	
	
	/**ユーザー更新（1件）*/
	public void updateWorkTimeOne(
			@Param("workDate")String workDate,
			@Param("loginId")String loginId,
			@Param("startTime")String startTime,
			@Param("closeTime")String closeTime,
			@Param("restTime")LocalTime restTime
	        );



	
}
