package com.example.controller;

import org.modelmapper.ModelMapper;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;
import com.example.domainUser.model.CorrectRequestEntity;
import com.example.domainUser.service.CorrectRequestService;
import com.example.form.CorrectRequestForm;

@ExtendWith(MockitoExtension.class)
class CorrectRequestDetailADMControllerTest {

    @InjectMocks
    private CorrectRequestDetailADMController controller;

    @Mock
    private CorrectRequestService correctRequestservice;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private Model model;
    
    @Captor
    private ArgumentCaptor<Integer> requestIdCaptor;

    @Test
    public void testGetCorrectRequestDetailADM() {
        // テストデータのセットアップ
    	Integer correctRequestId = 1;
        CorrectRequestEntity correctRequest = new CorrectRequestEntity();
        CorrectRequestForm form = new CorrectRequestForm();

        when(correctRequestservice.getCorrectRequestOne(correctRequestId)).thenReturn(correctRequest);
        when(modelMapper.map(correctRequest, CorrectRequestForm.class)).thenReturn(form);

        // メソッド呼び出し
        String viewName = controller.getCorrectRequestDetailADM(form, model, correctRequestId);

        // メソッドの戻り値が期待どおりであることを確認
        assertEquals("admin/correctRequestDetail", viewName);
        verify(correctRequestservice,times(1)).getCorrectRequestOne(correctRequestId);   // サービスが適切に呼び出されたことを確認
        verify(model).addAttribute(eq("CorrectRequestForm"),any(CorrectRequestForm.class));
    }
    
    @Test
    public void testUpdateCorrectRequestApproval() {
        // テストデータのセットアップ
        CorrectRequestForm form = new CorrectRequestForm();
        form.setCorrectRequestId(1);
        
        // メソッド呼び出し
        String viewName = controller.updateCorrectRequestApproval(form, model);

        // メソッドの戻り値が期待どおりであることを確認
        assertEquals("redirect:/admin/correctRequestList", viewName); 
       // verify(correctRequestservice).updateRequestStaApproval(correctRequestId); //サービスが適切に呼び出されたことを確認
        verify(correctRequestservice).updateRequestStaApproval(requestIdCaptor.capture());
        assertEquals(1, requestIdCaptor.getValue().intValue());
    
    }

    @Test
    public void testUpdateCorrectRequestRemand() {
        // テストデータのセットアップ
        CorrectRequestForm form = new CorrectRequestForm();
        form.setCorrectRequestId(1);
        
        // メソッド呼び出し
        String viewName = controller.updateCorrectRequestRemand(form, model);

        // メソッドの戻り値が期待どおりであることを確認
        assertEquals("redirect:/admin/correctRequestList", viewName); 
        verify(correctRequestservice).updateRequestStaRemand(requestIdCaptor.capture()); //サービスが適切に呼び出されたことを確認
        assertEquals(1, requestIdCaptor.getValue().intValue());
    }
    
    
}
