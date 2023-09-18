package com.example.domainUser.model;

import lombok.Data;

@Data
public class UserMapperEntity {
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