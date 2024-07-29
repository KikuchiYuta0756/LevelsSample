package com.example.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domainUser.model.PaidAppEntity;
import com.example.domainUser.model.RequestStatesEntity;
import com.example.domainUser.service.CorrectRequestService;
import com.example.domainUser.service.PaidAppService;
import com.example.form.PaidRequestListForm;
import com.example.form.RequestRecordListForm;

@Controller
@RequestMapping("/admin")
public class UserPaidRequestListController {

	@Autowired
	private PaidAppService paidappservice;
	
	@Autowired
	private CorrectRequestService correctrequestservice;
	
	@Autowired
	private ModelMapper modelMapper;	

	/** 有給一覧初期表示画面(ユーザー)を表示 */
	@GetMapping("/userPaidRequestList")
	public String getUserPaidRequestListADM(@ModelAttribute PaidRequestListForm form, Model model) {

		// ログイン認証に使用したログインIDを利用する。
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String paidLoginId = auth.getName();		
		
		// 申請ステータスリストを取得
		List<RequestStatesEntity> requestStatesList = correctrequestservice.getAllRequestStates();
		model.addAttribute("requestStatesList", requestStatesList);	
		
		//formをPaidAppEntityクラスに変換
		PaidAppEntity paid = modelMapper.map(form, PaidAppEntity.class);
		paid.setPaidLoginId(paidLoginId);
				
		// 有給申請一覧取得
		List<PaidAppEntity> userPaidList = paidappservice.getUserPaidRequests(paid);
		model.addAttribute("userPaidList", userPaidList);

		// ユーザー一覧画面を表示
		return "admin/userPaidRequestList";
	}
	
	/** 有給申請の検索処理 */
	@PostMapping("/userPaidRequestList")
	public String postUserPaidRequestListADM(@ModelAttribute PaidRequestListForm form,  Model model) {
		
		// ログイン認証に使用したログインIDを利用する。
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String paidLoginId = auth.getName();				
		
		// 申請ステータスリストを取得
		List<RequestStatesEntity> requestStatesList = correctrequestservice.getAllRequestStates();
		model.addAttribute("requestStatesList", requestStatesList);
				
		//formをPaidAppEntityクラスに変換
		PaidAppEntity paid = modelMapper.map(form, PaidAppEntity.class);
		paid.setPaidLoginId(paidLoginId);
		
		// 有給申請一覧取得
		List<PaidAppEntity> userPaidList = paidappservice.selectUserPaidRequests(paid);
		model.addAttribute("userPaidList", userPaidList);

		// ユーザー一覧画面を表示
		return "admin/userPaidRequestList";
	}
		
}
