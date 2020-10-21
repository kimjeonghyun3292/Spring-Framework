package com.itwillbs.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.itwillbs.domain.ProductVO;

@Controller
public class SampleController3 {
	
	// 로그정보 처리 객체
		private static final Logger logger
		= LoggerFactory.getLogger(SampleController3.class); 
		
		// 페이지 이동시 객체 정보를 가지고 이동
		
		// http://localhost:8088/test/doD  
		@RequestMapping("doD")
		public String doD(Model model) {
			logger.info("doD() 호출!!!");
			
			// 상품 객체생성
			ProductVO vo = new ProductVO("phone", 100);
			
			// Model 객체 : 객체를 저장해서 뷰페이지로 전달(컨테이너_박스덩어리) => 값을 가지고 뷰페이지로 이동시키는 것이 주목적
			model.addAttribute(vo);  // vo객체의 vo를 model안에 vo라는 이름으로 저장해서 넘김
			           // KET값 없이 객체만(vo 만) 찍어서 보낼경우 값이 안보임 => jsp 페이지에서 ${product.name}으로 찍어야 값이 보임
			           // => 받아서 사용할때 전달하는 클래스 타입으로 호출  => 첫글자를 소문자로 변경해서 호출
			                 // (ProductVO를 넘기고 있는데 그대로 넘기면 못넘기기 때문에 첫 글자만 소문자로 바꿔서 productVO로 넘기겠다는 뜻)
			             // 객체 저장시, 키값이 없을 경우, 받아서 사용할때 전달하는 클래스의 타입으로 호출.
			              // => 클래스 타입의 첫글자를 소문자로 변경해서 호출  Product VO => product VO
			    
			
			return "doDPage";
			
		}
	

}
