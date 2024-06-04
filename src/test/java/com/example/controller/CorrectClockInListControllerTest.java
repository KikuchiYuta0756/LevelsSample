package com.example.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.ui.Model;

import com.example.domainUser.model.UserMapperEntity;
import com.example.domainUser.model.WorkTimeEntity;
import com.example.domainUser.service.UserService;
import com.example.domainUser.service.WorkTimeService;
import com.example.form.CorrectWorkTimeForm;

@ExtendWith(MockitoExtension.class)
class CorrectClockInListControllerTest {
	
    @InjectMocks
    private CorrectClockInListController controller;
    
    @Mock
    private UserService userService;
    
    @Mock
    private WorkTimeService worktimeService;

    @Mock
    private Model model;

    @Mock
    private ModelMapper modelMapper;

	   @Test
	    public void testGetCorrectBeforeClockInList() {
	        // テスト用データ
	        String loginId = "testLoginId";
	        UserMapperEntity userDetail = new UserMapperEntity(); // 適切なユーザーエンティティを設定する
	        WorkTimeEntity worktime = new WorkTimeEntity();
	        CorrectWorkTimeForm form = new CorrectWorkTimeForm();
	        List<WorkTimeEntity> clockList = new ArrayList<>();
	        clockList.add(worktime);
	        List<String> yearMonths = Arrays.asList(
          "2024-01", "2024-02", "2024-03", "2024-04", "2024-05", "2024-06",
          "2024-07", "2024-08", "2024-09", "2024-10", "2024-11", "2024-12");

	        // モックの振る舞いを設定
	        when(userService.getUserOne(loginId)).thenReturn(userDetail);
	        when(worktimeService.getClockTimes(loginId)).thenReturn(clockList);

	        // テスト対象のメソッドを呼び出す
	        String viewName = controller.getCorrectBeforeClockInList(form, model, loginId);

	        // モックが適切に呼び出されたことを検証
	        verify(userService).getUserOne(loginId);
	        verify(worktimeService).getClockTimes(loginId);

	        // モックの振る舞いに基づいて適切なデータがモデルに追加されたことを検証
	        verify(model).addAttribute("userDetail", userDetail);
	        verify(model).addAttribute("clockList", clockList);
	        verify(model).addAttribute("yearMonths", yearMonths);
	        
	        // ビュー名が正しいことを検証
	        assertEquals("admin/correctClockInList", viewName);
	    }
	   
	   
	    @Test
	    public void testGetSelectCorrectYearMonths() {
	        // テスト用データ
	        String loginId = "testLoginId";
	        String selectedYearMonth = "2024-06";
	        UserMapperEntity userDetails = new UserMapperEntity(); // 適切なユーザーエンティティを設定する
	        WorkTimeEntity worktime = new WorkTimeEntity();
	        CorrectWorkTimeForm form = new CorrectWorkTimeForm();
	        List<WorkTimeEntity> clockList = new ArrayList<>();
	        clockList.add(worktime);
	        List<String> yearMonths = Arrays.asList(
	                "2024-01", "2024-02", "2024-03", "2024-04", "2024-05", "2024-06",
	                "2024-07", "2024-08", "2024-09", "2024-10", "2024-11", "2024-12");
	        
	        // モックの振る舞いを設定
	        when(userService.getUserOne(loginId)).thenReturn(userDetails);
	        when(worktimeService.getSelectYearMonth(loginId, selectedYearMonth)).thenReturn(clockList);

	        // テスト対象のメソッドを呼び出す
	        String viewName = controller.getSelectCorrectYearMonths(loginId, form, model, selectedYearMonth);

	        // モックが適切に呼び出されたことを検証
	        verify(userService).getUserOne(loginId);
	        verify(worktimeService).getSelectYearMonth(loginId, selectedYearMonth);

	        // モックの振る舞いに基づいて適切なデータがモデルに追加されたことを検証
	        verify(model).addAttribute("userDetail", userDetails);
	        verify(model).addAttribute("clockList", clockList);
	        verify(model).addAttribute("yearMonths", yearMonths);
	        
	        // ビュー名が正しいことを検証
	        assertEquals("admin/correctClockInList", viewName);
	    }
	    
	    @Test
	    public void testPostCorrectBeforeClockInList() {
	        // テスト用データ
	        CorrectWorkTimeForm form = new CorrectWorkTimeForm();

	        // テスト対象のメソッドを呼び出す
	        String viewName = controller.postCorrectBeforeClockInList(form, model);

	        // モックが適切に呼び出されたことを検証
	        verify(worktimeService).updateWorkTimeOne(
	    	        form.getWorkDate(),
	    	        form.getLoginId(),
	    	        form.getStartTime(),
	    	        form.getCloseTime(),
	    	        form.getRestTime()
             );

	        // ビュー名が正しいことを検証
	        assertEquals("admin/correctClockInList", viewName);
	    }



}
