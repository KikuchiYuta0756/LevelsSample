package com.example.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import org.modelmapper.ModelMapper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

import com.example.domainUser.model.WorkTimeEntity;
import com.example.domainUser.service.WorkTimeService;
import com.example.form.CorrectWorkTimeDetailForm;

@ExtendWith(MockitoExtension.class)
public class CorrectClockInDetailsControllerTest {

    @InjectMocks
    private CorrectClockInDetailsController controller;	
	
    @Mock
    private WorkTimeService worktimeService;

    @Mock
    private Model model;

    
	@Mock
	private ModelMapper modelMapper;


    @Test
    public void testGetCorrectClockInDetail() {
        // テスト用のダミーデータ
        String workDate = "2024-05-25";
        String loginId = "testLoginId";
        WorkTimeEntity worktimeEntity = new WorkTimeEntity();
        CorrectWorkTimeDetailForm form = new CorrectWorkTimeDetailForm(); 

        // WorkTimeServiceのメソッドが呼び出されたときにダミーデータを返すように設定
        when(worktimeService.getWorkTimeOne(loginId, workDate)).thenReturn(worktimeEntity);
        when(modelMapper.map(worktimeEntity, CorrectWorkTimeDetailForm.class)).thenReturn(form);

        // コントローラーのメソッドを実行
        String viewName = controller.getCorrectClockInDetail(new CorrectWorkTimeDetailForm(), model, workDate, loginId);

        // 正しいメソッドが呼び出されたか検証
        verify(worktimeService, times(1)).getWorkTimeOne(loginId, workDate);        
        verify(model, times(1)).addAttribute(eq("correctWorkTimeDetailForm"), any(CorrectWorkTimeDetailForm.class)); // 正しいフォームオブジェクトがモデルに追加されたか検証 
        assertEquals("admin/correctClockInDetails", viewName);  // 正しいビューが返されたか検証
    }
    
    @Test
    public void testPostCorrectClockInDetail() {
        // テスト用のフォームデータ
        CorrectWorkTimeDetailForm form = new CorrectWorkTimeDetailForm();
        form.setWorkDate("2024-05-25");
        form.setLoginId("testLoginId");
        form.setStartTime("08:00");
        form.setCloseTime("17:00");

        // コントローラーのメソッドを実行
        String redirectUrl = controller.postCorrectClockInDetail(form, model);

        // 正しいメソッドが呼び出されたか検証
        verify(worktimeService, times(1)).updateWorkTimeOne(
            eq("2024-05-25"), eq("testLoginId"), eq("08:00"), eq("17:00"), any());

        // 正しいリダイレクト先URLが返されたか検証
        assertEquals("redirect:/admin/list", redirectUrl);
    }
}