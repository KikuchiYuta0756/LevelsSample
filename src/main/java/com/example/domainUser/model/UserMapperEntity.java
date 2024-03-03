package com.example.domainUser.model;

import java.util.Date;
import java.util.List;

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
	private Integer validation;
    private Date hire;
    private Integer authority;
    private Integer workFlg;
	private DepartmentEntity department;
    private RoleEntity role;
    private List<PaidAppEntity> paidAppList;
    private PaidEntity paid;
}