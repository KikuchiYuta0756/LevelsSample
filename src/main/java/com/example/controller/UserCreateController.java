package com.example.controller;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.application.service.UserApplicationService;

@Controller
@RequestMapping("/admin")
public class UserCreateController {

	@Autowired
	private UserApplicationService userApplicationService;

	/** ユーザー登録画面を表示 */
	@GetMapping("/create")
	public String getUserCreate(Model model) {

		// 性別を取得
		Map<String, Integer> genderMap = userApplicationService.getGenderMap();
		model.addAttribute("genderMap", genderMap);

		// ユーザー登録画面に遷移
		return "admin/create";
	}
	    /**ユーザー登録処理*/
	@PostMapping("/create")
	public String postUserCreate(){
		//利用者一覧画面にリダイレクト
		return "redirect:/list";
	}
}
