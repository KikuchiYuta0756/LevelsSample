package com.example.form;

import com.example.domainUser.model.RequestStatesEntity;

import lombok.Data;

@Data
public class CorrectListForm {
		private String correctLoginId;
		private String correctUserName;
		private Integer requestStaId;
		private RequestStatesEntity requestStates;
}
