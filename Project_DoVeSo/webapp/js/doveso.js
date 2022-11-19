/**
 * 
 */
 $(document).ready(function() {
	$("#formdove").submit(function() {
		let giaingay = $("#giaiNgay").val();
		let mave = $("#mave").val();
		let tinh = $("#tinh").val();
		
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
		if(mave == "") {
			$("#mave_error").text("Vui lòng nhập mã số vé dò!");
			flag = false;
		} else if(mave.match("^\\d{6}$") == null) {
			$("#mave_error").text("Mã số vé dò chưa hợp lệ!");
			flag = false;
		} else {
			$("#mave_error").text("");
		}
		 
		 //Tỉnh
		 if(tinh == "") {
			$("#tinh_error").text("Vui lòng chọn tỉnh!");
			flag = false;
		} else {
			$("#tinh_error").text("");
		}
		
		return flag;
	});
});