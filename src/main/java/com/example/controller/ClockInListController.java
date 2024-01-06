package com.example.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.example.domainUser.model.WorkTimeEntity;
import com.example.domainUser.service.WorkTimeService;



@Controller
@RequestMapping("/user")
public class ClockInListController {
	
	@Autowired
	private WorkTimeService worktimeService;
	
	//勤怠一覧（月次）の表示
	@GetMapping("/clockInList")
	public String getClockInList(Model model){
		
		//勤怠一覧（月次）を取得
		List<WorkTimeEntity> clockList = worktimeService.getClockTimes();
		
		//Modelに登録
		model.addAttribute("clockList", clockList);
		
	return "user/clockInList";
	}

		//勤怠修正画面の表示
		@GetMapping("/clockCorrection")
		public String getClockCorrection(){
			
						
		return "user/clockCorrection";
		}
	
}
