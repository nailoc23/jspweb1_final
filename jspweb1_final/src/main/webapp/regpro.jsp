<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="com.touzone.stsb.dao.*" %>
<%@ page import="com.touzone.stsb.vo.*" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MINI COOKIE</title>
</head>
<body>

<%
	String memid = request.getParameter("userid");
	String pw = request.getParameter("userpw");
	String name = request.getParameter("name");
	String email = request.getParameter("email");
	String phone = request.getParameter("phone");
	
	MemberVo membervo = new MemberVo();
	membervo.setMemid(memid);
	membervo.setPassword(pw);
	membervo.setName(name);
	membervo.setEmail(email);
	membervo.setPhone(phone);
	
	MemberDao memdao = MemberDao.getInstance();
	int result = memdao.insertMember(membervo);
	
	if(result>0) {
		System.out.println("로그인 성공");
		response.sendRedirect("loginform.jsp");
	}else{
		response.sendRedirect("regform.jsp");
	}
%>
	
	
	
	
	
	