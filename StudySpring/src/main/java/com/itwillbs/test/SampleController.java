package com.itwillbs.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SampleController { 
	
	// 로그정보 처리 객체
	private static final Logger logger
	 = LoggerFactory.getLogger(SampleController.class);
	
	// 특정 주소에 따른 처리 -> 메서드
	// * 메서드의 리턴타입이 void인 경우 [주소.jsp] 페이지 호출
	@RequestMapping("doA")    // (Controller페이지에서)주소 짤라서 command에 담아서 equals담아서 비교, if~else 쓰던 것을 이 코드 두줄에 표현 가능
	public void doA() {     // 매핑되는 주소가 바뀌면 찾아가는 jsp 주소도 같이 바뀜
		System.out.println("doA() 호출!!!");
		logger.info("doA() 호출@@@");
		
	}
	
	
	// doB 주소에 따른 처리 동작
	// "메세지 호출"
	@RequestMapping("doB")  // 메서드가 void일때는(리턴하지 않을때는), 이 주소에 해당하는 뷰가 찾아갈 수 있게 됨(뷰는 주소이름과 같음) 
	public void doB() {
		logger.info("doB() 메서드 호출@@@");
	} 
	
	
	
	
	
	
	
	
	
	
	
	

}
