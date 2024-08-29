package com.example.controller;

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
	@GetMapping("/correctRequestDetail/{correctRequestId}")
	public String getCorrectRequestDetailADM(CorrectRequestForm form, Model model,
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
	
	/**申請の承認処理*/
	@PostMapping(value = "/correctRequestDetail", params = "approval")
	public String updateCorrectRequestApproval(CorrectRequestForm form, Model model){
		
		CorrectRequestEntity correct = modelMapper.map(form, CorrectRequestEntity.class);
		
		//申請ステータスを更新
		correctRequestservice.updateRequestStaApproval(correct);
		
		//ユーザー一覧画面にリダイレクト
		return"redirect:/admin/correctRequestList";
	}

	/**申請の差し戻し処理*/
	@PostMapping(value = "/correctRequestDetail", params = "remand")
	public String updateCorrectRequestRemand(CorrectRequestForm form, Model model){
		
		//formをCorrectRequestEntityに変換
		CorrectRequestEntity correct = modelMapper.map(form, CorrectRequestEntity.class);		

		//申請ステータスを更新
		correctRequestservice.updateRequestStaRemand(correct);
		
		//ユーザー一覧画面にリダイレクト
		return"redirect:/admin/correctRequestList";
	}
	
	/**申請の却下処理*/
	@PostMapping(value = "/correctRequestDetail", params = "remove")
	public String updateCorrectRequestRemove(CorrectRequestForm form, Model model){

		//formをCorrectRequestEntityに変換
		CorrectRequestEntity correct = modelMapper.map(form, CorrectRequestEntity.class);		
		
		//申請ステータスを更新
		correctRequestservice.updateRequestStaRemove(correct);
		
		//ユーザー一覧画面にリダイレクト
		return"redirect:/admin/correctRequestList";
	}

	
}
