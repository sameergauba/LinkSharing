//alert("TEstOut");
$( document ).ready(function() {
//alert("TEst");
$('#register').submit(function(event){

	var firstName = document.getElementById("firstName");
	var lastName = document.getElementById("lastName");
	var email = document.getElementById("email");
	var userName = document.getElementById("userName");
	var password = document.getElementById("password_reg").value.length;
	//alert(password);
	var confirm_password = document.getElementById("confirm_password_reg");
	
	  if(firstName.value == "" )
	   {
	      alert( "Please provide your first name!" );
	      firstName.focus() ;
	      event.preventDefault();
	      //event.stopPropogation();
	      return false;
	   }
	   if( lastName.value == "" )
	   {
	      alert( "Please provide your last name!" );
	      lastName.focus() ;
	      event.preventDefault();
	     // event.stopPropogation();
	      return false;
	   }
	   if(email.value == "" )
	   {
	      alert( "Please provide your Email!" );
	      email.focus() ;
	      event.preventDefault();
	     // event.stopPropogation();
	      return false;
	   }
	   if(userName.value == "" )
	   {
	      alert( "Please provide a username!" );
	      userName.focus() ;
	      event.preventDefault();
	      //event.stopPropogation();
	      return false;
	   }
	   
	   //alert($("#userName").val().split(' '))
	   var a = $("#userName").val().split(' ');
	   //alert(a);
	   if(a.length>1){
		   alert( "No spaces are allowed in username!" );
		      userName.focus() ;
		      event.preventDefault();
		      //event.stopPropogation();
		      return false;
	   }
	   
	   if($("#password_reg").val().length == 0 )
	   {
	      alert( "Please fill the password!" );
	      password.focus() ;
	      event.preventDefault();
	      //event.stopPropogation();
	      return false;
	   }
	   if($("#confirm_password_reg").val().length == 0 )
	   {
	      alert( "Please confirm the password!" );
	     // confirm_password.focus() ;
	      event.preventDefault();
	    //  event.stopPropogation();
	      return false;
	   } 
	   /*var val1 = password.value;
	   var val2 = confirm_password.value;*/
	   if($("#password_reg").val() != $("#confirm_password_reg").val()){
	  	 alert( "passwords do not match!" );
	  	//password.focus() ;
	      event.preventDefault();
	//      event.stopPropogation();
	       return false; 
	   }
	   
	   return( true );
});

$("#forgot_pass").click(function() {
	var email = prompt("Enter your email address","");
	//alert(email);
	//alert(email!="");
	if(email!="")
	$.ajax({
		url : "/user/pass",
		data : "email=" + email ,
		success : function(data) {
			alert("Email sent to : " + email );
			//alert(data);
		}
	});
});
$(".view_full_site").click(function() {
	var url = $(this).attr('value');
	// alert(url);
	window.open(url);
});
});



/*var password = document.getElementById("password");
var confirm_password = document.getElementById("confirm_password");
function validatePassword(){
	
	
if(password.value != confirm_password.value) {
  confirm_password.setCustomValidity("Passwords Don't Match");
} else {
  confirm_password.setCustomValidity('');
}
}

password.onchange = validatePassword;
confirm_password.onkeyup = validatePassword;*/