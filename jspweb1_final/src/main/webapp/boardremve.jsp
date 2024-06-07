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
	String num = request.getParameter("num");   // 일련번호얻기
	
	BoardDao boarddao = BoardDao.getInstance();
	BoardVo boardVo = boarddao.selectBoardById(Integer.valueOf(num));// 주어진 일련번호에 해당하는 기존 게시물 얻기
	int result = boarddao.deleteBoardById(num); // 게시물 삭제

	// 성공 실패 처리
      
	if(result > 0) { // 게시물 삭제 성공시 첨부 파일도 삭제 
      
		System.out.println("삭제 성공");
		response.sendRedirect("boardlist.jsp");
       
	}else{
       System.out.println("삭제 실패");
       response.sendRedirect("boardview.jsp?num="+num);
              
       return;
    }
 
  %>   


</body>
</html>