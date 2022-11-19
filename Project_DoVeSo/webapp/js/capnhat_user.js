/**
 * 
 */
 $(document).ready(function() {
	$("#alert_succes").fadeOut(5000);
	
	$("#them_user").submit(function() {
		
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
		const valid_pass = /(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@!#$%&]).{8,}/;
		let pass = $("#pass").val();
		if($("#userid").val() == "" || $("#userid").val() == 0) {
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
			
			if(pass == "") {
				$("#error_pass").text("Vui lòng nhập vào trường này!");
				them = false;
			} else if(!valid_pass.test(pass)) {
				$("#error_pass").text("Password chưa hợp lệ!");
				them = false;
			} else {
				$("#error_pass").text("");
			}
		} 
		console.log(them + " " + ten + " " + email + " " + address  + " "  + phone + " " + username  + " " + pass + " id= " + $("#userid").val());
		return them;
	});
});