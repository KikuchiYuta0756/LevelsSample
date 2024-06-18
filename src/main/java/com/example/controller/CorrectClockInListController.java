package com.example.controller;

import java.util.Arrays;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.domainUser.model.UserMapperEntity;
import com.example.domainUser.model.WorkTimeEntity;
import com.example.domainUser.service.UserService;
import com.example.domainUser.service.WorkTimeService;
import com.example.form.CorrectWorkTimeForm;

@Controller
@RequestMapping("/admin")
public class CorrectClockInListController {

	@Autowired
	private WorkTimeService worktimeService;
	
	@Autowired
	private UserService userService;	

	@Autowired
	private ModelMapper modelMapper;

	// 勤怠一覧（月次）の表示（ユーザ毎）
	@GetMapping("/correctClockInList/{loginId}")
	public String getCorrectBeforeClockInList(CorrectWorkTimeForm form, Model model,
			@PathVariable("loginId")String loginId){
		//選択ユーザの1件取得
		UserMapperEntity userDetail = userService.getUserOne(loginId);

		
		//Modelに登録
		model.addAttribute("userDetail", userDetail);
		

		//勤怠一覧（月次）を取得
		List<WorkTimeEntity> clockList = worktimeService.getClockTimes(loginId);
		System.out.println("clockListは"+ clockList);
		
		//Modelに登録
		model.addAttribute("clockList", clockList);
		
		
		//年月リストを作成
		List<String> yearMonths = Arrays.asList(
				"2024-01", "2024-02", "2024-03", "2024-04", "2024-05", "2024-06", "2024-07", "2024-08", "2024-09", "2024-10", "2024-11", "2024-12");
		
		//Modelに追加
		model.addAttribute("yearMonths", yearMonths);
		
		    return "admin/correctClockInList";
}

	//年月選択の勤怠一覧を表示
	@PostMapping("/selectCorrectYearMonths")
	public String getSelectCorrectYearMonths(String loginId,
			CorrectWorkTimeForm form, Model model,
			@RequestParam("selectYearMonth") String selectedYearMonth){
		
	    System.out.println("ユーザは" + loginId);	
		
		//選択ユーザの1件取得
		UserMapperEntity userDetails = userService.getUserOne(loginId);
		
		//Modelに登録
		model.addAttribute("userDetail", userDetails);

		
		//選択された年月の勤怠一覧を表示する
		List<WorkTimeEntity> clockList =worktimeService.getSelectYearMonth(loginId, selectedYearMonth);

		//Modelに登録
		model.addAttribute("clockList", clockList);

		//年月リストを作成
		List<String> yearMonths = Arrays.asList(
				"2024-01", "2024-02", "2024-03", "2024-04", "2024-05", "2024-06", "2024-07", "2024-08", "2024-09", "2024-10", "2024-11", "2024-12");
		
		//Modelに追加
		model.addAttribute("yearMonths", yearMonths);	

	
		return "admin/correctClockInList";
}

	// 勤怠一覧（月次）の表示（ユーザ毎）
	@PostMapping("/correctClockInList")
	public String postCorrectBeforeClockInList(CorrectWorkTimeForm form, Model model){
		
		 //ユーザの勤怠情報を更新
		 worktimeService.updateWorkTimeOne(
				 form.getWorkDate(),
				 form.getLoginId(),
				 form.getStartTime(),
				 form.getCloseTime(),
				 form.getRestTime()
				 );		 
		 
	
    return "admin/correctClockInList";
    }


}
