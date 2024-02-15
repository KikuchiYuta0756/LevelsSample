package com.example.form;

import java.util.Date;
import java.util.List;

import com.example.domainUser.model.PaidAppEntity;

import lombok.Data;

@Data
public class RequestRecodeForm {
    private String loginId;
	private String password;
	private String userName;
	private String userNamekana;
	private String mailAddress;
	private Integer departmentId;
	private Integer roleId;
	private Integer validation;
    private Date hire;
    private Integer authority;
    private List<PaidAppEntity> paidAppList;

	
}
