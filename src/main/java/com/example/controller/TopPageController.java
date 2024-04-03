package com.example.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domainUser.model.UserMapperEntity;
import com.example.domainUser.service.UserService;
import com.example.domainUser.service.WorkTimeService;
import com.example.form.UserDetailForm;

@Controller
@RequestMapping("/common")
public class TopPageController {
	@Autowired
	private UserService userService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private WorkTimeService workTimeService;


	/**トップページを表示*/
	@GetMapping("/TopPage")
	public String getLogin(UserDetailForm form, Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
      //ログイン認証に使用したログインIDを利用する。
      String loginId = auth.getName();
      
		//ログイン認証ユーザーの情報を取得
		UserMapperEntity users = userService.getUserOne(loginId);
		
		form = modelMapper.map(users, UserDetailForm.class);
		
		// Modelに登録
		model.addAttribute("loginUserDetailForm", form);
		
		//初回の有給付与の処理
		userService.giveFirstPaidDays();
		
		//次回以降の有給付与の処理
		userService.updateGivePaidDays();
		
		return "common/TopPage";
	}
	
	/**権限毎の打刻画面にリダイレクト*/
//    @PostMapping("/TopPage")
//    public String postLogin(){
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        //ログイン認証に使用したログインIDを利用する。
//        String usersloginId = auth.getName();
//        
//        
//		//ログイン認証ユーザーの情報を取得
//		UserMapperEntity users = userService.getUserOne(usersloginId);
//		Integer userAuthority = users.getAuthorityFlg();
//        
//        if (userAuthority == 2) {
//            return "redirect:/admin/clockInADM"; // ADMINの場合は/admin/clockInADMにリダイレクト
//        } else {
//            return "redirect:/user/clockIn"; // ADMIN以外の場合は/user/clockInにリダイレクト
//        }
//    }

	/**トップページを表示*/
	@GetMapping("/division")
	public String getDivisionLogin(UserDetailForm form, Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        //ログイン認証に使用したログインIDを利用する。
        String usersloginId = auth.getName();
        
        
		//ログイン認証ユーザーの情報を取得
		UserMapperEntity users = userService.getUserOne(usersloginId);
		Integer userAuthority = users.getAuthorityFlg();
 
		//初回の有給付与の処理
		userService.giveFirstPaidDays();
		
		//次回以降の有給付与の処理
		userService.updateGivePaidDays();
		
		
		
        if (userAuthority == 2) {
            return "redirect:/admin/clockInADM"; // ADMINの場合は/admin/clockInADMにリダイレクト
        } else {
            return "redirect:/user/clockIn"; // ADMIN以外の場合は/user/clockInにリダイレクト
        }
    
	}
    
}
