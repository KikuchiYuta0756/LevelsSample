package com.example.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domainUser.model.CorrectRequestEntity;
import com.example.domainUser.service.CorrectRequestService;
import com.example.form.CorrectRequestForm;

@Controller
@RequestMapping("/user")
public class GeneralUserCorrectRequestDetailController {
	
	@Autowired
	private CorrectRequestService correctrequestservice;

	@Autowired
	private ModelMapper modelMapper;

	/** 修正申請詳細画面を表示 */
	@GetMapping("/generalUserCorrectRequestDetail/{correctRequestId}")
	public String getUserCorrectRequestDetail(CorrectRequestForm form, Model model,
			@PathVariable("correctRequestId") int correctRequestId) {

		// 修正申請を1件取得
		CorrectRequestEntity correctDetailADM = correctrequestservice.getCorrectRequestOne(correctRequestId);

		// UserMapperEntityをformに変換
		form = modelMapper.map(correctDetailADM, CorrectRequestForm.class);

		// Modelに登録
		model.addAttribute("CorrectRequestForm", form);

		// ユーザー詳細画面を表示
		return "user/generalUserCorrectRequestDetail";
	}
	
	/**申請の取り消し処理*/
	@PostMapping(value = "/generalUserCorrectRequestDetail", params = "remove")
	public String updateUserCorrectRequestRemove(CorrectRequestForm form, Model model){
		
		//申請ステータスを更新
		correctrequestservice.updateUserRequestStaRemove(form.getCorrectRequestId());
		
		//ユーザー一覧画面にリダイレクト
		return"redirect:/user/generalUserCorrectRequestDetail";
	}

	/**申請の提出処理*/
	@PostMapping(value = "/generalUserCorrectRequestDetail", params = "submission")
	public String updateUserCorrectRequestSubmission(Model model
			,@ModelAttribute CorrectRequestForm form){
		
		//申請ステータスを更新
		correctrequestservice.updateRequestStaSubmission(
				form.getCorrectRequestId(),
		        form.getCorrectDate(),
		        form.getCorrectStartTime(),
		        form.getCorrectCloseTime(),
		        form.getCorrectRestTime(),
		        form.getCorrectReason());
		
		//ユーザー一覧画面にリダイレクト
		return"redirect:/user/generalUserCorrectRequestDetail";
	}
}
