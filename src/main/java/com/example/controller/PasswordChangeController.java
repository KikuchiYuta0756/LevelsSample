package com.example.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
@RequestMapping("/login")
public class PasswordChangeController {
	
	//パスワード変更認証画面の表示
	@GetMapping("/beforePasswordChange")
	public String getBeforePasswordChange() {
		
		return "login/beforePasswordChange";
		
	}
	

	//パスワード変更認証画面の表示
	@PostMapping("/beforePasswordChange")
	public String postBeforePasswordChange() {
		
		return "login/afterPasswordChange";
		
	}

    
	//パスワード変更認証画面の表示
	@GetMapping("/afterPasswordChange")
	public String getAfterPasswordChange() {
		
		return "login/afterPasswordChange";
		
	}
	
	//パスワード変更認証画面の表示
	@PostMapping("/afterPasswordChange")
	public String postAfterPasswordChange() {
		
		return "login/login";
		
	}
	
	
}
