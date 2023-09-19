package com.example.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.domainUser.model.UserMapperEntity;
import com.example.domainUser.service.UserService;
import com.example.form.UserDetailForm;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/user")
@Slf4j
public class UserDetailController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	/**ユーザー詳細画面を表示*/
	@GetMapping("/userDetail/{userId:.+}")
	public String getUser(UserDetailForm form,Model model,
			@PathVariable("loginId")String loginId) {
		
		//ユーザーを1件取得
		UserMapperEntity user = userService.getUserOne(loginId);
		user.setPassword(null);
		
		//UserMapperEntityをformに変換
		form = modelMapper.map(user, UserDetailForm.class);
		
		//Modelに登録
		model.addAttribute("userDetailForm",form);
			
		//ユーザー詳細画面を表示
		return"user/detail";		
	}
}
