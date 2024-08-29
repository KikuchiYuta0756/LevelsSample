package com.example.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domainUser.model.PaidAppEntity;
import com.example.domainUser.service.PaidAppService;
import com.example.domainUser.service.WorkTimeService;
import com.example.form.PaidRequestForm;

@Controller
@RequestMapping("/admin")
public class PaidRequestDetailADMController {
	
	@Autowired
	private PaidAppService paidappservice;
	
	@Autowired
	private WorkTimeService worktimeservice;
	
	@Autowired
	private ModelMapper modelMapper;
	
	/**有給申請詳細画面を表示*/
@GetMapping("/paidRequestDetail/{paidAppId}")
public String getPaidRequestDetailADM(PaidRequestForm form, Model model,
		@PathVariable("paidAppId")int paidAppId) {
		
		//有給申請を1件取得
		PaidAppEntity paidappADM = paidappservice.getPaidAppOne(paidAppId);
		
		//UserMapperEntityをformに変換
		form = modelMapper.map(paidappADM, PaidRequestForm.class);
		
		//Modelに登録
		model.addAttribute("PaidRequestForm",form);
			
		//ユーザー詳細画面を表示
		return"admin/paidRequestDetail";		
	}

/**有給申請の承認処理*/
@PostMapping(value = "/paidRequestDetail", params = "approval")
public String updatePaidRequestApproval(PaidRequestForm form, Model model){
	
	PaidAppEntity paid = modelMapper.map(form, PaidAppEntity.class);
	System.out.println("PaidAppEntity"+ paid);
	
	//申請ステータスを更新
	paidappservice.updateRequestStaApproval(paid);
	
	//対象日付の備考カラム更新
	worktimeservice.updateWorkTimeRemarks(paid);
	
	//ユーザー一覧画面にリダイレクト
	return"redirect:/admin/paidRequestList";
}

/**有給申請の差し戻し処理*/
@PostMapping(value = "/paidRequestDetail", params = "remand")
public String updatePaidRequestRemand(PaidRequestForm form, Model model){
	System.out.println("formの値は"+ form);
	
	//formをUserMapperEntityに変換
	PaidAppEntity paid= modelMapper.map(form , PaidAppEntity.class);
	
	System.out.println("paidの値は"+ paid);
	
	//申請ステータスを更新
	paidappservice.updateRequestStaRemand(paid);
	
	//ユーザー一覧画面にリダイレクト
	return"redirect:/admin/paidRequestList";
}

/**有給申請の差し戻し処理*/
@PostMapping(value = "/paidRequestDetail", params = "remove")
public String updatePaidRequestRemove(PaidRequestForm form, Model model){

	//formをUserMapperEntityに変換
	PaidAppEntity paid= modelMapper.map(form , PaidAppEntity.class);
	
	//申請ステータスを更新
	paidappservice.updateRequestStaRemove(paid);
	
	//ユーザー一覧画面にリダイレクト
	return"redirect:/admin/paidRequestList";
}

}
