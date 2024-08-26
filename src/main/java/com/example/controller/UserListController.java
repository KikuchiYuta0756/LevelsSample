package com.example.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domainUser.model.UserMapperEntity;
import com.example.domainUser.service.UserService;
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
	public String getUserList(Model model, UserListForm form) {
				
		//formをUserMapperEntityクラスに変換
		UserMapperEntity user = modelMapper.map(form, UserMapperEntity.class);

		// ユーザー一覧取得
		List<UserMapperEntity> userList = userService.findValidationUsers(user);

		// Modelに登録
		model.addAttribute("userList", userList);

		// ユーザー一覧画面を表示
		return "admin/list";
	}

	/** ユーザー検索処理 */
	@PostMapping("/list")
	public String postUserList(UserListForm form
			, Model model
			, BindingResult bindingResult) {
		
		List<UserMapperEntity> userList;
		
		//入力チェック結果
		if(bindingResult.hasErrors()) {
		  //NG：ユーザー登録画面に戻る
			return getUserList(model,form);
		}		
		
		//formをUserMapperEntityクラスに変換
		UserMapperEntity user = modelMapper.map(form, UserMapperEntity.class);
		
        // チェックボックスが選択されているかどうかを確認
        if (user.getValidation() != null && user.getValidation() == 2) {
            // validationが2のユーザーを取得
        	userList = userService.findNotValidationUsers(user);
        } else {
            // 全てのユーザーを取得（フィルタリングなし）
        	userList = userService.findValidationUsers(user);
        }

		// Modelに登録
		model.addAttribute("userList", userList);

		// ユーザー一覧画面を表示
		return "admin/list";
	}


}
