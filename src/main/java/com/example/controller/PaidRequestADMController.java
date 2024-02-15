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

import com.example.domainUser.model.PaidAppEntity;
import com.example.domainUser.service.PaidAppService;
import com.example.form.PaidRequestForm;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/admin")
@Slf4j
public class PaidRequestADMController {
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private PaidAppService paidappservice;
	
	
	//有給申請画面の表示
	@GetMapping("/paidRequestADM")
	public String getPaidRequestADM(Model model
			,@ModelAttribute PaidRequestForm form){
					
	return "admin/paidRequestADM";
	}
	
	//有給申請処理
	@PostMapping("/paidRequestADM")
	public String postPaidRequestADM(Model model
			,@ModelAttribute @Validated PaidRequestForm form
			,BindingResult bindingResult){
		
		//入力チェック結果
		if(bindingResult.hasErrors()) {
			//有給申請画面に戻る
			return getPaidRequestADM(model,form);
		}
		
		log.info(form.toString());
		
		//formをPaidRequestEntityクラスに変換
		PaidAppEntity paidapp = modelMapper.map(form, PaidAppEntity.class);
		
		//有給申請登録
		paidappservice.paidAppCreate(paidapp);
		
		return "admin/paidRequestADM";
		
	}


}