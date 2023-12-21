package com.example.domainUser.model;

import java.time.LocalDateTime;

//import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class WorkTimeEntity {
	@DateTimeFormat(pattern = "yyyy/MM/dd")
//	private LocalDateTime workDate;
	private String workDate;
	private Integer userId;
//	private LocalDateTime startTime;
//	private LocalDateTime closeTime;
	private String startTime;
	private String closeTime;
	private LocalDateTime genWorkTime;
	private LocalDateTime restTime;
	private LocalDateTime overTime;
	private LocalDateTime actWorkTime;

	
}
