package com.example.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domainUser.model.UserMapperEntity;
import com.example.domainUser.service.UserService;

@Controller
@RequestMapping("/common")
public class TopPageController {
	@Autowired
	private UserService userService;
	
	@Autowired
	private ModelMapper modelMapper;


	/**ログイン画面を表示*/
	@GetMapping("/TopPage")
	public String getLogin(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
      //ログイン認証に使用したログインIDを利用する。
      String usersloginId = auth.getName();
      
      System.out.println("トップページのusersloginIdは"+ usersloginId);
      
		//ログイン認証ユーザーの情報を取得
		UserMapperEntity users = userService.getLoginUser(usersloginId);
		Integer userAuthority = users.getAuthorityFlg();
		System.out.println("トップページのuserAuthorityは"+ userAuthority);
		
		// Modelに登録
		model.addAttribute("userAuthority", userAuthority);


		
		return "common/TopPage";
	}
	
//	/**ユーザー一覧画面にリダイレクト*/
//    @PostMapping("/TopPage")
//    public String postLogin(){
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        //ログイン認証に使用したログインIDを利用する。
//        String usersloginId = auth.getName();
//        
//        System.out.println("トップページのusersloginIdは"+ usersloginId);
//        
//		//ログイン認証ユーザーの情報を取得
//		UserMapperEntity users = userService.getLoginUser(usersloginId);
//		Integer userAuthority = users.getAuthorityFlg();
//		System.out.println("トップページのuserAuthorityは"+ userAuthority);
//        
//        if (userAuthority == 2) {
//            return "redirect:/admin/clockInADM"; // ADMINの場合は/admin/clockInADMにリダイレクト
//        } else {
//            return "redirect:/user/clockIn"; // ADMIN以外の場合は/user/clockInにリダイレクト
//        }
        // モデルにユーザー情報をセットしてトップ画面に渡す
        //model.addAttribute("username", username);

//    }
        
}
