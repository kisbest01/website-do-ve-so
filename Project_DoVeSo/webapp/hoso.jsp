<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!-- JSTL library -->    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>

<!DOCTYPE html>
<html>
<head>

	<meta charset="UTF-8">
	<meta name="author" content="Kiss">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	
	<!-- Custom styles for this template-->
    <link href="${pageContext.request.contextPath}/css/sb-admin-2.min.css" rel="stylesheet">
	
	<title>Hồ Sơ</title>
	
	<!-- jQuery library -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	
	<!-- Font awesome library -->
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	
</head>
<body>
<div class="row mt-3">
	
	<!-- Upload image -->
	<div class="col-sm-3 mb-2">
	
		<!-- image -->
		<img src='<c:url value="${userlogin.getSrc() }"></c:url>' class="img-fluid rounded-circle shadow-lg" alt="Ảnh đại diện" id="image">
		
		<!-- upload file -->
		<input type="file" id="avatar" name= "file" accept=".jpg, .png" form="capnhat_user">
		<div class="d-flex justify-content-center mt-2">
			<button class="btn btn-primary" id="chon">Tải ảnh</button>
		</div>
	</div>
	
	<!-- Form update -->
	<form action="HoSoServlet?page=hoso" id="capnhat_user" method="post" enctype="multipart/form-data" class="col-sm-9 card shadow-lg py-2">
       	<div class="row m-sm-3">
       		
       		<!-- Username -->
       		<div class="col-sm">
        		<label for="email"><b>Tên đăng nhập:</b></label>
        		<input type="text" id="username" class="form-control" name="username" value="${userlogin.getUsername() }">
        		<span id="error_username" class="text-danger"></span>
        	</div>
        	
        	<!-- Email -->
			<div class="col-sm">
	       		<label for="email"><b>Email:</b></label>
	       		<input type="text" id="email" class="form-control" name="email" value="${userlogin.getEmail() }">
	       		<span id="error_email" class="text-danger"></span>
	       	</div>
       	</div>
       	<div class="row m-sm-3">
       	
       		<!-- Name -->
       		<div class="col-sm">
        		<label for="ten"><b>Họ và tên:</b></label>
        		<input type="text" id="ten" class="form-control" name="ten" value="${userlogin.getTen() }">
        		<span id="error_ten" class="text-danger"></span>
        	</div>
        	
        	<!-- Phone -->
        	<div class="col-sm">
        		<label for="phone"><b>Số điện thoại:</b></label>
        		<input type="text" id="phone" class="form-control" name="phone" value="${userlogin.getPhone() }">
        		<span id="error_phone" class="text-danger"></span>
        	</div>
       	</div>
       	
       	<!-- Address -->
       	<div class="m-sm-3 px-sm-2">
       		<label for="address"><b>Địa chỉ:</b></label>
       		<input type="text" id="address" class="form-control" name="address" value="${userlogin.getAddress() }">
       		<span id="error_address" class="text-danger"></span>
       	</div>
       	
       	<!-- Submit -->
       	<div class="row m-sm-3 px-2 mt-2">
       		<a class="btn btn-primary col-auto" href="${pageContext.request.contextPath}/DoiPassServlet?page=doipass">Đổi mật khẩu</a>
       		<div class="col"></div>
       		<button class="btn btn-primary col-auto" type="submit">Lưu</button>
       	</div>
     </form>
</div>

<!-- Card Alert -->
 <c:if test="${alert_succes != null}">
    <div class="d-flex justify-content-center">
	    <span class="alert ${alert_succes? 'alert-info' : 'alert-danger' }" id="alert" style="margin-top: -300px; margin-bottom: 300px;">
		    <c:forTokens items="${messenger }" delims=";" var="text">
		    	<c:out value="${text }"></c:out><br>
	    	</c:forTokens>
	    </span>
    </div>
</c:if>

<!-- jQuery -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/js-hoso.js"></script>
</body>
</html>