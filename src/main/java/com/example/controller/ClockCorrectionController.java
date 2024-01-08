package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class ClockCorrectionController {
	
	//勤怠修正申請画面の表示
	@GetMapping("/clockCorrection")
	public String getClockCorrection(){
		
					
	return "user/clockCorrection";
	}


}
