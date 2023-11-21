package com.example.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.domainUser.model.UserMapperEntity;
import com.example.domainUser.service.UserService;
import com.example.form.UserDetailForm;

@Controller
@RequestMapping("/admin")
public class UserDetailController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	/**ユーザー詳細画面を表示*/
@GetMapping("/userDetail/{loginId}")
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
		return"admin/userDetail";		
	}

/**ユーザー更新処理*/
@PostMapping(value = "/userDetail", params = "update")
public String updateUser(UserDetailForm form, Model model){
	
	//ユーザーを更新
	userService.updateUserOne(form.getLoginId(),
			form.getPassword(),
			form.getUserName(),
			form.getUserNamekana(),
			form.getMailAddress()
			);
	
	//ユーザー一覧画面にリダイレクト
	return"redirect:/admin/list";
}

/**ユーザー削除処理*/
@PostMapping(value = "/userDetail", params = "delete")
public String deleteUser(UserDetailForm form, Model model){
	
	//ユーザーを削除
	userService.deleteUserOne(form.getLoginId());
	
	//ユーザー一覧画面にリダイレクト
	return "redirect:/admin/list";
}
}