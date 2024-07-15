package com.example.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
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
import com.example.form.GroupOrder;
import com.example.form.PasswordChangeForm;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/common")
@Slf4j
public class PasswordChangeController {

	@Autowired
	private UserService userService;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private PasswordEncoder passwordEncoder;

	// パスワード変更画面の表示
	@GetMapping("/afterPasswordChange")
	public String getBeforePasswordChange(Model model, @ModelAttribute PasswordChangeForm form) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		// ログイン認証に使用したログインIDを利用する。
		String loginId = auth.getName();

		// ログイン認証ユーザーの情報を取得
		UserMapperEntity loginUser = userService.getUserOne(loginId);

		// UserMapperEntityをformに変換
		form = modelMapper.map(loginUser, PasswordChangeForm.class);

		// Modelに登録
		model.addAttribute("PasswordChangeForm", form);

		return "common/afterPasswordChange";
	}

	// パスワード変更処理
	@PostMapping("/afterPasswordChange")
	public String postAfterPasswordChange(Model model
		 ,@ModelAttribute @Validated(GroupOrder.class) PasswordChangeForm form
		 ,BindingResult bindingResult){

		// ログインユーザのログインIDを取得。
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String loginId = auth.getName();
		
		// ログイン認証ユーザーの情報を取得
		UserMapperEntity users = userService.getFindUserOne(loginId);
		Integer userAuthority = users.getAuthorityFlg();
		String nowPassword = users.getPassword();

		// 新パスワードが現在のパスワードと同一かチェック
		if (passwordEncoder.matches(form.getPassword(), nowPassword)) {
			// パスワードが同じ場合はエラーメッセージと返してパスワード変更画面の再表示する。
			model.addAttribute("error", "現在のパスワードと異なるパスワードを設定してください");
			return "common/afterPasswordChange";
		}
		
		//入力チェック結果
		if(bindingResult.hasErrors()) {
		  //NG：パスワード変更画面に戻る
			return getBeforePasswordChange(model,form);
		}
		
		log.info(form.toString());

		// パスワードの更新
		userService.updatePasswordOne(form.getLoginId(), form.getPassword());

		if (userAuthority == 2) {
			return "redirect:/admin/clockInADM"; // ADMINの場合は/admin/clockInADMにリダイレクト
		} else {
			return "redirect:/user/clockIn"; // ADMIN以外の場合は/user/clockInにリダイレクト
		}

	}

}
