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
    	
 	<title>Quên Mật Khẩu</title>

 	
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
                    	
                    	<!-- Card background -->
                        <div class="col-lg-6 d-none d-lg-block" style="background: url('https://stockdep.net/files/images/82585829.jpg') no-repeat center;"></div>
                        
                        <!-- Container -->
                        <div class="col-lg-6 py-5">
                            <div class="p-5">
                            
                            	<!-- Card header -->
                                <div class="text-center">
                                    <h1 class="h4 text-gray-900 mb-4">Quên Mật Khẩu</h1>
                                </div>
                                
                                <!-- Form send mail -->
                                <form action="QuenPassServlet" method="post" class="row mb-3" id="guima">
							      	<input type="hidden" name="action" value="layma">
							      	
							      	<!-- Email -->
							      	<div class="col">
							      		<input type="text" name="email" class="form-control email" placeholder="Nhập email ..." value="${param.email }">
							      		<span class="text-danger error_email"></span>
							      	</div>
							      	<button class="btn text-primary col-auto">Lấy mã</button>
							    </form>
							    
							    <!-- Form forgot pass -->
							    <form action="QuenPassServlet" method="post" id="quenpass">
							    	<input type="hidden" name="action" value="quenpass">
							    	
							    	<!-- Pass -->
							    	<div class="mb-3">
							    		<input type="text" name="maxacthuc" class="form-control maxacthuc" placeholder="Nhập mã xác thực ..." form="quenpass">
							      		<span class="text-danger error_ma"></span>
							    	</div>
							    	
							    	<!-- Email -->
							      	<input type="hidden" class="emailquen" name="email" value="${param.email }">
							      	
							      	<!-- Submit -->
							      	<div class="d-flex justify-content-end">
							      		<button class="btn btn-primary">Gửi</button>
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
		<span class="alert ${alert_succes? 'alert-info' : 'alert-danger' }" id="alert_succes" style="margin-top: -300px; margin-bottom: 300px;">
			<c:forTokens items="${messenger }" delims=";" var="text">
				<c:out value="${text }"></c:out><br>
			</c:forTokens>
		</span>
	</div>
</c:if>

<!-- jQuery -->
<script type="text/javascript">
$("#alert_succes").fadeOut(5000);

$(".email").change(function() {
	$(".emailquen").val($(".email").val());
});

$("#quenpass").submit(function() {	
	
	let login = true;
	
	const validRegex = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
	let email = $(".email").val();
	if(email == '') {
		$(".error_email").text('Vui lòng nhập vào trường này!');
		login = false;
	} else if(email.length > 30) {
		$(".error_email").text('Vui lòng nhập dưới 30 kí tự!');
		login = false;
	} else if(!validRegex.test(email)) {
		$(".error_email").text('Email chưa hợp lệ!');
		login = false;
	} else {
		$(".error_email").text('');
	}
	
	const valid_ma = /(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@!#$%&]).{8,}/;
	let maxacthuc = $(".maxacthuc").val();
	if(maxacthuc == '') {
		$(".error_ma").text('Vui lòng nhập vào trường này!');
		login = false;
	} else if(!valid_ma.test(maxacthuc)) {
		$(".error_ma").text('Password chưa hợp lệ!');
		login = false;
	} else {
		$(".error_ma").text('');
	}
	console.log(login);
	return login;
});	

$("#guima").submit(function() {	
	
	let login = true;
	
	const validRegex = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
	let email = $(".email").val();
	if(email == '') {
		$(".error_email").text('Vui lòng nhập vào trường này!');
		login = false;
	} else if(email.length > 30) {
		$(".error_email").text('Vui lòng nhập dưới 30 kí tự!');
		login = false;
	} else if(!validRegex.test(email)) {
		$(".error_email").text('Email chưa hợp lệ!');
		login = false;
	} else {
		$(".error_email").text('');
	}
	
	console.log(login);
	return login;
});		
</script>
</body>
</html>