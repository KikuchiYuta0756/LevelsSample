package com.example.domainUser.model;

import java.time.LocalTime;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class WorkTimeTotalEntity {
	private String workDateMonth;
	private Integer loginId;
	private Integer workDateTotal;
	private LocalTime actWorkTimeTotal;
	private LocalTime overTimeTotal;



}
