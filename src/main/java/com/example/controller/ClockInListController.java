package com.example.controller;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.domainUser.model.WorkTimeEntity;
import com.example.domainUser.model.WorkTimeTotalEntity;
import com.example.domainUser.service.WorkTimeService;
import com.example.form.UserDetailForm;
import com.example.form.WorkTimeForm;
import com.example.form.WorkTimeTotalForm;


@Controller
@RequestMapping("/user")
public class ClockInListController {
	
	@Autowired
	private WorkTimeService worktimeService;	
	
	@Autowired
	private ModelMapper modelMapper;
	
	//勤怠一覧（月次）の表示
	@GetMapping("/clockInList")
	public String getClockInList(WorkTimeTotalForm form, Model model){
		
		//勤怠一覧（月次）を取得
		List<WorkTimeEntity> clockList = worktimeService.getClockTimes();
		
		//Modelに登録
		model.addAttribute("clockList", clockList);
		
		//勤怠情報の各合計（月次）を取得
		WorkTimeTotalEntity workTimeTotal = worktimeService.getworkTimesTotal();
		
		//WorkTimeTotalEntityをformに変換
		form = modelMapper.map(workTimeTotal, WorkTimeTotalForm.class);

		//Modelに登録
		model.addAttribute("workTimeTotalForm", form);
		
		
		//年月リストを作成
		List<String> yearMonths = Arrays.asList(
				"2023-12", "2024-01", "2024-02", "2024-03", "2024-04", "2024-05", "2024-06", "2024-07", "2024-08", "2024-09", "2024-10", "2024-11");
		
		//Modelに追加
		model.addAttribute("yearMonths", yearMonths);

	return "user/clockInList";
	}

	//年月選択の勤怠一覧を表示
	@PostMapping("/selectYearMonths")
	public String getSelectYearMonths(WorkTimeTotalForm form, Model model, 
			@RequestParam("selectYearMonth") String selectedYearMonth){
		
		//選択された年月の勤怠一覧を表示する
		List<WorkTimeEntity> clockList =worktimeService.getSelectYearMonth(selectedYearMonth);
		
		//Modelに登録
		model.addAttribute("clockList", clockList);
		
		//勤怠情報の各合計（月次）を取得
		WorkTimeTotalEntity workTimeTotal = worktimeService.getSelectWorkTimesTotal(selectedYearMonth);
		
		//WorkTimeTotalEntityをformに変換
		form = modelMapper.map(workTimeTotal, WorkTimeTotalForm.class);

		//Modelに登録
		model.addAttribute("workTimeTotalForm", form);
		
		//年月リストを作成
		List<String> yearMonths = Arrays.asList(
				"2023-12", "2024-01", "2024-02", "2024-03", "2024-04", "2024-05", "2024-06", "2024-07", "2024-08", "2024-09", "2024-10", "2024-11");
		
		//Modelに追加
		model.addAttribute("yearMonths", yearMonths);


		
		
	return "user/clockInList";
	}
	
	
	//CSV出力の処理
	@PostMapping("/csvOutput")
    public void mainCSV(String[] args,
    		@RequestParam("selectYearMonth") String selectedYearMonth) {
       
		//選択された年月の勤怠一覧を表示する
		List<WorkTimeEntity> csvRecords = worktimeService.getSelectYearMonth(selectedYearMonth);
		
		//CSVにエクスポート
		exportToCsv(csvRecords,"ClockInList.csv");
		
	}
		
	public static void exportToCsv(List<WorkTimeEntity> csvRecords, String csvFilePath) {
	try(FileWriter writer = new FileWriter("ClockInList.csv",false)){
	
	//CSVヘッダーに書き込む
	writer.append("日付,出勤時間,退勤時間,休憩時間,実働時間,残業時間\n");	
	
	//DateTimeFormatterを定義
	DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
	
	
	//DBレコードを処理してCSVに書き込む
		for(WorkTimeEntity record : csvRecords) {
			
			writer.append(record.getWorkDate());
			writer.append(",");
			writer.append(record.getStartTime() != null ? record.getStartTime() : "");
			writer.append(",");
			writer.append(record.getCloseTime() != null ? record.getCloseTime() : "");
			writer.append(",");
			 
			//LocalTime型の休憩時間カラムを文字列に変換して書き込む
			 if (record.getRestTime() != null) {
			String resttimeToString = record.getRestTime().format(timeFormatter);
			writer.append(resttimeToString);
			 } else {
		    writer.append("");} 
			writer.append(",");
			//LocalTime型の実働時間カラムを文字列に変換して書き込む
			if (record.getActWorkTime() != null) {
			String actworktimeToString = record.getActWorkTime().format(timeFormatter);
			writer.append(actworktimeToString);
			} else {
			writer.append("");}
			writer.append(",");
			//LocalTime型の残業時間カラムを文字列に変換して書き込む
			if(record.getOverTime() != null) {
			String overtimeToString = record.getOverTime().format(timeFormatter);
			writer.append(overtimeToString);
			} else {
			writer.append("");}
			writer.append("\n");
			
		}
		
		System.out.println("csvファイルへの書き込みが完了しました");
		
	} catch (IOException ex) {
        ex.printStackTrace();
	}
  }
} 	 
	
