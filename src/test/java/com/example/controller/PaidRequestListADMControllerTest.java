package com.example.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
import org.springframework.ui.Model;

import com.example.domainUser.model.PaidAppEntity;
import com.example.domainUser.service.PaidAppService;
import com.example.form.PaidRequestListForm;

@ExtendWith(MockitoExtension.class)
class PaidRequestListADMControllerTest {

    @InjectMocks
    private PaidRequestListADMController controller;	
	
    @Mock
    private PaidAppService paidAppService;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private Model model;
    
    
    @Test
    void getPaidRequestListADM() {
        // Setup
        PaidRequestListForm form = new PaidRequestListForm();
        PaidAppEntity paid = new PaidAppEntity();
        List<PaidAppEntity> paidList = new ArrayList<>();
        paidList.add(paid);

        when(modelMapper.map(form, PaidAppEntity.class)).thenReturn(paid);
        when(paidAppService.getPaidRequests(paid)).thenReturn(paidList);

        // Exercise
        String viewName = controller.getPaidRequestListADM(form, model);

        // Verify
        verify(modelMapper).map(form, PaidAppEntity.class);
        verify(paidAppService).getPaidRequests(paid);
        verify(model).addAttribute("paidList", paidList);
        assertEquals("admin/paidRequestList", viewName);
    }

    @Test
    void postPaidRequestListADM() {
        // Setup
        PaidRequestListForm form = new PaidRequestListForm();
        PaidAppEntity paid = new PaidAppEntity();
        List<PaidAppEntity> paidList = new ArrayList<>();
        paidList.add(paid);

        when(modelMapper.map(form, PaidAppEntity.class)).thenReturn(paid);
        when(paidAppService.getPaidRequests(paid)).thenReturn(paidList);

        // Exercise
        String viewName = controller.postPaidRequestListADM(form, model);

        // Verify
        verify(modelMapper).map(form, PaidAppEntity.class);
        verify(paidAppService).getPaidRequests(paid);
        verify(model).addAttribute("paidList", paidList);
        assertEquals("admin/paidRequestList", viewName);
    }

}
