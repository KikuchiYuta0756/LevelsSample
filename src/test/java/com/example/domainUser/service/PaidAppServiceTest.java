package com.example.domainUser.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.domainUser.model.PaidAppEntity;

@ExtendWith(MockitoExtension.class)
class PaidAppServiceTest {

	@Mock
    private PaidAppService paidAppService;
	
	
	@Mock
    private PaidAppEntity paidapp;

    @Test
    void testPaidAppCreate() {
        // テスト対象のメソッドを呼び出す
        paidAppService.paidAppCreate(paidapp);
        
        // メソッドが適切に呼び出されたことを検証する
        verify(paidAppService).paidAppCreate(paidapp);
    }

    @Test
    void testGetPaidRequests() {
    	
    	List<PaidAppEntity> beforeResult = new ArrayList<>();
        // モックの振る舞いを定義する
        when(paidAppService.getPaidRequests(paidapp)).thenReturn(beforeResult);
        
        // テスト対象のメソッドを呼び出す
        List<PaidAppEntity> result = paidAppService.getPaidRequests(paidapp);
        
        // 結果が期待通りであることを検証する
        assertEquals(beforeResult, result);
        verify(paidAppService).getPaidRequests(paidapp);
    }

    @Test
    void testGetPaidAppOne() {
    	int paidAppId = 1;
        // モックの振る舞いを定義する
        when(paidAppService.getPaidAppOne(paidAppId)).thenReturn(paidapp);
        
        // テスト対象のメソッドを呼び出す
        PaidAppEntity result = paidAppService.getPaidAppOne(paidAppId);
        
        // 結果が期待通りであることを検証する
        assertEquals(paidapp,result);
        verify(paidAppService).getPaidAppOne(paidAppId);
    }

    @Test
    void testUpdateRequestStaApproval() {
    	int paidAppId = 1;
        // テスト対象のメソッドを呼び出す
        paidAppService.updateRequestStaApproval(paidAppId);
        
        // メソッドが適切に呼び出されたことを検証する
        verify(paidAppService).updateRequestStaApproval(paidAppId);
    }

    @Test
    void testUpdateRequestStaRemand() {
    	int paidAppId = 1;
        // テスト対象のメソッドを呼び出す
        paidAppService.updateRequestStaRemand(paidAppId);
        
        // メソッドが適切に呼び出されたことを検証する
        verify(paidAppService).updateRequestStaRemand(paidAppId);
    }

    @Test
    void testGetUserPaidRequests() {
    	String paidLoginId = "testLoginId";
    	List<PaidAppEntity> beforeResult = new ArrayList<>(); 
        // モックの振る舞いを定義する
        when(paidAppService.getUserPaidRequests(paidLoginId)).thenReturn(beforeResult);
        
        // テスト対象のメソッドを呼び出す
        List<PaidAppEntity> result = paidAppService.getUserPaidRequests(paidLoginId);
        
        // 結果が期待通りであることを検証する
        assertEquals(beforeResult, result);
        verify(paidAppService).getUserPaidRequests(paidLoginId);
    }

}
