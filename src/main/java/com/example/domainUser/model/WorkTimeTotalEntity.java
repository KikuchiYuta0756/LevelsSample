package com.example.domainUser.model;

import java.time.LocalTime;

import lombok.Data;

@Data
public class WorkTimeTotalEntity {
	private String workDateMonth;
	private String loginId;
	private Integer workDateTotal;
	private LocalTime actWorkTimeTotal;
	private LocalTime overTimeTotal;
}
