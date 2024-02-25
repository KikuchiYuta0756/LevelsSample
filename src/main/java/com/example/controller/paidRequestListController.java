package com.example.controller;

import java.util.Comparator;
import java.util.List;

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
import com.example.form.PaidRequestListForm;

@Controller
@RequestMapping("/admin")
public class paidRequestListController {

	@Autowired
	private PaidAppService paidappservice;
	
	@Autowired
	private ModelMapper modelMapper;	

	/** 有給一覧画面を表示 */
	@GetMapping("/paidRequestList")
	public String getPaidRequestList(@ModelAttribute PaidRequestListForm form,  Model model) {
		
		//formをPaidAppEntityクラスに変換
		PaidAppEntity paid = modelMapper.map(form, PaidAppEntity.class);
		
		// 有給申請一覧取得
		List<PaidAppEntity> paidList = paidappservice.getPaidRequests(paid);
		paidList.sort(Comparator.comparing(PaidAppEntity::getPaidRequestDateApp));

		// Modelに登録
		model.addAttribute("paidList", paidList);

		// ユーザー一覧画面を表示
		return "admin/paidRequestList";
	}

	
	/** 有給申請の検索処理 */
	@PostMapping("/paidRequestList")
	public String postPaidRequestList(@ModelAttribute PaidRequestListForm form,  Model model) {
		
		//formをPaidAppEntityクラスに変換
		PaidAppEntity paid = modelMapper.map(form, PaidAppEntity.class);
		
		// 有給申請一覧取得
		List<PaidAppEntity> paidList = paidappservice.getPaidRequests(paid);

		// Modelに登録
		model.addAttribute("paidList", paidList);

		// ユーザー一覧画面を表示
		return "admin/paidRequestList";
	}
	

}
