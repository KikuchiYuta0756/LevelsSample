package com.example.domainUser.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.domainUser.model.WorkTimeEntity;
import com.example.domainUser.model.WorkTimeTotalEntity;
import com.example.repository.WorkTimeMapperRepository;

@ExtendWith(MockitoExtension.class)
class WorkTimeServiceImplTest {

	
    @InjectMocks
    private WorkTimeServiceImpl workTimeService;

    @Mock    
    private WorkTimeMapperRepository workTimeMapper;
    
    @Mock
    private WorkTimeEntity workTime;
    
    @Mock
    private WorkTimeTotalEntity workTimeTotal;

    @Test
    public void testStartTimeSignup() {
        workTimeService.startTimeSignup(workTime);
        
        verify(workTimeMapper, times(1)).insertStartTime(workTime);
    }

    @Test
    public void testCloseTimeSignup() {
        workTimeService.closeTimeSignup(workTime);
        
        verify(workTimeMapper, times(1)).insertCloseTime(workTime);
    }

    @Test
    public void testOverTimeSignup() {
        workTimeService.overTimeSignup(workTime);
        
        verify(workTimeMapper, times(1)).insertOverTime(workTime);
    }

    @Test
    public void testGetClockTimes() {
        String loginId = "testLoginId";
        List<WorkTimeEntity> beforeResult = new ArrayList<>();
        when(workTimeMapper.findMany(loginId)).thenReturn(beforeResult);

        List<WorkTimeEntity> result = workTimeService.getClockTimes(loginId);

        assertEquals(beforeResult, result);
        verify(workTimeMapper, times(1)).findMany(loginId);
    }
    
    @Test
    public void testGetCorrectClockTimes() {
    	String loginId = "testLoginId";
        List<WorkTimeEntity> mockResult = new ArrayList<>();
        when(workTimeMapper.findManyOne(loginId)).thenReturn(mockResult);

        List<WorkTimeEntity> result = workTimeService.getCorrectClockTimes(loginId);

        assertEquals(mockResult, result);
        verify(workTimeMapper, times(1)).findManyOne(loginId);
    }

    @Test
    public void testGetSelectYearMonth() {
        String loginId = "testLoginId";
        String selectedYearMonth = "2024-06"; // Example selected year-month
        List<WorkTimeEntity> mockResult = new ArrayList<>();
        when(workTimeMapper.findSelectYearMonth(loginId, selectedYearMonth)).thenReturn(mockResult);

        List<WorkTimeEntity> result = workTimeService.getSelectYearMonth(loginId, selectedYearMonth);

        assertEquals(mockResult, result);
        verify(workTimeMapper, times(1)).findSelectYearMonth(loginId, selectedYearMonth);
    }

    @Test
    public void testGetClockTimesNextMonth() {
        List<WorkTimeEntity> mockResult = new ArrayList<>();
        when(workTimeMapper.findManyNextMonth()).thenReturn(mockResult);

        List<WorkTimeEntity> result = workTimeService.getClockTimesNextMonth();

        assertEquals(mockResult, result);
        verify(workTimeMapper, times(1)).findManyNextMonth();
        // Additional assertions if needed
    }

    @Test
    public void testGetworkTimesTotal() {
        String loginId = "testLoginId";
        when(workTimeMapper.totalWorkTime(loginId)).thenReturn(workTimeTotal);

        WorkTimeTotalEntity result = workTimeService.getworkTimesTotal(loginId);

        assertEquals(workTimeTotal, result);
        verify(workTimeMapper, times(1)).totalWorkTime(loginId);
        // Additional assertions if needed
    }

    @Test
    public void testGetSelectWorkTimesTotal() {
        String loginId = "user123";
        String selectedYearMonth = "2024-06"; // Example selected year-month
        when(workTimeMapper.selectTotalWorkTime(loginId, selectedYearMonth)).thenReturn(workTimeTotal);

        WorkTimeTotalEntity result = workTimeService.getSelectWorkTimesTotal(loginId, selectedYearMonth);

        assertEquals(workTimeTotal, result);
        verify(workTimeMapper, times(1)).selectTotalWorkTime(loginId, selectedYearMonth);
        // Additional assertions if needed
    }
    
    @Test
    public void testUpdateTotalWorkTime() {
        String loginId = "user123";
        workTimeService.updateTotalWorkTime(loginId);
        
        verify(workTimeMapper, times(1)).updateTotalWorkTime(loginId);
    }

    @Test
    public void testGetWorkTimeOne() {
        String loginId = "user123";
        String workDate = "2024-06-07"; // Example work date
        WorkTimeEntity mockResult = new WorkTimeEntity(); // Example entity
        when(workTimeMapper.workTimeOne(loginId, workDate)).thenReturn(mockResult);

        WorkTimeEntity result = workTimeService.getWorkTimeOne(loginId, workDate);

        assertEquals(mockResult, result);
        verify(workTimeMapper, times(1)).workTimeOne(loginId, workDate);
    }

    @Test
    public void testUpdateWorkTimeOne() {
        String loginId = "user123";
        String workDate = "2024-06-07"; // Example work date
        String startTime = "09:00:00"; // Example start time
        String closeTime = "18:00:00"; // Example close time
        LocalTime restTime = LocalTime.of(1, 0); // Example rest time

        workTimeService.updateWorkTimeOne(workDate, loginId, startTime, closeTime, restTime);

        verify(workTimeMapper, times(1)).updateWorkTimeOne(workDate, loginId, startTime, closeTime, restTime);
    }

    @Test
    public void testUserWorkTimeCreate() {
        String loginId = "user123";
        workTimeService.userWorkTimeCreate(loginId);
        
        verify(workTimeMapper, times(1)).insertUserWorkTimeCreate(loginId);
    }

    @Test
    public void testUserWorkTimeTotalCreate() {
        String loginId = "user123";
        workTimeService.userWorkTimeTotalCreate(loginId);
        
        verify(workTimeMapper, times(1)).insertUserWorkTimeTotalCreate(loginId);
    }

    @Test
    public void testGetSelectCorrectYearMonth() {
        String loginId = "user123";
        String selectedYearMonth = "2024-06"; // Example selected year-month
        List<WorkTimeEntity> mockResult = new ArrayList<>();
        when(workTimeMapper.findSelectCorrectYearMonth(loginId, selectedYearMonth)).thenReturn(mockResult);

        List<WorkTimeEntity> result = workTimeService.getSelectCorrectYearMonth(loginId, selectedYearMonth);

        assertEquals(mockResult, result);
        verify(workTimeMapper, times(1)).findSelectCorrectYearMonth(loginId, selectedYearMonth);
    }


}
