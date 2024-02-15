package com.example.repository;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;

import com.example.domainUser.model.WorkTimeEntity;



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
	
	/**前月の勤怠一覧（月次）の取得*/
	public  List<WorkTimeEntity> findManyLastMonth();

	/**来月の勤怠一覧（月次）の取得*/
	public  List<WorkTimeEntity> findManyNextMonth();

	/**勤怠一覧（月次）の取得*/
	public  WorkTimeEntity sumWorkTime();
	
	
}
