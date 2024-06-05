package com.example.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.domainUser.model.PaidAppEntity;

@ExtendWith(MockitoExtension.class)
class PaidAppRepositoryTest {

	@Mock
	private PaidAppRepository paidAppRepository;

	@Mock
	private PaidAppEntity paidAppEntity;

	@Test
	public void testInsertPaidApp() {
	when(paidAppRepository.insertPaidApp(paidAppEntity)).thenReturn(1);

	int result = paidAppRepository.insertPaidApp(paidAppEntity);

	assertEquals(1, result);
	verify(paidAppRepository, times(1)).insertPaidApp(paidAppEntity);
	}

	@Test
	public void testFindMany() {
	List<PaidAppEntity> mockList = Arrays.asList(paidAppEntity);
	when(paidAppRepository.findMany(paidAppEntity)).thenReturn(mockList);

	List<PaidAppEntity> resultList = paidAppRepository.findMany(paidAppEntity);

	assertEquals(mockList, resultList);
	verify(paidAppRepository, times(1)).findMany(paidAppEntity);
	}

	@Test
	public void testFindOne() {
	int paidAppId = 1;
	when(paidAppRepository.findOne(paidAppId)).thenReturn(paidAppEntity);
	PaidAppEntity result = paidAppRepository.findOne(paidAppId);

	assertEquals(paidAppEntity, result);
	verify(paidAppRepository, times(1)).findOne(paidAppId);
	}

	@Test
	public void testUpdateStaApproval() {
    int paidAppId = 123; // 任意のIDを設定
	paidAppRepository.updateStaApproval(paidAppId);

	verify(paidAppRepository, times(1)).updateStaApproval(paidAppId);
	}

	@Test
	public void testUpdateStaRemand() {
	int paidAppId = 123; // 任意のIDを設定
	paidAppRepository.updateStaRemand(paidAppId);

	verify(paidAppRepository, times(1)).updateStaRemand(paidAppId);
	}

	@Test
	public void testUserFindMany() {
	String paidLoginId = "testUser";
	List<PaidAppEntity> mockList = Arrays.asList(paidAppEntity);
	when(paidAppRepository.userFindMany(paidLoginId)).thenReturn(mockList);
	List<PaidAppEntity> resultList = paidAppRepository.userFindMany(paidLoginId);

	assertEquals(mockList, resultList);
	verify(paidAppRepository, times(1)).userFindMany(paidLoginId);
	}
}
