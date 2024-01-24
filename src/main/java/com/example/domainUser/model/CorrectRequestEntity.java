package com.example.domainUser.model;

import java.sql.Time;
import java.util.Date;

import lombok.Data;

@Data
public class CorrectRequestEntity {
	
	private Integer correctLoginId;
	private String correctUserName;
	private Date correctRequestDate;
	private Integer requestStaId;
	private Integer correctRequestId;
	private Date correctDate;
	private Time correctStartTime;
	private Time correctCloseTime;
	private Time correctRestTime;
	private Integer worktimeStaId;
	private String correctReason;
	private RequestStatesEntity requestStates;
	

}
