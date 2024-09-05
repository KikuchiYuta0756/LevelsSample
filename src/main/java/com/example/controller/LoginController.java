package com.example.controller;

import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

	/**ログイン画面を表示*/
	@GetMapping("/login")
	public String getLogin(){
		
		return "login/login";
	}
	
	/**TopPageControllerにレスポンス*/
    @PostMapping("/login")
    public String postLogin(){

    	return "redirect:/common/division";
    }    
    
	// 認証エラーが発生した場合の処理
	@PostMapping("/login/error")
	public String loginError(Model model, AuthenticationException exception) {
		model.addAttribute("errorMessage", exception.getMessage());
		return "login/login";
	}

    
}
