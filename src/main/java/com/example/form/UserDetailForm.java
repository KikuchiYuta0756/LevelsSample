package com.example.form;

import lombok.Data;

@Data
public class UserDetailForm {
	private String loginId;
	private String password;
	private String userName;
	private String userNamekana;
	private String mailAddress;
	private String department;
	private String role;
	private Integer gender;
	private Integer validation;

}
