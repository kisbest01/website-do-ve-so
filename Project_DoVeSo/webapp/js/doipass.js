/**
 * 
 */
 $(document).ready(function() {
	$("#alert_succes").fadeOut(5000);
	$("#doipass").submit(function() {
		const valid_pass = /(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@!#$%&]).{8,}/;
		
		let them = true;
		
		let cupass = $("#cupass").val();
		if(cupass == '') {
			$("#error_cupass").text('Vui lòng nhập vào trường này!');
			them = false;
		} else if(!valid_pass.test(cupass)) {
			$("#error_cupass").text('Password chưa hợp lệ!');
			them = false;
		} else {
			$("#error_cupass").text('');
		}
		
		let newpass = $("#newpass").val();
		if(newpass == '') {
			$("#error_newpass").text('Vui lòng nhập vào trường này!');
			them = false;
		} else if(!valid_pass.test(newpass)) {
			$("#error_newpass").text('Password chưa hợp lệ!');
			them = false;
		} else {
			$("#error_newpass").text('');
		}
		
		let repass = $("#repass").val();
		if(repass == '') {
			$("#error_repass").text('Vui lòng nhập vào trường này!');
			them = false;
		} else if(repass != newpass) {
			$("#error_repass").text('Password chưa đúng!');
			them = false;
		} else {
			$("#error_repass").text('');
		}
		return them;
	});
	
	$("#yeu").hide();
	$("#tb").hide();
	$("#manh").hide();
	$("#newpass").keydown(function() {
		let newpass = $("#newpass").val();
		if(newpass.length <= 8) {
			$("#yeu").show();
			$("#tb").hide();
			$("#manh").hide();
		} else if(newpass.length < 15) {
			$("#yeu").hide();
			$("#tb").show();
			$("#manh").hide();
		} else {
			$("#yeu").hide();
			$("#tb").hide();
			$("#manh").show();
		}
	});
});