package com.example.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import com.example.domainUser.model.WorkTimeEntity;
import com.example.domainUser.model.WorkTimeTotalEntity;
import com.example.domainUser.service.WorkTimeService;
import com.example.form.WorkTimeTotalForm;
import com.opencsv.CSVWriter;


	@Controller
	@RequestMapping("/admin")
	public class ClockInListADMController {
		
		@Autowired
		private WorkTimeService worktimeService;	
		
		@Autowired
		private ModelMapper modelMapper;
		
		//勤怠一覧（月次）の表示
		@GetMapping("/clockInListADM")
		public String getClockInListADM(WorkTimeTotalForm form, Model model){
		    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		    //ログイン認証に使用したログインIDを利用する。
		    String loginId = auth.getName();
			
			//勤怠一覧（月次）を取得
			List<WorkTimeEntity> clockList = worktimeService.getClockTimes(loginId);
            if(clockList == null) {
            	clockList = new ArrayList<>();
            }
			
			//勤怠情報の各合計（月次）を取得
			WorkTimeTotalEntity workTimeTotal = worktimeService.getworkTimesTotal(loginId);
            if(workTimeTotal == null) {
            	workTimeTotal = new WorkTimeTotalEntity();
            }
			
			//WorkTimeTotalEntityをformに変換
			form = modelMapper.map(workTimeTotal, WorkTimeTotalForm.class);

			//Modelに登録
			model.addAttribute("clockList", clockList);
			model.addAttribute("workTimeTotalForm", form);
			
			//年月リストを作成
			List<String> yearMonths = Arrays.asList(
					"2024-01", "2024-02", "2024-03", "2024-04", "2024-05", "2024-06",
					"2024-07", "2024-08", "2024-09", "2024-10", "2024-11", "2024-12");
			//Modelに追加
			model.addAttribute("yearMonths", yearMonths);

		return "admin/clockInListADM";
		}

		//年月選択の勤怠一覧を表示
		@PostMapping("/selectYearMonths")
		public String getSelectYearMonthsADM(WorkTimeTotalForm form, Model model, 
				@RequestParam("selectYearMonth") String selectedYearMonth){

		    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			//ログイン認証に使用したログインIDを利用する。
		    String loginId = auth.getName();
			
			//選択された年月の勤怠一覧を表示する
			List<WorkTimeEntity> clockList =worktimeService.getSelectYearMonth(loginId, selectedYearMonth);
			if(clockList == null) {
				clockList = new ArrayList<>(); 
			}
			
			//勤怠情報の各合計（月次）を取得
			WorkTimeTotalEntity workTimeTotal = worktimeService.getSelectWorkTimesTotal(loginId, selectedYearMonth);
			if(workTimeTotal == null) {
				workTimeTotal = new WorkTimeTotalEntity();
			}
			
			//WorkTimeTotalEntityをformに変換
			form = modelMapper.map(workTimeTotal, WorkTimeTotalForm.class);

			//Modelに登録
			model.addAttribute("clockList", clockList);
			model.addAttribute("workTimeTotalForm", form);
			
			//年月リストを作成
			List<String> yearMonths = Arrays.asList(
					"2024-01", "2024-02", "2024-03", "2024-04", "2024-05", "2024-06", "2024-07", "2024-08", "2024-09", "2024-10", "2024-11", "2024-12");
			
			//Modelに追加
			model.addAttribute("yearMonths", yearMonths);
			
		return "admin/clockInListADM";
		}
		
     	//CSV出力の処理
    	@PostMapping("/csvOutput")
        public RedirectView csvOutputADM(@RequestParam("selectYearMonth") String selectedYearMonth) {
    		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		//ログイン認証に使用したログインIDを利用する。
		String loginId = auth.getName();

     	//選択された年月の勤怠一覧を表示する
     	List<WorkTimeEntity> csvRecords = worktimeService.getSelectYearMonth(loginId, selectedYearMonth);
  	
     // ユーザーのホームディレクトリを取得
     	String userHome = System.getProperty("user.home");

     	// CSVファイルの保存先ディレクトリをデスクトップフォルダに設定
     	Path desktopDirectoryPath = Paths.get(userHome, "/OneDrive/ドキュメント");

     	// CSVファイルパス
     	Path csvFilePath = desktopDirectoryPath.resolve("clockInList.csv"); // デスクトップに保存するように変更

    	//CSVWriterの初期化
    	try(CSVWriter writer = new CSVWriter(Files.newBufferedWriter(csvFilePath, StandardOpenOption.CREATE, StandardOpenOption.WRITE))){
    		//CSVヘッダーの書き込み
    		String[] header = {"日付", "出勤時間", "退勤時間","休憩時間", "実働時間", "残業時間"};
            writer.writeNext(header);
            
            // DateTimeFormatter を定義
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

    		//DBレコードをCSVに書き込む
			for(WorkTimeEntity record : csvRecords) {
				String[] data = {
						record.getWorkDate(),
						record.getStartTime(),
						record.getCloseTime(),
						formatLocalTime(record.getRestTime(), timeFormatter),
						formatLocalTime(record.getActWorkTime(), timeFormatter),
						formatLocalTime(record.getOverTime(), timeFormatter)
				};
				writer.writeNext(data);
			}					
	   System.out.println("CSV ファイルにデータを書き込みました: " + csvFilePath);
	}catch(IOException e) {
		e.printStackTrace();
	}
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("/admin/clockInListADM");
        return redirectView;
    	
    	
   }
		private String formatLocalTime(LocalTime time, DateTimeFormatter formatter) {
            return time != null ? time.format(formatter) : "";		
   } 	
		
		
}