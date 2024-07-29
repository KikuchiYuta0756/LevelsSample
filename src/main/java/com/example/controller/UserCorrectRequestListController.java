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

import com.example.domainUser.model.CorrectRequestEntity;
import com.example.domainUser.model.RequestStatesEntity;
import com.example.domainUser.service.CorrectRequestService;
import com.example.form.CorrectListForm;

@Controller
@RequestMapping("/admin")
public class UserCorrectRequestListController {
	
	@Autowired
	private CorrectRequestService correctrequestservice;
	
	@Autowired
	private ModelMapper modelMapper;	

	/** 修正申請一覧初期表示画面(ユーザー)を表示 */
	@GetMapping("/userCorrectRequestList")
	public String getUserCorrectRequestListADM(@ModelAttribute CorrectListForm form, Model model) {

		// ログイン認証に使用したログインIDを利用する。
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String correctLoginId = auth.getName();		
		
		// 申請ステータスリストを取得
		List<RequestStatesEntity> requestStatesList = correctrequestservice.getAllRequestStates();
		model.addAttribute("requestStatesList", requestStatesList);	
		
		//formをPaidAppEntityクラスに変換
		CorrectRequestEntity correct = modelMapper.map(form, CorrectRequestEntity.class);
		correct.setCorrectLoginId(correctLoginId);
				
		// 有給申請一覧取得
		List<CorrectRequestEntity> userCorrectList = correctrequestservice.getUserCorrectRequests(correct);
		model.addAttribute("userCorrectList", userCorrectList);

		// ユーザー一覧画面を表示
		return "admin/userCorrectRequestList";
	}
	
	/** 修正申請の検索処理 */
	@PostMapping("/userCorrectRequestList")
	public String postUserCorrectRequestListADM(@ModelAttribute CorrectListForm form,  Model model) {
		
		// ログイン認証に使用したログインIDを利用する。
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String correctLoginId = auth.getName();				
		
		// 申請ステータスリストを取得
		List<RequestStatesEntity> requestStatesList = correctrequestservice.getAllRequestStates();
		model.addAttribute("requestStatesList", requestStatesList);
				
		//formをPaidAppEntityクラスに変換
		CorrectRequestEntity correct = modelMapper.map(form, CorrectRequestEntity.class);
		correct.setCorrectLoginId(correctLoginId);
		
		// 有給申請一覧取得
		List<CorrectRequestEntity> userCorrectList = correctrequestservice.selectUserCorrectRequests(correct);
		model.addAttribute("userCorrectList", userCorrectList);

		// ユーザー一覧画面を表示
		return "admin/userCorrectRequestList";
	}
		
}
