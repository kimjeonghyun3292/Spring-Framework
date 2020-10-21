package com.itwillbs.test;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"} // 이 위치에 있는 해당파일을 불러오겠다는 뜻
		)
public class MyBatisTest {
	
	// SqlSessionFactory 객체 생성 -> DI(의존주입)
	@Inject
	private SqlSessionFactory sqlFactory;   // bean에 있는 id로 그대로 가져감  (@Inject를 주석처리하면 객체 정보가 null이 됨. 전달된 정보값이 없어서)
	// xml 코드 - SqlSessionFactoryBean 객체 생성
	// => 주입하면서 SqlSessionFactory 형변환(업캐스팅)
	
	// 객체 주입 후 확인동작 - 메서드
	@Test
	public void testFactory() throws Exception{
		System.out.println("@@@@@@@@@ 생성된 객체 정보 : " + sqlFactory);
	}
	
	// 주입받은 sqlFactory 객체 사용 -> openSession() 호출해서 리턴되는 정보 확인하기!
	@Test
	public void testSession() throws Exception{
		
		SqlSession session = sqlFactory.openSession();  // 받아오는게 없어서 default로 , SqlSession을 리턴하고있기 때문에 Sqlsession으로 넣어줌
		System.out.println("@@@@@@@@@ 생성된 session 객체 : " + session);
		//session.delete(statement);  // jsp에서 sql변수에 담았던 쿼리를 xml파일로 다시 넣어줄것.
		//session.insert(statement);
		//session.update(statement);
		//session.select(statement, handler);
		
	}
	
	 

}
