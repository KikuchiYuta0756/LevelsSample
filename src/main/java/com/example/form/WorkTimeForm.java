package com.example.form;

import java.time.LocalTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.example.domainUser.model.DayOfWeekEntity;

import lombok.Data;
@Data
public class WorkTimeForm {
		private String workDate;
		private String dayofweekId;
		private String loginId;
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
