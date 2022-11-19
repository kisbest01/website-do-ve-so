<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!-- JSTL library -->    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="Kiss">	
    
    <!-- jQuery library -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    
	<title>Cập Nhật Người Dùng</title>
	
</head>
<body>

	<!-- Page Heading -->
    <div>
	    <h1 class="h3 mb-4 text-gray-800"><b><c:out value="${title }"></c:out></b></h1>
   	</div>

<div class="row justify-content-between">
<div class="col-xl-7 col-lg-6">
    <div class="card shadow mb-4">
       <div class="card-body">
       		
       		<!-- Form update or insert user -->
	        <form action="CapNhatUser?page=${param.page.equals('capnhatuser')? 'capnhatuser' : 'themuser' }&action=update" id="them_user" method="post" class="was-validated">
	        	<input type="hidden" id="userid" name="userid" value="${user.getId() }">
	        	<div class="row">
	        		
	        		<!-- User name -->
	        		<div class="form-group col-sm">
		        		<label for="email">Username:</label>
		        		<input type="text" id="username" class="form-control" name="username" value="${user.getUsername() }"  ${param.page.equals('capnhatuser')? 'readonly' : '' }>
		        		<span id="error_username" class="text-danger"></span>
		        	</div>
		        	
		        	<!-- Password -->
		        	<div class="form-group col-sm">
		        		<label for="pass">Mật khẩu:</label>
		        		<c:if test="${param.page.equals('capnhatuser') }">
		        			<input type="password" class="form-control" id="pass" value="********" readonly >
		        		</c:if>
		        		<c:if test="${param.page.equals('themuser') }">
		        			<input type="password" class="form-control" id="pass" name="pass" value="${user.getPassword() }" >
		        		</c:if>
		        		<span id="error_pass" class="text-danger"></span>
		        	</div>
	        	</div>
	        	
	        	<!-- Email -->
	        	<div class="form-group">
	        		<label for="email">Email:</label>
	        		<input type="text" id="email" class="form-control" name="email" value="${user.getEmail() }">
	        		<span id="error_email" class="text-danger"></span>
	        	</div>
	        	<div class="row">
	        	
	        		<!-- Name -->
	        		<div class="form-group col-sm">
		        		<label for="ten">Họ và tên:</label>
		        		<input type="text" id="ten" class="form-control" name="ten" value="${user.getTen() }">
		        		<span id="error_ten" class="text-danger"></span>
		        	</div>
		        	
		        	<!-- Phone -->
		        	<div class="form-group col-sm">
		        		<label for="phone">Số điện thoại:</label>
		        		<input type="text" id="phone" class="form-control" name="phone" value="${user.getPhone() }">
		        		<span id="error_phone" class="text-danger"></span>
		        	</div>
	        	</div>
	        	
	        	<!-- Address -->
	        	<div class="form-group">
	        		<label for="address">Địa chỉ:</label>
	        		<input type="text" id="address" class="form-control" name="address" value="${user.getAddress() }">
	        		<span id="error_address" class="text-danger"></span>
	        	</div>
	        	<div class="row">
	        	
	        		<!-- Role -->
	        		<div class="form-group col-auto col-sm">
		        		<label for="role">Role:</label>
		        		<select class="form-control" id="role" name="role">
		        			<option ${user.getRole().equals('User') ? 'selected' : '' } ${user.getRole().equals('Admin') ? 'disabled' : '' } value="0">User</option>
		            		<option ${user.getRole().equals('Admin') ? 'selected' : '' } value="1">Admin</option>
		            	</select>
		            	<span id="error_role" class="text-danger"></span>
		        	</div>
		        	
		        	<!-- Status -->
		        	<c:if test="${param.page.equals('capnhatuser') }">
		        	<div class="form-group col-auto col-sm">
		        		<label for="status">Trạng thái:</label>
		        		<select class="form-control" id="status" name="status">
		        			<option ${user.getStatus().equals('Ngoại tuyến') ? 'selected' : '' } value="1">Ngoại tuyến</option>
		            		<option ${user.getStatus().equals('Trực tuyến') ? 'selected' : '' } value="2">Trực tuyến</option>
		            		<option ${user.getStatus().equals('Đã khóa') ? 'selected' : '' } value="0">Đã khóa</option>
		            		<option ${user.getStatus().equals('Đã xóa') ? 'selected' : '' } value="-1">Đã xóa</option>
		            	</select>
		        	</div>
		        	</c:if>
	        	</div>
	        	<c:if test="${param.page.equals('themuser') }">
	        	
	        		<!-- Reset -->
	    			<input type="reset" class="btn btn-primary m-2" value="Reset">
	        	</c:if>
	        	
	        	<!-- Hủy -->
	        	<a class="btn btn-primary" href="${pageContext.request.contextPath}/UserServlet?page=quanlyuser&table=0&action=xem">Hủy</a>
	        	
	        	<!-- Submit update or insert -->
	        	<button class="btn btn-primary float-right" type="submit">${param.page.equals('capnhatuser')? 'Cập Nhật' : 'Thêm' }</button>
	        </form>
        </div>
    </div>
 </div> 
   
    <!-- Tab bar -->
    <div class="col-xl-5 col-lg-6">
    <c:if test="${param.page.equals('capnhatuser') }">
        <div class="card shadow mb-4">
        
            <!-- Card Header -->
            <div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                <h6 class="m-0 font-weight-bold text-primary">Lịch Sử Dò</h6>
            </div>
            
            <!-- Card Body -->
            <div class="card-body">
                <c:if test="${fn:length(listLSD) > 0 }">
                	<c:forEach var="i" begin="0" end="${fn:length(listLSD) - 1 }">
                		<div class="alert alert-info alert-dismissible">
                			<button type="button" class="close" data-dismiss="alert">&times;</button>
                			<p><i>${listLSD.get(i).getNgayDo() }</i></p>
		                	<p>Mã vé: ${listLSD.get(i).getMaVe() }</p>
		                	<p>Kết quả: Đã ${listLSD.get(i).getKetQua() }!</p>
                		</div>
	                </c:forEach>
                </c:if>
                <c:if test="${fn:length(listLSD) <= 0 }">
                	<p class="text-center">Lịch sử dò trống!</p>
                </c:if>
            </div>
        </div>
    </c:if>
    </div>
</div>

<!-- Card Alert -->
<c:if test="${alert_succes != null}">
    <div class="d-flex justify-content-center">
	    <span class="alert ${alert_succes? 'alert-info' : 'alert-danger' }" id="alert_succes" style="margin-top: -300px; margin-bottom: 300px;">
		    <c:forTokens items="${messenger }" delims=";" var="text">
		    	<c:out value="${text }"></c:out><br>
	    	</c:forTokens>
	    </span>
    </div>
</c:if>

<!-- jQuery -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/capnhat_user.js"></script>
</body>
</html>