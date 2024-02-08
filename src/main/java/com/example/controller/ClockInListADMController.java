package com.example.controller;

	import java.time.LocalDateTime;
	import java.time.format.DateTimeFormatter;
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
	import com.example.form.WorkTimeForm;


	@Controller
	@RequestMapping("/admin")
	public class ClockInListADMController {
		
		@Autowired
		private WorkTimeService worktimeService;
		
		@Autowired
		private ModelMapper modelMapper;
		
		//勤怠一覧（月次）の表示
		@GetMapping("/clockInListADM")
		public String getClockInList(WorkTimeForm form, Model model){
			
			//勤怠一覧（月次）を取得
			List<WorkTimeEntity> clockList = worktimeService.getClockTimes();
			
			//Modelに登録
			model.addAttribute("clockList", clockList);
			
			//LocalDateTimeで現在日時の取得
			LocalDateTime ldtnow = LocalDateTime.now();
			
			//Date出力形式を指定
			DateTimeFormatter dtfdate = DateTimeFormatter.ofPattern("yyyy-MM");
			
			String strdate = ldtnow.format(dtfdate);
			System.out.println("現在の月"+ strdate);	


			
//			//勤怠情報の各合計（月次）を取得
//			WorkTimeEntity clockInSum = worktimeService.getClockTimesSum();
//			
//			//UserMapperEntityをformに変換
//			form = modelMapper.map(clockInSum, WorkTimeForm.class);
//			
//			//Modelに登録
//			model.addAttribute("workTimeForm",form);


			
		return "admin/clockInListADM";
		}

		//前月の勤怠一覧（月次）の表示
		@GetMapping("/clockInListADMLastMonth")
		public String getClockInListLastMonth(Model model){
			
			//前月の勤怠一覧（月次）を取得
			List<WorkTimeEntity> clockList = worktimeService.getClockTimesLastMonth();
			
			//Modelに登録
			model.addAttribute("clockList", clockList);
			
		return "admin/clockInListADM";
		}

		//来月の勤怠一覧（月次）の表示
		@GetMapping("/clockInListADMNextMonth")
		public String getClockInListNextMonth(Model model){
			
			//前月の勤怠一覧（月次）を取得
			List<WorkTimeEntity> clockList = worktimeService.getClockTimesNextMonth();
			
			//Modelに登録
			model.addAttribute("clockList", clockList);
			
		return "admin/clockInListADM";
		}

		



}
