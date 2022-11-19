<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!-- JSTL library -->    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
<!DOCTYPE html>
<html>
<head>

	<meta charset="UTF-8">
	<meta name="author" content="Kiss">
	<meta name="viewport" content="width=device-width, initial-scale=1.0"> 
	
	<!-- W3.CSS library -->
	<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
	
	<title>Header</title>
	
	<!-- jQuery library -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	
</head>
<body>
<div class="w3-container">
	<!-- Title -->
	<div class="w3-container w3-center w3-white">
		<h1 class="w3-text-red"><b>DÒ VÉ SỐ ONLINE</b></h1>
	</div>
	
	<!-- Tab menu -->
	<div class="w3-bar w3-blue">
		<a href="${pageContext.request.contextPath}/HomeServlet?page=trangchu&table=0" class="w3-bar-item w3-button ${param.page == 'trangchu' ? 'w3-red' : '' } w3-hover-gray">
			<i class="fa fa-home"></i>
			Trang chủ
		</a>
		<a href="${pageContext.request.contextPath}/DoVeSoServlet?page=doveso" class="w3-bar-item w3-button ${param.page == 'doveso' ? 'w3-red' : '' } w3-hover-gray">Dò vé số</a>
		<a href="${pageContext.request.contextPath}/LichSuDoServlet?page=lichsudo&table=0" class="w3-bar-item w3-button ${param.page == 'lichsudo' ? 'w3-red' : '' } w3-hover-gray">Lịch sử dò</a>
		<div class="w3-dropdown-click w3-right">
			<div class="w3-button w3-border-left w3-hover-gray" id="hoso">
				<i class="fa fa-thin fa-id-card"></i>
				<c:out value="${userlogin.getTen() }"> </c:out>	
			</div>
			
			<!-- Drop down - User Information -->
			<div id="Demo" class="w3-dropdown-content w3-bar-block w3-card-4 w3-animate-zoom w3-margin-right" style="right:0">
			    <a class="w3-bar-item w3-button" href="${pageContext.request.contextPath}/HoSoServlet?page=hoso">
			        <i class="fa fa-user fa-sm fa-fw"></i>
			        Hồ sơ
			    </a>
			    <a class="w3-bar-item w3-button" href="#">
			        <i class="fa fa-cogs fa-sm fa-fw"></i>
			        Cài đặt
			    </a>
			    <a class="w3-bar-item w3-button" href="#">
			        <i class="fa fa-list fa-sm fa-fw"></i>
			        Lịch sử đăng nhập
			    </a>
			    <a class="w3-bar-item w3-button" href="${pageContext.request.contextPath}/LogoutServlet">
			    <i class="fa-right-from-bracket"></i>
			        <i class="fa fa-sign-out fa-sm fa-fw"></i>
			        Đăng xuất
			    </a>
			</div>
		</div>
		<c:if test="${userlogin.getROLE() == 1 }">
			<a href="${pageContext.request.contextPath}/QuanLyServlet?page=quanlyvedo&table=0&action=xem" class="w3-bar-item w3-button w3-hover-gray w3-right">Admin</a>
		</c:if>
	</div>
</div>

<!-- jQuery -->
<script type="text/javascript">
$(document).ready(function() {
	$("#hoso").click(function() {
	  let x = document.getElementById("Demo");
	  if (x.className.indexOf("w3-show") == -1) {
	    x.className += " w3-show";
	  } else { 
	    x.className = x.className.replace(" w3-show", "");
	  }
	});	
});
</script>
</body>
</html>