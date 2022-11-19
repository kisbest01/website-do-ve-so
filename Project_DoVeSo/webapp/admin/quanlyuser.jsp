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
    	
	<title>Quản Lý Người Dùng</title>
	
</head>
<body>

    <!-- Page Heading -->
    <div class="row">
	    <h1 class="h3 mb-2 text-gray-800 col"><b><c:out value="${title }"></c:out></b></h1>
	    <form action="UserServlet?page=quanlyuser&table=0&action=search" method="post" class="col row justify-content-end">
	    	<div class="col-auto">
	      		<input type="text" class="form-control bg-light border border-dark" placeholder="Search ..." name="search" value="${param.search }"><br>
	        </div>
	        <div class="col-auto">
	        	<button class="btn btn-primary" type="submit">Tìm Kiếm</button>
	        </div>
	   	</form>
   	</div>
	
    <!-- Container -->
    <div class="card shadow mb-4">
        <div class="row card-header py-3 justify-content-between">
        	
        	<!-- Card header -->
            <h6 class="m-0 font-weight-bold text-primary col-auto pt-2">Danh sách người dùng</h6>
            
            <!-- Form filter -->
            <form action="UserServlet?page=quanlyuser&table=0&action=filter" class="col-auto row" method="post">
            	<div class="col-auto">
	            	<select class="form-control" name="filter">
	            		<option ${param.filter.equals('Tất cả') ? 'selected' : '' }>Tất cả</option>
	            		<option ${param.filter.equals('Admin') ? 'selected' : '' }>Admin</option>
	            		<option ${param.filter.equals('User') ? 'selected' : '' }>User</option>
	            		<option ${param.filter.equals('Trực tuyến') ? 'selected' : '' }>Trực tuyến</option>
	            		<option ${param.filter.equals('Đã khóa') ? 'selected' : '' }>Đã khóa</option>
	            		<option ${param.filter.equals('Đã xóa') ? 'selected' : '' }>Đã xóa</option>
	            	</select>
            	</div>
            	
            	<!-- Submit -->
            	<button class="btn btn-primary col-auto">Apply</button>
            </form>
        </div>
        <div class="card-body">
            <div class="table-responsive">
            
            	<!-- Table list user -->
                <table class="table table-bordered text-center table-sm" id="userTable">
                    <thead>
                        <tr>
                        	<th><input type="checkbox" id="check_all"></th>
                            <th>STT</th>
                            <th>Username</th>
                            <th>Email</th>
                            <th>Tên</th>
                            <th>Số điện thoại</th>
                            <th>Địa chỉ</th>
                            <th>Role</th>
                            <th>Trạng thái</th>
                            <th> Cập nhật</th>
                        </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="i" begin="0" end="${fn:length(list) - 1}">
                        <tr>
                        	
                        	<!-- Check xóa / reset -->
                        	<td><input type="checkbox" id="check" form="form_xoa" name="check" value="${list.get(i).getEmail() }"></td>
                            
                            <!-- STT -->
                            <td>${i + param.table * 10 + 1 }</td>
                            
                            <!-- User Name -->
                            <td class="text-left">${list.get(i).getUsername() }</td>
                            
                            <!-- Email -->
                            <td class="text-left">${list.get(i).getEmail() }</td>
                            
                            <!-- Name -->
                            <td class="text-left">${list.get(i).getTen() }</td>
                            
                            <!-- Phone -->
                            <td>${list.get(i).getPhone() }</td>
                            
                            <!-- Address -->
                            <td class="text-left">${list.get(i).getAddress() }</td>
                            
                            <!-- Role -->
                            <td>${list.get(i).getRole() }</td>
                            
                            <!-- Status -->
                            <td>
                            	<span 
                            		class="border text-white border-dark rounded-lg ${list.get(i).getStatus().equals('Ngoại tuyến')? 'bg-secondary' : (list.get(i).getStatus().equals('Trực tuyến')? 'bg-success' : (list.get(i).getStatus().equals('Đã xóa')? 'bg-danger' : 'bg-warning')) } px-2">
                            		<c:out value="${list.get(i).getStatus() }"></c:out>
                           		</span>
                       		</td>
                       		
                       		<!-- Submit update -->
                       		<td><a href="${pageContext.request.contextPath}/CapNhatUser?page=capnhatuser&table=${param.table}&userid=${list.get(i).getId() }&username=${list.get(i).getUsername() }"><i class="fas fa-regular fa-wrench"></i></a></td>
                        </tr>
                    </c:forEach>    
                    </tbody>
                </table>
            </div>
            <div class="row justify-content-between">
            
            	<!-- Submit Xóa, Reset, Thêm -->
            	<div class="col-sm-auto row">
					<form action="UserServlet?page=quanlyuser&table=0&action=xoa" method="post" id="form_xoa" class="col-auto mt-2">
						<button class="btn btn-primary" onclick="return confirm('Bạn chắc muốn xóa chứ?')">Xóa</button>
					</form>
					<form action="UserServlet?page=quanlyuser&table=0&action=reset" method="post" id="form_reset" class="col-auto mt-2">
						<button class="btn btn-primary" onclick="return confirm('Bạn chắc muốn reset password chứ?')">Reset Password</button>
					</form>
					<div class="mt-2 px-2">
						<a class="btn btn-primary mx-1" href="${pageContext.request.contextPath}/CapNhatUser?page=themuser&table=${param.table}">Thêm</a>
					</div>
				</div>
				<div class="col-auto pt-2 text-primary">
					<c:out value="Showing ${param.table + 1} of ${size } tables"></c:out>
				</div>
				
				<!-- Tab Pagination -->
				<div class="col-auto">
					<form action="UserServlet" method="${param.action == 'search' || param.action == 'filter' ? 'post' : 'get' }">
						<input type="hidden" name="page" value="quanlyuser">
						<input type="hidden" name="action" value="${param.action }">
						<input type="hidden" name="search" value="${param.search }">
						<input type="hidden" name="filter" value="${param.filter }">
						<input type="hidden" name="username" value="${param.username }">
						<c:if test="${param.table >= 1 }">
								<button class="btn btn-outline-primary " name="table" value="${param.table - 1}">Previous</button>
						</c:if>
						<c:forEach var="table" begin="${param.table }" end="${param.table + 2}">
							<c:if test="${table < size }">
								<button class="btn btn-outline-primary ${param.table == table ? 'active' : '' }" name="table" value="${table }"><c:out value="${table + 1 }"></c:out></button>
							</c:if>
							</c:forEach>
							<c:if test="${param.table < size - 2 }">
								<button class="btn btn-outline-primary" name="table" value="${param.table + 1}">Next</button>
							</c:if>
					</form>
				</div>
           	</div>
        </div>
    </div>
    
    <!-- Tab Alert -->
    <c:if test="${alert_succes != null && param.table == 0}">
    <div class="d-flex justify-content-center">
	    <span class="alert ${alert_succes? 'alert-info' : 'alert-danger' }" id="alert_succes" style="margin-top: -300px; margin-bottom: 300px;">
		    <c:forTokens items="${messenger }" delims=";" var="text">
		    	<c:out value="${text }"></c:out><br>
	    	</c:forTokens>
	    </span>
    </div>
    </c:if>
    
    <!-- jQuery -->
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/quanly_user.js"></script>
</body>
</html>