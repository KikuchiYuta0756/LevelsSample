package com.example.domainUser.model;

import java.util.Date;

import lombok.Data;

@Data
public class PaidAppEntity {
	
	private Integer paidLoginId;
	private String paidUserName;
	private Date paidRequestDateApp;	
	private Date paidDateApp;
	private Integer paidAppId;
	private String requestStaId;
	private String paidAppReason;
	private RequestStatesEntity requestStates;

}
