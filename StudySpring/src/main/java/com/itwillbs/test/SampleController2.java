package com.itwillbs.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SampleController2 {
	
	// 로그정보 처리 객체
	private static final Logger logger
	= LoggerFactory.getLogger(SampleController2.class);  // 내가 가진 클래스에서 로그 정보를 찍겠다는 뜻
	
	
	// 메서드를 사용해서 특정 주소에 따른 처리
	// * 메서드 리턴타입이 String일 때 [리턴되는 문자열.jsp] 페이지 호출
	// -> 주소와 보여지는 뷰페이지 이름이 다름.
	// * 컨트롤러와 연결된 뷰페이지를 지정
	
	// http://localhost:8088/test/doC
	@RequestMapping("doC")
	public String doC() {
		logger.info("doC() 메서드 호출@@@@");
	//	return "abc";  // void 가 아니라 String이라서 보여지는 주소와 리턴되는 페이지가 다름
		                // => return이 있냐 없냐에 따라 구분해서 사용하게 됨
		return "home"; // => return을 다르게 해서, 주소에 해당하는 또다른 페이지를 찾아가는 방법
	}
	
	
	// doC.jsp 페이지로 이동 (파라미터 정보를 가지고 이동)
	// // http://localhost:8088/test/doC1
	// // http://localhost:8088/test/doC1?msg="hello"
	@RequestMapping(value = "doC1")
	public String doC1(@ModelAttribute("msg") String msg) { // el표현식에서는 여기 이름@ModelAttribute("msg1")이랑, 주소줄 이름, jsp페이지의 el표현식 이름 이렇게 세 가지가 같아야 출력 가능
		// spring에서 지원하는 애노테이션을 통해서 해당 파라미터값 전달
		
		
		logger.info("doC1() 메서드 호출!!!!");
		
	//	logger.info(" 파라미터 : " + msg);
		// 이 페이지가 연결되어서 뷰 페이지까지도 자동 연결됨
		
		
		return "doC";  // return은 주소 호출이 아니라, doC라는 이름의 jsp페이지(뷰)를 찾아가는 것
		          
	}
	
	// 주소 : ../test/doTotalC
	// 파라미터값 2개 전달 : name="사용자", tel=010-1234-4567
	// 정보확인 페이지 : total.jsp
	// http://localhost:8088/test/doTotalC?name=사용자&tel=010-1234-4567
	@RequestMapping("doTotalC")
	public String doTotal(@ModelAttribute("name") String name, @ModelAttribute("tel") String tel) {
			 {  // String을 쓰는 이유는 주소와 확인 페이지가 다르기 때문
		logger.info("주소 : /test/doTotalC  doTotal() 호출");
		
		return "total";
	}
			 
	
	
	}

}
