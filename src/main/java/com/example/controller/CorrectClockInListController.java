package com.example.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domainUser.model.WorkTimeEntity;
import com.example.domainUser.model.WorkTimeTotalEntity;
import com.example.domainUser.service.WorkTimeService;
import com.example.form.WorkTimeForm;
import com.example.form.WorkTimeTotalForm;

@Controller
@RequestMapping("/admin")
public class CorrectClockInListController {

	@Autowired
	private WorkTimeService worktimeService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	//勤怠一覧（月次）の表示
	@GetMapping("/clockInListADM")
	public String getClockInList(WorkTimeForm form, Model model){
		
		//勤怠一覧（月次）を取得
		List<WorkTimeEntity> correctclockList = worktimeService.getClockTimes();
		
		//WorkTimeEntityをformに変換
		form = modelMapper.map(correctclockList, WorkTimeForm.class);
		
		//Modelに登録
		model.addAttribute("workTimeForm", form);
				
	return "admin/correctClockInList";
	}
	
	//勤怠一覧（月次）の表示
	@PostMapping("/clockInListADM")
	public String updateClockInList(WorkTimeForm form, Model model){
		
		//勤怠を更新
		worktimeService.updateClockInOne(
				form.getWorkDate(),
				form.getUserId(),
				form.getStartTime(),
				form.getCloseTime(),
				form.getRestTime()
				);
		
	}
	
	

}
