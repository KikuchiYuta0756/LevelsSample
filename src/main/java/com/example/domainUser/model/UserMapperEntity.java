package com.example.domainUser.model;

import java.util.Date;
import lombok.Data;

@Data
public class UserMapperEntity {
	private String password;
	private String userName;
	private Date birthday;
	private Integer age;
	private Integer gender;
	private Integer departmentId;
	private String role;

}
