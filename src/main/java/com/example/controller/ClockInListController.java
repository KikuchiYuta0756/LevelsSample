package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/user")
public class ClockInListController {
	
	//勤怠表の表示
	@GetMapping("/clockInList")
	public String getClockInList(){
		
	return "user/clockInList";
	}

}
