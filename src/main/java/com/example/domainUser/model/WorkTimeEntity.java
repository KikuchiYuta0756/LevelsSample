package com.example.domainUser.model;

//import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class WorkTimeEntity {
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	//private LocalDateTime workDate;
	private String workDate;
	private Integer userId;
//	private LocalDateTime startTime;
//	private LocalDateTime closeTime;
	private String startTime;
	private String closeTime;
	private Integer workTime;
	private Integer restTime;
	private Integer workFlg;
	private Integer overTime;
	
}
