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
    // 수정 내용 얻기
    //번호
    String num = request.getParameter("num");
    String name = request.getParameter("name");
    String email = request.getParameter("email");
    String subject = request.getParameter("subject");
    String content = request.getParameter("content");
   
    System.out.println("번호:"+ num);
    System.out.println("작성자:"+ name);
   
    //vo 객체에 저장
    BoardVo boardVo = new BoardVo();
    boardVo.setNum(Integer.valueOf(num));
    boardVo.setName(name);
    boardVo.setSubject(subject);
    boardVo.setContent(content);
    
    // 디비접속
    BoardDao boarddao = BoardDao.getInstance();
    int result = boarddao.updateBoard(boardVo);
    
       
     // 성공/실패처리
     
     if(result > 0) {
       System.out.println("글수정 성공");
       // 성공시 상세 페이지로 이동
       response.sendRedirect("boardview.jsp?num="+num);
    }else{
       System.out.println("글수정 실패");
       response.sendRedirect("boardview.jsp?num="+num);
    }


%>   

</body>
</html>