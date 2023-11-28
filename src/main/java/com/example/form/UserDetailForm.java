package com.example.form;

import java.util.Date;

import com.example.domainUser.model.DepartmentEntity;
import com.example.domainUser.model.RoleEntity;

import lombok.Data;

@Data
public class UserDetailForm {
	private Integer userId;
	private String loginId;
	private String password;
	private String userName;
	private String userNamekana;
	private String mailAddress;	
	private DepartmentEntity department;
	private RoleEntity role;
	private Integer validation;
	private Integer authority;
	private Date hire;
}
