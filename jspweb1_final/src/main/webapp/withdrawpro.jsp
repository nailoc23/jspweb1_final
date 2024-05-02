<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ page import="com.touzone.stsb.dao.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%
	String id = request.getParameter("userid");
	System.out.println("아이디="+id);
	String pw = request.getParameter("userpw");
	
	MemberDao memberDao = MemberDao.getInstance();
	int cnt = memberDao.selectMemberByIdPw(id, pw);
	
	if(cnt>0) { // 아이디 비밀번호 확인
		System.out.println("아이디 비밀번호 확인 성공");
		
		int delresult = memberDao.deleteMember(id);
		if(delresult>0) {
			System.out.println("회원탈퇴정보수정 성공");
			session.invalidate();
			response.sendRedirect("loginform.jsp");
		}else{
			System.out.println("회원탈퇴정보수정 실패");
			response.sendRedirect("withdraw.jsp");
		}
		
	}else{
		System.out.println("아이디 비밀번호 확인 실패");
		response.sendRedirect("withdraw.jsp");
	}

		
	%>
</body>
</html>