/**
 * 
 */

 $(document).ready(function() {
	$("#formthem").submit(function() {
		let giaingay = $("#giaiNgay").val();
		let mien = $("#mien").val();
		let tinh = $("#seltinh").val();
		
		let dacbiet = $("#dacbiet").val();
		
		let giainhat = $("#giainhat").val();
		
		let giainhi1 = $("#giainhi1").val();
		let giainhi2 = $("#giainhi2").val();
		
		let giaiba1 = $("#giaiba1").val();
		let giaiba2 = $("#giaiba2").val();
		let giaiba3 = $("#giaiba3").val();
		let giaiba4 = $("#giaiba4").val();
		let giaiba5 = $("#giaiba5").val();
		let giaiba6 = $("#giaiba6").val();
		
		let giaibon1 = $("#giaibon1").val();
		let giaibon2 = $("#giaibon2").val();
		let giaibon3 = $("#giaibon3").val();
		let giaibon4 = $("#giaibon4").val();
		let giaibon5 = $("#giaibon5").val();
		let giaibon6 = $("#giaibon6").val();
		let giaibon7 = $("#giaibon7").val();
		
		let giainam1 = $("#giainam1").val();
		let giainam2 = $("#giainam2").val();
		let giainam3 = $("#giainam3").val();
		let giainam4 = $("#giainam4").val();
		let giainam5 = $("#giainam5").val();
		let giainam6 = $("#giainam6").val();
		
		let giaisau1 = $("#giaisau1").val();
		let giaisau2 = $("#giaisau2").val();
		let giaisau3 = $("#giaisau3").val();
		
		let giaibay1 = $("#giaibay1").val();
		let giaibay2 = $("#giaibay2").val();
		let giaibay3 = $("#giaibay3").val();
		let giaibay4 = $("#giaibay4").val();
		
		let giaitam = $("#giaitam").val();
		
		// Biến cờ hiệu
        let flag = true;
		
		//Ngày
		if(giaingay == "") {
			$("#ngay_error").text("Vui lòng nhập ngày!");
			flag = false;
		} else {
			$("#ngay_error").text("");
		}
		
		//Miền
		if(mien == "") {
			$("#mien_error").text("Vui lòng chọn miền!");
			flag = false;
		} else {
			$("#mien_error").text("");
		}
		 
		 //Tỉnh
		 if(tinh == "") {
			$("#tinh_error").text("Vui lòng chọn tỉnh!");
			flag = false;
		} else {
			$("#tinh_error").text("");
		}
		
		//Nhất	
		if(giainhat == "") {
			$("#nhat_error").text("Chưa nhập mã!");
			flag = false;
		} else if(giainhat.match("[0-9]{5}") == null) {
			$("#nhat_error").text("Mã không hợp lệ!");
			flag = false;
		} else {
			$("#nhat_error").text("");
		}
			
		if(mien == "Miền Bắc") {
			//Đặc biệt
			if(dacbiet == "") {
				$("#db_error").text("Chưa nhập mã!");
				flag = false;
			} else if(dacbiet.match("[0-9]{5}") == null) {
				$("#db_error").text("Mã không hợp lệ!");
				flag = false;
			} else {
				$("#db_error").text("");
			}
			
			//Nhì
			if(giainhi1 == "") {
				$("#nhi1_error").text("Chưa nhập mã!");
				flag = false;
			} else if(giainhi1.match("[0-9]{5}") == null) {
				$("#nhi1_error").text("Mã không hợp lệ!");
				flag = false;
			} else {
				$("#nhi1_error").text("");
			}
			if(giainhi2 == "") {
				$("#nhi2_error").text("Chưa nhập mã!");
				flag = false;
			} else if(giainhi2.match("[0-9]{5}") == null) {
				$("#nhi2_error").text("Mã không hợp lệ!");
				flag = false;
			} else {
				$("#nhi2_error").text("");
			}
			
			//Ba
			if(giaiba1 == "") {
				$("#ba1_error").text("Chưa nhập mã!");
				flag = false;
			} else if(giaiba1.match("[0-9]{5}") == null) {
				$("#ba1_error").text("Mã không hợp lệ!");
				flag = false;
			} else {
				$("#ba1_error").text("");
			}
			if(giaiba2 == "") {
				$("#ba2_error").text("Chưa nhập mã!");
				flag = false;
			} else if(giaiba2.match("[0-9]{5}") == null) {
				$("#ba2_error").text("Mã không hợp lệ!");
				flag = false;
			} else {
				$("#ba2_error").text("");
			}
			if(giaiba3 == "") {
				$("#ba3_error").text("Chưa nhập mã!");
				flag = false;
			} else if(giaiba3.match("[0-9]{5}") == null) {
				$("#ba3_error").text("Mã không hợp lệ!");
				flag = false;
			} else {
				$("#ba3_error").text("");
			}
			if(giaiba4 == "") {
				$("#ba4_error").text("Chưa nhập mã!");
				flag = false;
			} else if(giaiba4.match("[0-9]{5}") == null) {
				$("#ba4_error").text("Mã không hợp lệ!");
				flag = false;
			} else {
				$("#ba4_error").text("");
			}
			if(giaiba5 == "") {
				$("#ba5_error").text("Chưa nhập mã!");
				flag = false;
			} else if(giaiba5.match("[0-9]{5}") == null) {
				$("#ba5_error").text("Mã không hợp lệ!");
				flag = false;
			} else {
				$("#ba5_error").text("");
			}
			if(giaiba6 == "") {
				$("#ba6_error").text("Chưa nhập mã!");
				flag = false;
			} else if(giaiba6.match("[0-9]{5}") == null) {
				$("#ba6_error").text("Mã không hợp lệ!");
				flag = false;
			} else {
				$("#ba6_error").text("");
			}
			
			//Bốn
			if(giaibon1 == "") {
				$("#bon1_error").text("Chưa nhập mã!");
				flag = false;
			} else if(giaibon1.match("[0-9]{4}") == null) {
				$("#bon1_error").text("Mã không hợp lệ!");
				flag = false;
			} else {
				$("#bon1_error").text("");
			}
			if(giaibon2 == "") {
				$("#bon2_error").text("Chưa nhập mã!");
				flag = false;
			} else if(giaibon2.match("[0-9]{4}") == null) {
				$("#bon2_error").text("Mã không hợp lệ!");
				flag = false;
			} else {
				$("#bon2_error").text("");
			}
			if(giaibon3 == "") {
				$("#bon3_error").text("Chưa nhập mã!");
				flag = false;
			} else if(giaibon3.match("[0-9]{4}") == null) {
				$("#bon3_error").text("Mã không hợp lệ!");
				flag = false;
			} else {
				$("#bon3_error").text("");
			}
			if(giaibon4 == "") {
				$("#bon4_error").text("Chưa nhập mã!");
				flag = false;
			} else if(giaibon4.match("[0-9]{4}") == null) {
				$("#bon4_error").text("Mã không hợp lệ!");
				flag = false;
			} else {
				$("#bon4_error").text("");
			}
			$("#bon5_error").text("");
			$("#bon6_error").text("");
			$("#bon7_error").text("");
			
			//Năm
			if(giainam1 == "") {
				$("#nam1_error").text("Chưa nhập mã!");
				flag = false;
			} else if(giainam1.match("[0-9]{4}") == null) {
				$("#nam1_error").text("Mã không hợp lệ!");
				flag = false;
			} else {
				$("#nam1_error").text("");
			}
			if(giainam2 == "") {
				$("#nam2_error").text("Chưa nhập mã!");
				flag = false;
			} else if(giainam2.match("[0-9]{4}") == null) {
				$("#nam2_error").text("Mã không hợp lệ!");
				flag = false;
			} else {
				$("#nam2_error").text("");
			}
			if(giainam3 == "") {
				$("#nam3_error").text("Chưa nhập mã!");
				flag = false;
			} else if(giainam3.match("[0-9]{4}") == null) {
				$("#nam3_error").text("Mã không hợp lệ!");
				flag = false;
			} else {
				$("#nam3_error").text("");
			}
			if(giainam4 == "") {
				$("#nam4_error").text("Chưa nhập mã!");
				flag = false;
			} else if(giainam4.match("[0-9]{4}") == null) {
				$("#nam4_error").text("Mã không hợp lệ!");
				flag = false;
			} else {
				$("#nam4_error").text("");
			}
			if(giainam5 == "") {
				$("#nam5_error").text("Chưa nhập mã!");
				flag = false;
			} else if(giainam5.match("[0-9]{4}") == null) {
				$("#nam5_error").text("Mã không hợp lệ!");
				flag = false;
			} else {
				$("#nam5_error").text("");
			}
			if(giainam6 == "") {
				$("#nam6_error").text("Chưa nhập mã!");
				flag = false;
			} else if(giainam6.match("[0-9]{4}") == null) {
				$("#nam6_error").text("Mã không hợp lệ!");
				flag = false;
			} else {
				$("#nam6_error").text("");
			}
			
			//Sáu
			if(giaisau1 == "") {
				$("#sau1_error").text("Chưa nhập mã!");
				flag = false;
			} else if(giaisau1.match("[0-9]{3}") == null) {
				$("#sau1_error").text("Mã không hợp lệ!");
				flag = false;
			} else {
				$("#sau1_error").text("");
			}
			if(giaisau2 == "") {
				$("#sau2_error").text("Chưa nhập mã!");
				flag = false;
			} else if(giaisau2.match("[0-9]{3}") == null) {
				$("#sau2_error").text("Mã không hợp lệ!");
				flag = false;
			} else {
				$("#sau2_error").text("");
			}
			if(giaisau3 == "") {
				$("#sau3_error").text("Chưa nhập mã!");
				flag = false;
			} else if(giaisau3.match("[0-9]{3}") == null) {
				$("#sau3_error").text("Mã không hợp lệ!");
				flag = false;
			} else {
				$("#sau3_error").text("");
			}
			
			//Bảy
			if(giaibay1 == "") {
				$("#bay1_error").text("Chưa nhập mã!");
				flag = false;
			} else if(giaibay1.match("[0-9]{2}") == null) {
				$("#bay1_error").text("Mã không hợp lệ!");
				flag = false;
			} else {
				$("#bay1_error").text("");
			}
			if(giaibay2 == "") {
				$("#bay2_error").text("Chưa nhập mã!");
				flag = false;
			} else if(giaibay2.match("[0-9]{2}") == null) {
				$("#bay2_error").text("Mã không hợp lệ!");
				flag = false;
			} else {
				$("#bay2_error").text("");
			}
			if(giaibay3 == "") {
				$("#bay3_error").text("Chưa nhập mã!");
				flag = false;
			} else if(giaibay3.match("[0-9]{2}") == null) {
				$("#bay3_error").text("Mã không hợp lệ!");
				flag = false;
			} else {
				$("#bay3_error").text("");
			}
			if(giaibay4 == "") {
				$("#bay4_error").text("Chưa nhập mã!");
				flag = false;
			} else if(giaibay4.match("[0-9]{2}") == null) {
				$("#bay4_error").text("Mã không hợp lệ!");
				flag = false;
			} else {
				$("#bay4_error").text("");
			}
		} else {
			//Đặc biệt
			if(dacbiet == "") {
				$("#db_error").text("Chưa nhập mã!");
				flag = false;
			} else if(dacbiet.match("[0-9]{6}") == null) {
				$("#db_error").text("Mã không hợp lệ!");
				flag = false;
			} else {
				$("#db_error").text("");
			}
			
			//Nhì
			if(giainhi1 == "") {
				$("#nhi1_error").text("Chưa nhập mã!");
				flag = false;
			} else if(giainhi1.match("[0-9]{5}") == null) {
				$("#nhi1_error").text("Mã không hợp lệ!");
				flag = false;
			} else {
				$("#nhi1_error").text("");
			}
			$("#nhi2_error").text("");
			
			//Ba
			if(giaiba1 == "") {
				$("#ba1_error").text("Chưa nhập mã!");
				flag = false;
			} else if(giaiba1.match("[0-9]{5}") == null) {
				$("#ba1_error").text("Mã không hợp lệ!");
				flag = false;
			} else {
				$("#ba1_error").text("");
			}
			if(giaiba2 == "") {
				$("#ba2_error").text("Chưa nhập mã!");
				flag = false;
			} else if(giaiba2.match("[0-9]{5}") == null) {
				$("#ba2_error").text("Mã không hợp lệ!");
				flag = false;
			} else {
				$("#ba2_error").text("");
			}
			$("#ba4_error").text("");	
			$("#ba5_error").text("");
			
			//Bốn
			if(giaibon1 == "") {
				$("#bon1_error").text("Chưa nhập mã!");
				flag = false;
			} else if(giaibon1.match("[0-9]{5}") == null) {
				$("#bon1_error").text("Mã không hợp lệ!");
				flag = false;
			} else {
				$("#bon1_error").text("");
			}
			if(giaibon2 == "") {
				$("#bon2_error").text("Chưa nhập mã!");
				flag = false;
			} else if(giaibon2.match("[0-9]{5}") == null) {
				$("#bon2_error").text("Mã không hợp lệ!");
				flag = false;
			} else {
				$("#bon2_error").text("");
			}
			if(giaibon3 == "") {
				$("#bon3_error").text("Chưa nhập mã!");
				flag = false;
			} else if(giaibon3.match("[0-9]{5}") == null) {
				$("#bon3_error").text("Mã không hợp lệ!");
				flag = false;
			} else {
				$("#bon3_error").text("");
			}
			if(giaibon4 == "") {
				$("#bon4_error").text("Chưa nhập mã!");
				flag = false;
			} else if(giaibon4.match("[0-9]{5}") == null) {
				$("#bon4_error").text("Mã không hợp lệ!");
				flag = false;
			} else {
				$("#bon4_error").text("");
			}
			if(giaibon5 == "") {
				$("#bon5_error").text("Chưa nhập mã!");
				flag = false;
			} else if(giaibon5.match("[0-9]{5}") == null) {
				$("#bon5_error").text("Mã không hợp lệ!");
				flag = false;
			} else {
				$("#bon5_error").text("");
			}
			if(giaibon6 == "") {
				$("#bon6_error").text("Chưa nhập mã!");
				flag = false;
			} else if(giaibon6.match("[0-9]{5}") == null) {
				$("#bon6_error").text("Mã không hợp lệ!");
				flag = false;
			} else {
				$("#bon6_error").text("");
			}
			if(giaibon7 == "") {
				$("#bon7_error").text("Chưa nhập mã!");
				flag = false;
			} else if(giaibon7.match("[0-9]{5}") == null) {
				$("#bon7_error").text("Mã không hợp lệ!");
				flag = false;
			} else {
				$("#bon7_error").text("");
			}
			
			//Năm
			if(giainam1 == "") {
				$("#nam1_error").text("Chưa nhập mã!");
				flag = false;
			} else if(giainam1.match("[0-9]{4}") == null) {
				$("#nam1_error").text("Mã không hợp lệ!");
				flag = false;
			} else {
				$("#nam1_error").text("");
			}
			$("#nam2_error").text("");
			$("#nam3_error").text("");
			$("#nam4_error").text("");
			$("#nam5_error").text("");
			$("#nam6_error").text("");
			
			//Sáu
			if(giaisau1 == "") {
				$("#sau1_error").text("Chưa nhập mã!");
				flag = false;
			} else if(giaisau1.match("[0-9]{4}") == null) {
				$("#sau1_error").text("Mã không hợp lệ!");
				flag = false;
			} else {
				$("#sau1_error").text("");
			}
			if(giaisau2 == "") {
				$("#sau2_error").text("Chưa nhập mã!");
				flag = false;
			} else if(giaisau2.match("[0-9]{4}") == null) {
				$("#sau2_error").text("Mã không hợp lệ!");
				flag = false;
			} else {
				$("#sau2_error").text("");
			}
			if(giaisau3 == "") {
				$("#sau3_error").text("Chưa nhập mã!");
				flag = false;
			} else if(giaisau3.match("[0-9]{4}") == null) {
				$("#sau3_error").text("Mã không hợp lệ!");
				flag = false;
			} else {
				$("#sau3_error").text("");
			}
			
			//Bảy
			if(giaibay1 == "") {
				$("#bay1_error").text("Chưa nhập mã!");
				flag = false;
			} else if(giaibay1.match("[0-9]{3}") == null) {
				$("#bay1_error").text("Mã không hợp lệ!");
				flag = false;
			} else {
				$("#bay1_error").text("");
			}
			
			//Tám
			if(giaitam == "") {
				$("#tam_error").text("Chưa nhập mã!");
				flag = false;
			} else if(giaitam.match("[0-9]{2}") == null) {
				$("#tam_error").text("Mã không hợp lệ!");
				flag = false;
			} else {
				$("#tam_error").text("");
			}
		}
		return flag;
	});
	
	$(".tinh").hide();
	
	$("#mien").change(function() {
		$(".tinh").show();
		$("#giainhat").attr("placeholder", "5 chữ số 0-9");
		$("input[id*='giainhi']").attr("placeholder", "5 chữ số 0-9");
		$("input[id*='giaiba']").attr("placeholder", "5 chữ số 0-9");
		$("input[id*='giainam']").attr("placeholder", "4 chữ số 0-9");
		if($("#mien").val() == "Miền Bắc") {
			//Tỉnh
			$(".MB").show();
			$(".MT").hide();
			$(".MN").hide();
			
			//Đặc biệt
			$("#dacbiet").attr("placeholder", "5 chữ số 0-9");
			
			//Giải nhì
			$(".giainhi2").show();
			//bảng
			$("#nhi1").attr("colspan", "6");
			$("#nhi2").show();
			
			//Giải ba
			$(".giaiba14 #giaiba4").show();
			$(".giaiba25 #giaiba5").show();
			$(".giaiba36").show();
			$("div[class*='giaiba'] br").show();
			//bảng
			$("#ba14 .ba4").show();
			$("#ba25 .ba5").show();
			$("#ba36").show();
			$("#ba14").attr("colspan", "4");
			$("#ba25").attr("colspan", "4");
			
			//Giải bốn
			$(".giaibon25 #giaibon5").hide();
			$(".giaibon36 #giaibon6").hide();
			$(".giaibon47 #giaibon7").hide();
			$("div[class*='giaibon'] br").hide();
			$("input[id*='giaibon']").attr("placeholder", "4 chữ số 0-9");
			//bảng
			$("#bon5").hide();
			$("#bon6").hide();
			$("#bon7").hide();
			
			//Giai năm
			$(".giainam14 #giainam4").show();
			$(".giainam25").show();
			$(".giainam36").show();
			$("div[class*='giainam'] br").show();
			//bảng
			$("#nam14 .nam4").show();
			$("#nam25").show();
			$("#nam36").show();
			$("#nam14").attr("colspan", "4");
			
			//Giải sáu
			$("input[id*='giaisau']").attr("placeholder", "3 chữ số 0-9");
			
			//Giải bảy
			$(".giaibay2").show();
			$(".giaibay3").show();
			$(".giaibay4").show();
			$("input[id*='giaibay']").attr("placeholder", "2 chữ số 0-9");
			//bang
			$("#bay2").show();
			$("#bay3").show();
			$("#bay4").show();
			$("#bay1").attr("colspan", "3");
			
			//Giải tám
			$(".giaitam").hide();
			//bảng
			$("#tam").hide();
		} else {
			//Tỉnh
			if($("#mien").val() == "Miền Trung") {
				$(".MB").hide();
				$(".MT").show();
				$(".MN").hide();
			} else {
				$(".MB").hide();
				$(".MT").hide();
				$(".MN").show();
			}
			
			//Đặc biệt
			$("#dacbiet").attr("placeholder", "6 chữ số 0-9");
			
			//Giải nhì
			$(".giainhi2").hide();
			//bảng
			$("#nhi1").attr("colspan", "12");
			$("#nhi2").hide();
			
			//Giải ba
			$(".giaiba14 #giaiba4").hide();
			$(".giaiba25 #giaiba5").hide();
			$(".giaiba36").hide();
			$("div[class*='giaiba'] br").hide();
			//bảng
			$("#ba14 .ba4").hide();
			$("#ba25 .ba5").hide();
			$("#ba36").hide();
			$("#ba14").attr("colspan", "6");
			$("#ba25").attr("colspan", "6");
			
			//Giải bốn
			$(".giaibon25 #giaibon5").show();
			$(".giaibon36 #giaibon6").show();
			$(".giaibon47 #giaibon7").show();
			$("div[class*='giaibon'] br").show();
			$("input[id*='giaibon']").attr("placeholder", "5 chữ số 0-9");
			//bảng
			$("#bon5").show();
			$("#bon6").show();
			$("#bon7").show();
			
			//Giai năm
			$(".giainam14 #giainam4").hide();
			$(".giainam25").hide();
			$(".giainam36").hide();
			$("div[class*='giainam'] br").hide();
			//bảng
			$("#nam14 .nam4").hide();
			$("#nam25").hide();
			$("#nam36").hide();
			$("#nam14").attr("colspan", "12");
			
			//Giải sáu
			$("input[id*='giaisau']").attr("placeholder", "4 chữ số 0-9");
			
			//Giải bảy
			$(".giaibay2").hide();
			$(".giaibay3").hide();
			$(".giaibay4").hide();
			$("input[id*='giaibay']").attr("placeholder", "3 chữ số 0-9");
			//bang
			$("#bay2").hide();
			$("#bay3").hide();
			$("#bay4").hide();
			$("#bay1").attr("colspan", "12");
			
			//Giải tám
			$(".giaitam").show();
			$("#giaitam").attr("placeholder", "2 chữ số 0-9");
			//bảng
			$("#tam").show();
		}
	});
	
	//ngày, thứ
	$("#giaiNgay").change(function() {
		let date = new Date($("#giaiNgay").val());
		let ngay = date.getDate();
		if(ngay < 10) {
			ngay = "0" + ngay;
		}
		let thang = date.getMonth() + 1;
		if(thang < 10) {
			thang = "0" + thang;
		}
		$("#ngay").text(ngay + "-" + thang + "-" + date.getFullYear());
		let thu = date.getDay();
		switch(thu) {
			case 0: 
				thu = "Chủ Nhật"; 
				break;
			case 1: 
				thu = "Thứ Hai"; 
				break;
			case 2: 
				thu = "Thứ Ba"; 
				break;
			case 3: 
				thu = "Thứ Tư"; 
				break;
			case 4: 
				thu = "Thứ Năm"; 
				break;
			case 5: 
				thu = "Thứ Sáu"; 
				break;
			case 6: 
				thu = "Thứ Bảy";
		}
		$("#thu").text(thu);
	});
		
	//Tỉnh
	$("#seltinh").change(function() {
		$("#tinh").text($("#seltinh").val());
	});
	
	//Đặc biệt
	$("#dacbiet").change(function() {
		$("#db").text($("#dacbiet").val());
	});
	
	//Giải nhất
	$("#giainhat").change(function() {
		$("#nhat").text($("#giainhat").val());
	});
	
	//Giải nhì
	$("#giainhi1").change(function() {
		$("#nhi1").text($("#giainhi1").val());
	});
	$("#giainhi2").change(function() {
		$("#nhi2").text($("#giainhi2").val());
	});
	
	//Giải ba
	$("#giaiba1").change(function() {
		$(".ba1").text($("#giaiba1").val());
	});
	$("#giaiba2").change(function() {
		$(".ba2").text($("#giaiba2").val());
	});
	$("#giaiba3").change(function() {
		$(".ba3").text($("#giaiba3").val());
	});
	$("#giaiba4").change(function() {
		$(".ba4").text($("#giaiba4").val());
	});
	$("#giaiba5").change(function() {
		$(".ba5").text($("#giaiba5").val());
	});
	$("#giaiba6").change(function() {
		$(".ba6").text($("#giaiba6").val());
	});
	
	//Giải bon
	$("#giaibon1").change(function() {
		$("#bon1").text($("#giaibon1").val());
	});
	$("#giaibon2").change(function() {
		$("#bon2").text($("#giaibon2").val());
	});
	$("#giaibon3").change(function() {
		$("#bon3").text($("#giaibon3").val());
	});
	$("#giaibon4").change(function() {
		$("#bon4").text($("#giaibon4").val());
	});
	$("#giaibon5").change(function() {
		$("#bon5").text($("#giaibon5").val());
	});
	$("#giaibon6").change(function() {
		$("#bon6").text($("#giaibon6").val());
	});
	$("#giaibon7").change(function() {
		$("#bon7").text($("#giaibon7").val());
	});
	
	//Giải năm
	$("#giainam1").change(function() {
		$(".nam1").text($("#giainam1").val());
	});
	$("#giainam2").change(function() {
		$(".nam2").text($("#giainam2").val());
	});
	$("#giainam3").change(function() {
		$(".nam3").text($("#giainam3").val());
	});
	$("#giainam4").change(function() {
		$(".nam4").text($("#giainam4").val());
	});
	$("#giainam5").change(function() {
		$(".nam5").text($("#giainam5").val());
	});
	$("#giainam6").change(function() {
		$(".nam6").text($("#giainam6").val());
	});
	
	//Giải sáu
	$("#giaisau1").change(function() {
		$("#sau1").text($("#giaisau1").val());
	});
	$("#giaisau2").change(function() {
		$("#sau2").text($("#giaisau2").val());
	});
	$("#giaisau3").change(function() {
		$("#sau3").text($("#giaisau3").val());
	});
	
	//Giải bảy
	$("#giaibay1").change(function() {
		$("#bay1").text($("#giaibay1").val());
	});
	$("#giaibay2").change(function() {
		$("#bay2").text($("#giaibay2").val());
	});
	$("#giaibay3").change(function() {
		$("#bay3").text($("#giaibay3").val());
	});
	$("#giaibay4").change(function() {
		$("#bay4").text($("#giaibay4").val());
	});
	
	//Giải tám
	$("#giaitam").change(function() {
		$(".tam").text($("#giaitam").val());
	});
});