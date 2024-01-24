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

import com.example.domainUser.model.CorrectRequestEntity;
import com.example.domainUser.service.CorrectRequestService;
import com.example.form.CorrectRequestForm;


@Controller
@RequestMapping("/admin")
public class CorrectRequestDetailADMController {
	
	@Autowired
	private CorrectRequestService correctRequestservice;
	
	@Autowired
	private ModelMapper modelMapper;
	
	/**修正申請詳細画面を表示*/
	@GetMapping("/correctRequestList/{correctRequestId}")
	public String getCorrectRequestADM(CorrectRequestForm form, Model model,
			@PathVariable("correctRequestId")int correctRequestId) {

		//修正申請を1件取得
		CorrectRequestEntity correctDetailADM = correctRequestservice.getCorrectRequestOne(correctRequestId);
		
		//UserMapperEntityをformに変換
		form = modelMapper.map(correctDetailADM, CorrectRequestForm.class);
		
		//Modelに登録
		model.addAttribute("CorrectRequestForm",form);
			
		//ユーザー詳細画面を表示
		return"admin/correctRequestDetail";		
		
		
	}

}
