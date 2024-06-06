package com.example.domainUser.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

import com.example.domainUser.model.CorrectRequestEntity;
import com.example.domainUser.service.CorrectRequestService;
import com.example.repository.CorrectRequestRepository;

@ExtendWith(MockitoExtension.class)
class CorrectRequestServiceImplTest {
	
    @InjectMocks
    private CorrectRequestServiceImpl correctRequestServiceImpl;	
	
	@Mock
    private CorrectRequestService correctRequestService;
    
	@Mock
    private CorrectRequestEntity correct;

	@Mock
	private CorrectRequestRepository correctrequestrepository;

    @Test
    public void testCorrectRequestCreate() {
    	correctRequestServiceImpl.correctRequestCreate(correct);
        
        verify(correctrequestrepository, times(1)).insertOne(correct);
    }

    @Test
    public void testGetCorrectRequests() {
    	List<CorrectRequestEntity> beforeResult = new ArrayList<>();
        when(correctrequestrepository.findMany(correct)).thenReturn(beforeResult);
        List<CorrectRequestEntity> result = correctRequestServiceImpl.getCorrectRequests(correct);
        
        verify(correctrequestrepository, times(1)).findMany(correct);
        assertEquals(beforeResult, result);

    }

    @Test
    public void testGetCorrectRequestOne() {
        int correctRequestId = 1;
        when(correctrequestrepository.findOne(correctRequestId)).thenReturn(correct);
        CorrectRequestEntity result = correctRequestServiceImpl.getCorrectRequestOne(correctRequestId);
        
        assertEquals(correct, result);
        verify(correctrequestrepository, times(1)).findOne(correctRequestId);
    }

    @Test
    public void testUpdateRequestStaApproval() {
        int correctRequestId = 1;
        correctRequestServiceImpl.updateRequestStaApproval(correctRequestId);
        
        verify(correctrequestrepository, times(1)).updateStaApproval(correctRequestId);
    }

    @Test
    public void testUpdateRequestStaRemand() {
        int correctRequestId = 1;
        correctRequestServiceImpl.updateRequestStaRemand(correctRequestId);
        
        verify(correctrequestrepository, times(1)).updateStaRemand(correctRequestId);
    }

    @Test
    public void testGetUserCorrectRequests() {
        String correctLoginId = "testLoginId";
        List<CorrectRequestEntity> beforeResult = new ArrayList<>();
        when(correctrequestrepository.userFindMany(correctLoginId)).thenReturn(beforeResult);
        List<CorrectRequestEntity> result = correctRequestServiceImpl.getUserCorrectRequests(correctLoginId);
        
        assertEquals(beforeResult, result);
        verify(correctrequestrepository, times(1)).userFindMany(correctLoginId);
    }

}
