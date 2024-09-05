package com.example.form;

import java.time.LocalTime;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class WorkTimeTotalForm {
	private String workDateMonth;
	private String loginId;
	private Integer workDateTotal;

	@DateTimeFormat(pattern = "hh:mm")	
	private LocalTime actWorkTimeTotal;
	
	@DateTimeFormat(pattern = "hh:mm")
	private LocalTime overTimeTotal;

}
