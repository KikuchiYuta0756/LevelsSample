package com.example.form;

//import com.example.domainUser.model.DepartmentEntity;

import lombok.Data;

@Data
public class UserDetailForm {
	private String loginId;
	private String password;
	private String userName;
	private String userNamekana;
	private String mailAddress;
	//private DepartmentEntity departmentId;
	private String roleId;
	private Integer gender;
	private Integer validation;
}
