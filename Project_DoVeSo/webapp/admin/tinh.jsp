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
    
	<title>Thêm Thành Phố</title>
	
</head>
<body>

	<!-- Page Heading -->
	<div class="d-sm-flex align-items-center justify-content-between mb-4">
	    <h1 class="h3 mb-0 text-gray-800">
	    	<b><c:out value="${title }"></c:out></b>
	    </h1>
	</div>

<!-- Content Row -->
<div class="row">	
<c:set var="mien" value="${t != null ? t.getMien() : param.mien }"></c:set>
	<div class="col-xl-7 col-lg-6">
    	<div class="card shadow mb-4">
    	
            <!-- Card Header -->
            <div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                <h6 class="m-0 font-weight-bold text-primary">Danh sách các thành phố</h6>
            </div>
            
             <!-- Card Body -->
            <div class="card-body">
            <form action="ThemTinhServlet" method="get">
            	<input type="hidden" name="action" value="capnhat">
            	<input type="hidden" name="page" value="themtinh">
            	
            	<!-- List city for Northern -->
            	<div class="bg-dark text-center text-white">Miền Bắc</div>
				<c:forEach var="i" begin="0" end="${fn:length(tinh) - 1 }">
					<c:if test="${tinh.get(i).getMien() == 'Miền Bắc' }">
						<button style="margin:5px 0px;" name="tinh" class="btn ${param.tinh == tinh.get(i).getTentinh() ? 'btn-primary' : ''}" value="${tinh.get(i).getTentinh() }">
							<c:out value="${tinh.get(i).getTentinh() }"></c:out>
						</button>
					</c:if>
				</c:forEach>
				
				<!-- List city for Central -->
				<div class="bg-dark text-center text-white">Miền Trung</div>
				<c:forEach var="i" begin="0" end="${fn:length(tinh) - 1 }">
					<c:if test="${tinh.get(i).getMien() == 'Miền Trung' }">
						<button style="margin:5px 0px;" name="tinh" class="btn ${param.tinh == tinh.get(i).getTentinh() ? 'btn-primary' : ''}"" value="${tinh.get(i).getTentinh() }">
							<c:out value="${tinh.get(i).getTentinh() }"></c:out>
						</button>
					</c:if>
				</c:forEach>
				
				<!-- List city for South -->
				<div class="bg-dark text-center text-white">Miền Nam</div>
				<c:forEach var="i" begin="0" end="${fn:length(tinh) - 1 }">
					<c:if test="${tinh.get(i).getMien() == 'Miền Nam' }">
						<button style="margin:5px 0px;" name="tinh" class="btn ${param.tinh == tinh.get(i).getTentinh() ? 'btn-primary' : ''}"" value="${tinh.get(i).getTentinh() }">
							<c:out value="${tinh.get(i).getTentinh() }"></c:out>
						</button>
					</c:if>
				</c:forEach>
			</form>	
            </div>
       	</div>
    </div>
	<div class="col-xl-5 col-lg-6">
		<div class="card shadow mb-4 px-5 pt-5">
		
			<!-- Form insert or update city -->
			<form action="ThemTinhServlet?page=themtinh&action=${param.action }" method="post" id="formthem">
				<input type="hidden" name="id" value="${t.getId() }">
				
				<!-- Vùng miền -->
			    <div class="form-group row shadow-sm py-4">
			    	<label for="mien" class="col-auto align-self-center text-info"><b>Vùng Miền</b></label>
			    	<div class="col">
			    		<select class="form-control text-center" name="mien" id="mien">
			    			<option value="">Chọn vùng miền</option>
		    				<option ${mien.equalsIgnoreCase('Miền Bắc')? "selected": "" }>Miền Bắc</option>
		    				<option ${mien.equalsIgnoreCase('Miền Trung')? "selected": "" }>Miền Trung</option>
		    				<option ${mien.equalsIgnoreCase('Miền Nam')? "selected": "" }>Miền Nam</option>
			    		</select>
			    		<span id="mien_error" class="text-danger float-right"></span>
			    	</div>
			    </div>
			    
			    <!-- Tỉnh / thành phố -->
			    <div class="form-group row shadow-sm py-4">
			    	<label for="tinh" class="col-auto align-self-center text-info"><b>Tỉnh/Thành phố</b></label>
			    	<div class="col">
			    		<input type="text" class="form-control text-center" name="tinh" id="tinh" value="${param.tinh }">
			    		<span id="tinh_error" class="text-danger float-right"></span>
			    	</div>
			    </div>			    
			    <div class="d-flex justify-content-${param.action.equals('capnhat') ? 'between' : 'end' } my-4">
			    	
			    	<!-- Hủy -->
			    	<c:if test="${param.action.equals('capnhat') }">
			    		<a href="${pageContext.request.contextPath}/ThemTinhServlet?page=themtinh&action=them" class="btn btn-primary">Hủy</a>
			    	</c:if>
			    	
			    	<!-- Submit insert or update -->
		            <button class="btn btn-primary" type="submit">
		            	<c:out value="${param.action.equals('capnhat') ? 'Cập Nhật' : 'Thêm' }"></c:out>
		            </button>
		        </div>
		 	</form>
		</div>	
		
		<!-- Card Alert -->	 
		 <div class="card shadow mb-4">
		 
            <!-- Card Header -->
            <div class="card-header py-3">
                <h6 class="m-0 font-weight-bold text-primary">Thông Báo</h6>
        	</div>
        	
        	<!-- Card Body -->
            <div class="card-body">
				<div class="text-danger ">
				<c:forTokens items="${messenger }" delims=";" var="text">
					<p><c:out value="${text }"></c:out></p>
				</c:forTokens>
				</div>	
            </div>
        </div>
	</div>
</div>
<script type="text/javascript">
$(document).ready(function() {
	$("#formthem").submit(function() {
		let mien = $("#mien").val();
		let tinh = $("#tinh").val();
		let bl = true;
		
		if(mien == "") {
			$("#mien_error").text("Vui lòng nhập vào trường này!");
			bl = false;
		} else {
			$("#mien_error").text("");
		}
		
		if(tinh == "") {
			$("#tinh_error").text("Vui lòng nhập vào trường này!");
			bl = false;
		} else {
			$("#tinh_error").text("");
		}
		
		return bl;
	});
	
});
</script>
</body>
</html>