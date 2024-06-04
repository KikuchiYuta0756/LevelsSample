package com.example.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
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
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;

import com.example.domainUser.model.CorrectRequestEntity;
import com.example.domainUser.model.PaidAppEntity;
import com.example.domainUser.service.CorrectRequestService;
import com.example.domainUser.service.PaidAppService;
import com.example.form.CorrectRequestForm;
import com.example.form.PaidRequestForm;

@ExtendWith(MockitoExtension.class)
class RequestRecordControllerTest {


    @InjectMocks
    private RequestRecordController controller;

    @Mock
    private CorrectRequestService correctRequestService;

    @Mock
    private PaidAppService paidAppService;

    @Mock
    private Model model;

    @Mock
    private ModelMapper modelMapper;

    
    @Test
    public void getRequestRecord() {
        // Setup
    	String loginId = "testloginId";
    	
        Authentication authentication = mock(Authentication.class);
        when(authentication.getName()).thenReturn(loginId);

        SecurityContext securityContext = mock(SecurityContext.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);


        List<PaidAppEntity> paidList = new ArrayList<>();
        // Add test PaidAppEntity objects to paidList
        when(paidAppService.getUserPaidRequests(loginId)).thenReturn(paidList);

        List<CorrectRequestEntity> correctList = new ArrayList<>();
        // Add test CorrectRequestEntity objects to correctList
        when(correctRequestService.getUserCorrectRequests(loginId)).thenReturn(correctList);

        // Execute
        String viewName = controller.getRequestRecord(model);

        // Verify
        verify(paidAppService,times(1)).getUserPaidRequests(loginId); 
        verify(correctRequestService,times(1)).getUserCorrectRequests(loginId); 
        verify(model).addAttribute("paidList", paidList);
        verify(model).addAttribute("correctList", correctList);
        assertEquals("user/requestRecord", viewName);
    }

    @Test
    public void getRequestRecordPaidDetail() {
        // Setup
        int paidAppId = 123; // Example paidAppId
        PaidAppEntity paidAppEntity = new PaidAppEntity(); // Example PaidAppEntity
        PaidRequestForm form = new PaidRequestForm(); // Example PaidRequestForm

        when(paidAppService.getPaidAppOne(paidAppId)).thenReturn(paidAppEntity);
        when(modelMapper.map(paidAppEntity, PaidRequestForm.class)).thenReturn(form);

        // Execute
        String viewName = controller.getRequestRecordPaidDetail(form, model, paidAppId);

        // Verify
        verify(paidAppService,times(1)).getPaidAppOne(paidAppId); 
        verify(modelMapper).map(paidAppEntity, PaidRequestForm.class);
        verify(model).addAttribute("PaidRequestForm", form);
        assertEquals("user/requestRecordPaidDetail", viewName);
    }
    
    @Test
    public void getRequestRecordCorrectDetail() {
        // Setup
        int correctRequestId = 123; // 修正申請IDの例
        CorrectRequestEntity correctRequestEntity = new CorrectRequestEntity(); // 修正申請エンティティの例
        CorrectRequestForm form = new CorrectRequestForm(); // 修正申請フォームの例

        when(correctRequestService.getCorrectRequestOne(correctRequestId)).thenReturn(correctRequestEntity);
        when(modelMapper.map(correctRequestEntity, CorrectRequestForm.class)).thenReturn(form);

        // Execute
        String viewName = controller.getRequestRecordCorrectDetail(new CorrectRequestForm(), model, correctRequestId);

        // Verify
        verify(correctRequestService,times(1)).getCorrectRequestOne(correctRequestId);
        verify(modelMapper).map(correctRequestEntity, CorrectRequestForm.class);
        verify(model).addAttribute("CorrectRequestForm", form);
        assertEquals("user/requestRecordCorrectDetail", viewName);
    }

}
