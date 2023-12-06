package com.example.repository;

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
	

	
}
