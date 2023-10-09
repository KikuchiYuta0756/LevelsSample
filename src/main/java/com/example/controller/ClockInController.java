package com.example.controller;

//import java.util.Calendar;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/user")
public class ClockInController {
	
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
	
	
	//打刻後、打刻画面の再表示
	@PostMapping("/clockIn")
	public String updateClockIn() {
		
		//出勤ボタン押した時の処理、退勤を無効化
		//document.getElementById('on').addEventListener('click',function() {
		//document.querySelector('input[name="off"]').disabled = false;
	    	//})
		
		//onclick
		
		return "redirect:/user/clockIn";
	}
	

}
