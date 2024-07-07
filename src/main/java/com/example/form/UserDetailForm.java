package com.example.form;

import java.util.Date;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import com.example.domainUser.model.DepartmentEntity;
import com.example.domainUser.model.RoleEntity;
import com.example.validation.ValidPassword;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserDetailForm {
	
	private String loginId;
	
	@NotBlank(groups = ValidGroup1.class)
	@Length(min = 7, max = 15, groups = ValidGroup2.class)
	@ValidPassword(groups = ValidGroup2.class)
	private String password;
	
	private String userName;
	private String userNamekana;
	
	@Email(groups = ValidGroup1.class)
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
