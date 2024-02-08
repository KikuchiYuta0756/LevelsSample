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


import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequestMapping("/admin")
//@Slf4j

public class ClockInADMController {
	
	//勤怠打刻画面（Admin用）表示
	@GetMapping("/clockInADM")
	public String getClockInADM(){
				
		//勤怠打刻画面に遷移
	return "admin/clockInADM";
	}


}
