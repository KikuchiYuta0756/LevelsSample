package com.example.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
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

import com.example.domainUser.model.CorrectRequestEntity;
import com.example.domainUser.service.CorrectRequestService;
import com.example.form.CorrectListForm;
import com.example.form.CorrectRequestForm;

@ExtendWith(MockitoExtension.class)
class CorrectRequestListADMControllerTest {

    @InjectMocks
    private CorrectRequestListADMController controller;
	
    @Mock
    private CorrectRequestService correctRequestService;
    
    @Mock
    private Model model;
    
    @Mock
    private ModelMapper modelMapper;

    @Test
    void getCorrectRequestListADM() {
        // Setup
        CorrectListForm form = new CorrectListForm();
        CorrectRequestEntity entity = new CorrectRequestEntity();
        List<CorrectRequestEntity> correctList = new ArrayList<>();
        correctList.add(entity);

        when(modelMapper.map(form, CorrectRequestEntity.class)).thenReturn(entity);
        when(correctRequestService.getCorrectRequests(entity)).thenReturn(correctList);

        // Exercise
        String viewName = controller.getCorrectRequestListADM(form, model);

        // Verify
        verify(modelMapper).map(form, CorrectRequestEntity.class);
        verify(correctRequestService).getCorrectRequests(entity);
        verify(model).addAttribute("correctList", correctList);
        assertEquals("admin/correctRequestList", viewName);
    }

    @Test
    void postCorrectRequestListADM() {
        // Setup
        CorrectListForm form = new CorrectListForm();
        CorrectRequestEntity entity = new CorrectRequestEntity();
        List<CorrectRequestEntity> correctList = new ArrayList<>();
        correctList.add(entity);

        when(modelMapper.map(form, CorrectRequestEntity.class)).thenReturn(entity);
        when(correctRequestService.getCorrectRequests(entity)).thenReturn(correctList);

        // Exercise
        String viewName = controller.postCorrectRequestListADM(form, model);

        // Verify
        verify(modelMapper).map(form, CorrectRequestEntity.class);
        verify(correctRequestService).getCorrectRequests(entity);
        verify(model).addAttribute("correctList", correctList);
        assertEquals("admin/correctRequestList", viewName);
    }

}
