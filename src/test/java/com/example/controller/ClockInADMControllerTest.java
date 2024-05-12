//package com.example.controller;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//import static org.mockito.Mockito.*;
//
//import java.time.LocalDateTime;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.ui.Model;
//import org.springframework.web.servlet.ModelAndView;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContext;
//import org.springframework.security.core.context.SecurityContextHolder;
//
//import com.example.domainUser.model.UserMapperEntity;
//import com.example.domainUser.model.WorkTimeEntity;
//import com.example.domainUser.service.UserService;
//import com.example.domainUser.service.WorkTimeService;
//import com.example.form.UserDetailForm;
//
//class ClockInADMControllerTest {
//
//    private ClockInADMController controller;
//    private UserService userService;
//    private WorkTimeService workTimeService;
//    private Model model;
//
//    @BeforeEach
//    void setUp() {
//        userService = mock(UserService.class);
//        workTimeService = mock(WorkTimeService.class);
//        model = mock(Model.class);
//        controller = new ClockInADMController(userService, workTimeService);
//    }
//
//    @Test
//    void testGetClockIn() {
//        Authentication auth = mock(Authentication.class);
//        SecurityContext securityContext = mock(SecurityContext.class);
//        when(securityContext.getAuthentication()).thenReturn(auth);
//        SecurityContextHolder.setContext(securityContext);
//
//        UserMapperEntity userMapperEntity = new UserMapperEntity();
//        when(userService.getWorkFlg(anyString())).thenReturn(userMapperEntity);
//
//        UserDetailForm form = new UserDetailForm();
//        ModelAndView modelAndView = controller.getClockIn(form, model);
//
//        verify(model).addAttribute("userWorkFlg", form);
//        assert modelAndView.getViewName().equals("admin/clockInADM");
//    }
//
//    @Test
//    void testAttendanceTime() {
//        Authentication auth = mock(Authentication.class);
//        SecurityContext securityContext = mock(SecurityContext.class);
//        when(securityContext.getAuthentication()).thenReturn(auth);
//        SecurityContextHolder.setContext(securityContext);
//
//        LocalDateTime now = LocalDateTime.now();
//        UserMapperEntity userMapperEntity = new UserMapperEntity();
//        when(userService.getWorkFlg(anyString())).thenReturn(userMapperEntity);
//
//        WorkTimeEntity workTimeEntity = new WorkTimeEntity();
//        workTimeEntity.setWorkDate(now.toString());
//        workTimeEntity.setStartTime(now.toString());
//        workTimeEntity.setLoginId("test");
//
//        controller.attendanceTime();
//
//        verify(workTimeService).startTimeSignup(workTimeEntity);
//    }
//
//    @Test
//    void testLeavingTime() {
//        Authentication auth = mock(Authentication.class);
//        SecurityContext securityContext = mock(SecurityContext.class);
//        when(securityContext.getAuthentication()).thenReturn(auth);
//        SecurityContextHolder.setContext(securityContext);
//
//        LocalDateTime now = LocalDateTime.now();
//        UserMapperEntity userMapperEntity = new UserMapperEntity();
//        when(userService.getWorkFlg(anyString())).thenReturn(userMapperEntity);
//
//        WorkTimeEntity workTimeEntity = new WorkTimeEntity();
//        workTimeEntity.setWorkDate(now.toString());
//        workTimeEntity.setCloseTime(now.toString());
//        workTimeEntity.setLoginId("test");
//
//        controller.leavingTime();
//
//        verify(workTimeService).closeTimeSignup(workTimeEntity);
//    }
//
//}
