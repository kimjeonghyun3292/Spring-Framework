package com.itwillbs.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.itwillbs.domain.MemberVO;

// @Repository : DAO객체를 스프링에서 인식 할 수 있도록 처리
//              DAO 객체를 구현한 객체로 사용하도록 지정
@Repository
public class MemberDAOImpl implements MemberDAO {
	// DAO 동작
	
	// 디비 연결 (의존 주입)
	@Inject
	private SqlSession sqlSession;
	// -> Mapper가 있는 위치까지 접근
	
	// Mapper를 구분하는 값
	private static final String namespace
	   ="com.itwillbs.mapper.MemberMapper";   // 임의로 정한 주소  
	
	

	@Override
	public String getTime() {
		System.out.println("DAO : DB접근 sqlSession객체를 주입!");
		System.out.println("DAO : TEST에서 해당메서드 호출!!! ");
		System.out.println("DAO : memberMapper.xml 이동");
		System.out.println("DAO : Select구문을 실행하는 메서드를 실행해서 SQL구문 실행");
		//"com.itwillbs.mapper.MemberMapper.getTime";
		String result = sqlSession.selectOne(namespace+".getTime");
		System.out.println("DAO : SQL 구문 실행완료! 값 리턴 후 테스트로 이동");	
		return result;
	}
	
	
	

	@Override
	public void insertMember(MemberVO vo) {
		
		
		sqlSession.insert(namespace+".insertMember",vo);
		
	}
	
	
	
	@Override
	public MemberVO readMember(String userid) throws Exception {
		
		// 테스트(컨트롤러) 호출 -> 정보를 저장해서 -> DB
		
		// DB에 접근 가능하도록 생성한 객체
		MemberVO vo =   // vo가 리턴됨
		sqlSession.selectOne(namespace + ".readMember", userid);  // 구조가 제네릭으로 만들어져있어서 업, 다운캐스팅을 따로해줄필요없음
		                       // 쿼리실행해서 userid받아와서 돌려줌
		return vo;
	}
	
	
	
    //인터페이스 선언 -> 서브클래스 구현
	@Override
	public MemberVO readMemberWithIDPW(String userid, String userpw) throws Exception {
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		
		paramMap.put("userid", userid);
		paramMap.put("userpw", userpw);
		
		// DB로 정보를 전달하기 위해서는 sqlSession 객체 활용
		// * 1개 이상의 정보를 전달할때는 객체 단위로 전달
		// * 객체(VO) 안에 저장이 안 되는 정보의 경우 Map을 사용
		// => key-value : 이 때 key값은 sql구문의 #{ㅇㅇㅇ} 이름과 같아야함 
		
		
		MemberVO vo = 
		//sqlSession.selectOne(namespace + ".readWithIDPW", userid, userpw);
		  sqlSession.selectOne(namespace + ".readWithIDPW",paramMap);
		
		return vo;
		
		// (윗 세줄 ==) return sqlSession.selectOne(namespace + ".readWithIDPW");
	}




	
	
	
	

	
	
	
	
	
	
	
	

}
