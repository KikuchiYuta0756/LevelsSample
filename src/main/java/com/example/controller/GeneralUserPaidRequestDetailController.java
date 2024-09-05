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

import com.example.domainUser.model.PaidAppEntity;
import com.example.domainUser.service.PaidAppService;
import com.example.form.PaidRequestForm;

@Controller
@RequestMapping("/user")
public class GeneralUserPaidRequestDetailController {
	
	@Autowired
	private PaidAppService paidappservice;

	@Autowired
	private ModelMapper modelMapper;

	/** 個別申請履歴の有給申請詳細画面を表示 */
	@GetMapping("/generalUserPaidRequestDetail/{paidAppId}")
	public String getUserPaidRequestDetail(PaidRequestForm form, Model model,
			@PathVariable("paidAppId") int paidAppId) {

		// 有給申請を1件取得
		PaidAppEntity paidappADM = paidappservice.getPaidAppOne(paidAppId);

		// UserMapperEntityをformに変換
		form = modelMapper.map(paidappADM, PaidRequestForm.class);

		// Modelに登録
		model.addAttribute("PaidRequestForm", form);

		// ユーザー詳細画面を表示
		return "user/generalUserPaidRequestDetail";
	}
	
	/**有給申請の差し戻し処理*/
	@PostMapping(value = "/generalUserPaidRequestDetail", params = "remove")
	public String updatePaidRequestRemove(PaidRequestForm form, Model model){
		
		//申請ステータスを更新
		paidappservice.updateUserRequestStaRemove(form.getPaidAppId());
		
		//ユーザー一覧画面にリダイレクト
		return"redirect:/user/generalUserPaidRequestDetail";
	}
	
	/**申請の提出処理*/
	@PostMapping(value = "/generalUserPaidRequestDetail", params = "submission")
	public String updateUserPaidRequestSubmission(Model model
			,@ModelAttribute PaidRequestForm form){
		
		//申請ステータスを更新
		paidappservice.updatePaidRequestStaSubmission(
				form.getPaidAppId(),
		        form.getPaidRequestDateApp(),
		        form.getPaidAppReason());
		
		//ユーザー一覧画面にリダイレクト
		return"redirect:/user/generalUserPaidRequestDetail";
	}	

}
