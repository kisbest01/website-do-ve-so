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
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="Kiss">
    
    <!-- jQuery library -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    
	<title>Quản Lý Vé Dò</title>
	
</head>
<body>

<!-- Page Heading -->
<div class="d-sm-flex align-items-center justify-content-between mb-4">
    <h1 class="h3 mb-0 text-gray-800">Quản Lý Vé Dò</h1>
    <a href="${pageContext.request.contextPath}/CapNhatServlet?page=themvedo&table=${param.table}" class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm">Thêm Vé Dò</a>
</div> 

<!-- Content Row -->
<div class="row">
	
    <!-- Bảng giải các ngày -->
    <div class="col-xl-8 col-lg-7">
    <c:forEach var="i" begin="0" end="${list.size() - 1 }">
    	<div class="card shadow mb-4 px-3">
    	<c:set var="vd" value="${list.get(i) }"></c:set>
		<c:forEach var="k" begin="0" end="${fn:length(tinh) - 1 }">
			<c:if test="${vd.getMatinh() == tinh.get(k).getId() }">
				<c:set var="tentinh" value="${tinh.get(k).getTentinh() }"></c:set>
			</c:if>
		</c:forEach>
		<div class="table-responsive">
	        <table class="table table-bordered table-sm text-center font-weight-bolder mt-3" id="dataTable">
		        <thead>
		        	
		        	<!-- Tỉnh, ngày -->
		        	<tr>
		        		<th colspan="13" class="text-white bg-dark">
		        			Xổ số <c:out value="${tentinh } ${vd.getThu() } "></c:out><fmt:formatDate pattern = "dd-MM-yyyy" value = "${vd.getNgay() }"/>
	        			</th>
		        	</tr>
		        </thead>
	        <tbody>
	        
	        	<!-- Đặc biệt -->
	          	<tr>
					<td class="font-weight-normal">Đặc biệt</td>	
					<td colspan="12" class="text-danger" id="db">
						<c:out value="${vd.getGiaiDB() }"></c:out>
					</td>          		
	          	</tr>
	          	
	          	<!-- Giải nhất -->
	          	<tr>
					<td class="font-weight-normal">Giải nhất</td>	
					<td colspan="12" id="nhat">
						<c:out value="${vd.getGiaiNhat() }"></c:out>
					</td>               		
	          	</tr>
	          	
	          	<!-- Giải nhì -->
	          	<c:set var="listGiaiNhi" value="${fn:split(vd.getGiaiNhi(), ', ') }"></c:set>
	          	<tr>
					<td class="font-weight-normal">Giải nhì</td>	
					<c:forEach var="i" begin="0" end="${fn:length(listGiaiNhi) - 1 }">
						<c:if test="${fn:length(listGiaiNhi) <= 1 }">
		    				<td colspan="12" id="nhi${i + 1 }">
								<c:out value="${listGiaiNhi[i] }"></c:out>
							</td> 
						</c:if>
						<c:if test="${fn:length(listGiaiNhi) > 1 }">
		    				<td colspan="6" id="nhi${i + 1 }">
								<c:out value="${listGiaiNhi[i] }"></c:out>
							</td> 
						</c:if>
		    		</c:forEach>
	          	</tr>
	          	
	          	<!-- Giải ba -->
	          	<c:set var="listGiaiBa" value="${fn:split(vd.getGiaiBa(), ', ') }"></c:set>
	          	<tr>
					<td class="font-weight-normal">Giải ba</td>	
					<c:forEach var="i" begin="0" end="${fn:length(listGiaiBa) - 1 }" step="2">
						<c:if test="${fn:length(listGiaiBa) <= 2 }">
		    				<td colspan="6" id="ba${i + 1 }">
								<c:out value="${listGiaiBa[i] }"></c:out>
							</td> 
							<td colspan="6" id="ba${i + 2 }">
								<c:out value="${listGiaiBa[i + 1] }"></c:out>
							</td> 
						</c:if>
						<c:if test="${fn:length(listGiaiBa) > 2 }">
		    				<td colspan="4" id="ba${i + 1 }">
								<c:out value="${listGiaiBa[i] }"></c:out><br>
								<c:out value="${listGiaiBa[i + 1] }"></c:out>
							</td> 
						</c:if>
		    		</c:forEach>
	          	</tr>
	          	
	          	<!-- Giải bốn -->
	          	<c:set var="listGiaiBon" value="${fn:split(vd.getGiaiBon(), ', ') }"></c:set>
	          	<tr>
					<td rowspan="2" class="font-weight-normal">Giải bốn</td>	
					<c:forEach var="i" begin="0" end="3">
						<td colspan="3" id = "bon${i + 1 }">
							<c:out value="${listGiaiBon[i] }"></c:out>
						</td>   
		    		</c:forEach>      		
	          	</tr>
	          	
	          	<tr>
	          		<c:forEach var="i" begin="4" end="${fn:length(listGiaiBon) - 1 }">
		          		<td colspan="4" id = "bon${i + 1 }">
		          			<c:out value="${listGiaiBon[i] }"></c:out>
		          		</td>     	
		    		</c:forEach>     
	          	</tr>
	          	
	          	<!-- Giải năm -->
	          	<c:set var="listGiaiNam" value="${fn:split(vd.getGiaiNam(), ', ') }"></c:set>
	          	<tr>
					<td class="font-weight-normal">Giải năm</td>	
					<c:forEach var="i" begin="0" end="${fn:length(listGiaiNam) - 1 }" step="2">
						<c:if test="${fn:length(listGiaiNam) <= 1 }">	
							<td colspan="12" id = "nam${i + 1 }">
								<c:out value="${listGiaiNam[i] }"></c:out>
							</td> 
						</c:if>  
					<c:if test="${fn:length(listGiaiNam) > 1 }">
						<td colspan="4" id = "nam${i + 1 }">
							<c:out value="${listGiaiNam[i] }"></c:out><br>
							<c:out value="${listGiaiNam[i + 1] }"></c:out>
						</td> 
					</c:if>         	             
					</c:forEach>		
	          	</tr>
	          	
	          	<!-- Giải sáu -->
	          	<c:set var="listGiaiSau" value="${fn:split(vd.getGiaiSau(), ', ') }"></c:set>
	          	<tr>
					<td class="font-weight-normal">Giải sáu</td>
					<c:forEach var="i" begin="0" end="${fn:length(listGiaiSau) - 1 }" >
						<td colspan="4" id = "sau${i + 1 }">
							<c:out value="${listGiaiSau[i] }"></c:out>
						</td>
					</c:forEach>        		
	          	</tr>
	          	
	          	<!-- Giải bảy -->
	          	<c:set var="listGiaiBay" value="${fn:split(vd.getGiaiBay(), ', ') }"></c:set>
	          	<tr>
					<td class="font-weight-normal">Giải bảy</td>	
					<c:forEach var="i" begin="0" end="${fn:length(listGiaiBay) - 1 }" >
						<c:if test="${fn:length(listGiaiBay) <= 1 }">
							<td colspan="12" id = "bay${i + 1 }">
								<c:out value="${listGiaiBay[i] }"></c:out>
							</td> 
						</c:if>
						<c:if test="${fn:length(listGiaiBay) > 1 }">
							<td colspan="3" id = "bay${i + 1 }">
								<c:out value="${listGiaiBay[i] }"></c:out>
							</td>      		
						</c:if> 
					</c:forEach>        		
	          	</tr>
	          	
	          	<!-- Giải tám -->
	          	<c:set var="listGiaiTam" value="${fn:split(vd.getGiaiTam(), ', ') }"></c:set>
		    	<c:if test="${listGiaiTam[0] != '' }">	
	     		<tr>
					<td class="font-weight-normal">Giải tám</td>
					<td colspan="12" id = "tam">
						<c:out value="${vd.getGiaiTam() }"></c:out>
					</td>     		
	            </tr>
	            </c:if>
	      	</tbody>
	      </table>
      </div>
   
	   <!-- Submit -->        
	   <div class="row mb-3">
			<form action="QuanLyServlet?page=quanlyvedo&table=0" method="post" class="col-auto">
				<input type="hidden" name="page" value="quanlyvedo">
				<input type="hidden" name="action" value="xoa">
				<input type="hidden" name="ngay" value="${vd.getNgay() }">
				<input type="hidden" name="tinh" value="${tentinh }">
				<button class="btn btn-primary xoa" onclick="return confirm('Bạn chắc muốn xóa chứ?')">Xóa</button>
			</form>
			<div class="col-auto">
				<a href="${pageContext.request.contextPath}/CapNhatServlet?page=capnhatvedo&tinh=${tentinh }&ngay=${vd.getNgay() }&table=${param.table}" class="btn btn-primary">Cập Nhật</a>
			</div>
			<div class="col form-check text-right">
			    <input type="checkbox" class="form-check-input" name="checkxoa" value="${vd.getId() }" form="formxoa">
			   	<label class="form-check-label" for="checkxoa">Xóa</label>
			</div> 
		</div> 
	</div>   
	</c:forEach>
	
	<!-- Tab pagination -->
	<div class="row">
		<div class="col">
			<form action="QuanLyServlet" method="${param.action == 'xem' ? 'get' : 'post' }">
				<input type="hidden" name="page" value="quanlyvedo">
				<input type="hidden" name="action" value="${param.action }">
				<input type="hidden" name="ngay" value="${param.ngay }">
				<input type="hidden" name="tinh" value="${param.tinh }">
			<ul class="pagination">
				<li class="paginate_button page-item previous" id="dataTable_previous">
					<button type="submit" name="table" value="${param.table == 0 ? param.table : param.table - 1}" class="page-link">
						Previous
					</button>
				</li>
				<c:forEach var="table" begin="${param.table }" end="${param.table + 2 }">
				<c:if test="${table < size }">
					<li class="paginate_button page-item ${param.table == table ? 'active' : ''}">
						<button type="submit" name="table" value="${table}" class="page-link">
							<c:out value="${table + 1 }"></c:out>
						</button>
					</li>
				</c:if>
				</c:forEach>
				<li class="paginate_button page-item next" id="dataTable_next">
					<button type="submit" name="table" value="${param.table == size - 1 ? param.table : param.table + 1}" class="page-link">Next</button>
				</li>
			</ul>
			</form>
		</div>
		
		<div class="col-auto pt-2 text-primary">
			<c:out value="Showing ${param.table + 1} of ${size } tables"></c:out>
		</div>
		
		 <form id="formxoa" action="QuanLyServlet?page=quanlyvedo&table=0&action=xoanhieu" method="post" class="col">
			<div class="d-flex justify-content-end mb-2">
				<button class="btn btn-primary xoachon" type="submit" onclick="return confirm('Bạn chắc muốn xóa chứ?')">Xóa Đã Chọn</button>
			</div>
		</form>
	</div>
</div>

    <!-- Tab bar -->
    <div class="col-xl-4 col-lg-5">
        <div class="card shadow mb-4">
        
            <!-- Card Header -->
            <div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                <h6 class="m-0 font-weight-bold text-primary">Tìm Kiếm Vé Dò</h6>
            </div>
            
            <!-- Card Body -->
            <div class="card-body">
                <form action="QuanLyServlet?page=quanlyvedo&table=0&action=search" method="post">
                	<input type="date" class="form-control bg-light border-0 small" name="ngay" value="${param.ngay }"><br>
			        <input type="text" class="form-control bg-light border-0 small" placeholder="Tỉnh / Thành phố ..." name="tinh" value="${param.tinh }"><br>
			        <div class="input-group-append justify-content-end">
			            <button class="btn btn-primary" type="submit">Tìm Kiếm</button>
			        </div>
                </form>
            </div>
        </div>
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
</body>
</html>