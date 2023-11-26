package com.example.form;

import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class UserCreateForm {
	private String loginId;
	private String password;
	private String userName;
	private String userNamekana;
	private String mailAddress;
    private Integer departmentId;
	private Integer roleId;
	private Integer validation;
	private Integer authority;
	
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private Date hire;
}
