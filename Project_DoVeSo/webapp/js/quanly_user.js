/**
 * 
 */
 
 $(document).ready(function() {
	$("#alert_succes").fadeOut(5000);
	
	//check all or check unall
	$("#check_all").change(function() {
		if ($('#check_all').is(":checked")) {
			$("input[id*='check']").attr("checked", true);
			console.log('true');
		} else {
			$("input[id*='check']").attr("checked", false);
			console.log('false');
		}
	});
	
	//xoa
	$("#form_xoa").hide();
	$("#form_reset").hide();
	$("input[id*='check']").change(function() {
		if($("input[id*='check']").is(":checked")) {
			$("#form_xoa").show();
			$("#form_reset").show();
		} else {
			$("#form_xoa").hide();
			$("#form_reset").hide();
		}
	});
	
	$("#form_xoa").submit(function() {
		$("input[id*='check']").attr("form", "form_xoa");
	});
	
	$("#form_reset").submit(function() {
		$("input[id*='check']").attr("form", "form_reset");
	});
	
});