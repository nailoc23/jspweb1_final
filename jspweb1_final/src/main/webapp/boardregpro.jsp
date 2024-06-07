<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<%@ page import="com.touzone.stsb.dao.*" %> 
<%@ page import="com.touzone.stsb.vo.*" %> 


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	String name = request.getParameter("name");
	String subject = request.getParameter("subject");
	String content = request.getParameter("content");
	String email = request.getParameter("email");
	
	// 넘겨온 값을 모두 출력
	System.out.println(name+"-"+subject+"-"+content+"-"+email);

  	//폼값을 vo 객체에 저장
	BoardVo boardVo = new BoardVo();
	boardVo.setName(name);
	boardVo.setSubject(subject);
	boardVo.setContent(content);
	boardVo.setEmail(email);
	
	// 디비접속
	BoardDao boarddao = BoardDao.getInstance();
	int result = boarddao.insertBoard(boardVo);
	// if(result > 0 ) { // board_list.jsp 이동
	// else { // board_write.jsp
	if(result>0) {
		System.out.println("글쓰기 성공");
		response.sendRedirect("boardlist.jsp");
	}else{
		response.sendRedirect("boardregform.jsp");
	}
%>


</body>
</html>