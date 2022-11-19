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
    
	<title>Thay Đổi Password</title>
	
	<!-- Custom styles for this template-->
    <link href="${pageContext.request.contextPath}/css/sb-admin-2.min.css" rel="stylesheet">
    
    <!-- jQuery library -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    	
</head>
<body>

	<!-- Page Heading -->
    <div class="row pl-4 pt-2">
	    <h1 class="h3 mb-4 text-gray col"><b><c:out value="${title }"></c:out></b></h1>
	    
	    <!-- Card Alert -->
	    <c:if test="${alert_succes != null }">
		    <div class="alert ${alert_succes? 'alert-info' : 'alert-danger' }" id="alert_succes">
		    	<c:forTokens items="${messenger }" delims=";" var="text">
			    	<c:out value="${text }"></c:out><br>
		    	</c:forTokens>
		    </div>
	    </c:if>
	    <div class="col"></div>
   	</div>

<div class="row justify-content-between pl-4">
<div class="col-xl-7 col-lg-6">
    <div class="card shadow mb-4">
       <div class="card-body">
       		
       		<!-- Form update password -->
	        <form action="DoiPassServlet?page=doipass" method="post" class="was-validated" id="doipass">
	        	
	        	<!-- Current password -->
	        	<div class="form-group">
	        		<label for="address">Mật khẩu cũ:</label>
	        		<input type="password" id="cupass" class="form-control" name="cupass">
	        		<span id="error_cupass" class="text-danger"></span>
	        	</div>
	        	
	        	<!-- New password -->
	        	<div class="form-group">
	        		<label for="email">Mật khẩu mới:</label>
	        		<input type="password" id="newpass" class="form-control" name="newpass">
	        		<span id="error_newpass" class="text-danger"></span>
	        		<div class="progress mt-2" style="height:10px; width: 200px">
					  <div class="progress-bar bg-danger" style="width:30%" id="yeu">
					    Yếu
					  </div>
					  <div class="progress-bar bg-warning" style="width:60%" id="tb">
					    Trung bình
					  </div>
					  <div class="progress-bar bg-success" style="width:100%" id="manh">
					    Mạnh
					  </div>
					</div>
	        	</div>
	        	
	        	<!-- Enter the password -->
	        	<div class="form-group">
	        		<label for="address">Xác nhận lại mật khẩu:</label>
	        		<input type="password" id="repass" class="form-control" name="repass">
	        		<span id="error_repass" class="text-danger"></span>
	        	</div>
	        	
	        	<!-- Submit -->
	        	<button class="btn btn-primary" type="submit">Xác nhận</button>
	        </form>
        </div>
    </div>
 </div>  
  
    <!-- Tab bar -->
    <div class="col-xl-5 col-lg-6">
    </div>
</div>

<!-- jQuery -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/doipass.js"></script>
</body>
</html>