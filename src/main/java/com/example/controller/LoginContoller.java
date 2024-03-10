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
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        //ログイン認証に使用したログインIDを利用する。
//        String usersloginId = auth.getName();
//        
//        System.out.println("コントロールクラスのusersloginIdは"+ usersloginId);
//        
//		//ログイン認証ユーザーの情報を取得
//		UserMapperEntity users = userService.getLoginUser(usersloginId);
//		Integer userAuthority = users.getAuthorityFlg();
//		System.out.println("コントロールクラスのuserAuthorityは"+ userAuthority);
//        
//        if (userAuthority == 2) {
//            return "redirect:/admin/clockInADM"; // ADMINの場合は/admin/clockInADMにリダイレクト
//        } else {
//            return "redirect:/user/clockIn"; // ADMIN以外の場合は/user/clockInにリダイレクト
//        }
        // モデルにユーザー情報をセットしてトップ画面に渡す
        //model.addAttribute("username", username);
		return "redirect:/common/TopPage";

    }
        
//	/**ログイン認証ユーザのを表示*/
//	@PostMapping("/loginToHome")
//	public String getLoginToHome(){
//		
//		return "redirect:/admin/list";
//	}
    
    
}
