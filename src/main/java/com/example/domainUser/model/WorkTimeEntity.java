package com.example.domainUser.model;

import java.time.LocalTime;

import lombok.Data;

@Data
public class WorkTimeEntity {
	private String workDate;
	private String loginId;
	private String dayofweekId;
	private String startTime;
	private String closeTime;
	private LocalTime genWorkTime;
	private LocalTime restTime;
	private LocalTime actWorkTime;
	private LocalTime overTime;
	private String remarks;	
}
