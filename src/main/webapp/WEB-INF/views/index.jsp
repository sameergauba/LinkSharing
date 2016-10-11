<%@page import="com.ttnd.linksharing.dto.UserDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<script src="https://code.jquery.com/jquery-2.2.2.js"
	integrity="sha256-4/zUCqiq0kqxhZIyp4G0Gk+AOtCJsY1TA00k5ClsZYE="
	crossorigin="anonymous"></script>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"
	integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">
<!-- Optional theme -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css"
	integrity="sha384-fLW2N01lMqjakBkx3l/M9EahuwpSfeNvV63J5ezn3uZzapT0u7EYsXMjQV+0En5r"
	crossorigin="anonymous">


<script type="text/javascript"
	src="<c:url value="/theme1/js/validation.js" />"></script>

<!-- <script type="text/javascript">

var password = document.getElementById("password");
var confirm_password = document.getElementById("confirm_password");
//alert("hi");
function validatePassword(){
if(password.value != confirm_password.value) {
  confirm_password.setCustomValidity("Passwords Don't Match");
} else {
  confirm_password.setCustomValidity('');
}
}

password.onchange = validatePassword;
confirm_password.onkeyup = validatePassword;


</script> -->



<!-- Latest compiled and minified JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"
	integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS"
	crossorigin="anonymous"></script>
</head>
<body>

	<div class="container-fluid">
		<nav class="navbar navbar-default">

		<div class="navbar-header">
			<a class="navbar-brand" href="#"> Link Sharing </a>
		</div>
		<%-- <form class="navbar-form pull-right" role="search">
			<div class="form-group has-feedback has-feeback-left">
				<input type="text" class="form-control" placeholder="search">
				<i class="glyphicon glyphicon-search form-control-feedback"></i>
			</div>
		</form> --%> </nav>
	</div>
	<!------------------------------------------------------------------------------------------------------->

	<div style="background: yellow">${pageMsg}</div>
	<div class="container">
		<div class="row">
			<div class="container-fluid col-md-8 pull-left">
				<div class="panel panel-default">
					<div class="panel-heading">
						<span class="panel-title">Recent Shares</span>
					</div>
					<div id="" style="overflow: scroll; height: 500px;">
						<div class="panel-body">
							<c:forEach items="${recentResources}" var="res">
								<div class=""
									style="border: 2px solid black; margin-bottom: 5px">

									<div class="row">
										<div class="col-sm-3">
											<img class="img-thumbnail img-responsive"
												src="/profiles/${res.createdBy.userName}/ProfilePic"
												height=125px width=100px>
										</div>
										<div class="col-sm-9">
											<div class="row">
												<span class="col-sm-4"><a href="#">${res.createdBy.firstName}
														${res.createdBy.lastName}</a></span><span class="col-sm-4 text-muted">@${res.createdBy.userName}</span><span
													class="col-sm-2" style="color: blue"><a href="#">Java</a></span>
											</div>
											<div class="row">
												<p>${res.description}</p>
											</div>
											<div class="row">
												<!-- <span class="col-sm-1 pull-left"> <a href="#"><div
													class="fa fa-facebook-official"></div></a></span> <span
											class="col-sm-1 pull-left"><a href="#"><div
													class="fa fa-twitter inline"></div></a></span> <span
											class="col-sm-1 pull-left"><a href="#"><div
													class="fa fa-google-plus inline"></div></a></span>  -->
												<c:if test="${res.type=='link'}">
													<span class="pull-right" style="margin-right: 15px"><a
														class="view_full_site" value="${res.path}" href="">view
															full site </a></span>
												</c:if>
												<c:if test="${res.type=='document'}">
													<span class="pull-right" style="margin-right: 15px"><a
														class="document_down" value="${res.id}"
														href="/resource/download/document/${res.id}">download </a></span>
												</c:if>
											</div>
										</div>
									</div>

								</div>
							</c:forEach>

						</div>


					</div>


				</div>

				<div class="panel panel-default">
					<div class="panel-heading">
						<span class="panel-title">top Posts</span>
					</div>
					<div id="" style="overflow: scroll; height: 500px;">
						<div class="panel-body">
							<c:forEach items="${topPosts}" var="post">
								<div style="border: 2px solid black; margin-bottom: 5px">
									<div class="row">
										<div class="col-sm-3">
											<img class="img-thumbnail img-responsive"
												src="/profiles/${post.createdBy.userName}/ProfilePic"
												height=125px width=100px>
										</div>
										<div class="col-sm-9">
											<div class="row">
												<span class="col-sm-4"><a href="#">${post.createdBy.firstName}
														${post.createdBy.lastName}</a></span><span
													class="col-sm-4 text-muted">@${post.createdBy.userName}
													</span><span class="col-sm-2" style="color: blue"><a
													href="#">${post.topic.name}</a></span>
											</div>
											<div class="row">
												<p>${post.description}</p>
											</div>
											<!-- <div class="row">
												<span class="col-sm-1 pull-left"> <a href="#"><div
															class="fa fa-facebook-official"></div></a></span> <span
													class="col-sm-1 pull-left"><a href="#"><div
															class="fa fa-twitter inline"></div></a></span> <span
													class="col-sm-1 pull-left"><a href="#"><div
															class="fa fa-google-plus inline"></div></a></span> <span
													class="col-sm-4 pull-right"><a href="#">view
														post</a></span>
											</div> -->
										</div>
									</div>
								</div>
							</c:forEach>
						</div>
					</div>
					</div>
					<!-- <div class="panel panel-default">
					<div class="panel-heading">
						<span class="panel-title" col-xs-3 pull-left>Top posts</span>
						<span
							class="form-group col-xs-3 pull-right"> <select
							class="form-control" id="sel1">
								<option>Today</option>
								<option>1 Week</option>
								<option>1 Month</option>
								<option>1 Year</option>
						</select>
						</span>
					</div>
					<div class="panel-body">
						<div class="well">

							<div class="row">
								<div class="col-sm-3">
									<img class="img-thumbnail img-responsive"
										src="http://gurucul.com/wp-content/uploads/2015/01/default-user-icon-profile.png"
										height=125px width=100px>
								</div>
								<div class="col-sm-9">
									<div class="row">
										<span class="col-sm-4"><a href="#">Sameer Gauba</a></span><span
											class="col-sm-4 text-muted">@sameer 5min</span><span
											class="col-sm-2"><a href="#">Java</a></span>
									</div>
									<div class="row">
										<p>Lorem ipsum dolor sit amet, consectetur adipiscing
											elit. Nulla quam velit, vulputate nec, mattis ac neque. Duis
										</p>
									</div>
									<div class="row">
										<span class="col-sm-1 pull-left"> <a href="#"><div
													class="fa fa-facebook-official"></div></a></span> <span
											class="col-sm-1 pull-left"><a href="#"><div
													class="fa fa-twitter inline"></div></a></span> <span
											class="col-sm-1 pull-left"><a href="#"><div
													class="fa fa-google-plus inline"></div></a></span> <span
											class="col-sm-4 pull-right"><a href="#">view post</a></span>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div> -->
				</div>
				<div class="container-fluid col-md-4">
					<div class="panel panel-default">
						<div class="panel-heading">
							<span class="panel-title">Login</span>
						</div>
						<div class="panel-body">
							<form class="form-horizontal" role="form" action="/user/login" method="post" >
								<div style="background: yellow">${loginError}${activeError}</div>
								<div class="form-group">
									<label class="control-label col-sm-2"> Username </label>
									<div class="col-sm-8 pull-right">
										<input type="text" class="form-control" id="user"
											name="username">
									</div>
								</div>
								<div class="form-group">
									<label class="control-label col-sm-2"> Password </label>
									<div class="col-sm-8 pull-right">
										<input type="password" class="form-control" name="password">
									</div>
								</div>
								<div class=form-group">
									<a href="#" class="col-sm-6" id="forgot_pass"
										value="forgot_pass_value">Forgot Password?</a> <input
										type="submit" value="Login"
										class="btn btn-default col-sm-2 pull-right">
								</div>
							</form>
						</div>
					</div>
					<div class="panel panel-default">
						<div class="panel-heading">
							<span class="panel-title">Register</span>
						</div>
						<div class="panel-body">


							<form:form class="form-horizontal" role="form" id="register"
								commandName="user" onsubmit="validate();" modelAttribute="user"
								action="/user/register" enctype="multipart/form-data">
								<%--  <form:errors path="*" /> --%>

								<!---------------------------------Firstname------------------------------->
								<div class="form-group">
									<label class="control-label col-sm-2"> Firstname </label>
									<div class="col-sm-8 pull-right">
										<form:input type="text" class="form-control" path="firstName"
											id="firstName" />
									</div>
								</div>
								<!--------------------------------Lastname--------------------------------->
								<div class="form-group">
									<label class="control-label col-sm-2"> Lastname </label>
									<div class="col-sm-8 pull-right">
										<form:input type="text" class="form-control" path="lastName"
											id="lastName" />
									</div>
								</div>
								<!--------------------------------Email--------------------------------->
								<div class="form-group">
									<label class="control-label col-sm-2"> Email </label>
									<div class="col-sm-8 pull-right">
										<form:input type="email" class="form-control" path="email"
											id="email" />
										<form:errors path="email" />
									</div>
								</div>
								<!--------------------------------Username--------------------------------->
								<div class="form-group">
									<label class="control-label col-sm-2"> Username </label>
									<div class="col-sm-8 pull-right">
										<form:input type="text" class="form-control" path="userName"
											id="userName" />
										<form:errors path="userName" />
									</div>
								</div>
								<!--------------------------------Password--------------------------------->
								<div class="form-group">
									<label class="control-label col-sm-2"> Password </label>
									<div class="col-sm-8 pull-right">
										<form:input type="password" class="form-control"
											path="password" id="password_reg" />
									</div>
								</div>
								<!--------------------------------con Password--------------------------------->
								<div class="form-group">
									<label class="control-label col-sm-2"> Confirm Password
									</label>
									<div class="col-sm-8 pull-right">
										<form:input type="password" class="form-control"
											path="conPass" id="confirm_password_reg" />
									</div>
								</div>
								<!--------------------------------Upload--------------------------------->
								<div class="form-group">
									<label class="control-label col-sm-2"> Photo </label>
									<div class="col-sm-8 pull-right">
										<input type="file" class="filestyle" name="photo" />
									</div>
								</div>
								<!--------------------------------Register btn--------------------------------->
								<div class="col-sm-4  pull-right">
									<input type="submit" value="Register"
										class="form-control btn btn-default" />
								</div>

							</form:form>
						</div>
					</div>
				</div>
			</div>
		</div>
</body>
</html>



