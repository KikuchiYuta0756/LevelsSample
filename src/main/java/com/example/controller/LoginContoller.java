package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.domainUser.model.UserMapperEntity;
import com.example.domainUser.service.UserService;

@Controller
public class LoginContoller {
	@Autowired
	private UserService userService;

	/**ログイン画面を表示*/
	@GetMapping("/login")
	public String getLogin(){
		
		return "login/login";
	}
	
	/**ユーザー一覧画面にリダイレクト*/
    @PostMapping("/login")
    public String postLogin(){

    	return "redirect:/common/division";

    }    
    
}
