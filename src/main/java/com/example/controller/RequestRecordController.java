//package com.example.controller;
//
//import java.util.List;
//
//import org.modelmapper.ModelMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PathVariable;
//
//import com.example.domainUser.model.UserMapperEntity;
//import com.example.domainUser.model.WorkTimeEntity;
//import com.example.domainUser.service.PaidAppService;
//import com.example.domainUser.service.UserService;
//import com.example.domainUser.service.WorkTimeService;
//import com.example.form.RequestRecodeForm;
//
//
//@Controller
//@RequestMapping("/user")
//public class RequestRecordController {
//	
//	@Autowired
//	private PaidAppService paidappservice;
//	
//	@Autowired
//	private ModelMapper modelMapper;
//
//	/**申請履歴画面を表示*/
//    @GetMapping("/requestRecord/{loginId}")
//    public String getRequestRecord(RequestRecodeForm form, Model model,
//		@PathVariable("loginId")String loginId) {
//	
//	//ユーザーを1件取得
//    RequestRecodeForm paidrequest  = paidappservice.getPaidApps(loginId);
//	
//	//UserMapperEntityをformに変換
//	form = modelMapper.map(paidrequest, RequestRecodeForm.class);
//	form.setPaidAppList(paidrequest, getPaidAppList);
//	
//	//Modelに登録
//	model.addAttribute("userDetailForm",form);
//		
//	//ユーザー詳細画面を表示
//	return"user/clockInList";
//
//    }
//	
//	
//}
