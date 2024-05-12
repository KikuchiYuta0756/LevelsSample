package com.example.domainUser.model;

import java.sql.Time;
import java.time.LocalTime;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class WorkTimeTotalEntity {
	private String workDateMonth;
	private String loginId;
	private Integer workDateTotal;
	private LocalTime actWorkTimeTotal;
	private LocalTime overTimeTotal;
}
