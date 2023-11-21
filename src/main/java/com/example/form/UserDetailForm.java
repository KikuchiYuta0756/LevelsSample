package com.example.form;

import java.util.Date;

import com.example.domainUser.model.DepartmentEntity;
import com.example.domainUser.model.RoleEntity;

import lombok.Data;

@Data
public class UserDetailForm {
	private String loginId;
	private String password;
	private String userName;
	private String userNamekana;
	private String mailAddress;
	private Integer gender;
	private Integer validation;
	private DepartmentEntity department;
	private RoleEntity role;
	private Date hire;
}
