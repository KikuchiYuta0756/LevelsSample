package com.example.domainUser.model;

//import java.text.SimpleDateFormat;
//import java.time.LocalDateTime;

import lombok.Data;

@Data
public class WorkTimeEntity {
	private Integer worktimeId;
	private String workDate;
	private Integer userId;
	private String startTime;
	private String closeTime;
	private Integer workTime;
	private Integer restTime;
	private Integer overTime;
	
}
