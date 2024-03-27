package com.example.controller;

import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.application.service.UserApplicationService;
import com.example.domainUser.model.UserMapperEntity;
import com.example.domainUser.model.DepartmentEntity;
import com.example.domainUser.model.RoleEntity;
import com.example.domainUser.service.UserService;
import com.example.domainUser.service.WorkTimeService;
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
	
	@Autowired
	private WorkTimeService workTimeService;
	

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
		System.out.println("ユーザ登録は"+ user);
		
		//UserMapperEntityからログインIDのみを取り出す
		String loginId = user.getLoginId();
		System.out.println("登録ユーザのログインIDは"+ loginId);

		
		//ユーザー登録
		userService.userCreate(user);
		
		//有給テーブルにユーザ登録
		userService.userPaidCreate(user);
		
		
		//勤怠テーブルに新規ユーザの一年分の年月日を格納する
		workTimeService.userWorkTimeCreate(loginId);
		
		//勤怠合計テーブルに新規ユーザの年月毎のレコードを作成する
		workTimeService.userWorkTimeTotalCreate(loginId);
		
		
		
		//利用者一覧画面にリダイレクト
		return "admin/clockInADM";
	}
	
	/**データベース関連の例外処理*/
	@ExceptionHandler(DataAccessException.class)
	public String dataAccessExceptionHandler(DataAccessException e, Model model){
		
		//空文字をセット
		model.addAttribute("error","");
		
		//メッセージをModelに登録
		model.addAttribute("message", "UserCreateControllerで例外が発生しました");
		
		//HTTPのエラーコード（500）をModelに登録
		model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR);
		
		return "error";
	}

	/**その他の例外処理*/
	@ExceptionHandler(Exception.class)
	public String exceptionHandler(Exception ex, Model model){
		
		//空文字をセット
		model.addAttribute("error","");
		
		//メッセージをModelに登録
		model.addAttribute("message", "UserCreateControllerで例外が発生しました");
		
		//HTTPのエラーコード（500）をModelに登録
		model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR);
		
		return "error";
	}
	
	
}
