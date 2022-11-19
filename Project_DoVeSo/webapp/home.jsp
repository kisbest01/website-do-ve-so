<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!-- JSTL library -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
    
<!DOCTYPE html>
<html lang="vi">
<head>

	<meta charset="UTF-8">
	<meta name="author" content="Kiss">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	
	<!-- W3.CSS library -->
	<link rel="stylesheet" href="https://www.w3shools.com/w3css/4/w3.css">
	
	<title>
		<c:out value="${title }"></c:out>
	</title>
	
	<!-- Custom icon for title -->
	<link rel="icon" href="https://scontent.fsgn5-10.fna.fbcdn.net/v/t31.18172-8/21994184_1367906893322716_2854525214528461723_o.jpg?_nc_cat=101&ccb=1-7&_nc_sid=cdbe9c&_nc_ohc=r-lyxa5zauAAX8kyatE&_nc_ht=scontent.fsgn5-10.fna&oh=00_AfDNDIL5KYTKcDWIPXAwFl6AkjL8aWOpuaANIEvMnJXBAQ&oe=638DBEC9">
	
	<!-- font awesome library -->
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	
</head>
<body>
<div class="w3-light-gray">

<!-- Header -->
<jsp:include page="header.jsp"></jsp:include>

<!-- Container -->
<div class="w3-container w3-margin-bottom">

	<!-- Tab bar left -->
	<div class="w3-bar-block w3-col m2 w3-margin-top">
		<form action="HomeServlet?page=trangchu&table=0&ngay&action=search" method="post">
			<div class="w3-black w3-center w3-text-white w3-padding">Miền Bắc</div>
			<c:forEach var="i" begin="0" end="${fn:length(tinh) - 1 }">
				<c:if test="${tinh.get(i).getMien() == 'Miền Bắc' }">
					<button style="margin:5px 0px;" name="tinh" class="w3-bar-item w3-white w3-center w3-border w3-border-blue w3-round-xxlarge w3-button ${param.tinh == tinh.get(i).getTentinh() ? 'w3-gray' : ''}" value="${tinh.get(i).getTentinh() }">
						<c:out value="${tinh.get(i).getTentinh() }"></c:out>
					</button>
				</c:if>
			</c:forEach>
			<div class="w3-black w3-center w3-text-white w3-padding">Miền Trung</div>
			<c:forEach var="i" begin="0" end="${fn:length(tinh) - 1 }">
				<c:if test="${tinh.get(i).getMien() == 'Miền Trung' }">
					<button style="margin:5px 0px;" name="tinh" class="w3-bar-item w3-white w3-center w3-border w3-border-blue w3-round-xxlarge w3-button ${param.tinh == tinh.get(i).getTentinh() ? 'w3-gray' : ''}" value="${tinh.get(i).getTentinh() }">
						<c:out value="${tinh.get(i).getTentinh() }"></c:out>
					</button>
				</c:if>
			</c:forEach>
			<div class="w3-black w3-center w3-text-white w3-padding">Miền Nam</div>
			<c:forEach var="i" begin="0" end="${fn:length(tinh) - 1 }">
				<c:if test="${tinh.get(i).getMien() == 'Miền Nam' }">
					<button style="margin:5px 0px;" name="tinh" class="w3-bar-item w3-white w3-center w3-border w3-border-blue w3-round-xxlarge w3-button ${param.tinh == tinh.get(i).getTentinh() ? 'w3-gray' : ''}" value="${tinh.get(i).getTentinh() }">
						<c:out value="${tinh.get(i).getTentinh() }"></c:out>
					</button>
				</c:if>
			</c:forEach>	
		</form>
	</div>
	
	<!-- Container -->
	<c:if test="${title == 'Trang Chủ' }">
		<jsp:include page="trangchu.jsp"></jsp:include>
	</c:if>
	<c:if test="${title == 'Dò Vé Số' }">
		<jsp:include page="doveso.jsp"></jsp:include>
	</c:if>
	<c:if test="${title == 'Lịch Sử Dò' }">
		<jsp:include page="lichsudo.jsp"></jsp:include>
	</c:if>
	<c:if test="${title == 'Hồ Sơ' }">
		<jsp:include page="hoso.jsp"></jsp:include>
	</c:if>
	<c:if test="${title == 'Thay Đổi Mật Khẩu' }">
		<jsp:include page="/admin/doipass.jsp"></jsp:include>
	</c:if>
</div>

<!-- Footer -->
<jsp:include page="footer.jsp"></jsp:include>
</div>
</body>
</html>