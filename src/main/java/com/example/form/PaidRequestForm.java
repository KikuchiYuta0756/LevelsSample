package com.example.form;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class PaidRequestForm {
	
	private Integer paidLoginId;
	private String paidUserName;
	
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private Date paidRequestDateApp;
	
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private Date paidDateApp;
	
	private Integer paidAppId;
	private String requestStaId;
	private String paidAppReason;

}
