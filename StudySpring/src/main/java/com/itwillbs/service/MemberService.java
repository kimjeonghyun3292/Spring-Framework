package com.itwillbs.service;

import com.itwillbs.domain.MemberVO;

public interface MemberService {
	
	// 서비스 계층
	
	// 회원 가입 ( 일반회원가입 / SNS 계정 - 간단 회원가입 )    //서비스(일반회원일때, sns일때)->DAO->insert(VO)
	public void insertMember(MemberVO vo);   // 저장하고 ServiceImpl에서 구현
	
	// 회원 로그인체크
	public MemberVO loginMember(MemberVO vo);
	

}
