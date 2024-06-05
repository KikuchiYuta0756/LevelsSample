package com.example.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.domainUser.model.DepartmentEntity;
import com.example.domainUser.model.PaidAppEntity;
import com.example.domainUser.model.PaidEntity;
import com.example.domainUser.model.RoleEntity;
import com.example.domainUser.model.UserMapperEntity;

@ExtendWith(MockitoExtension.class)
class UserMapperRepositoryTest {

	@Mock
	private UserMapperRepository userMapperRepository;


	@Mock
	private UserMapperEntity user;
	
	@Mock
	private PaidAppEntity paidApp;
	

	@Test
	public void testInsertOne() {

	userMapperRepository.insertOne(user);

	verify(userMapperRepository, times(1)).insertOne(user);
	}

	@Test
	public void testFindMany() {
	List<UserMapperEntity> expectedUsers = Arrays.asList(user);
	when(userMapperRepository.findMany(user)).thenReturn(expectedUsers);

	List<UserMapperEntity> actualUsers = userMapperRepository.findMany(user);

	assertEquals(expectedUsers, actualUsers);
	verify(userMapperRepository, times(1)).findMany(user);
	}

	@Test
	public void testFindOne() {
	String loginId = "testLoginId";
	when(userMapperRepository.findOne(loginId)).thenReturn(user);
	UserMapperEntity actualUser = userMapperRepository.findOne(loginId);

	assertEquals(user, actualUser);
	verify(userMapperRepository, times(1)).findOne(loginId);
	}
	
	@Test
	public void testGetLoginUser() {
	String loginId = "testLoginId";
	when(userMapperRepository.getLoginUser(loginId)).thenReturn(user);
	UserMapperEntity actualUser = userMapperRepository.getLoginUser(loginId);

	assertEquals(user, actualUser);
	verify(userMapperRepository, times(1)).getLoginUser(loginId);
	}
	
	@Test
	public void testPaidRequestFindOne() {
	String loginId = "testLoginId";
	when(userMapperRepository.paidRequestFindOne(loginId)).thenReturn(paidApp);
	PaidAppEntity actualUser = userMapperRepository.paidRequestFindOne(loginId);

	assertEquals(paidApp, actualUser);
	verify(userMapperRepository, times(1)).paidRequestFindOne(loginId);
	}

	@Test
	public void testUpdateOne() {
	String loginId = "testLoginId";
	String encryptPassword = "testPassword";
	String userName = "testName";
	String userNamekana = "testNameKana";
	String mailAddress = "test@mail.com";
	Integer departmentId = 1;
	Integer roleId = 1;
	Integer validation = 1;
	Integer authority = 1;
	Date hire = new Date();

	doNothing().when(userMapperRepository).updateOne(loginId, encryptPassword, userName, userNamekana, mailAddress, departmentId, roleId, validation, authority, hire);
	userMapperRepository.updateOne(loginId, encryptPassword, userName, userNamekana, mailAddress, departmentId, roleId, validation, authority, hire);

	verify(userMapperRepository, times(1)).updateOne(loginId, encryptPassword, userName, userNamekana, mailAddress, departmentId, roleId, validation, authority, hire);
	}

	@Test
	public void testUpdatePasswordOne() {
	String loginId = "testLoginId";
	String encryptPassword = "testPassword";
	doNothing().when(userMapperRepository).updatePasswordOne(loginId, encryptPassword);

	userMapperRepository.updatePasswordOne(loginId, encryptPassword);

	verify(userMapperRepository, times(1)).updatePasswordOne(loginId, encryptPassword);
	}

	
	@Test
	public void testDeleteOne() {
	String loginId = "testLoginId";
	when(userMapperRepository.deleteOne(loginId)).thenReturn(1);

	int result = userMapperRepository.deleteOne(loginId);

	assertEquals(1, result);
	verify(userMapperRepository, times(1)).deleteOne(loginId);
	}

	@Test
	public void testFindAllDepartments() {
	DepartmentEntity department = new DepartmentEntity();
	List<DepartmentEntity> expectedDepartments = Arrays.asList(department);
	when(userMapperRepository.findAll()).thenReturn(expectedDepartments);

	List<DepartmentEntity> actualDepartments = userMapperRepository.findAll();

	assertEquals(expectedDepartments, actualDepartments);
	verify(userMapperRepository, times(1)).findAll();
	}

	@Test
	public void testFindAllRoles() {
	RoleEntity role = new RoleEntity();
	List<RoleEntity> expectedRoles = Arrays.asList(role);
	when(userMapperRepository.findAll2()).thenReturn(expectedRoles);

	List<RoleEntity> actualRoles = userMapperRepository.findAll2();

	assertEquals(expectedRoles, actualRoles);
	verify(userMapperRepository, times(1)).findAll2();
	}

    @Test
    public void testFindWorkFlg() {
        // モックの設定
        String loginId = "testLoginId";
        when(userMapperRepository.findWorkFlg(loginId)).thenReturn(user);
        UserMapperEntity result = userMapperRepository.findWorkFlg(loginId);
        
        // 結果検証
    	assertEquals(user, result);
    	verify(userMapperRepository, times(1)).findWorkFlg(loginId);
    }
    
    // テストupdateWorkFlgLeavingメソッド
    @Test
    public void testUpdateWorkFlgLeaving() {
        // モックの設定
        String loginId = "testLoginId";      
    	userMapperRepository.updateWorkFlgLeaving(loginId);

    	verify(userMapperRepository, times(1)).updateWorkFlgLeaving(loginId);
    }
    
    @Test
    public void testUpdateWorkFlgAttendance() {
        // モックの設定
        String loginId = "testLoginId";
    	userMapperRepository.updateWorkFlgAttendance(loginId);

    	verify(userMapperRepository, times(1)).updateWorkFlgAttendance(loginId);
    }
    
    // テストinsertUserPaidCreateメソッド
    @Test
    public void testInsertUserPaidCreate() {

    	when(userMapperRepository.insertUserPaidCreate(user)).thenReturn(1);
    	int result = userMapperRepository.insertUserPaidCreate(user);

    	assertEquals(1, result);
    	verify(userMapperRepository, times(1)).insertUserPaidCreate(user);
    }
    
    // テストgiveFirstPaidDaysメソッド
    @Test
    public void testGiveFirstPaidDays() {
        // テスト実行
    	userMapperRepository.giveFirstPaidDays();
       
    	verify(userMapperRepository, times(1)).giveFirstPaidDays();
    	
    }
    
    // テストupdateGivePaidDaysメソッド
    @Test
    public void testUpdateGivePaidDays() {
        // テスト実行
    	userMapperRepository.updateGivePaidDays();
        
    	verify(userMapperRepository, times(1)).updateGivePaidDays();
    }
	
	@Test
	public void testGetPaidDays() {
	String loginId = "testLoginId";
	PaidEntity expectedPaidDays = new PaidEntity();
	when(userMapperRepository.getPaidDays(loginId)).thenReturn(expectedPaidDays);

	PaidEntity actualPaidDays = userMapperRepository.getPaidDays(loginId);

	assertEquals(expectedPaidDays, actualPaidDays);
	verify(userMapperRepository, times(1)).getPaidDays(loginId);

	}
}
