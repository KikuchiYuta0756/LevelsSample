package com.example.form;

import java.util.Date;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import com.example.domainUser.model.DepartmentEntity;
import com.example.domainUser.model.PaidEntity;
import com.example.domainUser.model.RoleEntity;
import com.example.validation.ValidPassword;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class UserCreateForm {
	
	@NotBlank(groups = ValidGroup1.class)
	@Length(min = 8, max = 12, groups = ValidGroup2.class)
	@Pattern(regexp ="^[a-zA-Z0-9]+$", groups = ValidGroup2.class)
	private String loginId;
	
	@NotBlank(groups = ValidGroup1.class)
	@Length(min = 7, max = 15, groups = ValidGroup2.class)
    @ValidPassword(groups = ValidGroup2.class)
	private String password;
	
	@NotBlank(groups = ValidGroup1.class)
	private String userName;
	
	@NotBlank(groups = ValidGroup1.class)
	private String userNamekana;
	
	@NotBlank(groups = ValidGroup1.class)
	@Email(groups = ValidGroup2.class)
	private String mailAddress;
	
    private Integer departmentId;
	
	private Integer roleId;
	
	@NotNull(groups = ValidGroup1.class)
	private Integer validation;
	
	@NotNull(groups = ValidGroup1.class)
	private Integer authorityFlg;
	
	@NotNull(groups = ValidGroup1.class)
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private Date hire;
	
	private DepartmentEntity department;
    private RoleEntity role;
	
    private PaidEntity paid;
}
