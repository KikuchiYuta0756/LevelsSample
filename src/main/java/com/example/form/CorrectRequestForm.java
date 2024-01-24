package com.example.form;

import java.sql.Time;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class CorrectRequestForm {
	
	private Integer correctLoginId;
	private String correctUserName;
	
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private Date correctRequestDate;
	
	private Integer requestStaId;
	private Integer correctRequestId;
	
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private Date correctDate;

	@DateTimeFormat(pattern = "HH:MM:ss")
	private Time correctStartTime;
	
	@DateTimeFormat(pattern = "HH:MM:ss")
	private Time correctCloseTime;
	
	@DateTimeFormat(pattern = "HH:MM:ss")
	private Time correctRestTime;
	
	private Integer worktimeStaId;
	
	private String correctReason;

}
