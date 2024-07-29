package com.example.form;

import com.example.domainUser.model.RequestStatesEntity;

import lombok.Data;

@Data
public class RequestRecordListForm {
	
	private String paidLoginId;
	private Integer requestStaId;
	private RequestStatesEntity requestStates;


}
