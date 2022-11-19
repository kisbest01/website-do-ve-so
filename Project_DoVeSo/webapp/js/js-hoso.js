/**
 * 
 */
 $(document).ready(function() {
	
	$("#alert").fadeOut(5000);
	$("#avatar").hide();
	
	$("#chon").click(function() {
		$("#avatar").click();
	});
	
	$("#avatar").change(function(e) {
		// Khởi tạo đối tượng FileReader
		const reader = new FileReader();
		const fileTypes = ['image/png', 'image/jpeg'];
	    const maxSize = 30 * 1024 * 1024; // 30MB
	    
		console.log(e.target.files[0]);
		
		const file  = e.target.files[0];
		
		// Validate file type
        if (fileTypes.indexOf(file.type) == -1) {
            alert("File không hợp lệ (chỉ file hình jpg và hình png được chấp nhận)");
            return;
        }
        
        // Validate file size
        if (file.size > maxSize) {
            alert("Dung lượng file vượt quá giới hạn (tối đa 30MB được chấp nhận)");
            return;
        }

		// Đọc thông tin tập tin đã được đăng tải
		reader.readAsDataURL(file);
		
		// Lắng nghe quá trình đọc tập tin hoàn thành
		reader.addEventListener("load", (event) => {
			// Lấy chuỗi Binary thông tin hình ảnh
			const url = event.target.result;
			
			// Thực hiện hành động gì đó, có thể append chuỗi giá trị này vào thẻ IMG
			console.log(url) // data:image/jpeg;base64,/9j/4AAQSkZJRgABAQEASABIAA........
			$("#image").attr("src", url);
		});
	});

	$("#capnhat_user").submit(function() {
		
		let them = true;
		
		const validRegex = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
		let email = $("#email").val();
		if(email == "") {
			$("#error_email").text("Vui lòng nhập email của người dùng!");
			them = false;
		} else if(email.length > 30) {
			$("#error_email").text("Vui lòng nhập dưới 30 kí tự!");
			them = false;
		} else if(!validRegex.test(email)) {
			$("#error_email").text("Email chưa hợp lệ!");
			them = false;
		} else {
			$("#error_email").text("");
		}
		
		let ten = $("#ten").val();
		if(ten == "") {
			$("#error_ten").text("Vui lòng nhập vào trường này!");
			them = false;
		} else if(ten.length > 30) {
			$("#error_ten").text("Vui lòng nhập dưới 30 kí tự!");
			them = false;
		} else {
			$("#error_ten").text("");
		}
		
		/* 	Viettel: 09, 03
			MobiFone: 09, 07
			VinaPhone: 09, 08
			Vietnamobile và Gmobile: 09, 05 */
		const vnf_regex = /((09|03|07|08|05)+([0-9]{8})\b)/g;
		let phone = $("#phone").val();
		if(phone == "") {
			$("#error_phone").text("Vui lòng nhập vào trường này!");
			them = false;
		} else if(!vnf_regex.test(phone)) {
			$("#error_phone").text("Số điện thoại chưa hợp lệ!");
			them = false;
		} else {
			$("#error_phone").text("");
		}
		
		let address = $("#address").val();
		if(address == "") {
			$("#error_address").text("Vui lòng nhập vào trường này!");
			them = false;
		} else if(address.length > 300) {
			$("#error_address").text("Vui lòng nhập dưới 300 kí tự!");
			them = false;
		} else {
			$("#error_address").text("");
		}
		
		const vali_username = /^[a-zA-Z0-9]+$/;
		let username = $("#username").val();
		if(username == "") {
			$("#error_username").text("Vui lòng nhập username của người dùng!");
			them = false;
		} else if(username.length > 30) {
			$("#error_username").text("Vui lòng nhập dưới 30 kí tự!");
			them = false;
		} else if(!vali_username.test(username)) {
			$("#error_username").text("Username chưa hợp lệ!");
			them = false;
		} else {
			$("#error_username").text("");
		}
			
		console.log(them + " " + ten + " " + email + " " + address  + " "  + phone + " " + username  + " " + " id= " + $("#userid").val());
		return them;
	});
});