package com.example.form;

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
	private Integer gender;
	private Integer validation;
}
