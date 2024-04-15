package com.example.domainUser.model;

import java.time.LocalTime;

import lombok.Data;

@Data
public class WorkTimeEntity {
	private String workDate;
	private String dayofweekId;
	private String loginId;
	private String startTime;
	private String closeTime;
	private LocalTime genWorkTime;
	private LocalTime restTime;
	private LocalTime overTime;
	private LocalTime actWorkTime;
}
