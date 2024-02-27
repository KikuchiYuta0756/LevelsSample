package com.example.form;

import java.sql.Time;
import java.time.LocalTime;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class WorkTimeTotalForm {
	private String workDateMonth;
	private String loginId;
	private Integer workDateTotal;
	
	private String actWorkTimeTotal;
	
	@DateTimeFormat(pattern = "hh:mm")
	private LocalTime overTimeTotal;

}
