package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domainUser.model.PaidAppEntity;
import com.example.domainUser.service.PaidAppService;

@Controller
@RequestMapping("/admin")
public class paidRequestListController {

	@Autowired
	private PaidAppService paidappservice;

	/** 有給一覧画面を表示 */
	@GetMapping("/paidRequestList")
	public String getPaidRequestList(Model model) {

		// ユーザー一覧取得
		List<PaidAppEntity> paidList = paidappservice.getPaidRequests();

		// Modelに登録
		model.addAttribute("paidList", paidList);

		// ユーザー一覧画面を表示
		return "admin/paidRequestList";
	}


}
