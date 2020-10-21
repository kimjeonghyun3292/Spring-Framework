package com.itwillbs.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.itwillbs.domain.MemberVO;
import com.itwillbs.persistence.MemberDAO;

// @Service : 해당 클래스를 서비스 객체로 처리 하겠다. (스프링에서 인식)

@Service
public class MemberServiceImpl implements MemberService{

	// DB 처리하기위한 객체 주입 (의존주입 - 의존하고있는 객체를 직접 가져오는것, root-context.xml(객체가 준비된 상태)의 서비스 패키지에서 가져오는 것)
	@Inject
	private MemberDAO mdao;
	
	
	@Override
	public void insertMember(MemberVO vo) {
		// 컨트롤러 -> 서비스 호출 -> DAO 호출 -> Mapper -> DB    => 서비스와, (DAO 호출 -> Mapper -> DB)를 연결하기		
		System.out.println("Service : 회원가입 동작");
		if(vo==null) {
			// 처리
			return;  
		}
		
		mdao.insertMember(vo);

	}


	@Override
	public MemberVO loginMember(MemberVO vo) {
        System.out.println("S : 컨트롤러에서 호출");
        System.out.println("S : 필요한 정보를 받아서 DAO 전달");
        MemberVO returnVO = null;
        try {
        	 // DAO 객체 생성 (DI)
        	returnVO = mdao.readMemberWithIDPW(vo.getUserid(), vo.getUserpw());
		} catch (Exception e) {
			e.printStackTrace();
			returnVO = null;
		}

		return returnVO;
	}
	
	
	
	
	
	
	
	

}
