package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domainUser.model.CorrectRequestEntity;
import com.example.domainUser.service.CorrectRequestService;

@Controller
@RequestMapping("/admin")
public class CorrectRequestListController {
	
	@Autowired
	private CorrectRequestService correctrequestservice;
	
	//勤怠修正申請一覧を表示
	@GetMapping("/correctRequestList")
	public String getCorrectRequestList(Model model) {
		
		//修正申請一覧取得
		List<CorrectRequestEntity> correctList = correctrequestservice.getCorrectRequests();
		
		//modelに登録
		model.addAttribute("correctList", correctList);
		
		//修正申請一覧を表示
		return "admin/correctRequestList";
	}
	

}
