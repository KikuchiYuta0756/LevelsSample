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
import org.springframework.web.bind.annotation.SessionAttributes;

import java.lang.reflect.Type;
import java.util.ArrayList;

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
		
		  List<WorkTimeEntity> correctWorkTimeList = worktimeService.getCorrectClockTimes(loginId);

		  model.addAttribute("correctWorkTimeList", correctWorkTimeList);	
	
    return "admin/correctClockInList";
}

	/*
	 * public List<CorrectWorkTimeForm> convertToFormList(List<WorkTimeEntity>
	 * workTimeList) { List<CorrectWorkTimeForm> formList = new ArrayList<>(); for
	 * (WorkTimeEntity employee : workTimeList) { CorrectWorkTimeForm form = new
	 * CorrectWorkTimeForm(); form.setEmployee Id(String.valueOf(employee.getId()));
	 * form.setEmployeeName(employee.getName());
	 * form.setEmployeeDepartment(employee.getDepartment()); formList.add(form); }
	 * return formList; } }
	 */

	/*
	 * public String getCorrectBeforeClockInList(CorrectWorkTimeForm form, Model
	 * model,
	 * 
	 * @PathVariable("loginId")String loginId){
	 * 
	 * //勤怠一覧（月次）を取得 /* List<WorkTimeEntity> correctclockList =
	 * worktimeService.getCorrectClockTimes(loginId); List<CorrectWorkTimeForm>
	 * formList = new ArrayList<>(); for (WorkTimeEntity entity : correctclockList)
	 * { CorrectWorkTimeForm correctworkform =
	 * CorrectWorkTimeForm.fromEntity(entity); formList.add(correctworkform); }
	 * System.out.println(formList);
	 */
	// WorkTimeEntityをformに変換
	// form = modelMapper.map(correctclockList, CorrectWorkTimeForm.class);

	// Modelに登録

//	//勤怠一覧（月次）の表示
//	@PostMapping("/clockInListADM")
//	public String updateClockInList(WorkTimeForm form, Model model){
//		
////		//勤怠を更新
////		worktimeService.updateClockInOne(
////				form.getWorkDate(),
////				form.getUserId(),
////				form.getStartTime(),
////				form.getCloseTime(),
////				form.getRestTime()
////				);
//		
//	}

}
