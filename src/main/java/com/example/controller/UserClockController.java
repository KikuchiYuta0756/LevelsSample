package com.example.controller;

//import java.util.Calendar;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserClockController {
	
	//勤怠打刻画面の表示
	@GetMapping("/clockIn")
	public String getUserClock(){
		
		//Calendarクラスで月・日・現在時刻の取得
       // Calendar calendar = Calendar.getInstance();
        //System.out.println(calendar.get(Calendar.MONTH) + 1);
        //System.out.println(calendar.get(Calendar.DATE));
        //System.out.println(calendar.getTime());
		//勤怠打刻画面に遷移
	return "user/clockIn";
	}

}
