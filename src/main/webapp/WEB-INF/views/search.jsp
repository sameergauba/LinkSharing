
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>

<script src="http://code.jquery.com/jquery-2.1.4.min.js"></script>
<script src="http://code.jquery.com/jquery-migrate-1.2.1.js"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"
	integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7"
	crossorigin="anonymous">

<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!-- Optional theme -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css"
	integrity="sha384-fLW2N01lMqjakBkx3l/M9EahuwpSfeNvV63J5ezn3uZzapT0u7EYsXMjQV+0En5r"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">

<script type="text/javascript"
	src="<c:url value="/theme1/js/profile.js" />"></script>
<!-- Latest compiled and minified JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"
	integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS"
	crossorigin="anonymous"></script>




</head>
<body>
	
			<!---------------------------------------------------------Right Side Panel---------------------------------------------------------------->
			<div class="container-fluid col-md-8 pull-right">
				<div class="panel panel-default">
					<div class="panel-heading">
						<span class="panel-title">Search Results</span>
					</div>
					<div id="" style="overflow: scroll; height: 500px;">
						<div class="panel-body">
							<c:forEach items="${resources}" var="link">
								<div class="" id="${link.id}_post"
									style="border: 2px solid black; margin-bottom: 5px">

									<div class="row">
										<div class="col-sm-3">
											<img class="img-thumbnail img-responsive"
												src="/profiles/${link.createdBy.userName}/ProfilePic"
												height=125px width=100px>
										</div>
										<div class="col-sm-9">
											<div class="row">
												<span class="col-sm-4"><a href="#">${link.createdBy.firstName}
														${link.createdBy.lastName}</a></span><span
													class="col-sm-4 text-muted">@${link.createdBy.userName}</span><span
													class="col-sm-2"><a href="#">${link.topic.name}</a></span>
											</div>
											<div class="row">
												<p>${link.description}</p>
											</div>
											<div class="row">
												<!-- <span class="col-sm-1 pull-left"> <a href="#"><div
															class="fa fa-facebook-official"></div></a></span> <span
													class="col-sm-1 pull-left"><a href="#"><div
															class="fa fa-twitter inline"></div></a></span> <span
													class="col-sm-1 pull-left"><a href="#"><div
															class="fa fa-google-plus inline"></div></a></span> -->
												<span class="pull-right" style="margin-right: 15px"><a
													href="/resource/vposts/${link.id}">view post</a></span> <span
													class="pull-right" style="margin-right: 15px"><a
													href="#" class="mark_as_read" value="${link.id}">Mark
														As read </a></span>
												<c:if test="${link.type=='link'}">
													<span class="pull-right" style="margin-right: 15px"><a
														class="view_full_site" value="${link.path}" href="#">view
															full site </a></span>
												</c:if>
												<c:if test="${link.type=='document'}">
													<span class="pull-right" style="margin-right: 15px"><a
														class="document_down" value="${link.id}"
														href="/resource/download/document/${link.id}"
														>download </a></span>
												</c:if>
											</div>
										</div>
									</div>
								</div>
							</c:forEach>
							<%-- <c:forEach items="${documentResources}" var="doc">
								<div class="document"
									style="border: 2px solid black; margin-bottom: 5px">

									<div class="row">
										<div class="col-sm-3">
											<img class="img-thumbnail img-responsive"
												src="/profiles/${doc.createdBy.userName}/ProfilePic"
												height=125px width=100px>
										</div>
										<div class="col-sm-9">
											<div class="row">
												<span class="col-sm-4"><a href="#">${doc.createdBy.firstName}
														${doc.createdBy.lastName}</a></span><span class="col-sm-4 text-muted">@${doc.createdBy.userName}</span><span
													class="col-sm-2"><a href="#">${doc.topic.name}</a></span>
											</div>
											<div class="row">
												<p>${doc.description}</p>
											</div>
											<div class="row">
												<!-- <span class="col-sm-1 pull-left"> <a href="#"><div
															class="fa fa-facebook-official"></div></a></span> <span
													class="col-sm-1 pull-left"><a href="#"><div
															class="fa fa-twitter inline"></div></a></span> <span
													class="col-sm-1 pull-left"><a href="#"><div
															class="fa fa-google-plus inline"></div></a></span> --> <span
													class="pull-right" style="margin-right: 15px"><a
													href="#">view post</a></span> <span class="pull-right"
													style="margin-right: 15px"><a href="#" class="mark_as_read" value="${doc.id}" >Mark As
														read </a></span> <span class="pull-right" style="margin-right: 15px"><a
													href="#">download </a></span>
											</div>
										</div>
									</div>
								</div>
							</c:forEach> --%>

						</div>
					</div>
				</div>
			</div>
			
</body>

</html>
