package com.example.form;

import org.hibernate.validator.constraints.Length;

import com.example.validation.ValidPassword;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PasswordChangeForm {

	private String loginId;
	
	@NotBlank(groups = ValidGroup1.class)
	@Length(min = 7, max = 15, groups = ValidGroup2.class)
	@ValidPassword(groups = ValidGroup2.class)
	private String password;
	
	private String userName;
	private String userNamekana;

}
