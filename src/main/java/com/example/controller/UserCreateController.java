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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.application.service.UserApplicationService;
import com.example.domainUser.model.UserMapperEntity;
import com.example.domainUser.model.DepartmentEntity;
import com.example.domainUser.model.RoleEntity;
import com.example.domainUser.service.UserService;
import com.example.form.UserCreateForm;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/admin")
@Slf4j
public class UserCreateController {

	@Autowired
	private UserApplicationService userApplicationService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ModelMapper modelMapper;

	/** ユーザー登録画面を表示 */
	@GetMapping("/create")
	public String getUserCreate(Model model, 
			@ModelAttribute UserCreateForm form) {
		
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
		
		
		// ユーザー登録画面に遷移
		return "admin/create";
	}
		
	/**ユーザー登録処理*/
	@PostMapping("/create")
	public String postUserCreate(Model model
			,@ModelAttribute @Validated UserCreateForm form
			,BindingResult bindingResult){
		
		System.out.println("hello");
		
		//入力チェック結果
		if(bindingResult.hasErrors()) {
		  //NG：ユーザー登録画面に戻る
			return getUserCreate(model,form);
		}
		
		
		log.info(form.toString());
		
		//formをUserMapperEntityクラスに変換
		UserMapperEntity user = modelMapper.map(form, UserMapperEntity.class);
		
		//ユーザー登録
		userService.userCreate(user);
		
		//利用者一覧画面にリダイレクト
		return "admin/list";
	}
}
