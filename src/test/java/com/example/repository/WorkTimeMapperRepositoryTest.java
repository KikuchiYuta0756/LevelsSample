package com.example.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.domainUser.model.WorkTimeEntity;
import com.example.domainUser.model.WorkTimeTotalEntity;

@ExtendWith(MockitoExtension.class)
class WorkTimeMapperRepositoryTest {

	@Mock
	private WorkTimeMapperRepository workTimeMapper;
	
	@Mock
	private WorkTimeEntity worktime;
	
	@Mock
	private WorkTimeTotalEntity worktimetotal;

	@Test
	public void testInsertStartTime() {

	when(workTimeMapper.insertStartTime(worktime)).thenReturn(1);

	int result = workTimeMapper.insertStartTime(worktime);
	
	assertEquals(1, result);
	verify(workTimeMapper, times(1)).insertStartTime(worktime);
	}

	@Test
	public void testInsertCloseTime() {

	when(workTimeMapper.insertCloseTime(worktime)).thenReturn(1);

	int result = workTimeMapper.insertCloseTime(worktime);
	
	assertEquals(1, result);
	verify(workTimeMapper, times(1)).insertCloseTime(worktime);
	}

	
	@Test
	public void testInsertOverTime() {

	when(workTimeMapper.insertOverTime(worktime)).thenReturn(1);

	int result = workTimeMapper.insertOverTime(worktime);
	
	assertEquals(1, result);
	verify(workTimeMapper, times(1)).insertOverTime(worktime);
	}


	@Test
	public void testFindMany() {
	String loginId = "testLoginId";
	List<WorkTimeEntity> mockList = Arrays.asList(worktime);

	when(workTimeMapper.findMany(loginId)).thenReturn(mockList);
	List<WorkTimeEntity> workTimes = workTimeMapper.findMany(loginId);
	
	assertEquals(mockList, workTimes);
	verify(workTimeMapper, times(1)).findMany(loginId);
	}

	
	@Test
	public void testFindManyOne() {
	String loginId = "testLoginId";
	List<WorkTimeEntity> mockList = Arrays.asList(worktime);

	when(workTimeMapper.findManyOne(loginId)).thenReturn(mockList);
	List<WorkTimeEntity> workTimes = workTimeMapper.findManyOne(loginId);
	
	assertEquals(mockList, workTimes);
	verify(workTimeMapper, times(1)).findManyOne(loginId);
	}
	
	@Test
	public void testFindSelectYearMonth() {
	String loginId = "testLoginId";
	String selectedYearMonth = "2024-05-06";
	List<WorkTimeEntity> mockList = Arrays.asList(worktime);

	when(workTimeMapper.findSelectYearMonth(loginId,selectedYearMonth)).thenReturn(mockList);
	List<WorkTimeEntity> workTimes = workTimeMapper.findSelectYearMonth(loginId,selectedYearMonth);
	
	assertEquals(mockList, workTimes);
	verify(workTimeMapper, times(1)).findSelectYearMonth(loginId,selectedYearMonth);
	}
	
	@Test
	public void testFindManyNextMonth() {
	List<WorkTimeEntity> mockList = Arrays.asList(worktime);

	when(workTimeMapper.findManyNextMonth()).thenReturn(mockList);
	List<WorkTimeEntity> workTimes = workTimeMapper.findManyNextMonth();
	
	assertEquals(mockList, workTimes);
	verify(workTimeMapper, times(1)).findManyNextMonth();
	}
	
	@Test
	public void testTotalWorkTime() {
	String loginId = "testLoginId";

	when(workTimeMapper.totalWorkTime(loginId)).thenReturn(worktimetotal);
	WorkTimeTotalEntity workTimeTotal = workTimeMapper.totalWorkTime(loginId);
	
	assertEquals(worktimetotal, workTimeTotal);
	verify(workTimeMapper, times(1)).totalWorkTime(loginId);
	}
	
	@Test
	public void testSelectTotalWorkTime() {
	String loginId = "testLoginId";
	String selectedYearMonth = "2024-05-06";

	when(workTimeMapper.selectTotalWorkTime(loginId,selectedYearMonth)).thenReturn(worktimetotal);
	WorkTimeTotalEntity workTimeTotal = workTimeMapper.selectTotalWorkTime(loginId,selectedYearMonth);
	
	assertEquals(worktimetotal, workTimeTotal);
	verify(workTimeMapper, times(1)).selectTotalWorkTime(loginId,selectedYearMonth);
	}
	
	@Test
	public void testUpdateTotalWorkTime() {
	String loginId = "testLoginId";

	workTimeMapper.updateTotalWorkTime(loginId);
	
	verify(workTimeMapper, times(1)).updateTotalWorkTime(loginId);
	

	}
	
	@Test
	public void testWorkTimeOne() {
	String loginId = "testLoginId";
	String workDate = "2024-05-06";

	when(workTimeMapper.workTimeOne(loginId,workDate)).thenReturn(worktime);
	WorkTimeEntity workTimeTotal = workTimeMapper.workTimeOne(loginId,workDate);
	
	assertEquals(worktime, workTimeTotal);
	verify(workTimeMapper, times(1)).workTimeOne(loginId,workDate);
	}
	


	@Test
	public void testUpdateWorkTimeOne() {
	doNothing().when(workTimeMapper).updateWorkTimeOne(
	"2024-06-01", "user1", "09:00", "18:00", LocalTime.of(1, 0)
	);

	workTimeMapper.updateWorkTimeOne("2024-06-01", "user1", "09:00", "18:00", LocalTime.of(1, 0));

	verify(workTimeMapper, times(1)).updateWorkTimeOne(
	"2024-06-01", "user1", "09:00", "18:00", LocalTime.of(1, 0)
	);
	}
	
	@Test
	public void testInsertUserWorkTimeCreate() {
	String loginId = "testLoginId";

	workTimeMapper.insertUserWorkTimeCreate(loginId);
	
	verify(workTimeMapper, times(1)).insertUserWorkTimeCreate(loginId);
	}
	
	@Test
	public void testInsertUserWorkTimeTotalCreate() {
	String loginId = "testLoginId";

	workTimeMapper.insertUserWorkTimeTotalCreate(loginId);
	
	verify(workTimeMapper, times(1)).insertUserWorkTimeTotalCreate(loginId);
	}
	
	@Test
	public void testFindSelectCorrectYearMonth() {
	String loginId = "testLoginId";
	String selectedYearMonth = "2024-05-06";
	List<WorkTimeEntity> mockList = Arrays.asList(worktime);

	when(workTimeMapper.findSelectCorrectYearMonth(loginId,selectedYearMonth)).thenReturn(mockList);
	List<WorkTimeEntity> workTimes = workTimeMapper.findSelectCorrectYearMonth(loginId,selectedYearMonth);
	
	assertEquals(mockList, workTimes);
	verify(workTimeMapper, times(1)).findSelectCorrectYearMonth(loginId,selectedYearMonth);
	}

}
