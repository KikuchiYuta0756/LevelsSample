package com.example.controller;

import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.application.service.UserApplicationService;
import com.example.domainUser.model.DepartmentEntity;
import com.example.domainUser.model.RoleEntity;
import com.example.domainUser.model.UserMapperEntity;
import com.example.domainUser.model.WorkTimeEntity;
import com.example.domainUser.service.UserService;
import com.example.domainUser.service.WorkTimeService;
import com.example.form.CorrectWorkTimeForm;
import com.example.form.UserDetailForm;
import com.example.form.CorrectWorkTimeDetailForm;


@Controller
public class CorrectClockInDetailsController {
	
	@Autowired
	private WorkTimeService worktimeService;
	
	@Autowired
	private ModelMapper modelMapper;

	
	// 勤怠詳細の表示（ユーザ毎）
    //@GetMapping("/admin/correctClockInDetails/{workDate}/{loginId}")
    @GetMapping("/admin/correctClockInDetails/{workDate}")
    public String getCorrectClockInDetail(
    		CorrectWorkTimeDetailForm form, Model model,
    		@PathVariable("workDate")String workDate,
    		@RequestParam("loginId")String loginId) {
    				    	
        System.out.println("workDate: " + workDate);
        System.out.println("loginId: " + loginId);

	
		//勤怠詳細の1件取得
    	WorkTimeEntity worktime = worktimeService.getWorkTimeOne(loginId, workDate);

		//UserMapperEntityをformに変換
		form = modelMapper.map(worktime, CorrectWorkTimeDetailForm.class);
		
		//Modelに登録
		model.addAttribute("correctWorkTimeDetailForm",form);
			
		//ユーザー詳細画面を表示
		return"admin/correctClockInDetails";
    }
    
    /**ユーザー更新処理*/
    @PostMapping("/admin/correctClockInDetails")
    public String postCorrectClockInDetail(
    		CorrectWorkTimeDetailForm form, Model model){
    	
    	System.out.println("form"+ form);
    	
    	//勤怠詳細を更新
    	worktimeService.updateWorkTimeOne(
    			form.getWorkDate(),
    			form.getLoginId(),
    			form.getStartTime(),
    			form.getCloseTime(),
    			form.getRestTime()
       			);
    	
    	//勤怠詳細画面に遷移
    	return"redirect:/admin/list";
    }


}
