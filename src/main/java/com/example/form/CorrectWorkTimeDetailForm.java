package com.example.form;

import java.time.LocalTime;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class CorrectWorkTimeDetailForm {
	private String workDate;
	private Integer dayofweekId;
	private String loginId;
	@DateTimeFormat(pattern = "hh:mm")
	private LocalTime startTime;
	@DateTimeFormat(pattern = "hh:mm")
	private LocalTime closeTime;
	
	@DateTimeFormat(pattern = "hh:mm")
    private LocalTime genWorkTime;

	@DateTimeFormat(pattern = "hh:mm")
	private LocalTime restTime;
	
	@DateTimeFormat(pattern = "hh:mm")
	private LocalTime actWorkTime;

	@DateTimeFormat(pattern = "hh:mm")
	private LocalTime overTime;

}
