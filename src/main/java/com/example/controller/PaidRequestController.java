package com.example.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domainUser.model.PaidAppEntity;
import com.example.domainUser.model.PaidEntity;
import com.example.domainUser.service.PaidAppService;
import com.example.domainUser.service.UserService;
import com.example.form.GroupOrder;
import com.example.form.PaidRequestForm;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/user")
@Slf4j
public class PaidRequestController {
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private PaidAppService paidappservice;
	
	@Autowired
	private UserService userService;
	
	//有給申請画面の表示
	@GetMapping("/paidRequest")
	public String getPaidRequest(Model model,
			@ModelAttribute PaidRequestForm form){
	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    //ログイン認証に使用したログインIDを利用する。
	    String loginId = auth.getName();
	    
	    //有給日数を取得
	   PaidEntity paidDay = userService.getPaidDays(loginId);
	   System.out.println("paidDayは"+ paidDay);
	   
	   String paidDays = paidDay.getPaidDateNum();
	   System.out.println("paidDaysは"+ paidDays);
	   
	   model.addAttribute("paidDays", paidDays);
					
	return "user/paidRequest";
	}
	
	//有給申請処理
	@PostMapping("/paidRequest")
	public String postPaidRequest(Model model
			,@ModelAttribute @Validated(GroupOrder.class) PaidRequestForm form
			,BindingResult bindingResult){
	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    //ログイン認証に使用したログインIDを利用する。
	    String paidLoginId = auth.getName();		
		
		//入力チェック結果
		if(bindingResult.hasErrors()) {
			//NG　有給申請画面戻る
			return getPaidRequest(model,form);
		}
		
		log.info(form.toString());
		
		//formをPaidRequestEntityクラスに変換
		PaidAppEntity paidapp = modelMapper.map(form, PaidAppEntity.class);
		paidapp.setPaidLoginId(paidLoginId);
		
		//有給申請登録
		paidappservice.paidAppCreate(paidapp);
		
		return "user/clockInList";
		
	}


}
