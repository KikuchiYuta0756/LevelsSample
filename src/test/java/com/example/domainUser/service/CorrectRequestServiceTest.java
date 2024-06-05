package com.example.domainUser.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.domainUser.model.CorrectRequestEntity;

@ExtendWith(MockitoExtension.class)
class CorrectRequestServiceTest {

	@Mock
    private CorrectRequestService correctRequestService;
    
	@Mock
    private CorrectRequestEntity correct;
	
    @Test
    public void testCorrectRequestCreate() {
        correctRequestService.correctRequestCreate(correct);
        
        verify(correctRequestService).correctRequestCreate(correct);
    }
    
    @Test
    public void testGetCorrectRequests() {        
        List<CorrectRequestEntity> expectedResult = new ArrayList<>();
        // expectedResultに適切な値をセットアップ
        
        when(correctRequestService.getCorrectRequests(correct)).thenReturn(expectedResult);
        
        List<CorrectRequestEntity> actualResult = correctRequestService.getCorrectRequests(correct);
        
        assertEquals(expectedResult, actualResult);
        verify(correctRequestService).getCorrectRequests(correct);
    }
    
    @Test
    public void testGetCorrectRequestOne() {
        int correctRequestId = 1; // テスト用のID
                
        when(correctRequestService.getCorrectRequestOne(correctRequestId)).thenReturn(correct);
        
        CorrectRequestEntity actualResult = correctRequestService.getCorrectRequestOne(correctRequestId);
        
        assertEquals(correct, actualResult);
        verify(correctRequestService).getCorrectRequestOne(correctRequestId);
    }
    
    @Test
    public void testUpdateRequestStaApproval() {
        int correctRequestId = 1; // テスト用のID
        
        correctRequestService.updateRequestStaApproval(correctRequestId);
        
        // 正しい引数でメソッドが呼び出されたかどうかを確認するassert
        verify(correctRequestService).updateRequestStaApproval(correctRequestId);
    }
    
    @Test
    public void testUpdateRequestStaRemand() {
        int correctRequestId = 1; // テスト用のID
        
        correctRequestService.updateRequestStaRemand(correctRequestId);
        
        // 正しい引数でメソッドが呼び出されたかどうかを確認するassert
        verify(correctRequestService).updateRequestStaRemand(correctRequestId);
    }
    
    @Test
    public void testGetUserCorrectRequests() {
        String correctLoginId = "testLoginId"; // テスト用のID
        
        List<CorrectRequestEntity> expectedResult = new ArrayList<>();
        // expectedResultに適切な値をセットアップ
        
        when(correctRequestService.getUserCorrectRequests(correctLoginId)).thenReturn(expectedResult);
        
        List<CorrectRequestEntity> actualResult = correctRequestService.getUserCorrectRequests(correctLoginId);
        
        assertEquals(expectedResult, actualResult);
        verify(correctRequestService).getUserCorrectRequests(correctLoginId);
    }
}
