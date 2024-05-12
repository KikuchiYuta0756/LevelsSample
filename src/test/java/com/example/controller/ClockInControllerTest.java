package com.example.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import com.example.domainUser.model.UserMapperEntity;
import com.example.domainUser.service.UserService;
import com.example.domainUser.service.WorkTimeService;

@ExtendWith(MockitoExtension.class)
public class ClockInControllerTest {

    @Mock
    private UserService userService;

    @Mock
    private WorkTimeService workTimeService;

    @Mock
    private Model model;

    @InjectMocks
    private ClockInController controller;

    private MockMvc mockMvc;

    @Test
    public void testGetClockIn() throws Exception {
        // Mock userService.getWorkFlg(loginId)
        when(userService.getWorkFlg(anyString())).thenReturn(new UserMapperEntity());

        // Perform GET request
        mockMvc.perform(get("/user/clockIn"))
            .andExpect(status().isOk())
            .andExpect(view().name("user/clockIn"))
            .andExpect(model().attributeExists("userWorkFlg"));

        // Verify userService.getWorkFlg(loginId) is called
        verify(userService, times(1)).getWorkFlg(anyString());
    }

    @Test
    public void testAttendanceTime() throws Exception {
        // Perform POST request
        mockMvc.perform(post("/user/clockIn").param("attendance", ""))
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("/user/clockIn"));

        // Verify workTimeService.startTimeSignup(worktime) is called
        verify(workTimeService, times(1)).startTimeSignup(any());
        // Verify userService.getWorkFlgLeaving(loginId) is called
        verify(userService, times(1)).getWorkFlgLeaving(anyString());
    }

    @Test
    public void testLeavingTime() throws Exception {
        // Perform POST request
        mockMvc.perform(post("/user/clockIn").param("leaving", ""))
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("/user/clockIn"));

        // Verify workTimeService.closeTimeSignup(worktime) is called
        verify(workTimeService, times(1)).closeTimeSignup(any());
        // Verify workTimeService.overTimeSignup(worktimeentity) is called
        verify(workTimeService, times(1)).overTimeSignup(any());
        // Verify userService.getWorkFlgAttendance(loginId) is called
        verify(userService, times(1)).getWorkFlgAttendance(anyString());
        // Verify workTimeService.updateTotalWorkTime(loginId) is called
        verify(workTimeService, times(1)).updateTotalWorkTime(anyString());
    }

    // Setup method to initialize mockMvc before each test
    // You may need to adjust mockMvc setup according to your project's configuration
    // This is a basic setup example
    // @BeforeEach
    // public void setup() {
    //     mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    // }
}
