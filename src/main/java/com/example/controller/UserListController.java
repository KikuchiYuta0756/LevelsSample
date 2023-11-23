package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domainUser.model.UserMapperEntity;
import com.example.domainUser.service.UserService;

@Controller
@RequestMapping("/admin")
public class UserListController {

	@Autowired
	private UserService userService;

	/** ユーザー一覧画面を表示 */
	@GetMapping("/list")
	public String getUserList(Model model) {

		// ユーザー一覧取得
		List<UserMapperEntity> userList = userService.getUsers();

		// Modelに登録
		model.addAttribute("userList", userList);

		// ユーザー一覧画面を表示
		return "admin/list";
	}
}
