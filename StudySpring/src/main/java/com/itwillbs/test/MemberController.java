package com.itwillbs.test;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.itwillbs.domain.MemberVO;
import com.itwillbs.service.MemberService;

@Controller
@RequestMapping(value = "/member/*")
public class MemberController {
	
	// 서비스 처리 객체를 주입(DI)
	@Inject
	private MemberService service;
	
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	// http://localhost:8088/test/insert (X)
	// http://localhost:8088/test/member/insert (X)
	// http://localhost:8088/member/insert	=> 서버 더블클릭 -> 모듈명 클릭하고 edit해서 /만 남겨놓고 저장 -> 이 주소로 실행 후 메세지, 로그 찍히는 것 확인
	//  => 주소로 기능을 구분해서 쓸수있도록 하는 것이 목적 (주의점 : 실행시킨 후 로그가 실행되는 동안 다시 실행시키면 안됨)
	// 회원가입 처리 동작
	@RequestMapping(value = "/insert", method = RequestMethod.GET)  // 입력받는 방식
	public String insertGET() throws Exception {
		
		logger.info("C : 회원가입 페이지(정보입력)");
		logger.info("C : /member/insert -> /member/insertMember.jsp 페이지 이동");
		
		
		return "/member/insertMember";
	}
	
	
	@RequestMapping(value = "/insert", method = RequestMethod.POST)  // 똑같은 주소의 pro 
	public String insertPOST(MemberVO vo) throws Exception {
		logger.info("C : 회원가입 처리 페이지(정보처리)");
		logger.info("C : /member/insertMember.jsp -> 처리");  // 회원가입 페이지에서 정보넣고 회원가입누르면 inserPro라는 주소로 이동되는지 확인
		
		// 0. 한글처리
		// 1. 전달되는 정보 저장하기(파라미터값)
		 logger.info("C : " + vo);   // request.getParameter 로 출력하는것 대신한 것
		// 2. 서비스 객체 생성 (의존주입)
		// 3. 서비스 회원가입 동작 호출
		service.insertMember(vo);
		
		logger.info("C: 회원가입 완료!");
		// 4. 로그인 페이지로 이동(GET)
		//  [ /member/login ]
		
		
		return  "redirect:/member/login";    // 주소가 member/login으로 바뀜
	}
	
         // 모델2는 주소로 페이지를 옮기는 방식, spring은 방식으로 통제
	
	// http://localhost:8088/member/login
	// 로그인 처리(GET)
	@RequestMapping(value = "/login", method = RequestMethod.GET)      // member는 컨트롤러명이기때문에 빼고 login만 넣어도됨
	public String loginGET() throws Exception {
		
		logger.info("C : 로그인 처리 페이지");
		logger.info("C : /member/login -> /member/loginForm.jsp 이동");
		
		
		return  "/member/loginForm";
		
	}
	
	// http://localhost:8088/member/login (POST)
	// 로그인 처리(POST)
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String loginPOST(MemberVO vo, HttpSession session, RedirectAttributes rttr /*, @ModelAttribute("userid") String id */) throws Exception {
		                  // jsp페이지안에는 내장객체 session이 존재함 (로그인 클릭할때, vo받아왔던거처럼 session정보도 받아오겠다는 뜻)
		                 // model : controller -> view로 정보를 저장해서 전달하기 위한 객체 
		    
		logger.info("C : loginPOST 동작");
		logger.info("C : loginForm.jsp -> /login(POST) ");
		
		// 1. 전달정보를 저장(파라미터값:ID,PW)
		logger.info("C : " + vo);
	 // logger.info("C : id =" + id);
		// 2. 서비스 객체 생성 => 주입완료(위에)
		// 3. 서비스 로그인 체크 동작
		MemberVO returnVO = service.loginMember(vo);       // DB에서 값을 가지고 return해올때 모든 정보들이 다 들어있음(-> 여기서 필요한정보 꺼내오기)
		logger.info("C : 결과" + returnVO);
		// 4. 해당 결과에 따라 페이지 이동 제어
		//  - 해당 정보가 있을 경우 : -> main 페이지
		//    -> 5. 세션값 생성
		//  - 해당 정보가 없을 경우 : -> login 페이지
		if(returnVO == null) {
			return "redirect:/member/login";  // id, pw가 없을떄(틀릴때) 다시 로그인페이지로 돌아가도록 함
		}
		
		// 5. 세션값 생성
		session.setAttribute("id", returnVO.getUserid());
		
		// 6. 정보 저장해서 view페이지로 전달 ( model : controller -> view로 정보를 저장해서 전달하기 위한 객체 )
		//model.addAttribute("mvo", returnVO);  // ("key값", 실제 객체값) => 실제 객체는 returnVO안에 들어있음 => 실제값을 key값으로 저장해서 보냄
		// * 페이지 리다이렉트 이동시 ( RedirectAttributes 객체 ) 사용해서 정보 전달
		rttr.addFlashAttribute("mvo", returnVO);
		// rttr.addAttribute("mvo", returnVO); (X) => 객체를 넘겨서 캐스팅을 하는 과정에서 문제가 생김
		// rttr.addAttribute("mvo", "Hello1234"); => 주소에 보내게됨(주소에 사용할 수 있는 string타입으로 캐스팅해서)
			
		
		//logger.info("C : model ->" + model);
		
		return  "redirect:/member/main";   // 로그인하면, 메인정보를 가지고감(주소줄에)
		
	}
	
	// http://localhost:8088/member/main
	// main 페이지 
	@RequestMapping(value = "/main", method = RequestMethod.GET)    // member는 컨트롤러에 대한 주소
	public String mainGET() throws Exception{
		logger.info("C : main 페이지");
		logger.info("C : /main -> /member/main.jsp");
		
		return "/member/main";
	}
	
	
	// http://localhost:8088/member/logout
	// 로그아웃
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logoutGET(HttpSession session) throws Exception{   // location.href 주소를 쓰기 때문에 GET방식
		logger.info("C : 로그아웃 처리");
		// 세션초기화 -> 로그아웃
		session.invalidate();
		
		return "redirect:/member/main";
	}
	
	
	
}
