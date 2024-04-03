package com.example.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
//import java.time.Month;
//import java.text.SimpleDateFormat;

import org.modelmapper.ModelMapper;
//import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
//import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domainUser.model.UserMapperEntity;
import com.example.domainUser.model.WorkTimeEntity;
import com.example.domainUser.service.UserService;
import com.example.domainUser.service.WorkTimeService;
//import com.example.form.WorkTimeForm;
import com.example.form.UserDetailForm;

//import lombok.extern.slf4j.Slf4j;


import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequestMapping("/admin")
//@Slf4j
public class ClockInADMController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private WorkTimeService workTimeService;
	
	@Autowired
	private ModelMapper modelMapper;

	
	//勤怠打刻画面（ユーザ用表）示
	@GetMapping("/clockInADM")
	public String getClockIn(UserDetailForm form,
			Model model){
	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    //ログイン認証に使用したログインIDを利用する。
	    String loginId = auth.getName();
		
		//認証ユーザーの出退勤フラグを取得
		UserMapperEntity userWorkFlg = userService.getWorkFlg(loginId);

		//UserMapperEntityをformに変換
		form = modelMapper.map(userWorkFlg, UserDetailForm.class);


		// Modelに登録
		model.addAttribute("userWorkFlg", form);
				
		//勤怠打刻画面に遷移
	return "admin/clockInADM";
	}


	
	//出勤時間の登録処理
	@PostMapping(value = "/clockInADM", params = "attendance")
	public String attendanceTime() {
	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    //ログイン認証に使用したログインIDを利用する。
	    String loginId = auth.getName();

	    //LocalDateTimeで現在日時の取得
		LocalDateTime ldtnow = LocalDateTime.now();
		
        //分単位を取得
        int minute = ldtnow.getMinute();
        System.out.println("minuteの値は" + minute);
        //分を5捨6入して計算する10分単位に変換
        int roundedMinute = Math.round((float)(minute-1) / 10) * 10;
        System.out.println("roundedMinuteの値は" + roundedMinute);
                
        //LocalDateTimeの分部分を更新
        LocalDateTime adjustedDateTime = ldtnow.withMinute(roundedMinute);
        System.out.println("adjustedDateTimeの値は" + adjustedDateTime);
        
		//Date出力形式を指定
		DateTimeFormatter dtfdate = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		DateTimeFormatter dtftime = DateTimeFormatter.ofPattern("HH:mm");
		
		//日付型をString型に変換
		String strdate = adjustedDateTime.format(dtfdate);
		String strtime = adjustedDateTime.format(dtftime);
		
        System.out.println("strdateの値は" + strdate);
        System.out.println("strtimeの値は" + strtime);
		
		WorkTimeEntity worktime = new WorkTimeEntity();
		worktime.setWorkDate(strdate);
		worktime.setStartTime(strtime);
		worktime.setLoginId(loginId);
		
		workTimeService.startTimeSignup(worktime);
		
		//出退勤フラグ（退勤）を更新する
		userService.getWorkFlgLeaving(loginId);
		
		return "redirect:/admin/clockInADM";	
	}
        
	
	//退勤ボタン処理
	@PostMapping(value = "/clockInADM", params = "leaving")
	public String leavingTime() {
	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    //ログイン認証に使用したログインIDを利用する。
	    String loginId = auth.getName();

	    //LocalDateTimeで現在日時の取得
		LocalDateTime ldtnow = LocalDateTime.now();
		
        //分単位を取得
        int minute = ldtnow.getMinute();
        //分を5捨6入して計算する10分単位に変換
        int roundedMinute = Math.round((float)(minute-1) / 10) * 10;
                
        //LocalDateTimeの分部分を更新
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
		worktime.setLoginId(loginId);
		
        //退勤時間のDB登録
		workTimeService.closeTimeSignup(worktime);
		
		WorkTimeEntity worktimeentity = new WorkTimeEntity();
		worktimeentity.setWorkDate(strdate);
		worktimeentity.setLoginId(loginId);
		
		//残業時間のDB登録
		workTimeService.overTimeSignup(worktimeentity);
		
		//出退勤フラグ（出勤）を更新する
		userService.getWorkFlgAttendance(loginId);
		
		//月毎の合計実働時間を更新する
		workTimeService.updateTotalWorkTime(loginId);
		
		return "redirect:/admin/clockInADM";
	}
}
