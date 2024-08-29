package com.example.form;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.example.domainUser.model.RequestStatesEntity;
import com.example.validation.ValidPaidDate;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PaidRequestForm {
	
	private String paidLoginId;
	private String paidUserName;
	
	
	
	@NotNull(groups = ValidGroup1.class)
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	@ValidPaidDate (groups = ValidGroup2.class)
	private Date paidRequestDateApp;
	
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private Date paidDateApp;
	
	private Integer paidAppId;
	private Integer requestStaId;
	private String requestStaName;
	
	@NotNull(groups = ValidGroup1.class)
	@Size(min = 1, max = 200, groups = ValidGroup2.class)
	private String paidAppReason;
	private String rejectReason;
	private RequestStatesEntity requestStates;

}
