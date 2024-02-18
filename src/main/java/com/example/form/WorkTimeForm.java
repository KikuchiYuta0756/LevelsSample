package com.example.form;

import java.time.LocalTime;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
@Data
public class WorkTimeForm {
		private String workDate;
		private Integer dayofweekId;
		private Integer userId;
		@DateTimeFormat(pattern = "hh:mm")
		private String startTime;
		@DateTimeFormat(pattern = "hh:mm")
		private String closeTime;
		
		@DateTimeFormat(pattern = "hh:mm")
	    private LocalTime genWorkTime;

		@DateTimeFormat(pattern = "hh:mm")
		private LocalTime restTime;
		
		@DateTimeFormat(pattern = "hh:mm")
		private LocalTime actWorkTime;

		@DateTimeFormat(pattern = "hh:mm")
		private LocalTime overTime;
	

}
