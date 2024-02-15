package com.example.controller;

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

import com.example.domainUser.model.CorrectRequestEntity;
import com.example.domainUser.service.CorrectRequestService;
import com.example.form.CorrectRequestForm;

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
	public String getClockCorrection(Model model,
			@ModelAttribute CorrectRequestForm form){
		
					
	return "admin/correctRequestADM";
	}
	
	/**修正申請の登録処理*/
	@PostMapping("/correctRequestADM")
	public String postClockCorrection(Model model,
			@ModelAttribute CorrectRequestForm form){
		
		System.out.print("Hello");
				
		log.info(form.toString());
		
		//formをCorrectRequestEntityクラスに変換
		CorrectRequestEntity correct = modelMapper.map(form, CorrectRequestEntity.class);
		
		//ユーザー登録
		correctRequestService.correctRequestCreate(correct);
		
		//利用者一覧画面にリダイレクト
		return "admin/clockInListADM";
	}
	


}