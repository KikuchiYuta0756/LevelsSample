package com.example.form;


import lombok.Data;

@Data
public class UserCreateForm {
	private String userId;
	private String password;
	private String userName;
	private String userNamekana;
	private String department;
	private String role;
	
	private Integer gender;
	private Integer validationMAp;
}
