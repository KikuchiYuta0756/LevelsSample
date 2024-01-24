package com.example.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domainUser.model.PaidAppEntity;
import com.example.domainUser.service.PaidAppService;
import com.example.form.PaidRequestForm;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/user")
@Slf4j
public class PaidRequestController {
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private PaidAppService paidappservice;
	
	
	//有給申請画面の表示
	@GetMapping("/paidRequest")
	public String getPaidRequest(Model model,
			@ModelAttribute PaidRequestForm form){
		
					
	return "user/paidRequest";
	}
	
	//有給申請処理
	@PostMapping("/paidRequest")
	public String postPaidRequest(Model model, @ModelAttribute PaidRequestForm form){
		
		log.info(form.toString());
		
		//formをPaidRequestEntityクラスに変換
		PaidAppEntity paidapp = modelMapper.map(form, PaidAppEntity.class);
		
		//有給申請登録
		paidappservice.paidAppCreate(paidapp);
		
		return "user/clockInList";
		
	}


}
