<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
  <h1>WEB-INF/views/doF.jsp</h1>
  <%
    request.setCharacterEncoding("UTF-8");
  %>
  
  <h2> 컨트롤러에서 전달된 객체 정보 </h2>
  
  <h2> 전달값(jsp) : <%=request.getParameter("msg") %> </h2>
  <h2> 전달값(el) : ${msg } </h2>

</body>
</html>