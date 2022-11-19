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
	
	<!-- jQuery library -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

	<title>Thêm Vé Dò</title>
	
</head>
<body>

<!-- Page Heading -->
<div class="d-sm-flex align-items-center justify-content-between mb-4">
    <h1 class="h3 mb-0 text-gray-800">
    	<b><c:out value="${title }"></c:out></b>
    </h1>
</div> 
<c:set var="listGiaiNhi" value="${fn:split(vd.getGiaiNhi(), ', ') }"></c:set>
<c:set var="listGiaiBa" value="${fn:split(vd.getGiaiBa(), ', ') }"></c:set>
<c:set var="listGiaiBon" value="${fn:split(vd.getGiaiBon(), ', ') }"></c:set>
<c:set var="listGiaiNam" value="${fn:split(vd.getGiaiNam(), ', ') }"></c:set>
<c:set var="listGiaiSau" value="${fn:split(vd.getGiaiSau(), ', ') }"></c:set>
<c:set var="listGiaiBay" value="${fn:split(vd.getGiaiBay(), ', ') }"></c:set>
<!-- Content Row -->
<div class="row justify-content-between">

<!-- Bảng giai các ngày -->
<div class="col-xl-7 col-lg-6 card shadow mb-4">
   		<!-- Form thêm vé dò -->
    	<form action="CapNhatServlet?page=${title.equals('Cập Nhật Vé Dò') ? 'capnhatvedo' : 'themvedo' }" method="post" class="mt-3" id="formthem">
			
			<!-- Ngày -->
		    <div class="pb-3 form-group row shadow-sm">
		    	<label for="giaingay" class="col align-self-center text-info"><b>Ngày</b></label>
		    	<div class="col">
		    		<input type="date" id="giaiNgay" class="form-control text-center" name="giaingay" value="<fmt:formatDate pattern = "yyyy-MM-dd" value = "${vd.getNgay() }" />">
		    		<span id="ngay_error" class="text-danger float-right"></span>
		    	</div>
		    </div>
		    
		    <!-- Tỉnh / thành phố -->
		    <div class="pb-3 form-group row shadow-sm">
		    	<label for="tinh" class="col align-self-center text-info"><b>Vùng Miền</b></label>
		    	<div class="col">
		    		<select class="form-control text-center" name="mien" id="mien">
		    			<option></option>
	    				<option ${tinh.getMien().equalsIgnoreCase('Miền Bắc')? "selected": "" }>Miền Bắc</option>
	    				<option ${tinh.getMien().equalsIgnoreCase('Miền Trung')? "selected": "" }>Miền Trung</option>
	    				<option ${tinh.getMien().equalsIgnoreCase('Miền Nam')? "selected": "" }>Miền Nam</option>
		    		</select>
		    		<span id="mien_error" class="text-danger float-right"></span>
		    	</div>
		    </div>
		    
		    <!-- Tỉnh / thành phố -->
		    <div class="pb-3 form-group row shadow-sm tinh">
		    	<label for="tinh" class="col align-self-center text-info"><b>Tỉnh/Thành phố</b></label>
		    	<div class="col">
		    		<select class="form-control text-center" name="tinh" id="seltinh" >
		    				<option></option>
			    			<c:forEach var="i" begin="0" end="${fn:length(listTinh) - 1 }">
			    				<option ${listTinh.get(i).getTentinh() == tinh.getTentinh()? "selected": "" }
			    					class="${listTinh.get(i).getMien() == 'Miền Bắc'? 'MB' : ''}${listTinh.get(i).getMien() == 'Miền Trung'? 'MT' : ''}${listTinh.get(i).getMien() == 'Miền Nam'? 'MN' : ''}" >
					    			<c:out value="${listTinh.get(i).getTentinh() }"></c:out>
					    		</option>
			    			</c:forEach>
		    		</select>
		    		<span id="tinh_error" class="text-danger float-right"></span>
		    	</div>
		    </div>
		    
		    <!-- Giải đặc biệt -->
		    <div class="pb-3 form-group row shadow-sm">
		    	<label for="dacbiet" class="col align-self-center text-info"><b>Giải đặc biệt</b></label>
		    	<div class="col">
		    		<input type="text" id="dacbiet" class="form-control text-center" name="dacbiet" value="${vd.getGiaiDB() }">
		    		<span id="db_error" class="text-danger float-right"></span>
		    	</div>
		    </div>
		    
		    <!-- Giải nhất -->
		    <div class="pb-3 form-group row shadow-sm">
		    	<label for="giainhat" class="col align-self-center text-info"><b>Giải nhất</b></label>
		    	<div class="col">
		    		<input type="text" id="giainhat" class="form-control text-center" name="giainhat" value="${vd.getGiaiNhat() }">
		    		<span id="nhat_error" class="text-danger float-right"></span>
		    	</div>
		    </div>
		    
		    <!-- Giải nhì -->
		    <div class="pb-3 form-group row shadow-sm">
		    	<label for="giainhi" class="col align-self-center text-info"><b>Giải nhì</b></label>
	    			<div class="col">
	    				<input type="text" id="giainhi1" class="form-control text-center" name="giainhi" value="${listGiaiNhi[0] }">
	   					<span id="nhi1_error" class="text-danger float-right"></span>
	   				</div>
	   				<c:if test="${tinh.getMien().equals('Miền Bắc') || param.page.equals('themvedo') }">
		   				<div class="col giainhi2">
		    				<input type="text" id="giainhi2" class="form-control text-center" name="giainhi" value="${listGiaiNhi[1] }">
		   					<span id="nhi2_error" class="text-danger float-right"></span>
		   				</div>
	   				</c:if>
		    </div>
		    
		    <!-- Giải ba -->
		    <div class="pb-3 form-group row shadow-sm">
		    	<label for="giaiba" class="col align-self-center text-info"><b>Giải ba</b></label>
			    		<div class="col giaiba14">
		    				<input type="text" id="giaiba1" class="form-control text-center" name="giaiba" value="${listGiaiBa[0] }">
		    				<span id="ba1_error" class="text-danger float-right"></span>
		    				<c:if test="${tinh.getMien().equals('Miền Bắc') || param.page.equals('themvedo') }">
			    				<br>
			    				<input type="text" id="giaiba4" class="form-control text-center" name="giaiba" value="${listGiaiBa[3] }">
			    				<span id="ba4_error" class="text-danger float-right"></span>
		    				</c:if>
		    			</div>
		    			<div class="col giaiba25">
		    				<input type="text" id="giaiba2" class="form-control text-center" name="giaiba" value="${listGiaiBa[1] }">
		    				<span id="ba2_error" class="text-danger float-right"></span>
		    				<c:if test="${tinh.getMien().equals('Miền Bắc') || param.page.equals('themvedo') }">
			    				<br>
			    				<input type="text" id="giaiba5" class="form-control text-center" name="giaiba" value="${listGiaiBa[4] }">
			    				<span id="ba5_error" class="text-danger float-right"></span>
		    				</c:if>
		    			</div>
		    			<c:if test="${tinh.getMien().equals('Miền Bắc') || param.page.equals('themvedo') }">
			    			<div class="col giaiba36">
			    				<input type="text" id="giaiba3" class="form-control text-center" name="giaiba" value="${listGiaiBa[2] }">
			    				<span id="ba3_error" class="text-danger float-right"></span>
			    				<br>
			    				<input type="text" id="giaiba6" class="form-control text-center" name="giaiba" value="${listGiaiBa[5] }">
			    				<span id="ba6_error" class="text-danger float-right"></span>
			    			</div>
		    			</c:if>
			</div>
			
			<!-- Giải bốn -->
			<div class="pb-3 form-group row shadow-sm">
		    	<label for="giaibon" class="col align-self-center text-info"><b>Giải bốn</b></label>
		    		<div class="col giaibon1">
	    				<input type="text" id="giaibon1" class="form-control text-center" name="giaibon" value="${listGiaiBon[0] }">
	    				<span id="bon1_error" class="text-danger float-right"></span>
	   				</div>
	    			<div class="col giaibon25">
	    				<input type="text" id="giaibon2" class="form-control text-center" name="giaibon" value="${listGiaiBon[1] }">
	    				<span id="bon2_error" class="text-danger float-right"></span>
	    				<c:if test="${!tinh.getMien().equals('Miền Bắc') || param.page.equals('themvedo') }">
		    				<br>
				    		<input type="text" id="giaibon5" class="form-control text-center" name="giaibon" value="${listGiaiBon[4] }">
				    		<span id="bon5_error" class="text-danger float-right"></span>
			    		</c:if>
	   				</div>
	   				<div class="col giaibon36">
	    				<input type="text" id="giaibon3" class="form-control text-center" name="giaibon" value="${listGiaiBon[2] }">
	    				<span id="bon3_error" class="text-danger float-right"></span>
	    				<c:if test="${!tinh.getMien().equals('Miền Bắc') || param.page.equals('themvedo') }">
		    				<br>
				    		<input type="text" id="giaibon6" class="form-control text-center" name="giaibon" value="${listGiaiBon[5] }">
				    		<span id="bon6_error" class="text-danger float-right"></span>
			    		</c:if>
	   				</div>
	   				<div class="col giaibon47">
	    				<input type="text" id="giaibon4" class="form-control text-center" name="giaibon" value="${listGiaiBon[3] }">
	    				<span id="bon4_error" class="text-danger float-right"></span>
	    				<c:if test="${!tinh.getMien().equals('Miền Bắc') || param.page.equals('themvedo') }">
		    				<br>
				    		<input type="text" id="giaibon7" class="form-control text-center" name="giaibon" value="${listGiaiBon[6] }">
				    		<span id="bon7_error" class="text-danger float-right"></span>
			    		</c:if>
	   				</div>
		    </div>
		    
		    <!-- Giải năm -->		    
		    <div class="pb-3 form-group row shadow-sm">
		    	<label for="giainam" class="col align-self-center text-info"><b>Giải năm</b></label>
		    		<div class="col giainam14">
	    				<input type="text" id="giainam1" class="form-control text-center" name="giainam" value="${listGiaiNam[0] }">
	    				<span id="nam1_error" class="text-danger float-right"></span>
	    				<c:if test="${tinh.getMien().equals('Miền Bắc') || param.page.equals('themvedo') }">
		    				<br>
			    			<input type="text" id="giainam4" class="form-control text-center" name="giainam" value="${listGiaiNam[3] }">
			    			<span id="nam4_error" class="text-danger float-right"></span>
		    			</c:if>
	    			</div>
	    			<c:if test="${tinh.getMien().equals('Miền Bắc') || param.page.equals('themvedo') }">
		    			<div class="col giainam25">
		    				<input type="text" id="giainam2" class="form-control text-center" name="giainam" value="${listGiaiNam[1] }">
		    				<span id="nam2_error" class="text-danger float-right"></span>
		    				<br>
			    			<input type="text" id="giainam5" class="form-control text-center" name="giainam" value="${listGiaiNam[4] }">
			    			<span id="nam5_error" class="text-danger float-right"></span>
		    			</div>
		    			<div class="col giainam36">
		    				<input type="text" id="giainam3" class="form-control text-center" name="giainam" value="${listGiaiNam[2] }">
		    				<span id="nam3_error" class="text-danger float-right"></span>
		    				<br>
			    			<input type="text" id="giainam6" class="form-control text-center" name="giainam" value="${listGiaiNam[5] }">
			    			<span id="nam6_error" class="text-danger float-right"></span>
		    			</div>
	    			</c:if>
		    </div>
		    
		    <!-- Giải sáu -->		    
		    <div class="pb-3 form-group row shadow-sm">
		    	<label for="giaisau" class="col align-self-center text-info"><b>Giải sáu</b></label>
		    		<div class="col giaisau1">
	    				<input type="text" id="giaisau1" class="form-control text-center" name="giaisau" value="${listGiaiSau[0] }">
	    				<span id="sau1_error" class="text-danger float-right"></span>
	    			</div>
	    			<div class="col giaisau2">
	    				<input type="text" id="giaisau2" class="form-control text-center" name="giaisau" value="${listGiaiSau[1] }">
	    				<span id="sau2_error" class="text-danger float-right"></span>
	    			</div>
	    			<div class="col giaisau3">
	    				<input type="text" id="giaisau3" class="form-control text-center" name="giaisau" value="${listGiaiSau[2] }">
	    				<span id="sau3_error" class="text-danger float-right"></span>
	    			</div>
		    </div>
		    
		    <!-- Giải bảy -->		    
		    <div class="pb-3 form-group row shadow-sm">
		    	<label for="giaibay" class="col align-self-center text-info"><b>Giải bảy</b></label>
	    		<div class="col giaibay1">
	   				<input type="text" id="giaibay1" class="form-control text-center" name="giaibay" value="${listGiaiBay[0] }">
	   				<span id="bay1_error" class="text-danger float-right"></span>
	   			</div>
	   			<c:if test="${tinh.getMien().equals('Miền Bắc') || param.page.equals('themvedo') }">
		   			<div class="col giaibay2">
		   				<input type="text" id="giaibay2" class="form-control text-center" name="giaibay" value="${listGiaiBay[1] }">
		   				<span id="bay2_error" class="text-danger float-right"></span>
		   			</div>
		   			<div class="col giaibay3">
		   				<input type="text" id="giaibay3" class="form-control text-center" name="giaibay" value="${listGiaiBay[2] }">
		   				<span id="bay3_error" class="text-danger float-right"></span>
		   			</div>
		   			<div class="col giaibay4">
		   				<input type="text" id="giaibay4" class="form-control text-center" name="giaibay" value="${listGiaiBay[3] }">
		   				<span id="bay4_error" class="text-danger float-right"></span>
		   			</div>
	   			</c:if>
		    </div>
		    <c:if test="${!tinh.getMien().equals('Miền Bắc') || param.page.equals('themvedo') }">
			    <!-- Giải tám -->
			    <div class="pb-3 form-group row shadow-sm giaitam">
			    	<label for="giaitám" class="col align-self-center text-info"><b>Giải tám</b></label>
			    	<div class="col">
			    		<input type="text" id="giaitam" class="form-control text-center" name="giaitam" value="${vd.getGiaiTam() }">
			    		<span id="tam_error" class="text-danger float-right"></span>
			    	</div>
			    </div>
		    </c:if>
		    <c:if test="${param.page.equals('themvedo') }">
			    <!-- Reset -->
		    	<input type="reset" class="btn btn-primary m-2" value="Reset">
	   		</c:if>
	   		 <!-- Hủy -->
	   		 <a href="${pageContext.request.contextPath}/QuanLyServlet?page=quanlyvedo&table=${param.table }&action=xem" class="btn btn-primary m-2">Hủy</a>
	   		
	   		<!-- Submit -->
	    	<button class="btn btn-primary m-2 float-right" type="submit">${title.equals('Cập Nhật Vé Dò') ? 'Cập Nhật' : 'Thêm' }</button>
	    	
	   		</form>
</div>

<!-- Tab bar -->
<div class="col-xl-5 col-lg-6">
    <div class="table-responsive card shadow mb-4">
    	<c:if test="${title.equals('Thêm Vé Dò') }">
    	
    	<!-- Table vé dò -->
 		<table class="table table-bordered table-sm text-center font-weight-bolder" id="dataTable">
	        <thead>
	        	
	        	<!-- Tỉnh, ngày -->
	        	<tr>
	        		<th colspan="13" class="text-white bg-dark">
	        			Xổ số 
	        			<span id="tinh"><span class="spinner-border spinner-border-sm"></span></span>
	        			<span id="thu"><span class="spinner-border spinner-border-sm"></span></span>
	        			<span id="ngay"><span class="spinner-border spinner-border-sm"></span></span>
        			</th>
	        	</tr>
	        </thead>
        <tbody>
        
        	<!-- Đặc biệt -->
          	<tr>
				<td class="font-weight-normal">Đặc biệt</td>	
				<td colspan="12" class="text-danger" id="db">
					<span class="spinner-border spinner-border-sm"></span>
				</td>          		
          	</tr>
          	
          	<!-- Giải nhất -->
          	<tr>
				<td class="font-weight-normal">Giải nhất</td>	
				<td colspan="12" id="nhat">
					<span class="spinner-border spinner-border-sm"></span>
				</td>               		
          	</tr>
          	
          	<!-- Giải nhì -->
          	<tr>
				<td class="font-weight-normal">Giải nhì</td>	
    				<td colspan="6" id="nhi1">
    					<span class="spinner-border spinner-border-sm"></span>
					</td> 
    				<td colspan="6" id="nhi2">
    					<span class="spinner-border spinner-border-sm"></span>
					</td> 
          	</tr>
          	
          	<!-- Giải ba -->
          	<tr>
				<td class="font-weight-normal">Giải ba</td>	
    				<td colspan="4" id="ba14">
						<span class="ba1"><span class="spinner-border spinner-border-sm"></span></span><br>
						<span class="ba4"><span class="spinner-border spinner-border-sm"></span></span>
					</td> 
					<td colspan="4" id="ba25">
						<span class="ba2"><span class="spinner-border spinner-border-sm"></span></span><br>
						<span class="ba5"><span class="spinner-border spinner-border-sm"></span></span>
					</td> 
					<td colspan="4" id="ba36">
						<span class="ba3"><span class="spinner-border spinner-border-sm"></span></span><br>
						<span class="ba6"><span class="spinner-border spinner-border-sm"></span></span>
					</td> 
          	</tr>
          	
          	<!-- Giải bốn -->
          	<tr>
				<td rowspan="2" class="font-weight-normal">Giải bốn</td>	
					<td colspan="3" id = "bon1">
						<span class="spinner-border spinner-border-sm"></span>
					</td> 
					<td colspan="3" id = "bon2">
						<span class="spinner-border spinner-border-sm"></span>
					</td>      	
					<td colspan="3" id = "bon3">
						<span class="spinner-border spinner-border-sm"></span>
					</td>      	
					<td colspan="3" id = "bon4">
						<span class="spinner-border spinner-border-sm"></span>
					</td>      	     		
          	</tr>
          	<tr>
	          		<td colspan="4" id = "bon5">
	          			<span class="spinner-border spinner-border-sm"></span>
	          		</td>   
	          		<td colspan="4" id = "bon6">
	          			<span class="spinner-border spinner-border-sm"></span>
	          		</td>   
	          		<td colspan="4" id = "bon7">
	          			<span class="spinner-border spinner-border-sm"></span>
	          		</td>    	  
          	</tr>
          	
          	<!-- Giải năm -->
          	<tr>
				<td class="font-weight-normal">Giải năm</td>	
				<td colspan="4" id = "nam14">
					<span class="nam1"><span class="spinner-border spinner-border-sm"></span></span><br>
					<span class="nam4"><span class="spinner-border spinner-border-sm"></span></span>
				</td> 
				<td colspan="4" id = "nam25">
					<span class="nam2"><span class="spinner-border spinner-border-sm"></span></span><br>
					<span class="nam5"><span class="spinner-border spinner-border-sm"></span></span>
				</td> 
				<td colspan="4" id = "nam36">
					<span class="nam3"><span class="spinner-border spinner-border-sm"></span></span><br>
					<span class="nam6"><span class="spinner-border spinner-border-sm"></span></span>
				</td> 
          	</tr>
          	
          	<!-- Giải sáu -->
          	<tr>
				<td class="font-weight-normal">Giải sáu</td>		
				<td colspan="4" id = "sau1">
					<span class="spinner-border spinner-border-sm"></span>
				</td> 
				<td colspan="4" id = "sau2">
					<span class="spinner-border spinner-border-sm"></span>
				</td>
				<td colspan="4" id = "sau3">
					<span class="spinner-border spinner-border-sm"></span>
				</td>     		
          	</tr>
          	
          	<!-- Giải bảy -->
          	<tr>
				<td class="font-weight-normal">Giải bảy</td>	
				<td colspan="3" id = "bay1">
					<span class="spinner-border spinner-border-sm"></span>
				</td>  
				<td colspan="3" id = "bay2">
					<span class="spinner-border spinner-border-sm"></span>
				</td> 
				<td colspan="3" id = "bay3">
					<span class="spinner-border spinner-border-sm"></span>
				</td> 
				<td colspan="3" id = "bay4">
					<span class="spinner-border spinner-border-sm"></span>
				</td>     		    		
          	</tr>
          	
          	<!-- Giải tám -->
     		<tr id = "tam">
				<td class="font-weight-normal">Giải tám</td>
				<td colspan="12" class="tam">
					<span class="spinner-border spinner-border-sm"></span>
				</td>     		
            </tr>
      	</tbody>
      </table>
      </c:if>
      
      <!-- Table Cập nhật vé dò -->
      <c:if test="${title.equals('Cập Nhật Vé Dò') }">
 		<table class="table table-bordered table-sm text-center font-weight-bolder" id="dataTable">
	        <thead>
	        
	        	<!-- Tỉnh, ngày -->
	        	<tr>
	        		<th colspan="13" class="text-white bg-dark">
	        			Xổ số 
	        			<span id="tinh"><c:out value="${tinh.getTentinh() } "></c:out></span>
	        			<span id="thu"><c:out value="${vd.getThu() } "></c:out></span>
	        			<span id="ngay"><fmt:formatDate pattern = "dd-MM-yyyy" value = "${vd.getNgay() }"/></span>
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
          	<tr>
				<td class="font-weight-normal">Giải nhì</td>
				<c:forEach var="i" begin="0" end="${fn:length(listGiaiNhi) - 1 }">
					<td colspan="${12 / fn:length(listGiaiNhi)}" id="nhi${i + 1 }">
						<c:out value="${listGiaiNhi[i] }"></c:out>
					</td> 
				</c:forEach>	
          	</tr>
          	
          	<!-- Giải ba -->
          	<tr>
				<td class="font-weight-normal">Giải ba</td>	
				<c:if test="${fn:length(listGiaiBa) <= 2 }">
					<c:forEach var="i" begin="0" end="${fn:length(listGiaiBa) - 1 }">
						<td colspan="6" class="ba${i + 1 }">
							<c:out value="${listGiaiBa[i ] }"></c:out>
						</td> 
					</c:forEach>
				</c:if>
				<c:if test="${fn:length(listGiaiBa) > 2 }">
					<c:forEach var="i" begin="0" end="${fn:length(listGiaiBa) - 1 }" step="2">
						<fmt:formatNumber var="giaiba1" type="number" value="${i / 2 + 1 }"></fmt:formatNumber>
						<fmt:formatNumber var="giaiba2" type="number" value="${i / 2 + 4 }"></fmt:formatNumber>
						<td colspan="4" id="ba${giaiba1 }${giaiba2 }">
							<span class="ba${giaiba1 }"><c:out value="${listGiaiBa[i / 2] }"></c:out></span><br>
							<span class="ba${giaiba2 }"><c:out value="${listGiaiBa[i / 2 + 3] }"></c:out></span>
						</td> 
					</c:forEach>
				</c:if>
          	</tr>
          	
          	<!-- Giải bốn -->
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
          	<tr>
				<td class="font-weight-normal">Giải năm</td>	
				<c:if test="${fn:length(listGiaiNam) <= 1 }">
					<td colspan="12" class = "nam1">
						<c:out value="${listGiaiNam[0] }"></c:out>
					</td> 
				</c:if>
				<c:if test="${fn:length(listGiaiNam) > 1 }">
				<c:forEach var="i" begin="0" end="${fn:length(listGiaiNam) - 1 }" step="2">
					<fmt:formatNumber var="giaibon1" type="number" value="${i / 2 + 1 }"></fmt:formatNumber>
					<fmt:formatNumber var="giaibon2" type="number" value="${i / 2 + 4 }"></fmt:formatNumber>
					<td colspan="4" id = "nam${giaibon1 }${giaibon2}">
						<span class="nam${giaibon1 }"><c:out value="${listGiaiNam[i / 2] }"></c:out></span><br>
						<span class="nam${giaibon2 }"><c:out value="${listGiaiNam[i / 2 + 3] }"></c:out></span>
					</td> 
				</c:forEach>
				</c:if>
          	</tr>
          	
          	<!-- Giải sáu -->
          	<tr>
				<td class="font-weight-normal">Giải sáu</td>
				<c:forEach var="i" begin="0" end="${fn:length(listGiaiSau) - 1 }">
					<td colspan="4" id = "sau${i + 1 }">
						<c:out value="${listGiaiSau[i] }"></c:out>
					</td> 
				</c:forEach>		   		
          	</tr>
          	
          	<!-- Giải bảy -->
          	<tr>
				<td class="font-weight-normal">Giải bảy</td>	
				<c:forEach var="i" begin="0" end="${fn:length(listGiaiBay) - 1 }">
					<td colspan="${12 / fn:length(listGiaiBay) }" id = "bay${i + 1 }">
						<c:out value="${listGiaiBay[i] }"></c:out>
					</td>
				</c:forEach>  		    		
          	</tr>
          	
          	<!-- Giải tám -->
          	<c:if test="${!tinh.getMien().equalsIgnoreCase('Miền Bắc') }">
          		<tr id = "tam">
					<td class="font-weight-normal">Giải tám</td>
					<td colspan="12" class="tam">
						<c:out value="${vd.getGiaiTam() }"></c:out>
					</td>     		
	            </tr>
          	</c:if>
      	</tbody>
      </table>
      </c:if>
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

<!-- jQuery -->
<script src="${pageContext.request.contextPath}/js/update.js"></script>

</body>
</html>