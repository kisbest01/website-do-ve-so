<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!-- JSTL library -->    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
   
<!DOCTYPE html>
<html>
<head>

	<meta charset="UTF-8">
	<meta name="author" content="Kiss">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	
	<!-- W3.CSS library -->
	<link rel="stylesheet" href="https://www.w3shools.com/w3css/4/w3.css">
	
	<title>Dò Vé Số</title>
	
	<!-- jQuery library -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	
	<!-- Font awesome library -->
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	
</head>
<body>
<div class="w3-container w3-col m7 w3-margin-top">

	<!-- Card Header -->
	<div class="w3-container w3-black w3-text-white">
	    <h6>Dò Vé Số</h6>
	</div>
	
    <!-- Card Body -->
    <div class="w3-white w3-container w3-card-4 w3-padding-24">
    
    <!-- Form dò vé số -->
    <form action="DoVeSoServlet?page=doveso&table=0&action=dove" method="post" id="formdove">
    
       	<!-- Ngày -->
		<div class="w3-cell-row w3-panel">
			<label for="giaingay" class="w3-cell"><b>Ngày</b></label>
			<div class="w3-cell">
				<input type="date" id="giaiNgay" class="w3-input w3-border w3-round-large w3-center" name="giaingay" value="${param.giaingay }">
				<span id="ngay_error" class="w3-text-red  w3-right"></span>
			</div>
		</div>
		
		<!-- Tỉnh / thành phố -->
		<div class="w3-cell-row w3-panel">
			<label for="tinh" class="w3-cell"><b>Tỉnh/Thành phố</b></label>
			<div class="w3-cell">
				<select class="w3-select w3-border w3-round-large w3-center" name="tinh" id="tinh" >
					<option value="">Chọn tỉnh/thành phố</option>
					<c:forEach var="i" begin="0" end="${fn:length(tinh) - 1 }">
						<option ${param.tinh.equals(tinh.get(i).getTentinh()) ? 'selected' : '' }>
				 			<c:out value="${tinh.get(i).getTentinh() }"></c:out>
				 		</option>
					</c:forEach>
				</select>
				<span id="tinh_error" class="w3-text-red  w3-right"></span>
			</div>
		</div>
		
		<!-- Mã vé -->
		<div class="w3-cell-row w3-panel">
			<label for="mave" class="w3-cell"><b>Mã Vé</b></label>
			<div class="w3-cell">
				<input type="text" id="mave" class="w3-input w3-border w3-round-large w3-center" name="mave" value="${param.mave }">
				<span id="mave_error" class="w3-text-red  w3-right"></span>
			</div>
		</div>
		<button class="w3-button w3-blue w3-round-large w3-right w3-margin-top" type="submit">Dò Vé Số</button>
	</form>
    </div>
</div>

<!-- Tab bar right -->
<div class="w3-col m3 w3-margin-top">
	
	<!-- Card search -->
	<div class="w3-white w3-margin-bottom">
	
		<!-- Card Header -->
		<div class="w3-container w3-black w3-text-white">
		    <h6>Tìm Kiếm Lịch Sử Dò</h6>
		</div>
		
		<!-- Card Body -->
		<div class="w3-container w3-padding-16">
			<form action="LichSuDoServlet?page=lichsudo&table=0&action=search" method="post">
			
				<!-- Tỉnh / Thành phố -->
				<select class="w3-select w3-border w3-margin-bottom" name="tinh">
					<option value="">Chọn tỉnh</option>
					<c:forEach var="i" begin="0" end="${fn:length(tinh) - 1 }">
						<option>
							<c:out value="${tinh.get(i).getTentinh() }"></c:out>
						</option>
					</c:forEach>
				</select>
				
				<!-- Kết quả -->
				<select class="w3-select w3-border" name="ketqua">
					<option value="">Chọn kết quả dò</option>
					<option ${param.ketqua.equals('Không trúng') ? 'selected' : '' }>Không trúng</option>
					<option ${param.ketqua.equals('Giải đặc biệt') ? 'selected' : '' }>Giải đặc biệt</option>
					<option ${param.ketqua.equals('Giải nhất') ? 'selected' : '' }>Giải nhất</option>
					<option ${param.ketqua.equals('Giải nhì') ? 'selected' : '' }>Giải nhì</option>
					<option ${param.ketqua.equals('Giải ba') ? 'selected' : '' }>Giải ba</option>
					<option ${param.ketqua.equals('Giải bốn') ? 'selected' : '' }>Giải bốn</option>
					<option ${param.ketqua.equals('Giải năm') ? 'selected' : '' }>Giải năm</option>
					<option ${param.ketqua.equals('Giải sáu') ? 'selected' : '' }>Giải sáu</option>
					<option ${param.ketqua.equals('Giải bảy') ? 'selected' : '' }>Giải bảy</option>
					<option ${param.ketqua.equals('Giải tám') ? 'selected' : '' }>Giải tám</option>
				</select>
				
				<!-- Submit -->
				<button class="w3-button w3-blue w3-round-large w3-right w3-margin-top" type="submit">Tìm Kiếm</button>
			</form>
		</div>
	</div>
    
    <!-- Card Alert -->   
	<div class="w3-white">
	
		<!-- Card Header -->
	   	<div class="w3-container w3-black w3-text-white">
	    	<h6>Thông Báo</h6>
		</div>
		
		<!-- Card Body -->
	    <div class="w3-container w3-padding-16">
		<div class="w3-text-red">
			<c:forTokens items="${messenger }" delims=";" var="text">
				<p><c:out value="${text }"></c:out></p>
			</c:forTokens>
		</div>	
	    </div>
	</div>
</div>

<!-- jQuery submit -->
<script src="${pageContext.request.contextPath}/js/doveso.js"></script>
</body>
</html>