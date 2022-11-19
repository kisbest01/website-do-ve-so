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
	
	<!-- W3.CSS library -->
	<link rel="stylesheet" href="https://www.w3shools.com/w3css/4/w3.css">
	
	<title>Lịch Sử Dò</title>
	
	<!-- Font awesome library -->
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	
</head>
<body>
<div class="w3-container w3-col m7 w3-margin-top">

	<!-- Card header -->
	<div class="w3-container w3-black w3-text-white">
    	<h6>Lịch Sử Dò</h6>
    </div>
    
    <!-- Card Body -->
    <div>
    <c:if test="${fn:length(list) > 0 }">
    
    <!-- Form xóa -->
    <form action="LichSuDoServlet?page=lichsudo&table=0&action=xoa" method="post">
   	<c:forEach var="i" begin="0" end="${fn:length(list) - 1 }">
   		<div class="w3-panel w3-white w3-display-container w3-round-xxlarge w3-card-4">
   			<button type="submit" name="id" value="${list.get(i).getId() }" class="w3-button w3-display-topright" onclick="return confirm('Bạn chắc muốn xóa chứ?')">&times;</button>
   			<p><i><fmt:formatDate pattern = "dd-MM-yyyy" value = "${list.get(i).getNgayDo() }"/></i>
   				<input type="checkbox" name="check" class="w3-check" value="${list.get(i).getId() }" form="xoanhieu">
   			</p>
   			<div class="w3-cell-row">
   				<span class="w3-cell"><b>Mã vé:</b> ${list.get(i).getMaVe() }</span>
	        	<span class="w3-cell"><b>Ngày xổ:</b> <fmt:formatDate pattern = "dd-MM-yyyy" value = "${list.get(i).getGiaingay() }"/></span>
	        	<span class="w3-cell"><b>Tỉnh/Thành Phố:</b> ${list.get(i).getTinh() }</span>
   			</div>		            
       		<p><b>Kết quả:</b> Đã ${list.get(i).getKetQua() }!</p>
   		</div>
	</c:forEach>
    </form>
    
    <!-- Form xóa nhiều -->
    <form action="LichSuDoServlet?page=lichsudo&table=0&action=xoanhieu" method="post" id="xoanhieu">
   		<button class="w3-button w3-blue w3-round-large" type="submit" onclick="return confirm('Bạn chắc muốn xóa chứ?')">Xóa</button>
    </form>
    
    <!-- Tab pagination -->
    <div class="w3-center">
	<div class="w3-bar">
	<form action="LichSuDoServlet?page=lichsudo&action=${param.action }&ngay=${param.ngay }&ketqua=${param.ketqua }" method="${param.action == 'search' ? 'post' : 'get' }">
		<button  name="table" value="${param.table == 0 ? param.table : param.table - 1}" class="w3-button">&laquo;</button>
		<c:forEach var="table" begin="${param.table }" end="${param.table + 2 }">
		<c:if test="${table < size }">
			<button name="table" value="${table}" class="w3-button ${param.table == table ? 'w3-gray' : ''}" >
				<c:out value="${table + 1 }"></c:out>
			</button>
		</c:if>
		</c:forEach>
		<button name="table" value="${param.table == size - 1 ? param.table : param.table + 1}" class="w3-button">&raquo;</button>
	</form>
	</div>
	</div>
    </c:if>
    
    <!-- page empty -->
    <c:if test="${fn:length(list) <= 0 }">
    	<p class="w3-white w3-center w3-padding-64 w3-text-red w3-round-xxlarge w3-card-4">Lịch sử dò trống!</p>
    </c:if>
    </div>
</div>

<!-- Tab bar right -->
<div class="w3-col m3 w3-margin-top">
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
						<option ${param.tinh.equals(tinh.get(i).getTentinh()) ? 'selected' : '' }>
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
</body>
</html>