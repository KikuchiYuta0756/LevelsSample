package com.example.form;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.example.domainUser.model.AuthorityEntity;
import com.example.domainUser.model.DepartmentEntity;
import com.example.domainUser.model.PaidAppEntity;
import com.example.domainUser.model.PaidEntity;
import com.example.domainUser.model.RoleEntity;

import lombok.Data;

@Data
public class UserDetailForm {
	//private Integer userId;
	private String loginId;
	private String password;
	private String userName;
	private String userNamekana;
	private String mailAddress;	
	private Integer departmentId;
	private Integer roleId;
	private Integer validation;

	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private Date hire;	
	
	private Integer authorityFlg;
	private Integer workFlg;
	private DepartmentEntity department;
    private RoleEntity role;
}
