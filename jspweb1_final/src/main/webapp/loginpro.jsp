<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.touzone.stsb.dao.*" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MINI COOKIE</title>
</head>
<body>

<%
	String id = request.getParameter("userid");
	String pw = request.getParameter("userpw");
	
	MemberDao memberDao = MemberDao.getInstance();
	int cnt = memberDao.selectMemberByIdPw(id, pw);
	
	if(cnt>0) {
		System.out.println("로그인성공");
		response.sendRedirect("loginform.jsp");
	}else{
		System.out.println("로그인실패");
		response.sendRedirect("loginform.jsp");
	}

%>
</body>
</html>