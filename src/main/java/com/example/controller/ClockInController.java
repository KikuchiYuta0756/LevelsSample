package com.example.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
//import java.time.Month;
//import java.text.SimpleDateFormat;

//import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.domainUser.model.WorkTimeEntity;
import com.example.domainUser.service.WorkTimeService;
//import com.example.form.WorkTimeForm;


//import lombok.extern.slf4j.Slf4j;



@Controller
@RequestMapping("/user")
//@Slf4j
public class ClockInController {
	
	@Autowired
	private WorkTimeService workTimeService;
		
	//@Autowired
	//private ModelMapper modelMapper; 
	
	//勤怠打刻画面の表示
	@GetMapping("/clockIn")
	public String getClockIn(){
				
		//勤怠打刻画面に遷移
	return "user/clockIn";
	}
	
	
	//出勤ボタン処理
	@PostMapping(value = "/clockIn", params = "attendance")
	public String attendanceTime() {
		
		//ここから案①
		//LocalDateTimeのインスタンス
		/*LocalDateTime ldt = LocalDateTime.now();
		
		WorkTimeEntity worktime = new WorkTimeEntity();
		worktime.setWorkDate(ldt);
		worktime.setStartTime(ldt);
		//WorkTimeEntity workTime = modelMapper.map(sdate,WorkTimeEntity.class);
		
		workTimeService.worktimeSignup(worktime);*/
		//案①終了
		
		//ここから案②
		//LocalDateTimeで現在日時の取得
		LocalDateTime ldt = LocalDateTime.now();

		
		//Date出力形式を指定
		DateTimeFormatter dtfdate = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		DateTimeFormatter dtftime = DateTimeFormatter.ofPattern("HH:mm:ss");
		
		//日付型をString型に変換
		String strdate = ldt.format(dtfdate);
		String strtime = ldt.format(dtftime);
		
		WorkTimeEntity worktime = new WorkTimeEntity();
		worktime.setWorkDate(strdate);
		worktime.setStartTime(strtime);

		workTimeService.worktimeSignup(worktime);
		//案②終了
		
		//出勤ボタン押した時の処理、退勤を無効化
		//document.getElementById('on').addEventListener('click',function() {
		//document.querySelector('input[name="off"]').disabled = false;
	    	//})
		
		//onclick
		
		return "redirect:/user/clockIn";
	}
	
	//退勤ボタン処理
	@PostMapping(value = "/clockIn", params = "leaving")
	public String leavingTime() {
		
		LocalDateTime ldt = LocalDateTime.now();

		
		//Date出力形式を指定
		DateTimeFormatter dtfdate = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		DateTimeFormatter dtftime = DateTimeFormatter.ofPattern("HH:mm:ss");
		
		//日付型をString型に変換
		String strdate = ldt.format(dtfdate);
		String strtime = ldt.format(dtftime);
		
		WorkTimeEntity worktime = new WorkTimeEntity();
		worktime.setWorkDate(strdate);
		worktime.setCloseTime(strtime);

		workTimeService.worktimeupdate(worktime);

		
		
		return "redirect:/user/clockIn";
	}
	

}
