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
	
	<title>Trang Chủ</title>
	
</head>
<body>
<div class="w3-container w3-col m7">
	<c:forEach var="i" begin="0" end="${fn:length(list) - 1 }">
		<c:set var="vd" value="${list.get(i) }"></c:set>

		<c:forEach var="k" begin="0" end="${fn:length(tinh) - 1 }">
			<c:if test="${vd.getMatinh() == tinh.get(k).getId() }">
				<c:set var="tentinh" value="${tinh.get(k).getTentinh() }"></c:set>
			</c:if>
		</c:forEach>
		
		<!-- Table vé số -->
		<table class="w3-table w3-border w3-bordered w3-panel w3-white" id="dataTable">
        <thead>
        
        	<!-- Tỉnh , ngày -->
        	<tr>
        		<th colspan="13" class="w3-border w3-black w3-text-white w3-center">
        			Xổ số <c:out value="${tentinh } ${vd.getThu() } "></c:out><fmt:formatDate pattern = "dd-MM-yyyy" value = "${vd.getNgay() }"/>
       			</th>
        	</tr>
        </thead>
        <tbody>
        
        	<!-- Đặc biệt -->
          	<tr>
				<td class="w3-border w3-center" >Đặc biệt</td>	
				<td colspan="12" class="w3-border w3-center w3-text-red" id="db">
					<c:out value="${vd.getGiaiDB() }"></c:out>
				</td>          		
          	</tr>
          	
          	<!-- Giải nhất -->
          	<tr>
				<td class="w3-border w3-center">Giải nhất</td>	
				<td colspan="12" id="nhat" class="w3-border w3-center">
					<c:out value="${vd.getGiaiNhat() }"></c:out>
				</td>               		
          	</tr>
          	
          	<!-- Giải nhì -->
          	<c:set var="listGiaiNhi" value="${fn:split(vd.getGiaiNhi(), ', ') }"></c:set>
          	<tr>
				<td class="w3-border w3-center">Giải nhì</td>	
				<c:forEach var="i" begin="0" end="${fn:length(listGiaiNhi) - 1 }">
					<c:if test="${fn:length(listGiaiNhi) <= 1 }">
	    				<td colspan="12" id="nhi${i + 1 }" class="w3-border w3-center">
							<c:out value="${listGiaiNhi[i] }"></c:out>
						</td> 
					</c:if>
					<c:if test="${fn:length(listGiaiNhi) > 1 }">
	    				<td colspan="6" id="nhi${i + 1 }" class="w3-border w3-center">
							<c:out value="${listGiaiNhi[i] }"></c:out>
						</td> 
					</c:if>
	    		</c:forEach>
          	</tr>
          	
          	<!-- Giải ba -->
          	<c:set var="listGiaiBa" value="${fn:split(vd.getGiaiBa(), ', ') }"></c:set>
          	<tr>
				<td class="w3-border w3-center">Giải ba</td>	
				<c:forEach var="i" begin="0" end="${fn:length(listGiaiBa) - 1 }" step="2">
					<c:if test="${fn:length(listGiaiBa) <= 2 }">
	    				<td colspan="6" id="ba${i + 1 }" class="w3-border w3-center">
							<c:out value="${listGiaiBa[i] }"></c:out>
						</td> 
						<td colspan="6" id="ba${i + 2 }" class="w3-border w3-center">
							<c:out value="${listGiaiBa[i + 1] }"></c:out>
						</td> 
					</c:if>
					<c:if test="${fn:length(listGiaiBa) > 2 }">
	    				<td colspan="4" id="ba${i + 1 }" class="w3-border w3-center">
							<c:out value="${listGiaiBa[i] }"></c:out><br>
							<c:out value="${listGiaiBa[i + 1] }"></c:out>
						</td> 
					</c:if>
	    		</c:forEach>
          	</tr>
          	
          	<!-- Giải bốn -->
          	<c:set var="listGiaiBon" value="${fn:split(vd.getGiaiBon(), ', ') }"></c:set>
          	<tr>
				<td rowspan="2" class="w3-border w3-center">Giải bốn</td>	
				<c:forEach var="i" begin="0" end="3">
					<td colspan="3" id = "bon${i + 1 }" class="w3-border w3-center">
						<c:out value="${listGiaiBon[i] }"></c:out>
					</td>   
	    		</c:forEach>      		
          	</tr>
          	
          	<tr>
          		<c:forEach var="i" begin="4" end="${fn:length(listGiaiBon) - 1 }">
	          		<td colspan="4" id = "bon${i + 1 }" class="w3-border w3-center">
	          			<c:out value="${listGiaiBon[i] }"></c:out>
	          		</td>     	
	    		</c:forEach>     
          	</tr>
          	
          	<!-- Giải năm -->
          	<c:set var="listGiaiNam" value="${fn:split(vd.getGiaiNam(), ', ') }"></c:set>
          	<tr>
				<td class="w3-border w3-center">Giải năm</td>	
				<c:forEach var="i" begin="0" end="${fn:length(listGiaiNam) - 1 }" step="2">
					<c:if test="${fn:length(listGiaiNam) <= 1 }">	
						<td colspan="12" id = "nam${i + 1 }" class="w3-border w3-center">
							<c:out value="${listGiaiNam[i] }"></c:out>
						</td> 
					</c:if>  
				<c:if test="${fn:length(listGiaiNam) > 1 }">
					<td colspan="4" id = "nam${i + 1 }" class="w3-border w3-center">
						<c:out value="${listGiaiNam[i] }"></c:out><br>
						<c:out value="${listGiaiNam[i + 1] }"></c:out>
					</td> 
				</c:if>         	             
				</c:forEach>		
          	</tr>
          	
          	<!-- Giải sáu -->
          	<c:set var="listGiaiSau" value="${fn:split(vd.getGiaiSau(), ', ') }"></c:set>
          	<tr>
				<td class="w3-border w3-center">Giải sáu</td>
				<c:forEach var="i" begin="0" end="${fn:length(listGiaiSau) - 1 }" >
					<td colspan="4" id = "sau${i + 1 }" class="w3-border w3-center">
						<c:out value="${listGiaiSau[i] }"></c:out>
					</td>
				</c:forEach>        		
          	</tr>
          	
          	<!-- Giải bảy -->
          	<c:set var="listGiaiBay" value="${fn:split(vd.getGiaiBay(), ', ') }"></c:set>
          	<tr>
				<td class="w3-border w3-center">Giải bảy</td>	
				<c:forEach var="i" begin="0" end="${fn:length(listGiaiBay) - 1 }" >
					<c:if test="${fn:length(listGiaiBay) <= 1 }">
						<td colspan="12" id = "bay${i + 1 }" class="w3-border w3-center">
							<c:out value="${listGiaiBay[i] }"></c:out>
						</td> 
					</c:if>
					<c:if test="${fn:length(listGiaiBay) > 1 }">
						<td colspan="3" id = "bay${i + 1 }" class="w3-border w3-center">
							<c:out value="${listGiaiBay[i] }"></c:out>
						</td>      		
					</c:if> 
				</c:forEach>        		
          	</tr>
          	
          	<!-- Giải tám -->
          	<c:set var="listGiaiTam" value="${fn:split(vd.getGiaiTam(), ', ') }"></c:set>
	    	<c:if test="${listGiaiTam[0] != '' }">	
     		<tr>
				<td class="w3-border w3-center">Giải tám</td>
				<td colspan="12" id = "tam" class="w3-border w3-center">
					<c:out value="${vd.getGiaiTam() }"></c:out>
				</td>     		
            </tr>
            </c:if>
      	</tbody>
      </table>
	</c:forEach>
	
	<!-- Tab pagination -->
	<div class="w3-center">
		<div class="w3-bar">
		<form action="HomeServlet?page=trangchu&action=${param.action }&ngay=${param.ngay }&tinh=${param.tinh }" method="${param.action == 'search' ? 'post' : 'get' }">
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
</div>

<div class="w3-col m3 w3-margin-top">

	<!-- Card search -->
	<div class="w3-white w3-margin-bottom">
	
         <!-- Card Header -->
         <div class="w3-container w3-black w3-text-white">
             <h6>Tìm Kiếm Vé Dò</h6>
         </div>
         
         <!-- Card Body -->
         <div class="w3-container w3-padding-16">
         <form action="HomeServlet?page=trangchu&table=0&action=search" method="post">
         	<input type="date" class="w3-input w3-border-0 w3-light-grey w3-round-large" name="ngay" value="${param.ngay }"><br>
		    <input type="text" class="w3-input w3-border-0 w3-light-grey w3-round-large" placeholder="Tỉnh / Thành phố ..." name="tinh" value="${param.tinh }"><br>
		    <button class="w3-button w3-blue w3-round-large w3-right" type="submit">Tìm Kiếm</button>
         </form>
         </div>
     </div>
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