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
	
	/**労働時間の登録*/
	public int insertGenWorkTime(WorkTimeEntity worktime);
	
	/**実働時間の登録*/
	public int insertActWorkTime(WorkTimeEntity worktime);

	/**残業時間の登録*/
	public int insertOverTime(WorkTimeEntity worktime);
	
	/**勤怠一覧（月次）の取得*/
	public  List<WorkTimeEntity> findMany();

}
