package com.example.domainUser.service;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.domainUser.model.WorkTimeEntity;
import com.example.domainUser.model.WorkTimeTotalEntity;

@ExtendWith(MockitoExtension.class)

class WorkTimeServiceTest {

	   @Mock
	    private WorkTimeService workTimeService ;

	    @Mock
	    private WorkTimeEntity worktime ;

	    @Mock
	    private WorkTimeTotalEntity worktimetotal ;
	   
	    @Test
	    public void testStartTimeSignup() {
            workTimeService.startTimeSignup(worktime);	
            
	        verify(workTimeService).startTimeSignup(worktime);
	    }

	    @Test
	    public void testCloseTimeSignup() {
            workTimeService.closeTimeSignup(worktime);	
            
	        verify(workTimeService).closeTimeSignup(worktime);
	    }

	    @Test
	    public void testOverTimeSignup() {
            workTimeService.overTimeSignup(worktime);	
            
	        verify(workTimeService).overTimeSignup(worktime);
	    }

	    @Test
	    public void testGetClockTimes() {
	        String loginId = "testLoginId";
	        List<WorkTimeEntity> beforeResult = new ArrayList<>();
	        when(workTimeService.getClockTimes(loginId)).thenReturn(beforeResult);
	        List<WorkTimeEntity> result = workTimeService.getClockTimes(loginId);
	        
	        assertSame(result, result);
	        verify(workTimeService).getClockTimes(loginId);
	    }
	    
	    @Test
	    public void testGetCorrectClockTimes() {
	        String loginId = "testleLoginId";
	        List<WorkTimeEntity> beforeResult = new ArrayList<>();
	        when(workTimeService.getCorrectClockTimes(loginId)).thenReturn(beforeResult);
	        List<WorkTimeEntity> result = workTimeService.getCorrectClockTimes(loginId);
	        
	        assertSame(result, result);
	        verify(workTimeService).getCorrectClockTimes(loginId);
	    }
	    
	    @Test
	    public void testGetSelectYearMonth() {
	        String loginId = "testleLoginId";
	        String selectedYearMonth = "2024-05-06";
	        
	        List<WorkTimeEntity> beforeResult = new ArrayList<>();
	        when(workTimeService.getSelectYearMonth(loginId, selectedYearMonth)).thenReturn(beforeResult);
	        List<WorkTimeEntity> result = workTimeService.getSelectYearMonth(loginId, selectedYearMonth);
	        
	        assertSame(result, result);
	        verify(workTimeService).getSelectYearMonth(loginId, selectedYearMonth);
	    }
	    
	    @Test
	    public void testGetClockTimesNextMonth() {
	        
	        List<WorkTimeEntity> beforeResult = new ArrayList<>();
	        when(workTimeService.getClockTimesNextMonth()).thenReturn(beforeResult);
	        List<WorkTimeEntity> result = workTimeService.getClockTimesNextMonth();
	        
	        assertSame(result, result);
	        verify(workTimeService).getClockTimesNextMonth();
	    }
	    
	    @Test
	    public void testGetWorkTimesTotal() {
	        String loginId = "testLoginId";
	        when(workTimeService.getworkTimesTotal(loginId)).thenReturn(worktimetotal);
	        WorkTimeTotalEntity result = workTimeService.getworkTimesTotal(loginId);
	        
	        verify(workTimeService).getworkTimesTotal(loginId);
	        assertSame(worktimetotal, result);
	    }

	    @Test
	    public void testGetSelectWorkTimesTotal() {
	    	String loginId = "testLoginId";
	        String selectedYearMonth = "2024-06";
	        when(workTimeService.getSelectWorkTimesTotal(loginId, selectedYearMonth)).thenReturn(worktimetotal);
	        WorkTimeTotalEntity result = workTimeService.getSelectWorkTimesTotal(loginId, selectedYearMonth);
	        
	        verify(workTimeService).getSelectWorkTimesTotal(loginId, selectedYearMonth);
	        assertSame(worktimetotal, result);
	    }

	    @Test
	    public void testUpdateTotalWorkTime() {
	        String loginId = "testLoginId";
	        workTimeService.updateTotalWorkTime(loginId);
	        
	        verify(workTimeService).updateTotalWorkTime(loginId);
	    }

	    @Test
	    public void testGetWorkTimeOne() {
	        String loginId = "testLoginId";
	        String workDate = "2024-06-06"; 
	        
	        when(workTimeService.getWorkTimeOne(loginId, workDate)).thenReturn(worktime);
	        WorkTimeEntity result = workTimeService.getWorkTimeOne(loginId, workDate);
	        
	        
	        assertSame(worktime, result);
	    }

	    @Test
	    public void testUpdateWorkTimeOne() {
	    	doNothing().when(workTimeService).updateWorkTimeOne(
	    	"2024-06-01", "user1", "09:00", "18:00", LocalTime.of(1, 0));
	    	
	    	workTimeService.updateWorkTimeOne("2024-06-01", "user1", "09:00", "18:00", LocalTime.of(1, 0));

	    	verify(workTimeService, times(1)).updateWorkTimeOne(
	    	"2024-06-01", "user1", "09:00", "18:00", LocalTime.of(1, 0)
	    	);
	    	
	    }

	    @Test
	    public void testUserWorkTimeCreate() {
	        String loginId = "testLoginId";
	        workTimeService.userWorkTimeCreate(loginId);
	        
	        verify(workTimeService).userWorkTimeCreate(loginId);
	    }

	    @Test
	    public void testUserWorkTimeTotalCreate() {
	        String loginId = "testLoginId";
	        workTimeService.userWorkTimeTotalCreate(loginId);
	        
	        verify(workTimeService).userWorkTimeTotalCreate(loginId);
	    }

	    @Test
	    public void testGetSelectCorrectYearMonth() {
	        String loginId = "testLoginId";
	        String selectedYearMonth = "2024-06"; // Example year-month
	        List<WorkTimeEntity> mockList = new ArrayList<>();
	        Mockito.when(workTimeService.getSelectCorrectYearMonth(loginId, selectedYearMonth)).thenReturn(mockList);
	        List<WorkTimeEntity> result = workTimeService.getSelectCorrectYearMonth(loginId, selectedYearMonth);
	       
	        verify(workTimeService).getSelectCorrectYearMonth(loginId, selectedYearMonth);
	        assertSame(mockList, result);
	    }




}
