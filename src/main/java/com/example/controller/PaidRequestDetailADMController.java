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

import com.example.domainUser.model.PaidAppEntity;
import com.example.domainUser.service.PaidAppService;
import com.example.form.PaidRequestForm;
import com.example.form.UserDetailForm;

@Controller
@RequestMapping("/admin")
public class PaidRequestDetailADMController {
	
	@Autowired
	private PaidAppService paidappservice;
	
	@Autowired
	private ModelMapper modelMapper;
	
	/**有給申請詳細画面を表示*/
@GetMapping("/paidRequestDetail/{paidAppId}")
public String getPaidAppADM(PaidRequestForm form, Model model,
		@PathVariable("paidAppId")int paidAppId) {
		
		//有給申請を1件取得
		PaidAppEntity paidappADM = paidappservice.getPaidAppOne(paidAppId);
		
		//UserMapperEntityをformに変換
		form = modelMapper.map(paidappADM, PaidRequestForm.class);
		
		//Modelに登録
		model.addAttribute("PaidRequestForm",form);
			
		//ユーザー詳細画面を表示
		return"admin/paidRequestList";		
	}

/**有給申請の承認処理*/
@PostMapping(value = "/paidRequestDetail", params = "approval")
public String updateUser(PaidRequestForm form, Model model){
	
	//申請ステータスを更新
	paidappservice.updateRequestStaOne(
			form.getPaidAppId()
			);
	
	//ユーザー一覧画面にリダイレクト
	return"redirect:/admin/paidRequestList";
}

/**ユーザー削除処理*/
/**@PostMapping(value = "/userDetail", params = "delete")
public String deleteUser(UserDetailForm form, Model model){
	
	//ユーザーを削除
	userService.deleteUserOne(form.getLoginId());
	
	//ユーザー一覧画面にリダイレクト
	return "admin/paidRequestList";
}*/
	
}
