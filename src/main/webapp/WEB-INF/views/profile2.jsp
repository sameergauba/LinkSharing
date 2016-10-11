
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

<%-- <c:set var="contextPath" value="${pageContext.request.contextPath}" /> --%>
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
	<!-----------------------------------------Navigation bar Top--------------------------------->


	<div class="container-fluid">
		<nav class="navbar navbar-default">

			<div class="navbar-header">
				<a class="navbar-brand" href="/"> Link Sharing </a>
			</div>
			<ul class="nav navbar-nav pull-right">
				<li><a href="#" data-toggle="modal" data-target="#myTopic"><span
						class="glyphicon glyphicon-comment" style="font-size: 20px"></span></a></li>
				<li><a href="#" data-toggle="modal" data-target="#myInvite"><span
						class="glyphicon glyphicon-envelope" style="font-size: 20px"></span></a></li>
				<li><a href="#" data-toggle="modal" data-target="#myDoc"><span
						class="glyphicon glyphicon-link" style="font-size: 20px"></span></a></li>
				<li><a href="#" data-toggle="modal" data-target="#myLink"><span
						class="glyphicon glyphicon-book" style="font-size: 20px"></span></a></li>
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown" role="button" aria-haspopup="true"
					aria-expanded="false"><span class="glyphicon glyphicon-user"
						style="font-size: 20px">${user.firstName}</span> <span
						class="caret"></span></a>
					<ul class="dropdown-menu">
						<!-- <li><a href="#">Profile</a></li>
						<li><a href="#">Users</a></li>
						<li><a href="#">Topics</a></li>
						<li><a href="#">Posts</a></li> -->
						<li><a href="/user/logout">Logout</a></li>
					</ul></li>
			</ul>
			<form class="navbar-form pull-right" id="search_form" role="search"
				action="">
				<div class="form-group has-feedback has-feeback-left">
					<input type="text" class="form-control" id="search_box"
						placeholder="search" name="search">
					<!-- <i class="glyphicon glyphicon-search form-control-feedback"></i> -->
					<button type="submit" id="search_button" class="glyphicon glyphicon-search form-control-feedback">
						<!-- <i class="glyphicon glyphicon-search form-control-feedback"></i> -->
					</button>
				</div>
			</form>


		</nav>
	</div>
	<!------------------------------------------------------------------------------------------->
	<!--------------------------------------Create Topic Modals----------------------------------------------->
	<div id="myTopic" class="modal fade" role="dialog">
		<div class="modal-dialog modal-sm" style="display: table">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<div class="modal-title">Create Topic</div>
				</div>

				<form:form role="form" commandName="topicDTO"
					modelAttribute="topicDTO" action="/topic/create">
					<div class="modal-body">
						<form:errors path="*" />
						<div class="row form-group" style="margin-bottom: 10px">
							<span class="col-sm-4"><label class="form-label">Name</label></span>
							<span class="col-sm-8"><form:input type="text"
									class="form-control" path="name" placeholder="Name" /></span>
						</div>
						<div class="row" style="margin-bottom: 10px">
							<span class="col-xs-4"><label class="form-label">Visiblity</label></span>
							<span class="col-xs-8 form-group"> <form:select id="sel2"
									path="visibility" class="form-control">
									<!-- <option value="public">Public</option>
    							<option value="private">Private</option> -->
									<form:options items="${topicDTO.visibility.values()}" />
								</form:select>
							</span>
						</div>
					</div>
					<div class="modal-footer">
						<input type="submit" value="save" name="save"
							class="btn btn-default" />
						<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					</div>
				</form:form>
			</div>

		</div>
	</div>
	<!------------------------------------------------------------------------------------------------------->
	<!---------------------------------------Create Invite Modals------------------------------------------->
	<div id="myInvite" class="modal fade" role="dialog">
		<div class="modal-dialog modal-sm">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<div class="modal-title">Send Invitation</div>
				</div>
				<div class="modal-body">
					<div class="row form-group" style="margin-bottom: 10px">
						<span class="col-sm-4"><label class="form-label">Email</label></span><span
							class="col-sm-8"><input id="save_invitation_val_email"
							type="email" class="form-control" placeholder="Email"></span>
					</div>
					<div class="row form-group" style="margin-bottom: 10px">
						<span class="col-sm-4"><label class="form-label">Topic</label></span><span
							class="col-sm-8"> <select id="save_invitation_val_topic"
							path="" class="form-control">
								<c:forEach items="${subscriptions}" var="subs">
									<%-- <c:if test="${subs.pk.topic.visibility eq 'Private'}"> --%>
									<option value="${subs.topic.id}">${subs.topic.name}</option>
									<%-- </c:if> --%>
								</c:forEach>
						</select>
						</span>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" id="save_invitation"
						value="save_invitation_val" class="btn btn-default">Save</button>
					<button type="button" id="save_invitation_close"
						class="btn btn-default" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>
	<!---------------------------------------------------------------------------------------------------->
	<!-------------------------------------------------Share Document------------------------------------->
	<div id="myDoc" class="modal fade" role="dialog">
		<div class="modal-dialog modal-sm">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<div class="modal-title">Share Document</div>
				</div>
				<form:form modelAttribute="documentDTO"
					action="/resource/create/document" enctype="multipart/form-data">
					<div class="modal-body">

						<div class="row form-group" style="margin-bottom: 10px">
							<span class="col-sm-4"><label class="form-label">Document</label></span><span
								class="col-sm-8"><input type="file" name="file"
								class="form-control" /></span>
						</div>
						<div class="row form-group" style="margin-bottom: 10px">
							<span class="col-sm-4"><label class="form-label">Description</label></span><span
								class="col-sm-8"><form:textarea path="description"
									rows="6" class="form-control" placeholder="Description"></form:textarea></span>
						</div>
						<div class="row form-group">
							<span class="col-sm-4"><label class="form-label">Topic</label></span><span
								class="col-sm-8"> <form:select id="sel1" path="topicId"
									class="form-control">

									<c:forEach items="${subscriptions}" var="subs">
										<form:option value="${subs.topic.id}">${subs.topic.name}</form:option>
									</c:forEach>

								</form:select>
							</span>
						</div>

					</div>
					<div class="modal-footer">
						<input type="submit" value="Share" class="btn btn-default" />
						<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					</div>
				</form:form>
			</div>
		</div>
	</div>
	<!---------------------------------------------------------------------------------------------------->
	<!--------------------------------------------Share Link---------------------------------------------->
	<div id="myLink" class="modal fade" role="dialog">
		<div class="modal-dialog modal-sm">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<div class="modal-title">Share Link</div>
				</div>
				<form:form modelAttribute="linkDTO" action="/resource/create/link"
					enctype="multipart/form-data">
					<div class="modal-body">
						<div class="row form-group" style="margin-bottom: 10px">
							<span class="col-sm-4"><label class="form-label">Link</label></span><span
								class="col-sm-8"><form:input type="text"
									class="form-control" placeholder="Link" path="url" /></span>
						</div>
						<div class="row form-group" style="margin-bottom: 10px">
							<span class="col-sm-4"><label class="form-label">Description</label></span><span
								class="col-sm-8"><form:textarea path="description"
									rows="6" class="form-control" placeholder="Description"></form:textarea></span>
						</div>
						<div class="row form-group">
							<span class="col-sm-4"><label class="form-label">Topic</label></span><span
								class="col-sm-8"> <form:select id="sel1" path="topicId"
									class="form-control">

									<c:forEach items="${subscriptions}" var="subs">
										<form:option value="${subs.topic.id}">${subs.topic.name}</form:option>
									</c:forEach>

								</form:select>
							</span>
						</div>
					</div>
					<div class="modal-footer">
						<input type="submit" value="Share" class="btn btn-default" />
						<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					</div>
				</form:form>
			</div>
		</div>
	</div>
	<!----------------------------------------------------------------------------------------------------->
	<!---------------------------------------Page Content------------------------------------------------->
	<div class="container">
		<div style="background: yellow">${pageMsg}</div>
		<div class="row">
			<div class="container-fluid col-md-4 pull-left">
				<div class="panel panel-default">
					<div class="panel-body">

						<div class="row">
							<div class="col-sm-4">
								<img class="img-thumbnail img-responsive"
									src="/profiles/${user.userName}/ProfilePic" height=125px
									width=100px>
							</div>
							<div class="col-sm-8">
								<div class="row">
									<span><h4>${user.firstName} ${user.lastName}</h4></span>
								</div>
								<div class="row">
									<p class="text-muted">@${user.userName}</p>
								</div>
								<div class="row">
									<span class="col-sm-6 text-muted pull-left">Subscriptions</span>
									<span class="col-sm-6 text-muted pull-left">Topics</span>
								</div>
								<div class="row">
									<span class="col-sm-6 pull-left"><a href="#">${subscriptionSize}</a></span>
									<span class="col-sm-6 pull-left"><a href="#">${topicSize}</a></span>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!---------------------------------------------------------Right Side Panel---------------------------------------------------------------->
			<div id="result_div" >
			<div class="container-fluid col-md-8 pull-right">
				<div class="panel panel-default">
					<div class="panel-heading">
						<span class="panel-title">Inbox</span>
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
														href="/resource/download/document/${link.id}">download
													</a></span>
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
			</div>
			<!-------------------------------------------------------------Subscriptions------------------------------------------->
			<div class="container-fluid col-md-4 pull-left">
				<div class="panel panel-default">
					<div class="panel-heading">
						<span class="panel-title">Subscriptions</span>
					</div>
					<!--	<div class="panel-body"> -->
					<div id="" style="overflow: scroll; height: 300px;">
						<ul class="list-group">

							<c:set var="count" value="${0}"></c:set>


							<c:forEach items="${subscriptions}" var="subs">
								<c:if test="${count<5}">
									<c:set var="count" value="${count+1}"></c:set>
									<li class="list-group-item" id="li_${subs.topic.id}">
										<div class="row" style="margin-bottom: 5px">
											<div class="col-sm-3">
												<img class="img-thumbnail img-responsive pull-left"
													src="/profiles/${subs.topic.createdBy.userName}/ProfilePic"
													height=75px width=75px>
											</div>
											<div class="col-sm-9">
												<div class="row mytopic_name"
													id="subs__${subs.topic.id}_real">
													<span class="col-sm-4 pull-left"><a href="#"
														id="subs__${subs.topic.id}_real_topic_label">${subs.topic.name}</a></span>
													<span class="col-sm-4 text-muted" style="margin-right: 1em">@${subs.topic.createdBy.userName}</span>
												</div>
												<div class="row" id="subs__${subs.topic.id}"
													style="display: none">
													<span class="pull-left form-group"><span
														class="col-sm-5"><input type="text"
															class="form-control" id="text_${subs.topic.id}"></span><span
														class="col-sm-3"><button type="button"
																class="btn btn-default topic_save"
																value="${subs.topic.id}">Save</button></span><span
														class="col-sm-3"><button type="button"
																class="btn btn-default topic_change_cancel"
																value="subs__${subs.topic.id}">Cancel</button></span></span>
												</div>
												<div class="row">
													<span class="col-sm-4 text-muted">Subscriptions</span> <span
														class="col-sm-4 text-muted pull-right">Posts</span>
												</div>
												<div class="row">
													<span class="col-sm-4"><a href="#">${subs.subsSize}</a></span>
													<span class="col-sm-4 pull-right"><a href="#">${subs.postsSize}</a></span>
												</div>
												<div class="row">
													<c:if
														test="${subs.user.userName != subs.topic.createdBy.userName}">
														<span class="col-sm-4"><a
															href="/subscription/unsubscribe/${subs.topic.id}">Unsubscribe</a></span>
													</c:if>
												</div>
											</div>
										</div>
										<div class="row form-group">
											<span class="col-sm-4 pull-left"> <select
												val="${subs.id}" class="form-control serious_select">

													<option value="VerySerious"
														<c:if test="${subs.seriousness eq 'VerySerious'}">
               selected
               </c:if>>VERY_SERIOUS
													</option>
													<option value="Serious"
														<c:if test="${subs.seriousness eq 'Serious'}">
               selected
               </c:if>>SERIOUS
													</option>
													<option value="Casual"
														<c:if test="${subs.seriousness eq 'Casual'}">
               selected
               </c:if>>CASUAL
													</option>
											</select>
											</span>
											<c:if
												test="${subs.topic.createdBy.id eq user.id or user.admin==true }">
												<span class="col-sm-4 pull-left"> <select
													class="private_select" val="${subs.topic.id}"
													class="form-control">
														<option value="Public"
															<c:if test="${subs.topic.visibility eq 'Public'}">
               selected
               </c:if>>Public
														</option>
														<option value="Private"
															<c:if test="${subs.topic.visibility eq 'Private'}">
               selected
               </c:if>>Private
														</option>
												</select>
												</span>
											</c:if>
											<!-- <span class="col-sm-1 pull-left glyphicon glyphicon-envelope"
											style="font-size: 20px"></span> -->
											<c:if
												test="${subs.user.userName == subs.topic.createdBy.userName or user.admin == true}">
												<span
													class="col-sm-1 pull-left glyphicon glyphicon-pencil edit_topic_name"
													value="subs__${subs.topic.id}" style="font-size: 20px"></span>
												<a href="/topic/delete/${subs.topic.id}"><span
													value="${subs.topic.id}"
													class="col-sm-1 pull-left glyphicon glyphicon-trash delete_topic_sub"
													style="font-size: 20px"></span></a>
											</c:if>
										</div>
									</li>
								</c:if>
							</c:forEach>


						</ul>
					</div>
					<!--	</div> -->
				</div>
			</div>


		</div>

		<div class="row">
			<!----------------------------------------------------------------Trending-------------------------------------------------->
			<div class="container-fluid col-md-4 pull-left">
				<div class="panel panel-default">
					<div class="panel-heading">
						<span class="panel-title">Trending Topics</span>
					</div>
					<div id="" style="overflow: scroll; height: 300px;">
						<ul class="list-group">
							<c:forEach items="${trendTopics}" var="topic">
								<li class="list-group-item">
									<div class="row" style="margin-bottom: 5px">
										<div class="col-sm-3">
											<img class="img-thumbnail img-responsive pull-left"
												src="/profiles/${topic.createdBy.userName}/ProfilePic"
												height=75px width=75px>
										</div>
										<div class="col-sm-9">
											<div class="row">
												<span class="col-sm-4 pull-left"><a href="#">${topic.name}</a></span>
												<span class="col-sm-4 text-muted">@${topic.createdBy.userName}</span>
											</div>
											<div class="row">
												<span class="col-sm-4 text-muted">Subscriptions</span> <span
													class="col-sm-4 text-muted pull-right">Posts</span>
											</div>
											<div class="row">
												<span class="col-sm-4"><a href="#">${topic.subsSize}</a></span>
												<span class="col-sm-4 pull-right"><a href="#">${topic.postsSize}</a></span>
											</div>
											<div class="row">
												<span class="col-sm-4"><a
													href="/subscription/subscribe/${topic.id}">Subscribe</a></span>
											</div>
										</div>
									</div>
								</li>
							</c:forEach>
							<!-- <li class="list-group-item">
								<div class="row" style="margin-bottom: 5px">
									<div class="col-sm-3">
										<img class="img-thumbnail img-responsive pull-left"
											src="http://gurucul.com/wp-content/uploads/2015/01/default-user-icon-profile.png"
											height=75px width=75px>
									</div>
									<div class="col-sm-9">
										<div class="row">
											<span class="pull-left form-group"><span
												class="col-sm-5"><input type="text"
													class="form-control"></span><span class="col-sm-3"><button
														type="button" class="btn btn-default">Save</button></span><span
												class="col-sm-3"><button type="button"
														class="btn btn-default">Cancel</button></span></span>
										</div>
										<div class="row">
											<span class="col-sm-4 text-muted">@Sameer</span> <span
												class="col-sm-4 text-muted">Subscriptions</span> <span
												class="col-sm-4 text-muted pull-right">Topics</span>
										</div>
										<div class="row">
											<span class="col-sm-4"><a href="#">Unsubscribe</a></span> <span
												class="col-sm-4"><a href="#">50</a></span> <span
												class="col-sm-4"><a href="#">30</a></span>
										</div>
									</div>
								</div>
								<div class="row form-group">
									<span class="col-sm-4 pull-left"> <select
										class="form-control">
											<option>Serious</option>
											<option>Normal</option>
											<option>Not Important</option>
									</select>
									</span> <span class="col-sm-4 pull-left"> <select
										class="form-control">
											<option>Private</option>
											<option>Public</option>
											<option>Protected</option>
									</select>
									</span> <span class="col-sm-1 pull-left glyphicon glyphicon-envelope"
										style="font-size: 20px"></span> <span
										class="col-sm-1 pull-left glyphicon glyphicon-pencil"
										style="font-size: 20px"></span> <span
										class="col-sm-1 pull-left glyphicon glyphicon-trash"
										style="font-size: 20px"></span>
								</div>
							</li> -->
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!----------------------------------------------------------------------------------------------------->

</body>

</html>
