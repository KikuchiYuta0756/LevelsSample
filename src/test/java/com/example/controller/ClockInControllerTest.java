package com.example.controller;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;
import org.modelmapper.ModelMapper;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import com.example.domainUser.model.UserMapperEntity;
import com.example.domainUser.model.WorkTimeEntity;
import com.example.domainUser.service.UserService;
import com.example.domainUser.service.WorkTimeService;
import com.example.form.UserDetailForm;

@ExtendWith(MockitoExtension.class)
public class ClockInControllerTest {

    @InjectMocks
    private ClockInController clockInController;	
	
    @Mock
    private UserService userService;

    @Mock
    private Model model;    
    
    @Mock
    private WorkTimeService workTimeService;
    
    @Mock
    private UserMapperEntity userMapperEntity;
    
    @Mock
    private ModelMapper modelMapper;
    
    @Mock
    private Clock clock;

    @Test
    public void testGetClockIn() {
        // テスト用データ
    	String loginId = "testLoginId";
        UserDetailForm form = new UserDetailForm();
        form.setWorkFlg(1);

        Authentication authentication = mock(Authentication.class);
        when(authentication.getName()).thenReturn(loginId);

        SecurityContext securityContext = mock(SecurityContext.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);

        when(userService.getWorkFlg(loginId)).thenReturn(userMapperEntity);
        when(modelMapper.map(userMapperEntity, UserDetailForm.class)).thenReturn(form);
        
        //実行
        String viewName = clockInController.getClockIn(new UserDetailForm(), model);

        //検証
        assertEquals("user/clockIn", viewName);  //メソッドの戻り値を検証

        // Modelに正しいオブジェクトが登録されたか検証
        verify(model).addAttribute(eq("userWorkFlg"), any(UserDetailForm.class));
    }        

    @Test
    public void testAttendanceTime() {
        //テストユーザの準備
    	String loginId = "testLoginId";
 
        Authentication authentication = mock(Authentication.class); //Authenticationのモック化
        when(authentication.getName()).thenReturn(loginId);         //when()の引数にはモック化されたオブジェクトをセットする必要がある

        SecurityContext securityContext = mock(SecurityContext.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);

        //Setup
    	LocalDateTime firstPatternDate = LocalDateTime.of(2024, 5, 15, 9, 15);
    	Clock fixedClock = Clock.fixed(firstPatternDate.atZone(ZoneId.systemDefault()).toInstant(),ZoneId.systemDefault());
        lenient().when(clock.instant()).thenReturn(fixedClock.instant());
        lenient().when(clock.getZone()).thenReturn(fixedClock.getZone());

        // Mocking workTimeService.startTimeSignup() method
        WorkTimeEntity worktime = new WorkTimeEntity();
        worktime.setWorkDate("2024-05-15");
        worktime.setStartTime("09:10");
        worktime.setLoginId(loginId);
        doNothing().when(workTimeService).startTimeSignup(any(WorkTimeEntity.class));
        
        //Mocking userService.getWorkFlgLeaving() method
        doNothing().when(userService).getWorkFlgLeaving(loginId);
        
    	//Excute
    	String viewName = clockInController.attendanceTime();
    	
    	//Verify
        verify(workTimeService, times(1)).startTimeSignup(any(WorkTimeEntity.class));
        verify(userService, times(1)).getWorkFlgLeaving(loginId);
        assertEquals("redirect:/user/clockIn", viewName);
    	
    	}
    
    @Test
    public void testLeavingTime() {
    	
        //テストユーザの準備
    	String loginId = "testLoginId";
 
        Authentication authentication = mock(Authentication.class); //Authenticationのモック化
        when(authentication.getName()).thenReturn(loginId);         //when()の引数にはモック化されたオブジェクトをセットする必要がある

        SecurityContext securityContext = mock(SecurityContext.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);

        //Setup
    	LocalDateTime firstPatternDate = LocalDateTime.of(2024, 5, 15, 9, 16);
    	Clock fixedClock = Clock.fixed(firstPatternDate.atZone(ZoneId.systemDefault()).toInstant(),ZoneId.systemDefault());
        lenient().when(clock.instant()).thenReturn(fixedClock.instant());
        lenient().when(clock.getZone()).thenReturn(fixedClock.getZone());

        // Mocking workTimeService.startTimeSignup() method
        WorkTimeEntity worktime = new WorkTimeEntity();
        worktime.setWorkDate("2024-05-15");
        worktime.setCloseTime("09:20");
        worktime.setLoginId(loginId);
        doNothing().when(workTimeService).closeTimeSignup(any(WorkTimeEntity.class));
        doNothing().when(workTimeService).overTimeSignup(any(WorkTimeEntity.class));
        
        //Mocking userService.getWorkFlgLeaving() method
        doNothing().when(userService).getWorkFlgAttendance(loginId);
        
        //Mocking userService.getWorkFlgLeaving() method
        doNothing().when(workTimeService).updateTotalWorkTime(loginId);
        
    	//Excute
    	String viewName = clockInController.leavingTime();
    	
    	//Verify
        verify(workTimeService, times(1)).closeTimeSignup(any(WorkTimeEntity.class));
        verify(workTimeService, times(1)).overTimeSignup(any(WorkTimeEntity.class));
        verify(userService, times(1)).getWorkFlgAttendance(loginId);
        verify(workTimeService, times(1)).updateTotalWorkTime(loginId);         
        assertEquals("redirect:/user/clockIn", viewName);            	
    }
}
