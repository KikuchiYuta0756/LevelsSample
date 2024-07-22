package com.example.domainUser.model;

import java.util.Date;

import lombok.Data;

@Data
public class CorrectRequestEntity {

	private String correctLoginId;
	private String correctUserName;
	private Date correctRequestDate;
	private Integer requestStaId;
	private String requestStaName;
	private Integer correctRequestId;
	private Date correctDate;
	private String correctStartTime;
	private String correctCloseTime;
	private String correctRestTime;
	private String worktimeStaId;
	private String correctReason;
	private RequestStatesEntity requestStates;
}
