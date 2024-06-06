package com.example.domainUser.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.domainUser.model.DepartmentEntity;
import com.example.domainUser.model.PaidEntity;
import com.example.domainUser.model.RoleEntity;
import com.example.domainUser.model.UserMapperEntity;
import com.example.repository.UserMapperRepository;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userServiceImpl;

	@Mock
    private UserMapperRepository userMapperRepository;
	
	@Mock
    private PasswordEncoder encoder;

	@Mock
	private UserMapperEntity userMapperEntity;
	
    @Test
    void testUserCreate() {
       userServiceImpl.userCreate(userMapperEntity);

        verify(userMapperRepository, times(1)).insertOne(userMapperEntity);
    }

    @Test
    void testGetUsers() {
        // Setup
        List<UserMapperEntity> expectedUsers = new ArrayList<>();

        when(userMapperRepository.findMany(userMapperEntity)).thenReturn(expectedUsers);

        // Test
        List<UserMapperEntity> actualUsers = userServiceImpl.getUsers(userMapperEntity);

        // Verify
        assertEquals(expectedUsers, actualUsers);
        verify(userMapperRepository, times(1)).findMany(userMapperEntity);
    }
    
    @Test
    void testGetUserOne() {
    	String loginId = "testLoginId";

        when(userMapperRepository.findOne(loginId)).thenReturn(userMapperEntity);
        UserMapperEntity result = userServiceImpl.getUserOne(loginId);

        // Verify
        assertEquals(userMapperEntity, result);
        verify(userMapperRepository, times(1)).findOne(loginId);
    }
    
    @Test
    void testUpdateUserOne() {
        // Setup
        String loginId = "testLoginId";
        String password = "password123";
        String userName = "Test User";
        String userNamekana = "テスト ユーザー";
        String mailAddress = "test@example.com";
        Integer departmentId = 1;
        Integer roleId = 1;
        Integer validation = 1;
        Integer authority = 1;
        Date hire = new Date();

        when(encoder.encode(password)).thenReturn("encodedPassword");

        // Test
        userServiceImpl.updateUserOne(loginId, password, userName, userNamekana, mailAddress, departmentId, roleId, validation, authority, hire);
        
        // Verify
        verify(userMapperRepository, times(1)).updateOne(loginId, "encodedPassword", userName, userNamekana, mailAddress, departmentId, roleId, validation, authority, hire);
    }

    @Test
    void testUpdatePasswordOne() {
        // Setup
        String loginId = "testLoginId";
        String password = "testPassword";

        when(encoder.encode(password)).thenReturn("encodedPassword");

        // Test
        userServiceImpl.updatePasswordOne(loginId, password);

        // Verify
        verify(userMapperRepository, times(1)).updatePasswordOne(loginId, "encodedPassword");
    }

    @Test
    void testDeleteUserOne() {
        // Setup
        String loginId = "testLoginId";

        // Test
        userServiceImpl.deleteUserOne(loginId);

        // Verify
        verify(userMapperRepository, times(1)).deleteOne(loginId);
    }

    @Test
    void testGetAllDepartment() {
        // Setup
        List<DepartmentEntity> expectedList = new ArrayList<>();

        when(userMapperRepository.findAll()).thenReturn(expectedList);

        // Test
        List<DepartmentEntity> actualList = userServiceImpl.getAllDepartment();

        // Verify
        assertEquals(expectedList, actualList);
        verify(userMapperRepository, times(1)).findAll();
    }

    @Test
    void testGetAllRole() {
        // Setup
        List<RoleEntity> expectedRoles = new ArrayList<>();

        when(userMapperRepository.findAll2()).thenReturn(expectedRoles);

        // Test
        List<RoleEntity> actualRoles = userServiceImpl.getAllRole();

        // Verify
        assertEquals(expectedRoles, actualRoles);
        verify(userMapperRepository, times(1)).findAll2();
    }

    @Test
    void testGetWorkFlg() {
        // Setup
        String loginId = "testLoginId";
        UserMapperEntity expectedUser = new UserMapperEntity();

        when(userMapperRepository.findWorkFlg(loginId)).thenReturn(expectedUser);

        // Test
        UserMapperEntity actualUser = userServiceImpl.getWorkFlg(loginId);

        // Verify
        assertEquals(expectedUser, actualUser);
        verify(userMapperRepository, times(1)).findWorkFlg(loginId);
    }

    @Test
    void testGetWorkFlgLeaving() {
        // Setup
        String loginId = "testLoginId";

        // Test
        userServiceImpl.getWorkFlgLeaving(loginId);

        // Verify
        verify(userMapperRepository, times(1)).updateWorkFlgLeaving(loginId);
    }

    @Test
    void testGetWorkFlgAttendance() {
        // Setup
        String loginId = "testLoginId";

        // Test
        userServiceImpl.getWorkFlgAttendance(loginId);

        // Verify
        verify(userMapperRepository, times(1)).updateWorkFlgAttendance(loginId);
    }

    @Test
    void testUserPaidCreate() {
        // Setup
        UserMapperEntity user = new UserMapperEntity();

        // Test
        userServiceImpl.userPaidCreate(user);

        // Verify
        verify(userMapperRepository, times(1)).insertUserPaidCreate(user);
    }

    @Test
    void testGiveFirstPaidDays() {
        // Test
    	userServiceImpl.giveFirstPaidDays();

        // Verify
        verify(userMapperRepository, times(1)).giveFirstPaidDays();
    }

    @Test
    void testUpdateGivePaidDays() {
        // Test
    	userServiceImpl.updateGivePaidDays();

        // Verify
        verify(userMapperRepository, times(1)).updateGivePaidDays();
    }

    @Test
    void testGetPaidDays() {
        // Setup
        String loginId = "testLoginId";
        PaidEntity expectedPaid = new PaidEntity();

        when(userMapperRepository.getPaidDays(loginId)).thenReturn(expectedPaid);

        // Test
        PaidEntity actualPaid = userServiceImpl.getPaidDays(loginId);

        // Verify
        assertEquals(expectedPaid, actualPaid);
    }



}
