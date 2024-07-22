package com.example.form;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PaidRequestForm {
	
	private String paidLoginId;
	private String paidUserName;
	
	
	
	@NotNull(groups = ValidGroup1.class)
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private Date paidRequestDateApp;
	
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private Date paidDateApp;
	
	private Integer paidAppId;
	private Integer requestStaId;
	
	@NotNull(groups = ValidGroup1.class)
	@Size(min = 1, max = 200, groups = ValidGroup2.class)
	private String paidAppReason;

}
