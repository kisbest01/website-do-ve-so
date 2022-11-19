<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

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
    	
 	<title>Tạo Tài Khoản Mới</title>
 	
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
<div class="container">
<div class="row justify-content-center">
<div class="col-xl-10 col-lg-12 col-md-9">
    <div class="card o-hidden border-0 shadow-lg my-5">
    <div class="card-body p-0">
        <div class="row">
        	
        	<!-- Tab Background -->
            <div class="col-lg-6 d-none d-lg-block" style="background: url('https://stockdep.net/files/images/82585829.jpg') no-repeat center;"></div>
            
            <!-- Container -->
            <div class="col-lg-6">
                <div class="p-5">
                	
                	<!-- Card header -->
                    <div class="text-center">
                        <h1 class="h4 text-gray-900 mb-4">Tạo Tài Khoản Mới</h1>
                    </div>
                    
                    <!-- Form new account -->
                    <form action="NewAccountServlet" method="post">
                    
                    	<!-- User name -->
					 	<div class="form-group">
					  		<label for="email">Username:</label>
					  		<input type="text" id="username" class="form-control" name="username" value="${param.username }">
					  		<span id="error_username" class="text-danger"></span>
					  	</div>
					  	
					  	<!-- Email -->
					  	<div class="form-group">
					  		<label for="email">Email:</label>
					  		<input type="text" id="email" class="form-control" name="email" value="${param.email }">
					  		<span id="error_email" class="text-danger"></span>
					  	</div>
					  	<div class="row">
					  	
					  		<!-- Name -->
					  		<div class="form-group col-sm">
						   		<label for="ten">Họ và tên:</label>
						   		<input type="text" id="ten" class="form-control" name="ten" value="${param.ten }">
						   		<span id="error_ten" class="text-danger"></span>
						   	</div>
						   	
						   	<!-- Phone -->
						   	<div class="form-group col-sm">
						   		<label for="phone">Số điện thoại:</label>
						   		<input type="text" id="phone" class="form-control" name="phone" value="${param.phone }">
						   		<span id="error_phone" class="text-danger"></span>
						   	</div>
					  	</div>
					  	
					  	<!-- Address -->
					  	<div class="form-group">
					  		<label for="address">Địa chỉ:</label>
					  		<input type="text" id="address" class="form-control" name="address" value="${param.address }">
					   		<span id="error_address" class="text-danger"></span>
					   	</div>
					   	
					   	<!-- Submit -->
						<div class="d-flex justify-content-end">
					  		<button class="btn btn-primary">Tạo</button>
					  	</div>
					</form>  	
                    <hr>
                    
                    <!-- Back -->
                    <div class="text-center">
                        <a class="small" href="${pageContext.request.contextPath}/LoginServlet">Trở lại</a>
                   </div>                              
                </div>
            </div>
        </div>
    </div>
    </div>
</div>
</div>
</div>

<!-- Card Alert -->
<c:if test="${messenger != null }">
<div class="d-flex justify-content-center">
	<span class="alert ${alert_succes? 'alert-info' : 'alert-danger' }" id="alert_succes" style="margin-top: -400px; margin-bottom: 400px;">
	<c:forTokens items="${messenger }" delims=";" var="text">
		<c:out value="${text }"></c:out><br>
	</c:forTokens>
	</span>
</div>
</c:if>

<!-- jQuery -->
<script type="text/javascript">
	$("#alert_succes").fadeOut(5000);
	
	$("form").submit(function() {
			let them = true;
			
			const validRegex = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
			let email = $("#email").val();
			if(email == "") {
				$("#error_email").text("Vui lòng nhập email của người dùng!");
				them = false;
			} else if(email.length > 30) {
				$("#error_email").text("Vui lòng nhập dưới 30 kí tự!");
				them = false;
			} else if(!validRegex.test(email)) {
				$("#error_email").text("Email chưa hợp lệ!");
				them = false;
			} else {
				$("#error_email").text("");
			}
			
			let ten = $("#ten").val();
			if(ten == "") {
				$("#error_ten").text("Vui lòng nhập vào trường này!");
				them = false;
			} else if(ten.length > 30) {
				$("#error_ten").text("Vui lòng nhập dưới 30 kí tự!");
				them = false;
			} else {
				$("#error_ten").text("");
			}
			
			/* 	Viettel: 09, 03
				MobiFone: 09, 07
				VinaPhone: 09, 08
				Vietnamobile và Gmobile: 09, 05 */
			const vnf_regex = /((09|03|07|08|05)+([0-9]{8})\b)/g;
			let phone = $("#phone").val();
			if(phone == "") {
				$("#error_phone").text("Vui lòng nhập vào trường này!");
				them = false;
			} else if(!vnf_regex.test(phone)) {
				$("#error_phone").text("Số điện thoại chưa hợp lệ!");
				them = false;
			} else {
				$("#error_phone").text("");
			}
			
			let address = $("#address").val();
			if(address == "") {
				$("#error_address").text("Vui lòng nhập vào trường này!");
				them = false;
			} else if(address.length > 300) {
				$("#error_address").text("Vui lòng nhập dưới 300 kí tự!");
				them = false;
			} else {
				$("#error_address").text("");
			}
			
			const vali_username = /^[a-zA-Z0-9]+$/;
			let username = $("#username").val();
			if(username == "") {
				$("#error_username").text("Vui lòng nhập username của người dùng!");
				them = false;
			} else if(username.length > 30) {
				$("#error_username").text("Vui lòng nhập dưới 30 kí tự!");
				them = false;
			} else if(!vali_username.test(username)) {
				$("#error_username").text("Username chưa hợp lệ!");
				them = false;
			} else {
				$("#error_username").text("");
			}

			return them;
		});
</script>
</body>
</html>