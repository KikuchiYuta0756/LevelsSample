package com.example.domainUser.model;

import java.util.Date;

import lombok.Data;

@Data
public class PaidAppEntity {

	private String paidLoginId;
	private String paidUserName;
	private Date paidRequestDateApp; // 申請日
	private Date paidDateApp;
	private Integer paidAppId;
	private Integer requestStaId;
	private String requestStaName;
	private String paidAppReason;
	private String rejectReason;	
	private RequestStatesEntity requestStates;

}
