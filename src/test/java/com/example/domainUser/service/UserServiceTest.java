package com.example.domainUser.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.domainUser.model.DepartmentEntity;
import com.example.domainUser.model.PaidEntity;
import com.example.domainUser.model.RoleEntity;
import com.example.domainUser.model.UserMapperEntity;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

	    @Mock
	    private UserService userService;

	    @Mock
	    private UserMapperEntity user;

	    @Mock
	    private DepartmentEntity departmentEntity;

	    @Mock
	    private RoleEntity roleEntity;

	    @Mock
	    private PaidEntity paidEntity;

	    @Test
	    public void testUserCreate() {
	        userService.userCreate(user);

	        verify(userService).userCreate(user);
	    }

	    @Test
	    public void testGetUsers() {
	        List<UserMapperEntity> beforeResult = new ArrayList<>();
	        
	        when(userService.getUsers(user)).thenReturn(beforeResult);
	        
	        List<UserMapperEntity> result = userService.getUsers(user);
	        // 適切なアサーションを追加する
	        assertEquals(beforeResult, result);
	        verify(userService).getUsers(user);
	    }

	    @Test
	    public void testGetUserOne() {
	        String loginId = "testLoginId";
	        
	        when(userService.getUserOne(loginId)).thenReturn(user);
	        UserMapperEntity users = userService.getUserOne(loginId);
	        // 適切なアサーションを追加する
	        assertEquals(user,users);
	        verify(userService).getUserOne(loginId);
	    }

	    @Test
	    public void testUpdateUserOne() {
	        // テスト用の引数を設定する
	        String loginId = "testLoginId";
	        String password = "testPassword";
	        String userName = "testUserName";
	        String userNamekana = "testUserNameKana";
	        String mailAddress = "test@example.com";
	        Integer departmentId = 1;
	        Integer roleId = 1;
	        Integer validation = 1;
	        Integer authority = 1;
	        Date hire = new Date();

	    	doNothing().when(userService).updateUserOne(loginId, password, userName, userNamekana, mailAddress, departmentId, roleId, validation, authority, hire);
	        userService.updateUserOne(loginId, password, userName, userNamekana, mailAddress, departmentId, roleId, validation, authority, hire);

	    	verify(userService, times(1)).updateUserOne(loginId, password, userName, userNamekana, mailAddress, departmentId, roleId, validation, authority, hire);
	    }

	    @Test
	    public void testUpdatePasswordOne() {
	        // テスト用の引数を設定する
	        String loginId = "testLoginId";
	        String newPassword = "newPassword";

	        userService.updatePasswordOne(loginId, newPassword);

	    	verify(userService, times(1)).updatePasswordOne(loginId, newPassword);
	    }

	    @Test
	    public void testDeleteUserOne() {
	        String loginId = "testLoginId";

	        userService.deleteUserOne(loginId);

	        verify(userService, times(1)).deleteUserOne(loginId);
	    }

	    @Test
	    public void testGetAllDepartment() {
	        List<DepartmentEntity> departments = new ArrayList<>();
	        
	        when(userService.getAllDepartment()).thenReturn(departments);
	        
	        List<DepartmentEntity> result = userService.getAllDepartment();
	        // 適切なアサーションを追加する
	        assertEquals(departments, result);
	        verify(userService).getAllDepartment();

	        // 適切なアサーションを追加する
	    }

	    @Test
	    public void testGetAllRole() {
	        List<RoleEntity> roles = new ArrayList<>();
	        
	        when(userService.getAllRole()).thenReturn(roles);
	        
	        List<RoleEntity> result = userService.getAllRole();
	        // 適切なアサーションを追加する
	        assertEquals(roles, result);
	        verify(userService).getAllRole();
	    }
	    
	    @Test
	    public void testGetWorkFlg() {
	        String loginId = "testLoginId";
	        
	        when(userService.getWorkFlg(loginId)).thenReturn(user);
	        UserMapperEntity workFlg = userService.getWorkFlg(loginId);

	        assertEquals(user, workFlg);
	        verify(userService).getWorkFlg(loginId);
	    }

	    @Test
	    public void testGetWorkFlgLeaving() {
	        String loginId = "testLoginId";
	        userService.getWorkFlgLeaving(loginId);
	       
	        verify(userService).getWorkFlgLeaving(loginId);
	    }

	    @Test
	    public void testGetWorkFlgAttendance() {
	        String loginId = "testLoginId";
	        userService.getWorkFlgAttendance(loginId);
		       
	        verify(userService).getWorkFlgAttendance(loginId);
	    }

	    @Test
	    public void testUserPaidCreate() {
	        userService.userPaidCreate(user);
	       
	        verify(userService).userPaidCreate(user);
	    }

	    @Test
	    public void testGiveFirstPaidDays() {
	        userService.giveFirstPaidDays();
	       
	        verify(userService).giveFirstPaidDays();
	    }

	    @Test
	    public void testUpdateGivePaidDays() {
	        userService.updateGivePaidDays();
	       
	        verify(userService).updateGivePaidDays();
	    }

	    @Test
	    public void testGetPaidDays() {
	        String loginId = "testLoginId";
	        when(userService.getPaidDays(loginId)).thenReturn(paidEntity);
	        PaidEntity paidDays = userService.getPaidDays(loginId);
	        // 適切なアサーションを追加する
	        
	        assertEquals(paidEntity, paidDays);
	        verify(userService).getPaidDays(loginId);
	    }
	}
