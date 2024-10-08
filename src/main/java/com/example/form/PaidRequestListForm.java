package com.example.form;

import com.example.domainUser.model.RequestStatesEntity;

import lombok.Data;

@Data
public class PaidRequestListForm {
	
	private String paidLoginId;
	private String paidUserName;
	private Integer requestStaId;
	private RequestStatesEntity requestStates;
}
