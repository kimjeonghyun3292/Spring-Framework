package com.itwillbs.test;

import javax.inject.Inject;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

// @RunWith
// @ContextConfiguration
//  => 스프링으로 테스트하겠다.  
// @WebAppConfiguration 
//  => 스프링 MVC로 테스트하겠다.

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/*.xml"})

public class SampleControllerTest {
	
	    // 로그정보 처리 객체
		private static final Logger logger
		= LoggerFactory.getLogger(SampleController5.class); 
		
		// 객체 의존 주입
		@Inject
		private WebApplicationContext wac;
		
		private MockMvc mockMvc;
		// => 브라우저에서 페이지 요청, 응답을 처리하는 객체
		// => 가상이 요청, 응답을 처리 
		
		@Before 
		public void setup() {
			// Test 실행전 준비 사항
			this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build(); //객체 주입받아온것(wac)으로 실행 준비를 하겠다는 뜻
			
			logger.info("mockMvc 객체 생성완료 (준비 완료)");
			
		}
		
		@Test
		public void testDoA() throws Exception{
			
			logger.info(" Junit을 사용해서 컨트롤러 동작 실행 ");
			
			mockMvc.perform(MockMvcRequestBuilders.get("/doA"));  //Junit으로 실행
			// test 목적이기때문에 동작을 하는가 하지 않는가만 체크 가능(web화면에는 나타나지 않음)
			
		}
	

}
