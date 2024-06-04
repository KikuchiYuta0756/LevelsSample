package com.example.controller;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.servlet.view.RedirectView;

import com.example.domainUser.model.WorkTimeEntity;
import com.example.domainUser.model.WorkTimeTotalEntity;
import com.example.domainUser.service.UserService;
import com.example.domainUser.service.WorkTimeService;
import com.example.form.WorkTimeTotalForm;

@ExtendWith(MockitoExtension.class)
class ClockInListADMControllerTest {
	
    @InjectMocks
    private ClockInListADMController controller;
    
    @Mock
    private UserService userService;

    @Mock
    private Model model;

    @Mock
    private ModelMapper modelMapper;

	
	@Mock
	private WorkTimeService worktimeService;
	
    
    @Test
    public void testGetClockInListADM() {
        // Arrange
        String loginId = "testUser";
        WorkTimeTotalForm form = new WorkTimeTotalForm();
        WorkTimeTotalEntity workTimeTotal = new WorkTimeTotalEntity();
        List<WorkTimeEntity> clockList = new ArrayList<>();
        List<String> yearMonths = Arrays.asList(
                "2024-01", "2024-02", "2024-03", "2024-04", "2024-05", "2024-06",
                "2024-07", "2024-08", "2024-09", "2024-10", "2024-11", "2024-12");       
        Authentication authentication = mock(Authentication.class);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        when(authentication.getName()).thenReturn(loginId); // モックの認証情報を設定

        when(worktimeService.getClockTimes(loginId)).thenReturn(clockList);
        when(worktimeService.getworkTimesTotal(loginId)).thenReturn(workTimeTotal);
        when(modelMapper.map(workTimeTotal, WorkTimeTotalForm.class)).thenReturn(form);

        // Act
        String viewName = controller.getClockInListADM(new WorkTimeTotalForm(), model);

        // Assert
        assertEquals("admin/clockInListADM", viewName);
        verify(worktimeService).getClockTimes(loginId);
        verify(worktimeService).getworkTimesTotal(loginId);
        verify(modelMapper).map(workTimeTotal, WorkTimeTotalForm.class);
        verify(model).addAttribute("clockList", clockList);
        verify(model).addAttribute("workTimeTotalForm", form);
        verify(model).addAttribute("yearMonths", yearMonths);
    }

	
	    @Test
	    public void testGetSelectYearMonthsADM() {
	        // Setup
	        WorkTimeTotalForm form = new WorkTimeTotalForm();
	        String loginId = "testUser";
	        String selectedYearMonth = "2024-06";
	        List<WorkTimeEntity> clockList = new ArrayList<>();
	        WorkTimeTotalEntity workTimeTotal = new WorkTimeTotalEntity();
	        List<String> yearMonths = Arrays.asList(
	                "2024-01", "2024-02", "2024-03", "2024-04", "2024-05", "2024-06",
	                "2024-07", "2024-08", "2024-09", "2024-10", "2024-11", "2024-12");
	        
	        Authentication authentication = mock(Authentication.class);
	        SecurityContextHolder.getContext().setAuthentication(authentication);
	        when(authentication.getName()).thenReturn(loginId); // モックの認証情報を設定

	        when(worktimeService.getSelectYearMonth(loginId, selectedYearMonth)).thenReturn(clockList);
	        when(worktimeService.getSelectWorkTimesTotal(loginId, selectedYearMonth)).thenReturn(workTimeTotal);
	        when(modelMapper.map(workTimeTotal, WorkTimeTotalForm.class)).thenReturn(form);

	        // Exercise
	        String viewName = controller.getSelectYearMonthsADM(form, model, selectedYearMonth);

	        // Verify
	        verify(worktimeService).getSelectYearMonth(loginId, selectedYearMonth);
	        verify(worktimeService).getSelectWorkTimesTotal(loginId, selectedYearMonth);
	        verify(modelMapper).map(workTimeTotal, WorkTimeTotalForm.class);
	        verify(model).addAttribute("clockList", clockList);
	        verify(model).addAttribute("workTimeTotalForm", form);
	        verify(model).addAttribute("yearMonths", yearMonths);
	        assertEquals("admin/clockInListADM", viewName);
	    }

	    @Test
	    public void testCsvOutputADM() throws IOException {
	        // テスト用データ
	        String selectedYearMonth = "2024-06";
	        String loginId = "testUser";
	        WorkTimeEntity worktime = new WorkTimeEntity();
	        worktime.setWorkDate("2024-06-01");
	        worktime.setStartTime("09:00");
	        worktime.setCloseTime("18:00");
	        worktime.setRestTime(LocalTime.parse("01:00"));
	        worktime.setActWorkTime(LocalTime.parse("08:00"));
	        worktime.setOverTime(LocalTime.parse("00:00"));
	        
	        List<WorkTimeEntity> csvRecords = new ArrayList<>();
	        csvRecords.add(worktime);
	        
	        Authentication authentication = mock(Authentication.class);
	        SecurityContextHolder.getContext().setAuthentication(authentication);

	        
	        when(authentication.getName()).thenReturn(loginId); // モックの認証情報を設定
	        when(worktimeService.getSelectYearMonth(loginId, selectedYearMonth)).thenReturn(csvRecords);

	        // テスト対象のメソッドを呼び出し
	        RedirectView result = controller.csvOutputADM(selectedYearMonth);
	        
	        String userHome = System.getProperty("user.home");
	        Path desktopDirectoryPath = Paths.get(userHome, "/OneDrive/ドキュメント");
	        Path csvFilePath = desktopDirectoryPath.resolve("clockInList.csv");
	        assertTrue(Files.exists	(csvFilePath), "CSVファイルが保存されました");

	        // ファイル内容の確認
	        try (BufferedReader reader = new BufferedReader(new FileReader(csvFilePath.toFile()))) {
	            // ヘッダーの確認
	            String headerLine = reader.readLine();
	            String[] expectedHeader = {"\"日付\"", "\"出勤時間\"", "\"退勤時間\"", "\"休憩時間\"", "\"実働時間\"", "\"残業時間\""};
	            assertEquals(String.join(",", expectedHeader), headerLine, "CSVファイルのヘッダーが正しいこと");

	            // レコードの確認
	            String record;
	            int recordCount = 0;
	            while ((record = reader.readLine()) != null) {
	                String[] fields = record.split(",");
	                String[] expectedFields = {"\"" + csvRecords.get(recordCount).getWorkDate() + "\"",
	                           "\"" + csvRecords.get(recordCount).getStartTime().toString() + "\"",
	                           "\"" + csvRecords.get(recordCount).getCloseTime().toString() + "\"",
	                           "\"" + csvRecords.get(recordCount).getRestTime().toString() + "\"",
	                           "\"" + csvRecords.get(recordCount).getActWorkTime().toString() + "\"",
	                           "\"" + csvRecords.get(recordCount).getOverTime().toString() + "\""};
	                assertArrayEquals(expectedFields, fields, "CSVファイルのレコードが正しいこと");
	            }	            
	        }
	        // テスト結果の検証
	        verify(worktimeService).getSelectYearMonth(loginId, selectedYearMonth);
	        assertEquals("/admin/clockInListADM", result.getUrl());
	    }	   
 }
