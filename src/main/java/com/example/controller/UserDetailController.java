package com.example.controller;

import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.application.service.UserApplicationService;
import com.example.domainUser.model.DepartmentEntity;
import com.example.domainUser.model.RoleEntity;
import com.example.domainUser.model.UserMapperEntity;
import com.example.domainUser.service.UserService;
import com.example.form.UserDetailForm;

@Controller
@RequestMapping("/admin")
public class UserDetailController {
	
	@Autowired
	private UserApplicationService userApplicationService;
	
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
		
		//Modelに登録
		model.addAttribute("userDetailForm",form);
			
		//ユーザー詳細画面を表示
		return"admin/userDetail";		
	}

/**ユーザー更新処理*/
@PostMapping(value = "/userDetail", params = "update")
public String updateUser(UserDetailForm form, Model model){
	
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
			form.getAuthority(),
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