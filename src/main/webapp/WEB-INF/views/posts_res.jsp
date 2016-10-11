<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"
	integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7"
	crossorigin="anonymous">
<link rel="stylesheet" href="<c:url value="/theme1/css/posts.css" />" />
<script src="<c:url value="/theme1/js/posts.js" />" type="text/javascript"></script>
<!-- Optional theme -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css"
	integrity="sha384-fLW2N01lMqjakBkx3l/M9EahuwpSfeNvV63J5ezn3uZzapT0u7EYsXMjQV+0En5r"
	crossorigin="anonymous">

	
	<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">
	
<!-- Latest compiled and minified JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"
	integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS"
	crossorigin="anonymous"></script>
</head>
<body>
	<!-- --------------------------------UPPER BAR---------------------------------------------- -->
	<div class="container-fluid">
		<nav class="navbar navbar-default">

			<div class="navbar-header">
				<a class="navbar-brand" href="/user/login"> Link Sharing </a>
			</div>
			<ul class="nav navbar-nav pull-right">
				<!-- <li><a href="/user/login"><span
						class="glyphicon glyphicon-home" style="font-size: 20px"></span></a></li> -->
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown" role="button" aria-haspopup="true"
					aria-expanded="false"><span class="glyphicon glyphicon-user"
						style="font-size: 20px">${user.firstName}</span> <span
						class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="/user/logout">Logout</a></li>
					</ul></li>
			</ul>
			<%-- <form class="navbar-form pull-right" role="search">
				<div class="form-group has-feedback has-feeback-left">
					<input type="text" class="form-control" placeholder="search">
					<i class="glyphicon glyphicon-search form-control-feedback"></i>
				</div>
			</form> --%>


		</nav>
	</div>
	<!------------------------------------------------------------------------------------------->




	<!-- -------------------------------TRENDING TOPICS------------------------------------------------- -->
	<div class="container-fluid col-md-6 pull-right"
		style="height: 500px; overflow: auto">
		<div class="panel panel-default">
			<div class="panel-heading">
				<span class="panel-title">Trending Topics</span>
			</div>
			
			<ul class="list-group">
			<div id="" style="overflow: scroll; height: 400px;">
				<c:forEach items="${trendingTopics}" var="trends">
					<li class="list-group-item">
						<div class="row" style="margin-bottom: 5px">
							<div class="col-sm-3">
								<%-- <c:set var="photoLocationParts" value="${fn:split(trends.userDto.photoPath, '/')}" />
         		<c:set var="photoLocation" value="${fn:length(photoLocationParts)}" />
         		<c:set var="photoLocation" value="${photoLocation - 1}" /> --%>
								<img class="img-thumbnail img-responsive pull-left"
									src="<c:url value="/profiles/${trends.createdBy.userName}/ProfilePic" />"
									height=75px width=75px>
							</div>
							<div class="col-sm-9">
								<div class="row">
									<span class="col-sm-4 pull-left"><a href="#">${trends.name}</a></span>
									<span class="col-sm-4 text-muted">@${trends.createdBy.userName}</span>
								</div>
								<div class="row">
									<span class="col-sm-4 text-muted">Subscriptions</span> <span
										class="col-sm-4 text-muted pull-right">Posts</span>
								</div>
								<div class="row">
									<span class="col-sm-4"><a href="#">${trends.subsSize}</a></span>
									<span class="col-sm-4 pull-right"><a href="#">${trends.postsSize}</a></span>
								</div>
								<div class="row">
									<span class="col-sm-4"><a
										href="/subscription/subscribe/${trends.id}">Subscribe</a></span>
								</div>
							</div>
						</div>
					</li>
				</c:forEach>
				</div>
				</ul>
		</div>
	</div>
	<!-------------------------------------------------------------Posts------------------------------------------->
	<div class="container-fluid col-md-6 pull-left">
		<div class="panel panel-default">
			<div class="panel-heading">
				<span class="panel-title">Post</span>
			</div>
			<div class="panel-body">
				<div class="well">

					<div class="row">
						<div class="col-sm-3">
							<%--  <c:set var="photoLocationParts" value="${fn:split(Resource.createdBy.photoPath, '/')}" />
         		<c:set var="photoLocation" value="${fn:length(photoLocationParts)}" />
         		<c:set var="photoLocation" value="${photoLocation - 1}" /> --%>
							<img class="img-thumbnail img-responsive"
								src="<c:url value="/profiles/${resource.createdBy.userName}/ProfilePic" />"
								height=125px width=100px>
						</div>
						<div class="col-sm-9">
							<div class="row">

								<span class="col-sm-4"><a href="#">${resource.createdBy.firstName}
										${resource.createdBy.lastName}</a></span><span
									class="col-sm-4 text-muted">@${resource.createdBy.userName}</span><span
									class="col-sm-2" style="color: blue"><a href="#">${Resource.topicDto.name}</a></span>
							</div>
							<div class="row">
								<p id="real_description">${resource.description}</p>
								<div class="row" id="change_description" style="display: none;">
									<span class="pull-left form-group"> <span
										class="col-sm-5"> <input type="text"
											class="form-control" id="changed_input"></span> <span
										class="col-sm-3">
											<button type="button"
												class="btn btn-default save_changed_name"
												id="${resource.id}">Save</button>
									</span> <span class="col-sm-3">
											<button type="button"
												class="btn btn-default cancel_changed_name">Cancel</button>
									</span>
									</span>
								</div>
							</div>
							<!-- ---------------------RATING---------------------------- -->
							<div class="acidjs-rating-stars">
								<form>
									<input type="radio" class="rate_clicked"
										resource="${resource.id}" name="group-1"
										<c:if test="${resource.rating eq 5}">checked</c:if>
										id="group-1-0" value="5" /><label for="group-1-0"></label>
									<!--
        -->
									<input type="radio" class="rate_clicked"
										resource="${resource.id}" name="group-1"
										<c:if test="${resource.rating eq 4}">checked</c:if>
										id="group-1-1" value="4" /><label for="group-1-1"></label>
									<!--
        -->
									<input type="radio" class="rate_clicked"
										resource="${resource.id}" name="group-1"
										<c:if test="${resource.rating eq 3}">checked</c:if>
										id="group-1-2" value="3" /><label for="group-1-2"></label>
									<!--
        -->
									<input type="radio" class="rate_clicked"
										resource="${resource.id}" name="group-1"
										<c:if test="${resource.rating eq 2}">checked</c:if>
										id="group-1-3" value="2" /><label for="group-1-3"></label>
									<!--
        -->
									<input type="radio" class="rate_clicked"
										resource="${resource.id}" name="group-1"
										<c:if test="${resource.rating eq 1}">checked</c:if>
										id="group-1-4" value="1" /><label for="group-1-4"></label>
								</form>
							</div>
							<!-- ---------------------------------------------- -->

							<div class="row">
								<c:if
									test="${resource.createdBy.email eq user.email or resource.createdBy.isAdmin eq true }">


									<span class="col-sm-4 pull-right"><a href="#"
										id="edit_resource">Edit</a></span>
									<span class="col-sm-4 pull-right"><a href="#"
										class="delete_resource" id="${resource.id}">Delete</a></span>
								</c:if>
								<c:set var="resourcePath"
									value="<c:url value='${resource.path}' />" />

								<c:if test="${resource.type eq 'link'}">
								<span class="col-sm-1 pull-left"> <a target="_blank" href="https://www.facebook.com/sharer/sharer.php?u=${resource.path}"><i
															class="fa fa-facebook-official"  ></i></a></span>
									
									<span class="col-sm-1 pull-left"> <a
										href="https://twitter.com/home?status=${resource.path}"
										target="_blank"> <i
															class="fa fa-twitter inline"></i>
									</a>
									</span>
									<span class="col-sm-1 pull-left"> <a
										href="https://plus.google.com/share?url=${resource.path}"
										target="_blank"> <i
															class="fa fa-google-plus inline"></i>
									</a>
									</span>
								</c:if>
								<c:if test="${resource.type eq 'document'}">
									<span class="col-sm-1 pull-left"> <a href="https://www.facebook.com/sharer/sharer.php?u=${resource.path}" target="_blank" ><i
															class="fa fa-facebook-official"></i></a></span>
									
									<span class="col-sm-1 pull-left"> <a
										href="https://twitter.com/home?status=${resource.path}"
										target="_blank"> <i
															class="fa fa-twitter inline"></i>
									</a>
									</span>
									<span class="col-sm-1 pull-left"> <a
										href="https://plus.google.com/share?url=${resource.path}"
										target="_blank"> <i
															class="fa fa-google-plus inline"></i>
									</a>
									</span>
								</c:if>

							</div>
						</div>
					</div>
				</div>

			</div>
		</div>




		<%--    <div class="panel panel-default">
            <div class="panel-heading">
			<span class="panel-title">Post</span>
			</div>
	<!--	<div class="panel-body"> -->
        <ul class="list-group">
		<c:forEach items="${subscriptions}" var="topics">
        <li class="list-group-item" id="sub_${topics.topicId}">
          <div class="row" style="margin-bottom:5px">
            <div class="col-sm-3">
            <c:set var="photoLocationParts" value="${fn:split(topics.userDto.photoPath, '/')}" />
         		<c:set var="photoLocation" value="${fn:length(photoLocationParts)}" />
         		<c:set var="photoLocation" value="${photoLocation - 1}" />
              <img class="img-thumbnail img-responsive pull-left" src="<c:url value="/profile/picture/${photoLocationParts[photoLocation]}" />" height=75px width=75px>
            </div>
            <div class="col-sm-9">
              <div class="row" id="real_sub_${topics.topicId}">
                <span class="col-sm-4 pull-left" id="real_sub_text_${topics.topicId}">${topics.name}</span>
                <span class="col-sm-4 text-muted">@${topics.userDto.userName}</span>
              </div>
              
              <div class="row" id="change_sub_${topics.topicId}" style="display:none;">
                <span class="pull-left form-group">
                <span class="col-sm-5">
                <input type="text" class="form-control" id="changed_input_sub_${topics.topicId}"></span>
                <span class="col-sm-3">
                <button type="button" class="btn btn-default save_changed_name" id="save_topic_name_${topics.topicId}">Save</button></span>
                <span class="col-sm-3">
                <button type="button" class="btn btn-default cancel_changed_name" id="cancel_topic_name_${topics.topicId}">Cancel</button>
                </span>
                </span>
              </div>
              
              <div class="row">
                 
                <span class="col-sm-4 text-muted">Subscriptions</span>
                <span class="col-sm-4 text-muted pull-right">Posts</span>
              </div>
              <div class="row">
                <span class="col-sm-4 pull-left"><a href="#">${topics.totalSubscriptions}</a></span>
                <span class="col-sm-4 pull-right"><a href="#">${topics.totalPosts}</a></span>
              </div>
              <c:if test="${topics.userDto.userId != user.userId }">
              <div class="row">
              <span class="col-sm-4"><a href="/topic/unsubscribe?topic=${topics.topicId}">Unsubscribe</a></span>
              </div>
              </c:if>
            </div>
          </div>
          <div class="row form-group">
            <span class="col-sm-4 pull-left">
              <select class="form-control">
                <option value="SERIOUS" <c:if test="${topics.seriousness eq 'SERIOUS'}">
                selected
                </c:if>>SERIOUS
                </option>
                <option value="VERY_SERIOUS" <c:if test="${topics.seriousness eq 'VERY_SERIOUS'}">
                selected
                </c:if>>VERY_SERIOUS
                </option>
                <option value="CASUAL" <c:if test="${topics.seriousness eq 'CASUAL'}">
                selected
                </c:if>>CASUAL
                </option>
              </select>
            </span>
            <c:if test="${topics.userDto.userId eq user.userId }">
            <span class="col-sm-4 pull-left">
              <select class="form-control">
                <option value="PUBLIC" <c:if test="${topics.visibility eq 'PUBLIC'}">
                selected
                </c:if>>Public
                </option>
                <option value="PRIVATE" <c:if test="${topics.visibility eq 'PRIVATE'}">
                selected
                </c:if>>Private
                </option>
              </select>
            </span>
            <span class="col-sm-1 pull-left glyphicon glyphicon-pencil edit_topic_name_icon" value="sub_${topics.topicId}" style="font-size:20px"></span>
            <span class="col-sm-1 pull-left glyphicon glyphicon-trash delete_topic_name_icon" value="sub_${topics.topicId}" style="font-size:20px"></span>
            </c:if>
          </div>
        </li>
		</c:forEach>
        		<!--	</div> -->
		</div> --%>
	</div>


	</div>