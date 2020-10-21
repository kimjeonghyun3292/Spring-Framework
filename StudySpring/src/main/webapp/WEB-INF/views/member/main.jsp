<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
  <h1>WEB-INF/views/member/main.jsp</h1>
  
  <h1> 메인페이지 </h1>
  
  <%
    String id = (String)session.getAttribute("id");
    
    if(id == null) {
    	// 로그인 페이지로 이동
    	response.sendRedirect("/member/login");
    }
  
  %>
  로그인 ID (JSP): <%=id %> <br>
  로그인 ID2 (EL): ${id }<br><br>
  <input type="button" value="로그아웃" onclick="location.href='/member/logout'">
  <hr>
  
  <h2> 사용자 이름 : ${mvo.username }</h2>
  <h2> 사용자 이메일 : ${mvo.useremail }</h2>
  ${mvo }
  <%=request.getParameter("mvo") %>
 


</body>
</html>