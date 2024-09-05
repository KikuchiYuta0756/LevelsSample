package com.example.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
		
		//年月リスト作成のため、現在の年月日情報を取得
		LocalDate now = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM");

		// 今月
		String currentMonth = now.format(formatter);
		// 先月
		String previousMonth = now.minusMonths(1).format(formatter);

        // 勤怠確認用の年月リスト作成
        List<String> yearMonths = new ArrayList<>();
        yearMonths.add(currentMonth);
        yearMonths.add(previousMonth);
        model.addAttribute("yearMonths", yearMonths);
		
		    return "admin/correctClockInList";
}

	//年月選択の勤怠一覧を表示
	@PostMapping("/selectCorrectYearMonths")
	public String getSelectCorrectYearMonths(@RequestParam("loginId")String loginId,
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

		//年月リスト作成のため、現在の年月日情報を取得
		LocalDate now = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM");

		// 今月
		String currentMonth = now.format(formatter);
		// 先月
		String previousMonth = now.minusMonths(1).format(formatter);

        // 勤怠確認用の年月リスト作成
        List<String> yearMonths = new ArrayList<>();
        yearMonths.add(currentMonth);
        yearMonths.add(previousMonth);
        model.addAttribute("yearMonths", yearMonths);
	
	
		return "admin/correctClockInList";
}

	// 勤怠一覧（月次）の表示（ユーザ毎）
	@PostMapping("/correctClockInList")
	public String postCorrectBeforeClockInList(CorrectWorkTimeForm form, Model model){
		
		System.out.println(form);
		
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
