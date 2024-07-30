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
public class UserCorrectRequestDetailController {
	
	@Autowired
	private CorrectRequestService correctrequestservice;

	@Autowired
	private ModelMapper modelMapper;


	/** 修正申請詳細画面を表示 */
	@GetMapping("/userCorrectRequestDetail/{correctRequestId}")
	public String getUserCorrectRequestDetail(CorrectRequestForm form, Model model,
			@PathVariable("correctRequestId") int correctRequestId) {

		// 修正申請を1件取得
		CorrectRequestEntity correctDetailADM = correctrequestservice.getCorrectRequestOne(correctRequestId);

		// UserMapperEntityをformに変換
		form = modelMapper.map(correctDetailADM, CorrectRequestForm.class);

		// Modelに登録
		model.addAttribute("CorrectRequestForm", form);

		// ユーザー詳細画面を表示
		return "admin/userCorrectRequestDetail";
	}
	
	/**申請の却下処理*/
	@PostMapping(value = "/userCorrectRequestDetail", params = "remove")
	public String updateUserCorrectRequestRemove(CorrectRequestForm form, Model model){
		
		//申請ステータスを更新
		correctrequestservice.updateRequestStaRemove(form.getCorrectRequestId());
		
		//ユーザー一覧画面にリダイレクト
		return"redirect:/admin/userCorrectRequestList";
	}

	/**申請の却下処理*/
	@PostMapping(value = "/userCorrectRequestDetail", params = "submission")
	public String updateUserCorrectRequestSubmission(CorrectRequestForm form, Model model){
		
		//申請ステータスを更新
		correctrequestservice.updateRequestStaSubmission(form.getCorrectRequestId());
		
		//ユーザー一覧画面にリダイレクト
		return"redirect:/admin/userCorrectRequestList";
	}



}
