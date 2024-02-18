package com.example.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
import com.example.domainUser.model.WorkTimeTotalEntity;
import com.example.domainUser.service.WorkTimeService;
import com.example.form.UserDetailForm;
import com.example.form.WorkTimeForm;
import com.example.form.WorkTimeTotalForm;


@Controller
@RequestMapping("/user")
public class ClockInListController {
	
	@Autowired
	private WorkTimeService worktimeService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	//勤怠一覧（月次）の表示
	@GetMapping("/clockInList")
	public String getClockInList(WorkTimeTotalForm form, Model model){
		
		//勤怠一覧（月次）を取得
		List<WorkTimeEntity> clockList = worktimeService.getClockTimes();
		
		//Modelに登録
		model.addAttribute("clockList", clockList);
		
//		//LocalDateTimeで現在日時の取得
//		LocalDateTime ldtnow = LocalDateTime.now();
		
		//Date出力形式を指定
//		DateTimeFormatter dtfdate = DateTimeFormatter.ofPattern("yyyy-MM");
//		
//		String strdate = ldtnow.format(dtfdate);
//		model.addAttribute("strdate",strdate);

		
		//勤怠情報の各合計（月次）を取得
		WorkTimeTotalEntity workTimeTotal = worktimeService.getworkTimesTotal();
		
		//WorkTimeTotalEntityをformに変換
		form = modelMapper.map(workTimeTotal, WorkTimeTotalForm.class);

		
		//Modelに登録
		model.addAttribute("workTimeTotalForm", form);


	return "user/clockInList";
	}

	//前月の勤怠一覧（月次）の表示
	@GetMapping("/clockInListLastMonth")
	public String getClockInListLastMonth(Model model){
		
		//前月の勤怠一覧（月次）を取得
		List<WorkTimeEntity> clockList = worktimeService.getClockTimesLastMonth();
		
		
		//Modelに登録
		model.addAttribute("clockList", clockList);
		
	return "user/clockInList";
	}

	//来月の勤怠一覧（月次）の表示
	@GetMapping("/clockInListNextMonth")
	public String getClockInListNextMonth(Model model){
		
		//前月の勤怠一覧（月次）を取得
		List<WorkTimeEntity> clockList = worktimeService.getClockTimesNextMonth();
		
		//Modelに登録
		model.addAttribute("clockList", clockList);
		
	return "user/clockInList";
	}

	
}


