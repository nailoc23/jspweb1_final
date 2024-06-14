<%@ page language="java" contentType="text/html; charset=UTF-8"%>


<%@ page import="com.touzone.stsb.dao.*" %> 
<%@ page import="com.touzone.stsb.vo.*" %> 
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="utf-8">
  <meta content="width=device-width, initial-scale=1.0" name="viewport">

  <title>MINI COOKIE</title>
  <meta content="" name="description">
  <meta content="" name="keywords">

  <!-- Favicons -->
  <link href="assets/img/favicon.png" rel="icon">
  <link href="assets/img/apple-touch-icon.png" rel="apple-touch-icon">

  <!-- Google Fonts -->
  <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Jost:300,300i,400,400i,500,500i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i" rel="stylesheet">

  <!-- Vendor CSS Files -->
  <link href="assets/vendor/aos/aos.css" rel="stylesheet">
  <link href="assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
  <link href="assets/vendor/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">
  <link href="assets/vendor/boxicons/css/boxicons.min.css" rel="stylesheet">
  <link href="assets/vendor/glightbox/css/glightbox.min.css" rel="stylesheet">
  <link href="assets/vendor/remixicon/remixicon.css" rel="stylesheet">
  <link href="assets/vendor/swiper/swiper-bundle.min.css" rel="stylesheet">

  <!-- Template Main CSS File -->
  <link href="assets/css/style.css" rel="stylesheet">

  <!-- =======================================================
  * Template Name: Arsha
  * Template URL: https://bootstrapmade.com/arsha-free-bootstrap-html-template-corporate/
  * Updated: Mar 17 2024 with Bootstrap v5.3.3
  * Author: BootstrapMade.com
  * License: https://bootstrapmade.com/license/
  ======================================================== -->
</head>

 <% 
   // DAO를 생성해 DB에 연결
   BoardDao boarddao = BoardDao.getInstance(); 
   
   // 사용자가 입력한 검색 조건을 Map에 저장
   //Map<String, Object> parm = new HashMap<String, Object>();
   
   //String searchField = request.getParameter("searchField");
   //String searchWord = request.getParameter("searchWord");
   
   //if(searchWord != null) {
   //   param.put("searchField", searchField);
   //   param.put("searchWord", searchWord);
   //}
   
   String searchKey = request.getParameter("searchKey");
   if(searchKey==null) { searchKey = ""; }
   System.out.println("검색어="+ searchKey);   
   
   String strPage = request.getParameter("page");
   int pnum = 0;
   if(strPage==null) { 
	   pnum=1;
   }else{
	   pnum = Integer.valueOf(strPage);
   }
   System.out.println("검색어="+ searchKey);  
   // 제목검색
   List<BoardVo> boardLists = boarddao.selectBoardList(searchKey, pnum);

   int pageTotal = boarddao.selectBoardTotal();
   int boardPerPage = 10; // 페이지 당 10개
   int pageCnt = (pageTotal % boardPerPage==0) ? (pageTotal / boardPerPage) : (pageTotal / boardPerPage)+1;
   
   System.out.println("총수="+pageCnt );
%>   
<body>

  <!-- ======= Header ======= -->
  <header id="header" class="fixed-top header-inner-pages">
    <div class="container d-flex align-items-center">

      <h1 class="logo me-auto"><a href="index.jsp">MINI COOKIE</a></h1>
      <!-- Uncomment below if you prefer to use an image logo -->
      <!-- <a href="index.html" class="logo me-auto"><img src="assets/img/logo.png" alt="" class="img-fluid"></a>-->

      <nav id="navbar" class="navbar">
        <ul>
          <li><a class="nav-link scrollto active" href="#hero">Home</a></li>
          <li><a class="nav-link scrollto" href="#about">About</a></li>
          <li><a class="nav-link scrollto" href="#services">Services</a></li>
          <li><a class="nav-link   scrollto" href="#portfolio">Smart Shop</a></li>
          <li><a class="nav-link scrollto" href="#team">Consultant</a></li>
          <li class="dropdown"><a href="#"><span>Customer Center</span> <i class="bi bi-chevron-down"></i></a>
            <ul>
              <li><a href="boardlist.jsp">공지사항</a></li>              
              <li><a href="#">News</a></li>
              <li><a href="#">포토갤러리</a></li>
              <li><a href="#">자료실</a></li>
            </ul>
          </li>
          <li class="dropdown"><a href="#"><span>MyPage</span> <i class="bi bi-chevron-down"></i></a>
            <ul>
              <li><a href="regform.jsp">회원가입</a></li>              
              <li><a href="loginform.jsp">로그인</a></li>
              <li><a href="#">ID/PW찾기</a></li>
              <li><a href="memupform.jsp">회원정보수정</a></li>
              <li><a href="withdraw.jsp">회원탈퇴</a></li>
              <li><a href="#">주문목록</a></li>
            </ul>
          </li>
          <li><a class="getstarted scrollto" href="#about">Get Started</a></li>
        </ul>
        <i class="bi bi-list mobile-nav-toggle"></i>
      </nav><!-- .navbar -->

    </div>
  </header><!-- End Header -->

  <main id="main">

    <!-- ======= Breadcrumbs ======= -->
    <section id="breadcrumbs" class="breadcrumbs">
      <div class="container">

        <ol>
          <li><a href="index.html">Customer Center</a></li>
          <li>공지사항</li>
        </ol>
        <h2>공지사항</h2>

      </div>
    </section><!-- End Breadcrumbs -->

    <section class="inner-page">
  	<div class="container">
    <h2>공지사항 목록</h2>
    
    <!-- 검색창 -->
    <div class="input-group mb-3">
       <input type="text" class="form-control" placeholder="검색어 입력" value="" id="searchWord">
       <div class="input-group-append">
          <button class="btn btn-outline-secondary" type="button" id="searchBtn">검색</button>
       </div>
    </div>
    
    <div class="table-responsive">
      <table class="table table-striped">
        <thead>
          <tr>
            <th>#</th>
            <th>제목</th>
            <th>작성자</th>
            <th>작성일</th>
            <th>조회수</th>
          </tr>
        </thead>
        <tbody>
        <%
  		if (boardLists.isEmpty()) {
      	// 게시물이 하나도 없을 때
		%>
		<tr>
			<td colspan="5" align="center">게시물이 없습니다
			</td>
		</tr>
	 	<%
	    }else{
	    	// 게시물이 있을 때
	        
	        for (BoardVo boardvo : boardLists)
	        {
	           //virtualNum = totalCount--; // 전체 게시물 수에서 시작해서 1씩 감소

		%>        
	        <tr>
            <td><%= boardvo.getNum() %></td>
            <td><a href="boardview.jsp?num=<%= boardvo.getNum() %>"><%= boardvo.getSubject() %></a></td>
            <td><%= boardvo.getName() %></td>
            <td><%= boardvo.getRegdate() %></td>
            <td><%= boardvo.getHit() %></td>
          </tr>
          
        <%
	        }
	    }
        %>
          <!-- 추가적인 게시글 행을 이곳에 추가합니다 -->
        </tbody>
      </table>
    </div>
    
	    <div class="container mt-3">
		  <div class="input-group mb-3">
		    <div class="ml-auto">
		      <a href="boardregform.jsp" class="btn btn-primary regBtn">글쓰기</a>
		    </div>
		  </div>
		</div>
    
	    <nav aria-label="Page navigation example">
	      <ul class="pagination justify-content-center">
	        <li class="page-item"><a class="page-link" href="boardlist.jsp?page=1">Previous</a></li>
	        <%
	        for(int p=1; p<=pageCnt; p++) {
	        %>
	        
	        <li class="page-item"><a class="page-link" href="boardlist.jsp?page=<%=p%>"><%=p%></a></li>
	              
	        <%
	        }
	        %>
	        <li class="page-item"><a class="page-link" href="boardlist.jsp?page=<%=pageCnt%>">Next</a></li>
	      </ul>
	    </nav>
  	</div>
</section>


  </main><!-- End #main -->

  <!-- ======= Footer ======= -->
  <footer id="footer">

    <div class="footer-newsletter">
      <div class="container">
        <div class="row justify-content-center">
          <div class="col-lg-6">
            <h4>Join Our Newsletter</h4>
            <p>우리의 뉴스레터에 가입하여 최신 소식과 특별 혜택을 받아보세요!</p>
            <form action="" method="post">
              <input type="email" name="email"><input type="submit" value="Subscribe">
            </form>
          </div>
        </div>
      </div>
    </div>

    <div class="footer-top">
      <div class="container">
        <div class="row">

          <div class="col-lg-3 col-md-6 footer-contact">
            <h3>MINI COOKIE</h3>
            <p>
              서울 특별시 종로구  <br>
              우정국로2길 21 <br>
              대왕빌딩 9층 <br><br>
              <strong>사업자등록번호:</strong> 778-85-00778<br>
              <strong>Email:</strong> info@minicookie.co.kr<br>
            </p>
          </div>

          <div class="col-lg-3 col-md-6 footer-links">
            <h4>Useful Links</h4>
            <ul>
              <li><i class="bx bx-chevron-right"></i> <a href="#">Home</a></li>
              <li><i class="bx bx-chevron-right"></i> <a href="#">About us</a></li>
              <li><i class="bx bx-chevron-right"></i> <a href="#">Services</a></li>
              <li><i class="bx bx-chevron-right"></i> <a href="#">Terms of service</a></li>
              <li><i class="bx bx-chevron-right"></i> <a href="#">Privacy policy</a></li>
            </ul>
          </div>

          <div class="col-lg-3 col-md-6 footer-links">
            <h4>Our Services</h4>
            <ul>
              <li><i class="bx bx-chevron-right"></i> <a href="#">Web Design</a></li>
              <li><i class="bx bx-chevron-right"></i> <a href="#">Web Development</a></li>
              <li><i class="bx bx-chevron-right"></i> <a href="#">Product Management</a></li>
              <li><i class="bx bx-chevron-right"></i> <a href="#">Consulting</a></li>
              <li><i class="bx bx-chevron-right"></i> <a href="#">AIoT Solution</a></li>
            </ul>
          </div>

          <div class="col-lg-3 col-md-6 footer-links">
            <h4>Our Social Networks</h4>
            <p>Cras fermentum odio eu feugiat lide par naso tierra videa magna derita valies</p>
            <div class="social-links mt-3">
              <a href="#" class="twitter"><i class="bx bxl-twitter"></i></a>
              <a href="#" class="facebook"><i class="bx bxl-facebook"></i></a>
              <a href="#" class="instagram"><i class="bx bxl-instagram"></i></a>
              <a href="#" class="google-plus"><i class="bx bxl-skype"></i></a>
              <a href="#" class="linkedin"><i class="bx bxl-linkedin"></i></a>
            </div>
          </div>

        </div>
      </div>
    </div>

    <div class="container footer-bottom clearfix">
      <div class="copyright">
        &copy; Copyright <strong><span>MINI COOKIE</span></strong>. All Rights Reserved
      </div>
      <div class="credits">
        <!-- All the links in the footer should remain intact. -->
        <!-- You can delete the links only if you purchased the pro version. -->
        <!-- Licensing information: https://bootstrapmade.com/license/ -->
        <!-- Purchase the pro version with working PHP/AJAX contact form: https://bootstrapmade.com/arsha-free-bootstrap-html-template-corporate/ -->
        Designed by <a href="https://bootstrapmade.com/">BootstrapMade</a>
      </div>
    </div>
  </footer><!-- End Footer -->

  <div id="preloader"></div>
  <a href="#" class="back-to-top d-flex align-items-center justify-content-center"><i class="bi bi-arrow-up-short"></i></a>

  <!-- Vendor JS Files -->
  <script src="assets/vendor/aos/aos.js"></script>
  <script src="assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
  <script src="assets/vendor/glightbox/js/glightbox.min.js"></script>
  <script src="assets/vendor/isotope-layout/isotope.pkgd.min.js"></script>
  <script src="assets/vendor/swiper/swiper-bundle.min.js"></script>
  <script src="assets/vendor/waypoints/noframework.waypoints.js"></script>
  <script src="assets/vendor/php-email-form/validate.js"></script>

  <!-- Template Main JS File -->
  <script src="assets/js/main.js"></script>

</body>

</html>