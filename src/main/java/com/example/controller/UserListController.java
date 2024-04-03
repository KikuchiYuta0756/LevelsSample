package com.example.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domainUser.model.UserMapperEntity;
import com.example.domainUser.service.UserService;
import com.example.form.UserCreateForm;
import com.example.form.UserListForm;

@Controller
@RequestMapping("/admin")
public class UserListController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private ModelMapper modelMapper;

	/** ユーザー一覧画面を表示 */
	@GetMapping("/list")
	public String getUserList(Model model) {
		
		UserListForm userListForm =new UserListForm();
	    //userListFormを初期化
		model.addAttribute("userListForm", userListForm);
		
//		//入力チェック結果
//		if(bindingResult.hasErrors()) {
//		  //NG：ユーザー登録画面に戻る
//			return getUserList(model,form);
//		}		

		//formをUserMapperEntityクラスに変換
		UserMapperEntity user = modelMapper.map(userListForm, UserMapperEntity.class);

		// ユーザー一覧取得
		List<UserMapperEntity> userList = userService.getUsers(user);

		// Modelに登録
		model.addAttribute("userList", userList);

		// ユーザー一覧画面を表示
		return "admin/list";
	}

	/** ユーザー検索処理 */
	@PostMapping("/list")
	public String postUserList(UserListForm userListForm, Model model) {
		
		//formをUserMapperEntityクラスに変換
		UserMapperEntity user = modelMapper.map(userListForm, UserMapperEntity.class);
		
		// ユーザー一覧取得
		List<UserMapperEntity> userList = userService.getUsers(user);

		// Modelに登録
		model.addAttribute("userList", userList);

		// ユーザー一覧画面を表示
		return "admin/list";
	}


}
