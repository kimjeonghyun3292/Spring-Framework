package com.itwillbs.test;

import java.sql.Connection;


import javax.inject.Inject;
import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

// @RunWith(SpringJUnit4ClassRunner.class) 구문의 의미 :
// => 해당 파일을 스프링으로 테스트 하겠다는 뜻
//@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"}) 구문의 의미 :
// => 해당 경로에 있는 xml 파일을 읽어서 사용하겠다는 뜻

@RunWith(SpringJUnit4ClassRunner.class)   //일반class인데 스프링으로 테스트 하겠다는 뜻
@ContextConfiguration(
		locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"}
		)
public class DataSourceTest {
	
	// DataSource 객체 생성
	// -> 객체가 필요하다 -> 객체와 의존 관계가 있다. -> 의존 주입 처리(DI)
	
	// @Inject : 해당 객체를 의존 주입 (root-context.xml에 있는 객체 접근)
	
	@Inject    // 의존관계가 있는 것들끼리 가져올 수 있음 (원래는 newDatasource로 만들어야하는데 xml의 bean에 있는 것들을 가지고 이곳에 주입한것
	private DataSource ds;            // 위 location 주소를 통해 불러와서 가져옴
	
	
	// 디비연결 테스트 동작 -> 메서드로.
	@Test
	public void testConn() throws Exception {
		
		try(Connection con = ds.getConnection()){
			System.out.println("@@@@@@@@연결된 객체 정보 : " + ds);
		}catch (Exception e) {
			e.printStackTrace();
	}
		
	}
	
	
	
	
	
	
	
	
	

}
