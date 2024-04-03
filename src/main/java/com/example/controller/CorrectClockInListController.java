package com.example.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;

import com.example.domainUser.model.WorkTimeEntity;
import com.example.domainUser.model.WorkTimeTotalEntity;
import com.example.domainUser.service.WorkTimeService;
import com.example.form.CorrectWorkTimeForm;
import com.example.form.WorkTimeForm;
import com.example.form.WorkTimeTotalForm;

@Controller
@RequestMapping("/admin")
public class CorrectClockInListController {

	@Autowired
	private WorkTimeService worktimeService;

	@Autowired
	private ModelMapper modelMapper;

	// 勤怠一覧（月次）の表示（ユーザ毎）
	@GetMapping("/correctClockInList/{loginId}")
	public String getCorrectBeforeClockInList(CorrectWorkTimeForm form, Model model,
			@PathVariable("loginId")String loginId){
		
		 //ユーザの勤怠一覧を取得 
		 List<WorkTimeEntity> correctWorkTimeList = worktimeService.getCorrectClockTimes(loginId);

		 
	        // エンティティをモデルに変換
	        List<WorkTimeEntity> models = new ArrayList<>();
	        for (WorkTimeEntity entity : correctWorkTimeList) {
	        	WorkTimeEntity workTimeModel = new WorkTimeEntity();
	        	workTimeModel.setWorkDate(entity.getWorkDate());
	        	workTimeModel.setStartTime(entity.getStartTime());
	        	workTimeModel.setCloseTime(entity.getCloseTime());
	        	workTimeModel.setRestTime(entity.getRestTime());
	        	workTimeModel.setActWorkTime(entity.getActWorkTime());
	        	workTimeModel.setOverTime(entity.getOverTime());
	            models.add(workTimeModel);
	        }
	        
	        // フォームクラスに設定
	        CorrectWorkTimeForm correstWorkTimeForm = new CorrectWorkTimeForm();
	        correstWorkTimeForm.setModels(models);
		 
		  //CorrectWorkTimeFormに変換する
		 //form = modelMapper.map(correctWorkTimeList, CorrectWorkTimeForm.class); 

		  model.addAttribute("correctWorkTimeList", correstWorkTimeForm);	
		  
			//年月リストを作成
			List<String> yearMonths = Arrays.asList(
					"2024-01", "2024-02", "2024-03", "2024-04", "2024-05", "2024-06", "2024-07", "2024-08", "2024-09", "2024-10", "2024-11", "2024-12");
			
			//Modelに追加
			model.addAttribute("yearMonths", yearMonths);

	
    return "admin/correctClockInList";
}

	// 勤怠一覧（月次）の表示（ユーザ毎）
	@PostMapping("/correctClockInList")
	public String postCorrectBeforeClockInList(CorrectWorkTimeForm form, Model model){
		
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

	// 勤怠修正一覧（月次）の表示（ユーザ毎）
	@PostMapping("/selectCorrectYearMonths")
	public String getSelectCorrectYearMonths(CorrectWorkTimeForm form, Model model,
			@RequestParam("selectYearMonth") String selectedYearMonth){
		
		String loginId = form.getLoginId();
		
		 //ユーザの勤怠一覧を取得 
		 List<WorkTimeEntity> correctWorkTimeList = worktimeService.getSelectCorrectYearMonth(loginId, selectedYearMonth);

	        // エンティティをモデルに変換
	        List<WorkTimeEntity> models = new ArrayList<>();
	        for (WorkTimeEntity entity : correctWorkTimeList) {
	        	WorkTimeEntity workTimeModel = new WorkTimeEntity();
	        	workTimeModel.setWorkDate(entity.getWorkDate());
	        	workTimeModel.setStartTime(entity.getStartTime());
	        	workTimeModel.setCloseTime(entity.getCloseTime());
	        	workTimeModel.setRestTime(entity.getRestTime());
	        	workTimeModel.setActWorkTime(entity.getActWorkTime());
	        	workTimeModel.setOverTime(entity.getOverTime());
	            models.add(workTimeModel);
	        }
	        
	        // フォームクラスに設定
	        CorrectWorkTimeForm correstWorkTimeForm = new CorrectWorkTimeForm();
	        correstWorkTimeForm.setModels(models);


		 
		  //CorrectWorkTimeFormに変換する
		 //form = modelMapper.map(correctWorkTimeList, CorrectWorkTimeForm.class); 

		  model.addAttribute("correctWorkTimeList", correstWorkTimeForm);	
		  
			//年月リストを作成
			List<String> yearMonths = Arrays.asList(
					"2024-01", "2024-02", "2024-03", "2024-04", "2024-05", "2024-06", "2024-07", "2024-08", "2024-09", "2024-10", "2024-11", "2024-12");
			
			//Modelに追加
			model.addAttribute("yearMonths", yearMonths);

	
    return "admin/correctClockInList";
}



}
