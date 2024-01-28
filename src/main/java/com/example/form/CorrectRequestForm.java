package com.example.form;

import java.sql.Time;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class CorrectRequestForm {
	
	private Integer correctLoginId;
	private String correctUserName;
	
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private Date correctRequestDate;
	
	private String requestStaId;
	private Integer correctRequestId;
	
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private String correctDate;

	@DateTimeFormat(pattern = "HH:MM")
	private String correctStartTime;
	
	@DateTimeFormat(pattern = "HH:MM")
	private String correctCloseTime;
	
	@DateTimeFormat(pattern = "HH:MM")
	private String correctRestTime;
	
	private Integer worktimeStaId;
	
	private String correctReason;

}
