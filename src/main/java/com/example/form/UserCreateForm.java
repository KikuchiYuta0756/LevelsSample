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

import lombok.Data;

@Data
public class UserCreateForm {
	
	@NotBlank
	private String loginId;
	
	@NotBlank
	@Length(min = 7, max = 100)
	@Pattern(regexp ="^[a-zA-Z0-9]+$")
	private String password;
	
	@NotBlank
	private String userName;
	
	@NotBlank
	private String userNamekana;
	
	@NotBlank
	@Email
	private String mailAddress;
	
	@NotNull
    private Integer departmentId;
	
	@NotNull
	private Integer roleId;
	
	@NotNull
	private Integer validation;
	
	@NotNull
	private Integer authority;
	
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	@NotNull
	private Date hire;
}
