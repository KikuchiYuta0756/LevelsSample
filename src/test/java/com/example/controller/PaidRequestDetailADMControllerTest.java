package com.example.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.ui.Model;

import com.example.domainUser.model.PaidAppEntity;
import com.example.domainUser.service.PaidAppService;
import com.example.form.PaidRequestForm;

@ExtendWith(MockitoExtension.class)
class PaidRequestDetailADMControllerTest {
	
    @InjectMocks
    private PaidRequestDetailADMController controller;

    @Mock
    private PaidAppService paidappservice;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private Model model;
    
    @Captor
    private ArgumentCaptor<Integer> requestIdCaptor;
	
    @Test
    public void testGetPaidRequestDetailADM() {

    // テストデータのセットアップ
	Integer paidAppId = 1;
    PaidAppEntity paidAppADM = new PaidAppEntity();
    PaidRequestForm form = new PaidRequestForm();

    when(paidappservice.getPaidAppOne(paidAppId)).thenReturn(paidAppADM);
    when(modelMapper.map(paidAppADM, PaidRequestForm.class)).thenReturn(form);

    // メソッド呼び出し
    String viewName = controller.getPaidRequestDetailADM(form, model, paidAppId);

    // メソッドの戻り値が期待どおりであることを確認
    assertEquals("admin/paidRequestDetail", viewName);
    verify(paidappservice,times(1)).getPaidAppOne(paidAppId);   // サービスが適切に呼び出されたことを確認
    verify(model).addAttribute(eq("PaidRequestForm"),any(PaidRequestForm.class));
}

    @Test
    public void testUpdatePaidRequestApproval() {
        // テストデータのセットアップ
    	PaidRequestForm form = new PaidRequestForm();
        form.setPaidAppId(1);
        
        // メソッド呼び出し
        String viewName = controller.updatePaidRequestApproval(form, model);

        // メソッドの戻り値が期待どおりであることを確認
        assertEquals("redirect:/admin/paidRequestList", viewName); 
        verify(paidappservice).updateRequestStaApproval(requestIdCaptor.capture());
        assertEquals(1, requestIdCaptor.getValue().intValue());
    
    }

    @Test
    public void testUpdatePaidRequestRemand() {
        // テストデータのセットアップ
    	PaidRequestForm form = new PaidRequestForm();
    	form.setPaidAppId(1);
        
        // メソッド呼び出し
        String viewName = controller.updatePaidRequestRemand(form, model);

        // メソッドの戻り値が期待どおりであることを確認
        assertEquals("redirect:/admin/paidRequestList", viewName); 
        verify(paidappservice).updateRequestStaRemand(requestIdCaptor.capture()); //サービスが適切に呼び出されたことを確認
        assertEquals(1, requestIdCaptor.getValue().intValue());
    }
    

    
}
