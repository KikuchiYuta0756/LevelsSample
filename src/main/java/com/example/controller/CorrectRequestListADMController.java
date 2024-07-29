package com.example.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
public class CorrectRequestListADMController {

	@Autowired
	private CorrectRequestService correctrequestservice;

	@Autowired
	private ModelMapper modelMapper;

	// 勤怠修正申請一覧を表示
	@GetMapping("/correctRequestList")
	public String getCorrectRequestListADM(@ModelAttribute CorrectListForm form, Model model) {

		// 申請ステータスリストを取得
		List<RequestStatesEntity> requestStatesList = correctrequestservice.getAllRequestStates();
		model.addAttribute("requestStatesList", requestStatesList);

		// formをCorrectRequestEntityクラスに変換
		CorrectRequestEntity correct = modelMapper.map(form, CorrectRequestEntity.class);

		// 修正申請一覧取得
		List<CorrectRequestEntity> correctList = correctrequestservice.getCorrectRequests(correct);
		model.addAttribute("correctList", correctList);

		// 修正申請一覧を表示
		return "admin/correctRequestList";
	}

	// 申請検索処理
	@PostMapping("/correctRequestList")
	public String postCorrectRequestListADM(@ModelAttribute CorrectListForm form, Model model) {

		// 申請ステータスリストを取得
		List<RequestStatesEntity> requestStatesList = correctrequestservice.getAllRequestStates();
		model.addAttribute("requestStatesList", requestStatesList);

		// formをCorrectRequestEntityクラスに変換
		CorrectRequestEntity correct = modelMapper.map(form, CorrectRequestEntity.class);

		// 修正申請一覧取得
		List<CorrectRequestEntity> correctList = correctrequestservice.getCorrectRequests(correct);
		model.addAttribute("correctList", correctList);

		// 修正申請一覧を表示
		return "admin/correctRequestList";
	}
}
