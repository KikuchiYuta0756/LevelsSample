package com.example.controller;

import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.application.service.UserApplicationService;
import com.example.domainUser.model.DepartmentEntity;
import com.example.domainUser.model.RoleEntity;
import com.example.domainUser.model.UserMapperEntity;
import com.example.domainUser.service.UserService;
import com.example.form.GroupOrder;
import com.example.form.UserDetailForm;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/admin")
@Slf4j
public class UserDetailController {
	
	@Autowired
	private UserApplicationService userApplicationService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	/**ユーザー詳細画面を表示*/
@GetMapping("/userDetail/{loginId}")
public String getUserDetail(Model model, UserDetailForm form,
		@PathVariable("loginId")String loginId) {
	
		//ユーザーを1件取得
		UserMapperEntity user = userService.getUserOne(loginId);
		
		//部署レコードの取得
		List<DepartmentEntity> departmentList = userService.getAllDepartment();
		model.addAttribute("departmentList", departmentList);
		
		//役職レコードの取得
		List<RoleEntity> roleList = userService.getAllRole();
		model.addAttribute("roleList", roleList);

		// ユーザー有効性を取得
		Map<String, Integer> validationMap = userApplicationService.getValidationMap();
		model.addAttribute("validationMap", validationMap);
		
		// ユーザー権限を取得
		Map<String, Integer> authorityMap = userApplicationService.getAuthorityMap();
		model.addAttribute("authorityMap", authorityMap);

		//UserMapperEntityをformに変換
		form = modelMapper.map(user, UserDetailForm.class);
		
		//Modelに登録
		model.addAttribute("userDetailForm",form);
			
		//ユーザー詳細画面を表示
		return"admin/userDetail";		
	}

/**ユーザー更新処理*/
@PostMapping(value = "/userDetail", params = "update")
public String updateUser(Model model
		,@ModelAttribute @Validated(GroupOrder.class) UserDetailForm form
		,BindingResult bindingResult){
	
	System.out.println("updateUserの値は" + form);
	
	// パスワードフォームが空の場合、nullをセットする
	if (form.getPassword().isEmpty()) {
		form.setPassword(null);
	}

	// 入力チェック結果
	if (bindingResult.hasErrors()) {
	    model.addAttribute("departmentList", userService.getAllDepartment());
	    model.addAttribute("roleList", userService.getAllRole());
	    model.addAttribute("validationMap", userApplicationService.getValidationMap());
	    model.addAttribute("authorityMap", userApplicationService.getAuthorityMap());
	    return "admin/userDetail";
	}
	
	log.error("Validation error: {}",bindingResult.hasErrors());
	
	//ユーザーを更新
	userService.updateUserOne(
			form.getLoginId(),
			form.getPassword(),
			form.getUserName(),
			form.getUserNamekana(),
			form.getMailAddress(),
			form.getDepartmentId(),
			form.getRoleId(),
			form.getValidation(),
			form.getAuthorityFlg(),
			form.getHire()
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