package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class PaidRequestController {
	
	//有給申請画面の表示
	@GetMapping("/paidRequest")
	public String getPaidRequest(){
		
					
	return "user/paidRequest";
	}


}
