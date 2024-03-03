package com.example.controller;

import java.util.Comparator;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.domainUser.model.CorrectRequestEntity;
import com.example.domainUser.model.PaidAppEntity;
import com.example.domainUser.model.UserMapperEntity;
import com.example.domainUser.model.WorkTimeEntity;
import com.example.domainUser.service.CorrectRequestService;
import com.example.domainUser.service.PaidAppService;
import com.example.domainUser.service.UserService;
import com.example.domainUser.service.WorkTimeService;
import com.example.form.CorrectListForm;
import com.example.form.PaidRequestListForm;
import com.example.form.RequestRecodeForm;


@Controller
@RequestMapping("/user")
public class RequestRecordController {
	
	@Autowired
	private CorrectRequestService correctrequestservice;
	
	@Autowired
	private PaidAppService paidappservice;
	
	@Autowired
	private ModelMapper modelMapper;

	/**申請履歴画面を表示*/
    @GetMapping("/requestRecord")
    public String getRequestRecord(Model model) {
	
		// 有給申請一覧取得
		List<PaidAppEntity> paidList = paidappservice.getUserPaidRequests();
		paidList.sort(Comparator.comparing(PaidAppEntity::getPaidRequestDateApp));

		// Modelに登録
		model.addAttribute("paidList", paidList);
		
		//修正申請一覧取得
		List<CorrectRequestEntity> correctList = correctrequestservice.getUserCorrectRequests();
		correctList.sort(Comparator.comparing(CorrectRequestEntity::getCorrectRequestDate));
		
		//modelに登録
		model.addAttribute("correctList", correctList);

	//ユーザー詳細画面を表示
	return"user/requestRecord";

    }
		
}
