$(document).ready(function(){
	$(".rate_clicked").click(function(){
		var resourceId=$(this).attr("resource");
		var value=$(this).val();
		$.ajax({
			type:"POST",
			url:"/resource/update/rating",
			data:"resourceId="+resourceId+"&score="+value,
            success:function(data){
            	if(data=="Done")
            		alert(data);
            	
            	if(data.indexOf("html")>-1){
					//alert(data);
					//window.location.href = "/";
					document.open();
				    document.write(data);
				    document.close();
				} 
            	
            }
		});
	});
	$(".delete_resource").click(function(){
		var resourceId=$(this).attr("id");
		$.ajax({
			type:"POST",
			url:"/resource/delete/post",
			data:"resourceId="+resourceId,
            success:function(data){
            	window.location.replace("/user/login");
            }
		});
	});
		
	
	$("#edit_resource").click(function(){
		$("#change_description").show();
		$("#real_description").hide();
	});
	$(".cancel_changed_name").click(function(){
		$("#change_description").val("");
		$("#change_description").hide();
		$("#real_description").show();
	});
	$(".save_changed_name").click(function(){
		//alert("in");
		var description=$("#changed_input").val();
		if(description==""){
			alert("description can not be empty.");
		}else{
		var id=$(this).attr("id");
		$.ajax({
			type:"POST",
			url:"/resource/update/post",
			data:"resourceId="+id+"&description="+description,
            success:function(data){
            	if(data=="Done"){
            	$("#change_description").val("");
            	$("#real_description").text(description);
            	$("#change_description").hide();
        		$("#real_description").show();
            }
            	
            	if(data.indexOf("html")>-1){
					//alert(data);
					//window.location.href = "/";
					document.open();
				    document.write(data);
				    document.close();
				} 
            	
            }
		});
		}
	});
});