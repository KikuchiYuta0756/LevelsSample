package com.example.form;

import java.util.Date;

import jakarta.validation.constraints.Email;
//import jakarta.validation.constraints.Max;
//import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;


import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import com.example.domainUser.model.PaidEntity;

import lombok.Data;

@Data
public class UserCreateForm {
	
	@NotNull//(groups = ValidGroup1.class)
	private Integer loginId;
	
	@NotBlank//(groups = ValidGroup1.class)
	@Length(min = 7, max = 100)
//	@Length(min = 7, max = 100, groups = ValidGroup2.class)
//	@Pattern(groups = ValidGroup2.class,regexp ="^[a-zA-Z0-9]+$")
	private String password;
	
	@NotBlank//(groups = ValidGroup1.class)
	private String userName;
	
	@NotBlank//(groups = ValidGroup1.class)
	private String userNamekana;
	
	@NotBlank//(groups = ValidGroup1.class)
	private String mailAddress;
	
    private Integer departmentId;
	
	private Integer roleId;
	
	@NotNull//(groups = ValidGroup1.class)
	private Integer validation;
	
	@NotNull//(groups = ValidGroup1.class)
	private Integer authorityFlg;
	
	@NotNull(groups = ValidGroup1.class)
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private Date hire;
	
    private PaidEntity paid;
}
