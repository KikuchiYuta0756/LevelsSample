package com.example.domainUser.model;

import java.util.Date;

import lombok.Data;

@Data
public class UserMapperEntity {
    private String loginId;
	private String password;
	private String userName;
	private String userNamekana;
	private String mailAddress;
	private Integer departmentId;	
	private Integer roleId;
	private Integer gender;
	//private Integer validation;
    private Date hire;
	private DepartmentEntity department;
    private RoleEntity role;
}