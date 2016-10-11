$(document).ready(function() {
	
	$(".edit_topic_name").click(function() {
		var id = $(this).attr('value');
		$("#" + id + "_real").hide();
		$("#" + id).show();

	});
	$(".topic_change_cancel").click(function() {
		var id = $(this).attr('value');
		//alert(id);
		$("#" + id + "_real").show();
		$("#" + id).hide();

	});
	$(".topic_save").click(function() {
		var id = $(this).attr('value');
		var topic_name = $("#text_" + id).val();
		// alert(topic_name);
		$.ajax({
			url : "/topic/edit",
			data : "topicName=" + topic_name + "&topicId=" + id,
			success : function(data) {
				if(data=="Done"){
				$("#subs__" + id + "_real_topic_label").html(topic_name);
				$("#subs__" + id + "_real").show();
				$("#subs__" + id).hide();
				$("#text_" + id).val("");
				}
				
				if(data.indexOf("html")>-1){
					//alert(data);
					//window.location.href = "/";
					document.open();
				    document.write(data);
				    document.close();
				} 
				//alert(data);
			}
		});
	});
	
	/*$(".document_down").click(function() {
		var id = $(this).attr('value');
		//var topic_name = $("#text_" + id).val();
		// alert(topic_name);
		alert(id);
		$.ajax({
			url : "/resource/download/document/",
			data : "resourceId=" + id,
			success : function(data) {
				alert(data);
			}
		});
	});*/

	/*
	 * $(".delete_topic_sub").click(function(){ var id=$(this).attr('value');
	 * var topic_sub = $("#li_"+id); //alert(id); $.ajax({ url :
	 * "/topic/delete", data:"topicId="+id, success : function(data) {
	 * if(data=="notdone"){ alert("Not deleted, Connection problem"); } else{
	 * topic_sub.empty(); topic_sub.remove(); } } });
	 */
	$(".view_full_site").click(function() {
		var url = $(this).attr('value');
		// alert(url);
		window.open(url);
	});

	$("#save_invitation").click(function() {
		var id = $(this).attr('value');
		var topic_id = $('#' + id + '_topic').val();
		var email = $('#' + id + '_email').val();
		/*alert(topic_id);
		alert(email);*/
		$.ajax({
			url : "/topic/request",
			data : "topicId=" + topic_id + "&email=" + email,
			success : function(data) {
				if (data == "Done") {
					// alert("Not deleted, Connection problem");
					//$("#myInvite").hide();
					$("#save_invitation_close").click();
				} else {
					
					//$("#myInvite").hide();
				if(data.indexOf("html")>-1){
					//alert(data);
					//window.location.href = "/";
					document.open();
				    document.write(data);
				    document.close();
				} else{
					alert("Some error occured. Email not sent.");
				}
				
				}

			}
		});
	});

	$(".mark_as_read").click(function() {
		var id = $(this).attr('value');
		//alert(id);
		/*
		 * var topic_id = $('#'+id+'_topic').val(); var email =
		 * $('#'+id+'_email').val();
		 */
		//alert(id);
		// alert(email);
		$.ajax({
			url : "/resource/mark",
			data : "resourceId=" + id,
			success : function(data) {
				if (data == "Done") {
					// alert("Not deleted, Connection problem");
					$("#"+id+"_post").hide();
				} else {
					//$("#myInvite").hide();
				if(data.indexOf("html")>-1){
					//alert(data);
					//window.location.href = "/";
					document.open();
				    document.write(data);
				    document.close();
				}else{
					alert("Some error occured. Post not marked as read.");
				}
				}
			}
		});
	});
	
	$(".serious_select").change(function() {
		
		var value = $(this).attr('value');
		var id = $(this).attr('val')
		/*alert(id);
		alert(value);*/
		
		
		$.ajax({
			url : "/subscription/serious",
			data : "serious=" + value + "&subscriptionId="+id,
			success : function(data) {
				/*if(data.redirect){
					 window.location.href = data.redirect;
				}*/
				if (data == "Done") {
					window.location.replace("/");
					// alert("Not deleted, Connection problem");
					//$("#"+id+"_post").hide();
					//alert("done");
				}else {
					
				
				if(data.indexOf("html")>-1){
					//alert(data);
					//window.location.href = "/";
					document.open();
				    document.write(data);
				    document.close();
				}else{
					alert("Some error occured. Seriousness is not changed.");
				} 
				}
				
			//	alert(data);

			}
		});
	});
	
$(".private_select").change(function() {
		
		var value = $(this).attr('value');
		var id = $(this).attr('val')
		/*alert(id);
		alert(value);*/
		
		
		$.ajax({
			url : "/subscription/private",
			data : "visibility=" + value + "&topicId="+id,
			success : function(data) {
				if (data == "Done") {
					// alert("Not deleted, Connection problem");
					//$("#"+id+"_post").hide();
					//alert("done");
				} else {
					
				
				if(data.indexOf("html")>-1){
					//alert(data);
					//window.location.href = "/";
					document.open();
				    document.write(data);
				    document.close();
				}else{
					alert("Some error occured. Visibility not changed.");
				} 
				}

			}
		});
	});
	
$('#search_form').submit(function(event) {
	
	var text = $('#search_box').val();
	
	$.ajax({
		url : "/user/search",
		data : "search=" + text,
		success : function(data) {
			if (data == "Done") {
				// alert("Not deleted, Connection problem");
				//$("#"+id+"_post").hide();
				//alert("done");
			} else {
				
			
			if(data.indexOf("div")>-1){
				//alert(data);
				//window.location.href = "/";
				//alert("inside");
				$('#result_div').html(data);
				
				/*document.open();
			    document.write(data);
			    document.close();*/
			}else{
				alert("Some error occured. Visibility not changed.");
			} 
			}

		}
	});
	event.preventDefault();
});
	

});

/*
 * function view_full(url){ alert(url); window.open(url); }
 */
/*
 * function subs(topic_name){ alert(topic_name); console.log(topic_name);
 * $.ajax({ type : "POST", contentType : "application/json", url :
 * "/ajax/subscribe/{topicName}", timeout : 100000, success : function(data) {
 * console.log("SUCCESS: ", data); }, error : function(e) { console.log("ERROR: ",
 * e); }, done : function(e) { console.log("DONE"); } });
 *  }
 */
