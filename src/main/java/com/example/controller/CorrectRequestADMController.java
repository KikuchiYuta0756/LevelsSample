package com.example.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domainUser.model.CorrectRequestEntity;
import com.example.domainUser.service.CorrectRequestService;
import com.example.form.CorrectRequestForm;
import com.example.form.GroupOrder;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/admin")
@Slf4j
public class CorrectRequestADMController {
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private CorrectRequestService correctRequestService;
	
	//勤怠修正申請画面の表示
	@GetMapping("/correctRequestADM")
	public String getCorrectRequestADM(Model model,
			@ModelAttribute CorrectRequestForm form){
						
	return "admin/correctRequestADM";
	}
	
	/**修正申請の登録処理*/
	@PostMapping("/correctRequestADM")
	public String postCorrectRequestADM(Model model
			,@ModelAttribute @Validated(GroupOrder.class) CorrectRequestForm form
			,BindingResult bindingResult){
		
		//ログイン認証に使用したログインIDを利用する。
	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String correctLoginId = auth.getName();
		
		//入力チェック結果
		if(bindingResult.hasErrors()) {
			//NG 修正申請画面に戻る
			return getCorrectRequestADM(model,form);
		}
				
		log.info(form.toString());
		
		//formをCorrectRequestEntityクラスに変換
		CorrectRequestEntity correct = modelMapper.map(form, CorrectRequestEntity.class);
        correct.setCorrectLoginId(correctLoginId);
		
		//ユーザー登録
		correctRequestService.correctRequestCreate(correct);
		
		//利用者一覧画面にリダイレクト
		return "redirect:/admin/clockInListADM";
	}
	


}