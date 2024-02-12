package com.example.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
//import java.time.Month;
//import java.text.SimpleDateFormat;

import org.modelmapper.ModelMapper;
//import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
//import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domainUser.model.UserMapperEntity;
import com.example.domainUser.model.WorkTimeEntity;
import com.example.domainUser.service.UserService;
import com.example.domainUser.service.WorkTimeService;
//import com.example.form.WorkTimeForm;
import com.example.form.UserDetailForm;


//import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/user")
//@Slf4j
public class ClockInController {
		
	@Autowired
	private UserService userService;
	
	@Autowired
	private WorkTimeService workTimeService;
	
	@Autowired
	private ModelMapper modelMapper;

	
	//勤怠打刻画面（ユーザ用表）示
	@GetMapping("/clockIn")
	public String getClockIn(UserDetailForm form,
			Model model){
		
		//認証ユーザーの出退勤フラグを取得
		UserMapperEntity userWorkFlg = userService.getWorkFlg();
		
		System.out.println(userWorkFlg);

		//UserMapperEntityをformに変換
		form = modelMapper.map(userWorkFlg, UserDetailForm.class);


		// Modelに登録
		model.addAttribute("userWorkFlg", form);
				
		//勤怠打刻画面に遷移
	return "user/clockIn";
	}


	
	//出勤時間の登録処理
	@PostMapping(value = "/clockIn", params = "attendance")
	public String attendanceTime() {
				
		//LocalDateTimeで現在日時の取得
		LocalDateTime ldtnow = LocalDateTime.now();
		
        // 分を10分単位に変換
        int minute = ldtnow.getMinute();
        int roundedMinute = Math.round((float) minute / 10) * 10;

        // 新しい LocalDateTime を作成して、分を変更
        LocalDateTime adjustedDateTime = ldtnow.withMinute(roundedMinute);
		
		//Date出力形式を指定
		DateTimeFormatter dtfdate = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		DateTimeFormatter dtftime = DateTimeFormatter.ofPattern("HH:mm");
		
		//日付型をString型に変換
		String strdate = adjustedDateTime.format(dtfdate);
		String strtime = adjustedDateTime.format(dtftime);
		
		WorkTimeEntity worktime = new WorkTimeEntity();
		worktime.setWorkDate(strdate);
		worktime.setStartTime(strtime);

		workTimeService.startTimeSignup(worktime);
		
		//出退勤フラグ（退勤）を更新する
		userService.getWorkFlgLeaving();
		
		return "redirect:/user/clockIn";	
	}
	
	//退勤ボタン処理
	@PostMapping(value = "/clockIn", params = "leaving")
	public String leavingTime() {
		
		LocalDateTime ldtnow = LocalDateTime.now();
		
        // 分を10分単位に変換
        int minute = ldtnow.getMinute();
        int roundedMinute = Math.round((float) minute / 10) * 10;

        // 新しい LocalDateTime を作成して、分を変更
        LocalDateTime adjustedDateTime = ldtnow.withMinute(roundedMinute);

		
		//Date出力形式を指定
		DateTimeFormatter dtfdate = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		DateTimeFormatter dtftime = DateTimeFormatter.ofPattern("HH:mm");
		
		//日付型をString型に変換
		String strdate = adjustedDateTime.format(dtfdate);
		String strtime = adjustedDateTime.format(dtftime);
		
		//
		WorkTimeEntity worktime = new WorkTimeEntity();
		worktime.setWorkDate(strdate);
		worktime.setCloseTime(strtime);
		
        //退勤時間のDB登録
		workTimeService.closeTimeSignup(worktime);
		
		WorkTimeEntity worktimeentity = new WorkTimeEntity();
		worktimeentity.setWorkDate(strdate);		
		
		//残業時間のDB登録
		workTimeService.overTimeSignup(worktimeentity);
		
		//出退勤フラグ（出勤）を更新する
		userService.getWorkFlgAttendance();

		
		
		
		return "redirect:/user/clockIn";
	}
	

}
