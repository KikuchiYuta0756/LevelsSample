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
		
		//Calendarクラスで月・日・現在時刻の取得
       // Calendar calendar = Calendar.getInstance();
        //System.out.println(calendar.get(Calendar.MONTH) + 1);
        //System.out.println(calendar.get(Calendar.DATE));
        //System.out.println(calendar.getTime());
		
		//勤怠打刻画面に遷移
	return "user/clockIn";
	}
	
	
	//出勤ボタン処理
	@PostMapping(value = "/clockIn", params = "attendance")
	public String attendanceTime(String sdate, String stime) {
		
		//LocalDateTimeのインスタンス
		LocalDateTime ldt = LocalDateTime.now();
		
		//SimpleDateFormat sdfdate = new SimpleDateFormat("yyyy年 MM月　dd日");
		//SimpleDateFormat sdftime = new SimpleDateFormat("HH時 mm分");
		
		//出力形式を指定
		//DateTimeFormatter dtfdate = DateTimeFormatter.ofPattern("MM-dd");
		DateTimeFormatter dtftime = DateTimeFormatter.ofPattern("HH:mm");
		
		
		//String strdate = sdfdate.format(ldt);
		//String strtime = sdftime.format(ldt);
		
		//日付型をString型に変換
		//String strdate = ldt.format(dtfdate);
		String strtime = ldt.format(dtftime);
		
		WorkTimeEntity workTime = new WorkTimeEntity();
		//workTime.setWorkDate(strdate);
		workTime.setStartTime(strtime);
		//WorkTimeEntity workTime = modelMapper.map(sdate,WorkTimeEntity.class);
		
		workTimeService.worktimeSignup(workTime);
		//workTimeService.worktimeSignup(strtime);
		
		//System.out.println(strdate);
		//System.out.println(strtime);
		
		//DBレコードに登録 ※ゴミコード
		//workTime.setWorkDate(strdate);
		//workTime.setStartTime(strtime);
		
		//月日を取得
		//int mdate = ldt.getMonthValue();
		//int ddate = ldt.getDayOfMonth();
		//System.out.println(mdate+"/"+ddate);
		
		
		
		//時間を取得
		//int htime = ldt.getHour();
		//int mtime = ldt.getMinute();
		//System.out.println(htime+":"+mtime);
		
		//出勤日時をDB登録
		//workTime.set
		
		
		//日の取得
		
		//LocalDateTime ldt = LocalDateTime.now();
		 //Spring
		
		//勤怠時間を登録
		//workTimeService.worktimeSignup(wtime);
		
		
		
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
		
		return "redirect:/user/clockIn";
	}
	

}
