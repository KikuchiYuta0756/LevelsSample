package com.example.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ExtendWith(MockitoExtension.class)
class LogoutControllerTest {

	//InjectMocksはLogoutControllerをテスト対象として注入している。
	@InjectMocks
	private LogoutController logoutController;
	
	private MockMvc mockMvc; 
	
	//BeforeEachアノテーションは各テストメソッドの前に実行される
	@BeforeEach
	void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(logoutController).build();
	}
	
	//testPostLogout()メソッドで"/logout"へのPOSTリクエストをシミュレートを実施
	@Test
	void testPostLogout() throws Exception {
		mockMvc.perform(post("/logout"))
		       .andExpect(status().is3xxRedirection())   //SpringのMockMvcフレームワークを使用するHTTPリクエストの検証メソッド
		       .andExpect(redirectedUrl("/login/login")); //リダイレクト先の検証
	}

}
