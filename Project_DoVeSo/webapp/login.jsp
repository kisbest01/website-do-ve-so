<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- JSTL library -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html lang="vi">
<head>

	<meta charset="UTF-8">
	<meta name="author" content="Kiss">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
 	
    <!-- Custom fonts for this template-->
    <link href="${pageContext.request.contextPath}/css/all.min.css" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">
	
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>      
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">   
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
 	
 	<title>Login</title>
 	
 	<!-- Custom icon for title -->
 	<link rel="icon" href="https://scontent.fsgn5-10.fna.fbcdn.net/v/t31.18172-8/21994184_1367906893322716_2854525214528461723_o.jpg?_nc_cat=101&ccb=1-7&_nc_sid=cdbe9c&_nc_ohc=r-lyxa5zauAAX8kyatE&_nc_ht=scontent.fsgn5-10.fna&oh=00_AfDNDIL5KYTKcDWIPXAwFl6AkjL8aWOpuaANIEvMnJXBAQ&oe=638DBEC9">
 	
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
                        <div class="col-lg-6 d-none d-lg-block" style="background: url('https://stockdep.net/files/images/82585829.jpg') no-repeat center;"></div>
                        <div class="col-lg-6">
                            <div class="p-5">
                            <c:forTokens items="${messenger }" delims=";" var="text">
                        		<div class="alert alert-danger text-center ml-2 alert-dismissible">
                        		 <button type="button" class="close" data-dismiss="alert">&times;</button>
		                            <c:out value="${text }"></c:out>
		                        </div>
							</c:forTokens>
                                <div class="text-center">
                                    <h1 class="h4 text-gray-900 mb-4">Chào Mừng Bạn Trở Lại!</h1>
                                </div>
                                <form class="user" action="LoginServlet" method="post" id="dangnhap">
                                	<input type="hidden" name="action" value="login">
                                	
                                	<!-- User name or Email -->
                                    <div class="form-group">
                                        <input type="text" class="form-control form-control-user" id="username" name="username" placeholder="Username or email">
                                            <span id="error_username" class="text-danger pl-3"></span>
                                    </div>
                                    
                                    <!-- Password -->
                                    <div class="form-group">
                                        <input type="password" class="form-control form-control-user" id="pass" name="password" placeholder="Mật khẩu">
                                            <span id="error_pass" class="text-danger pl-3"></span>
                                    </div>
                                    
                                    <!-- Check remember -->
                                    <div class="form-group">
                                        <div class="custom-control custom-checkbox small">
                                            <input type="checkbox" class="custom-control-input" id="check" name="check">
                                            <label class="custom-control-label" for="check">Ghi Nhớ</label>
                                        </div>
                                    </div>
                                    
                                    <!-- Summit -->
                                    <button class="btn btn-primary btn-user btn-block">
                                        Đăng Nhập
                                    </button> 
                                    <hr>
                                    <a href="index.html" class="btn btn-google btn-user btn-block">
                                       	Đăng Nhập Bằng Google
                                    </a>
                                    <a href="index.html" class="btn btn-facebook btn-user btn-block">
                                        Đăng Nhập Bằng Facebook
                                    </a>
                                </form>
                                <hr>
                                
                                <!-- Forgot password -->
                                <div class="text-center">
                                    <a class="small" href="${pageContext.request.contextPath}/QuenPassServlet">Quên mật khẩu?</a>
                                </div>
                                
                                <!-- New account -->
                                <div class="text-center">
                                    <a class="small" href="${pageContext.request.contextPath}/NewAccountServlet">Tạo tài khoản mới!</a>
                                </div>                               
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- jQuery submit -->
<script type="text/javascript">
$("#dangnhap").submit(function() {	
	
	let login = true;
	
	const validRegex = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
	const vali_username = /^[a-zA-Z0-9]+$/;
	let username = $("#username").val();
	if(username == '') {
		$("#error_username").text('Vui lòng nhập vào trường này!');
		login = false;
	} else if(username.length > 30) {
		$("#error_username").text('Vui lòng nhập dưới 30 kí tự!');
		login = false;
	} else if(username.includes('@')) {
		if(username.test(validRegex)) {
			$("#error_username").text('Email chưa hợp lệ!');
			login = false;
		}
	} else if(!username.includes('@')) {
		if(username.test(vali_username)) {
			$("#error_username").text('Username chưa hợp lệ!');
			login = false;
		}
	} else {
		$("#error_username").text('');
	}
	
	const valid_pass = /(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@!#$%&]).{8,}/;
	let pass = $("#pass").val();
	if(pass == '') {
		$("#error_pass").text('Vui lòng nhập vào trường này!');
		login = false;
	} else if(!valid_pass.test(pass)) {
		$("#error_pass").text('Password chưa hợp lệ!');
		login = false;
	} else {
		$("#error_pass").text('');
	}
	return login;
});		
</script>
</body>
</html>