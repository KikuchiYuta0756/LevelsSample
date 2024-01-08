package com.example.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.example.domainUser.model.WorkTimeEntity;
import com.example.domainUser.service.WorkTimeService;


@Controller
@RequestMapping("/user")
public class RequestRecordController {
	
	//申請履歴を表示
	@GetMapping("/requestRecord")
	public String getRequestRecord() {
	
	return "user/requestRecord";
 }

}
