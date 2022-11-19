<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!-- JSTL library -->    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>

	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="Kiss">		
	
	<title>
		<c:out value="${title }"></c:out>
	</title>
	
	<!-- Custom icon for title -->
	<link rel="icon" href="https://scontent.fsgn5-10.fna.fbcdn.net/v/t31.18172-8/21994184_1367906893322716_2854525214528461723_o.jpg?_nc_cat=101&ccb=1-7&_nc_sid=cdbe9c&_nc_ohc=r-lyxa5zauAAX8kyatE&_nc_ht=scontent.fsgn5-10.fna&oh=00_AfDNDIL5KYTKcDWIPXAwFl6AkjL8aWOpuaANIEvMnJXBAQ&oe=638DBEC9">
	
	<!-- Custom fonts for this template-->
    <link href="${pageContext.request.contextPath}/css/all.min.css" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>      
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">   
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
	<!-- Custom styles for this template-->
    <link href="${pageContext.request.contextPath}/css/sb-admin-2.min.css" rel="stylesheet">
</head>
<body>
<div id="wrapper">

	<!-- Side bar -->
	<ul class="navbar-nav bg-gradient-info sidebar sidebar-dark accordion" id="accordionSidebar">
	    <li>
		    <a class="sidebar-brand d-flex align-items-center justify-content-center" href="${pageContext.request.contextPath}/HoSoServlet?page=hoso">
		        <span class="sidebar-brand-icon">
		            <i class="fas fal fa-user-cog"></i>
		        </span>
		        <span class="sidebar-brand-text mx-2">Quản trị viên</span>
		    </a>
	    </li>
	    
	    <!-- Divider -->
	    <li>
	    	<hr class="sidebar-divider my-0">
	    </li>
	
	    <!-- Nav Item -->
	    <li class="nav-item ">
	        <a class="nav-link" href="${pageContext.request.contextPath}/HomeServlet?page=trangchu&table=0">
	            <i class="fa fa-home"></i>
	            <span>Trang Chủ</span></a>
	    </li>
	
	    <!-- Divider -->
	    <li>
	    	<hr class="sidebar-divider">
		</li>
		
	    <!-- Heading -->
	    <li class="sidebar-heading">
	        Giao diện
	    </li>
	
	    <!-- Nav Item -->
	    <li class="nav-item ${param.page.equals('quanlyvedo')? 'active' : '' }">
	        <a class="nav-link collapsed" href="${pageContext.request.contextPath}/QuanLyServlet?page=quanlyvedo&table=0&action=xem">
	          	<i class="fas fad fa-ticket-alt"></i>
	            <span>Quản lý vé dò</span>
	        </a>
	    </li>
	
	    <!-- Nav Item -->
	    <li class="nav-item ${param.page.equals('quanlyuser')? 'active' : '' }">
	        <a class="nav-link collapsed" href="${pageContext.request.contextPath}/UserServlet?page=quanlyuser&table=0&action=xem">
	            <i class="fas fa-light fa-users"></i>
	            <span>Quản lý người dùng</span>
	        </a>
	    </li>
	
	    <!-- Divider -->
	    <li>
	    	<hr class="sidebar-divider">
		</li>
		
	    <!-- Heading -->
	    <li class="sidebar-heading">
	        Thêm
	    </li>
	
	    <!-- Nav Item -->
	    <li class="nav-item ${param.page.equals('hoso')? 'active' : '' }">
	    	<a class="nav-link collapsed" href="${pageContext.request.contextPath}/HoSoServlet?page=hoso">
	            <i class="fas fa-fw fa-folder"></i>
	            <span>Hồ sơ</span>
	        </a>
        </li>
	    <li class="nav-item ${param.page.equals('doipass')? 'active' : '' }">    
	        <a class="nav-link collapsed" href="${pageContext.request.contextPath}/DoiPassServlet?page=doipass">
	            <i class="fas fa-fw fa-folder"></i>
	            <span>Đổi mật khẩu</span>
	        </a>
	    </li>
		<li class="nav-item ${param.page.equals('themtinh')? 'active' : '' }">
	    	<a class="nav-link collapsed" href="${pageContext.request.contextPath}/ThemTinhServlet?page=themtinh&action=them">
	            <i class="fas fa-fw fa-folder"></i>
	            <span>Thêm thành phố</span>
	        </a>
        </li>
        
	    <!-- Divider -->
	    <li>
	    	<hr class="sidebar-divider d-none d-md-block">
		</li>
		
	    <!-- Side bar Toggle (Side bar) -->
	    <li class="text-center d-none d-md-inline">
	        <button class="rounded-circle border-0" id="sidebarToggle"></button>
	    </li>
	</ul>
	<!-- End of Side bar -->
	
	<!-- Content -->
	<div id="content-wrapper" class="d-flex flex-column">

		<!-- Main Content -->
		<div id="content">
		
			<!-- Top bar -->
			<nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">
			
			    <!-- Side bar Toggle (Top bar) -->
				<button id="sidebarToggleTop" class="btn btn-link d-md-none rounded-circle mr-3">
				    <i class="fa fa-bars"></i>
				</button>
			
				<!-- Top bar Nav bar -->
				<ul class="navbar-nav ml-auto">
			   		<li class="topbar-divider d-none d-sm-block"></li>
			   		
				   <!-- Nav Item - User Information -->
				   <li class="nav-item dropdown no-arrow">
				       <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button"
				           data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
				           <span class="mr-2 d-none d-lg-inline text-gray-600 small">
				           <c:out value="${userlogin.getTen() }"></c:out>
				           </span>
				           <img class="img-profile rounded-circle"
				               src="${pageContext.request.contextPath}/media/undraw_profile.svg">
				       </a>
				       
			       		<!-- Drop down - User Information -->
			            <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in"
			                aria-labelledby="userDropdown">
			                <a class="dropdown-item" href="${pageContext.request.contextPath}/HoSoServlet?page=hoso">
			                    <i class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i>
			                    Hồ sơ
			                </a>
			                <a class="dropdown-item" href="#">
			                    <i class="fas fa-cogs fa-sm fa-fw mr-2 text-gray-400"></i>
			                    Cài đặt
			                </a>
			                <a class="dropdown-item" href="#">
			                    <i class="fas fa-list fa-sm fa-fw mr-2 text-gray-400"></i>
			                    Lịch sử đăng nhập
			                </a>
			                <div class="dropdown-divider"></div>
			                <a class="dropdown-item" href="${pageContext.request.contextPath}/LogoutServlet">
			                    <i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
			                    Đăng xuất
			                </a>
			            </div>
			        </li>
			    </ul>
			</nav>
			<!-- End of Topbar -->
			
			<!-- Begin Page Content -->
			<div class="container-fluid">
			<c:if test="${param.page.equals('quanlyvedo') }">
				<jsp:include page="quanlyvedo.jsp"></jsp:include>
			</c:if>
			<c:if test="${param.page.equals('capnhatvedo') || param.page.equals('themvedo') }">
				<jsp:include page="capnhatvedo.jsp"></jsp:include>
			</c:if>
			<c:if test="${param.page.equals('quanlyuser') }">
				<jsp:include page="quanlyuser.jsp"></jsp:include>
			</c:if>
			<c:if test="${param.page.equals('capnhatuser') || param.page.equals('themuser') }">
				<jsp:include page="capnhatuser.jsp"></jsp:include>
			</c:if>
			<c:if test="${param.page.equals('doipass') }">
				<jsp:include page="doipass.jsp"></jsp:include>
			</c:if>
			<c:if test="${param.page.equals('hoso') }">
				<jsp:include page="/hoso.jsp"></jsp:include>
			</c:if>
			<c:if test="${param.page.equals('themtinh') }">
				<jsp:include page="tinh.jsp"></jsp:include>
			</c:if>
			</div>
			<!-- End of Page Content -->
	
		</div>
		<!-- End of Main Content -->
	
		<!-- Footer -->
		<footer class="sticky-footer bg-white">
		    <div class="container my-auto">
		        <div class="copyright text-center my-auto">
		            <span>Copyright &copy; Your Website 2022</span>
		        </div>
		    </div>
		</footer>
		<!-- End of Footer -->
	
	</div>
	<!-- End of Content Wrapper -->	
</div>

<!-- Custom scripts for all pages-->
<script src="${pageContext.request.contextPath}/js/sb-admin-2.min.js"></script>
</body>
</html>