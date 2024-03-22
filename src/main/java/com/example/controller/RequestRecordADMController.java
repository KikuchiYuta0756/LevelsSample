package com.example.controller;

import java.util.Comparator;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domainUser.model.CorrectRequestEntity;
import com.example.domainUser.model.PaidAppEntity;
import com.example.domainUser.service.CorrectRequestService;
import com.example.domainUser.service.PaidAppService;

@Controller
@RequestMapping("/admin")
public class RequestRecordADMController {

	@Autowired
	private CorrectRequestService correctrequestservice;
	
	@Autowired
	private PaidAppService paidappservice;
	
	@Autowired
	private ModelMapper modelMapper;

	/**申請履歴画面を表示*/
    @GetMapping("/requestRecordADM")
    public String getRequestRecord(Model model) {
	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    //ログイン認証に使用したログインIDを利用する。
	    String paidLoginId = auth.getName();

		// 有給申請一覧取得
		List<PaidAppEntity> paidList = paidappservice.getUserPaidRequests(paidLoginId);
		paidList.sort(Comparator.comparing(PaidAppEntity::getPaidRequestDateApp));

		// Modelに登録
		model.addAttribute("paidList", paidList);
		
	    //ログイン認証に使用したログインIDを利用する。
		String correctLoginId = auth.getName();		
		
		//修正申請一覧取得
		List<CorrectRequestEntity> correctList = correctrequestservice.getUserCorrectRequests(correctLoginId);
		correctList.sort(Comparator.comparing(CorrectRequestEntity::getCorrectRequestDate));
		
		//modelに登録
		model.addAttribute("correctList", correctList);

	//ユーザー詳細画面を表示
	return"admin/requestRecordADM";

    }
	
}
