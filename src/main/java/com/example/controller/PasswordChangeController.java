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
import com.example.form.UserDetailForm;

import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
@RequestMapping("/common")
public class PasswordChangeController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	//パスワード変更認証画面の表示
	@GetMapping("/afterPasswordChange")
	public String getAfterPasswordChange(UserDetailForm form, Model model) {
      Authentication auth = SecurityContextHolder.getContext().getAuthentication();
      //ログイン認証に使用したログインIDを利用する。
      String loginId = auth.getName();
      System.out.println("usersloginIdは"+ loginId);

      //ログイン認証ユーザーの情報を取得
      UserMapperEntity loginUser = userService.getUserOne(loginId);
      
	  //UserMapperEntityをformに変換
      form = modelMapper.map(loginUser, UserDetailForm.class);
		
      //Modelに登録
      model.addAttribute("passwordChangeForm",form);
		
	  return "common/afterPasswordChange";
	}
	
	//パスワード変更認証画面の表示
	@PostMapping("/afterPasswordChange")
	public String postAfterPasswordChange(UserDetailForm form, Model model) {
		
		//パスワードの更新
		userService.updatePasswordOne(
				form.getLoginId(),
				form.getPassword());
		
		return "common/TopPage";
		
	}
	
	
}
