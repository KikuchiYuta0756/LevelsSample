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
import com.example.domainUser.service.CorrectRequestService;
import com.example.form.CorrectListForm;

@Controller
@RequestMapping("/admin")
public class CorrectRequestListController {
	
	@Autowired
	private CorrectRequestService correctrequestservice;
	
	@Autowired
	private ModelMapper modelMapper;
	
	//勤怠修正申請一覧を表示
	@GetMapping("/correctRequestList")
	public String getCorrectRequestList(@ModelAttribute CorrectListForm form, Model model) {
		
		//formをCorrectRequestEntityクラスに変換
		CorrectRequestEntity correct = modelMapper.map(form,CorrectRequestEntity.class);
		System.out.println("getcorrectは" + correct);
		
		//修正申請一覧取得
		List<CorrectRequestEntity> correctList = correctrequestservice.getCorrectRequests(correct);
		System.out.println("getcorrectListは" + correctList);	
		
		//modelに登録
		model.addAttribute("correctList", correctList);
		
		//修正申請一覧を表示
		return "admin/correctRequestList";
	}
	
	//修正申請検索処理
	@PostMapping("/correctRequestList")
	public String postCorrectRequest(@ModelAttribute CorrectListForm form, Model model) {
	
	//formをCorrectRequestEntityクラスに変換
	CorrectRequestEntity correct = modelMapper.map(form,CorrectRequestEntity.class);
	System.out.println("correctは" + correct);	
	
	//修正申請一覧取得
	List<CorrectRequestEntity> correctList = correctrequestservice.getCorrectRequests(correct);
	System.out.println("correctListは" + correctList);	
	
	//modelに登録
	model.addAttribute("correctList", correctList);
	
	//修正申請一覧を表示
	return "admin/correctRequestList";
}

}
