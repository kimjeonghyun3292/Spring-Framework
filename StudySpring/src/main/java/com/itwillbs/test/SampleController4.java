package com.itwillbs.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.itwillbs.domain.ProductVO;

@Controller
public class SampleController4 {
	
	// 로그정보 처리 객체
			private static final Logger logger
			= LoggerFactory.getLogger(SampleController4.class); 
			
			// * 페이지 이동 redirect 동작
			// 메서드 String 리턴시 이동할 주소앞에 "redirect:" 사용
			
			// http://localhost:8088/test/doE
			@RequestMapping("/doE")
			public String doE(Model model, RedirectAttributes rttr) {
				logger.info("doE() 호출");
				
			//	model.addAttribute("msg", "1234doE!");  // model객체에 addAttribute를 사용해도 주소줄에 정보 가져갈 수 있음(키값없이 가능)
				//model.addAttribute("1234doE!"); // => 키 지정 안한것(value만 지정). 둘 중 하나의방법으로 쓰면됨(아래쪽 코드가 더 많이 쓰임)
				// => 키가 지정안되어있으면 String 타입(문자열) 으로 호출을 해야되는데 못하니까 소문자s로 출력해야함 (규칙)
			//	model.addAttribute("msg", new TestVO("test", 1234));
				// => redirect : 전달X
				
				rttr.addAttribute("msg", "Add!!!!"); // => 새로고침을 해도 주소줄에 데이터에 남아있기 때문에 값이 계속 남아있음
			//	rttr.addFlashAttribute("msg", "flash!!!!");
		        // =>처음에 doE를 주소줄에서 불렀을때는 값을 가지고 가는것이 목적이기 때문에 값이 나옴 but, 새로고침을 하면
				//   doF 자체의 값이 없기때문에 사라짐 (일회성 => 조회수를 구현할때 해당됨)
				
				// 정리) RedirectAttributes 객체 : URI(주소줄)에 보이지 않게 데이터 전달
				// addAttribute() : URI에 표시가 됨, EL 표현식으로 볼 수 있음, JSP페이지에서 볼 수 있음(주소줄에 보이니까), 새로고침했을 때 값 유지
				// addFlashAttribute() : URI에 표시가 안됨, EL 표현식으로 볼 수 있음, JSP페이지에서 볼 수 없음, 새로고침했을때 유지X (1회성 데이터)
				
				
				//return "doF";   // => 주소줄에는 doE가 있는데 화면상에는 doF가 나옴
				//return "redirect:/doF";  // => 주소줄에 doE를 쳐도 doF로 바뀌면서 페이지도 doF를 찾아가게 됨
				                           // => return "redirect:/doF"; => 주소변경O, 페이지 변경O
				                           // => return "forward:/doF"; => 주소변경X, 페이지 변경O
				
				//return "redirect:/doF?msg=test1234";
				//return "redirect:/doF?msg=" + test;
				return "redirect:/doF";
				// => model.addAttribute(); 로 데이터 전달할 때는
				//    저장한 데이터는 파라미터형태로 전달됨
			}
			// redirect를 리턴하면 redirect를 하냐 안한의 차이가 생김
			
			    
			
			// http://localhost:8088/test/doF
			@RequestMapping("/doF")   // doE를 통해 doF의 페이지로 이동하게 됨
			public void doF(@ModelAttribute("msg") String msg) {  // view페이지 화면에 el표현식이 출력되게 하려면 @ModelAttribute를 넣어줘야함
				logger.info("doF() 호출");   
				 
			}
			

}
