package com.example.domainUser.service.impl;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.domainUser.model.PaidAppEntity;
import com.example.repository.PaidAppRepository;

@ExtendWith(MockitoExtension.class)
public class PaidAppServiceImplTest {

    @InjectMocks
    private PaidAppServiceImpl paidAppServiceImpl;
	
    @Mock
    private PaidAppRepository paidAppRepository;

    @Mock
    private PaidAppEntity paidApp;

    @Test
    public void testPaidAppCreate() {
        // テスト実行
    	paidAppServiceImpl.paidAppCreate(paidApp);

        // メソッドが呼び出されたかどうかを検証
        verify(paidAppRepository, times(1)).insertPaidApp(paidApp);
    }

    @Test
    public void testGetPaidRequests() {
        // モックの振る舞いを定義
        List<PaidAppEntity> expectedList = new ArrayList<>();
        when(paidAppRepository.findMany(paidApp)).thenReturn(expectedList);

        // テスト実行
        List<PaidAppEntity> resultList = paidAppServiceImpl.getPaidRequests(paidApp);

        // 結果を検証
        assertSame(expectedList, resultList);
        verify(paidAppRepository, times(1)).findMany(paidApp);
    }
    
    @Test
    public void testGetPaidAppOne() {
    	int paidAppId = 1;
    	when(paidAppRepository.findOne(paidAppId)).thenReturn(paidApp);
    	PaidAppEntity result = paidAppServiceImpl.getPaidAppOne(paidAppId);
        // テスト実行
    	paidAppServiceImpl.updateRequestStaApproval(paidAppId);

        assertSame(paidApp, result);
        verify(paidAppRepository, times(1)).findOne(paidAppId);
    }
    
    @Test
    public void testUpdateRequestStaApproval() {
    	int paidAppId = 1;
        // テスト実行
    	paidAppServiceImpl.updateRequestStaApproval(paidAppId);

        // メソッドが呼び出されたかどうかを検証
        verify(paidAppRepository, times(1)).updateStaApproval(paidAppId);
    }
    
    @Test
    public void testUpdateRequestStaRemand() {
    	int paidAppId = 1;
        // テスト実行
    	paidAppServiceImpl.updateRequestStaRemand(paidAppId);

        // メソッドが呼び出されたかどうかを検証
        verify(paidAppRepository, times(1)).updateStaRemand(paidAppId);
    }
    
    @Test
    public void testUserPaidRequests() {
    	String paidLoginId = "testLoginId";
        // モックの振る舞いを定義
        List<PaidAppEntity> expectedList = new ArrayList<>();
        when(paidAppRepository.userFindMany(paidLoginId)).thenReturn(expectedList);

        // テスト実行
        List<PaidAppEntity> resultList = paidAppServiceImpl.getUserPaidRequests(paidLoginId);

        // 結果を検証
        assertSame(expectedList, resultList);
        verify(paidAppRepository, times(1)).userFindMany(paidLoginId);
    }


}



