<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
  <h1>WEB-INF/views.doC.jsp</h1>
  
  
  <h2> 전달 데이터 : <%=request.getParameter("msg") %> </h2>
  <h2> 전달 데이터(el표현식) : ${msg } </h2>  <!-- 페이지의 안정성을 위해 el표현식으로 쓸 것 -> 에러 발생 혹은 데이터가 null일 때, 
                                               컴파일 에러가 아닌 빈공백으로 출력하게하기 위해 -->
                                               

</body>
</html>