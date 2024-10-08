package com.example.form;

import java.time.LocalTime;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.example.domainUser.model.WorkTimeEntity;

import lombok.Data;

@Data
public class CorrectWorkTimeForm {
	private String workDate;
	private Integer dayofweekId;
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
	
	private List<WorkTimeEntity> models;

}
