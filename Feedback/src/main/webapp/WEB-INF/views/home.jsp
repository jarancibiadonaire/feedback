<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9"> <![endif]-->
<!--[if !IE]><!-->
<html lang="es">
<!--<![endif]-->
<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page session="true"%>
<head>
<title>Feedback</title>

<!-- Meta -->
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">

<!-- Favicon -->
<link rel="shortcut icon"
	href="<c:url value="/resources/assets/favicon.ico"/>">

<!-- CSS Global Compulsory -->
<link rel="stylesheet"
	href="<c:url value="/resources/assets/plugins/bootstrap/css/bootstrap.min.css"/>">
<link rel="stylesheet"
	href="<c:url value="/resources/assets/css/style.css"/>">

<!-- CSS Implementing Plugins -->
<link rel="stylesheet"
	href="<c:url value="/resources/assets/plugins/line-icons/line-icons.css"/>">
<link rel="stylesheet"
	href="<c:url value="/resources/assets/plugins/font-awesome/css/font-awesome.min.css"/>">
<link rel="stylesheet"
	href="<c:url value="/resources/assets/plugins/sky-forms/version-2.0.1/css/custom-sky-forms.css"/>">
<link rel="stylesheet"
	href="<c:url value="/resources/assets/css/plugins/bootstrap-tag-cloud.css"/>">
<link rel="stylesheet"
	href="<c:url value="/resources/assets/plugins/scrollbar/src/perfect-scrollbar.css"/>">
<!--[if lt IE 9]>
        <link rel="stylesheet" href="<c:url value="/resources/assets/css/sky-forms-ie8.css"/>">
    <![endif]-->

<!-- CSS Page Style -->
<link rel="stylesheet"
	href="<c:url value="/resources/assets/css/pages/profile.css"/>">
<link rel="stylesheet"
	href="<c:url value="/resources/assets/css/pages/feature_timeline1.css"/>">
<link rel="stylesheet"
	href="<c:url value="/resources/assets/css/pages/feature_timeline2.css"/>">
<link rel="stylesheet"
	href="<c:url value="/resources/assets/css/pages/page_search_inner.css"/>">
<link rel="stylesheet"
	href="<c:url value="/resources/assets/css/arbor/site.css"/>">

<!-- CSS Theme -->
<link rel="stylesheet"
	href="<c:url value="/resources/assets/css/themes/default.css"/>"
	id="style_color">

<!-- CSS Customization -->
<link rel="stylesheet"
	href="<c:url value="/resources/assets/css/custom.css"/>">

<script
	src="http://ajax.googleapis.com/ajax/libs/angularjs/1.2.15/angular.min.js"></script>
</head>
<body data-spy="scroll" data-target=".tab-v2">
	<sec:authorize access="!isAuthenticated()">
		<c:redirect url="/" />
	</sec:authorize>
	<!-- End Small Modal -->
	<div class="wrapper">
		<!--=== Header ===-->
		<div class="header">
			<!-- Topbar -->
			<jsp:include page="topbar.jsp"></jsp:include>
			<!-- End Topbar -->

			<!-- Navbar -->
			<%-- <jsp:include page="navbar.jsp"></jsp:include> --%>
			<!-- End Navbar -->
		</div>
		<!--=== Content Part ===-->
		<div class="container content content-without-padding home-container"
			ng-app="myApp" ng-controller="homeController">
			<div class="row">
				<!-- Begin Sidebar Menu -->
				<div class="col-md-4">
					<button class="btn btn-xs rounded-4x btn-plus" type="button"
						ng-click="clickAddFeed()">
						<i class="fa fa-plus" ng-class="btnClass"></i>
					</button>
					<div class="contentHolder contentHolder-leftPanel hidden">
						<ul class="timeline-v2">
							<li class="hidden" id="newFeed" ng-show="show_panel"><i
								class="cbp_tmicon rounded-x hidden-xs"></i>
								<div class="cbp_tmlabel">
									<div class="text-justify">
										<div class="tab-v2">
											<ul class="nav nav-tabs">
												<li class="active"><a href="#home-0" data-toggle="tab">Feed</a></li>
											</ul>
											<div class="tab-content">
												<div class="tab-pane fade in active" id="home-0">
													<form:form
														action='${pageContext.request.contextPath}/ajax/publish_feed'
														class="sky-form without-border" id="feed-form"
														modelAttribute="feed">
														<div class="hidden">
															<form:input path="user"
																value="${pageContext.request.userPrincipal.name}" />
															<form:input path="origin" value="feedback" />
															<form:input path="location.lat" id="lat" />
															<form:input path="location.lng" id="lng" />
															<form:input path="location.address" id="address" />
															<form:input path="location.comuna" id="comuna" />
														</div>
														<label class="input"> <form:input type="text"
																id="title" name="title" placeholder="Título"
																path="title" cssClass="rounded-2x" />
														</label>
														<label class="textarea textarea-expandable"> <form:textarea
																name="description" rows="3" placeholder="Descripción"
																cssClass="rounded-2x" path="description" />
														</label>
														<div class="inline-group">
															<label class="radio"><form:radiobutton
																	value="Público" path="visibility" checked="true"></form:radiobutton><i
																class="rounded-x"></i>Público</label> <label class="radio"><form:radiobutton
																	value="Privado" path="visibility"></form:radiobutton><i
																class="rounded-x"></i>Privado</label>
														</div>
														<label class="label text-left">Tags: </label>
														<div class="row tags">
															<div id="tag-info">
																<input type="text" list="list">
																<datalist id="list">
																	<c:forEach items="${listTags}" var="tag">
																		<option value="${tag.name}"></option>
																	</c:forEach>
																</datalist>
																<button class="btn rounded-2x btn-sm" type="button">
																	<i class="fa fa-plus"></i>
																</button>
															</div>
															<ul id="tag-cloud"></ul>
														</div>
														<div class="text-center">
															<button type="submit"
																ng-click="btnClass='fa-plus';show_panel = false;"
																class="btn-u btn-u-sm rounded">Publicar</button>
														</div>
													</form:form>
												</div>
											</div>
										</div>
									</div>
								</div></li>
							<li ng-repeat="feed in feeds"><i
								class="cbp_tmicon rounded-x hidden-xs"></i>
								<div class="cbp_tmlabel">
									<div class="text-justify">
										<div class="tab-v2" id="feed-{{feed.id}}">
											<ul class="nav nav-tabs">
												<li class="active"><a href="#home-{{feed.id}}"
													data-toggle="tab">Feed</a></li>
												<li><a href="#profile-{{feed.id}}" data-toggle="tab">Comentarios
														<span class="badge rounded-2x badge-blue">{{feed.comments.length}}</span>
												</a></li>
												<li><a href="#messages-{{feed.id}}" data-toggle="tab">Tags
														<span class="badge rounded-2x badge-blue">{{feed.tagsData.length+feed.othersTagsData.length}}</span>
												</a></li>
											</ul>
											<div class="tab-content">
												<div class="tab-pane fade in active" id="home-{{feed.id}}">
													<h4>{{feed.title}}</h4>
													<p>{{feed.description}}</p>
													<hr class="devider devider-dashed hr-custom">
													<ul class="list-unstyled list-inline blog-info">
														<li><i class="fa fa-user"></i> {{feed.user}}</li>
														<li class="pull-right">
															<button class="btn-u btn-u-xs rounded"
																ng-click="toggle(feed.id,true)">
																<i class="fa fa-map-marker"></i>
															</button> <span class="label rounded label-u likes">{{feed.location.comuna}}</span>
														</li>
													</ul>
													<ul class="list-unstyled list-inline blog-info">
														<li><i class="fa fa-clock-o"></i> <em>
																{{feed.createdDate | date:'medium'}}</em></li>
													</ul>
												</div>
												<div class="tab-pane fade in" id="profile-{{feed.id}}">
													<form:form
														action='${pageContext.request.contextPath}/ajax/comment_feed'
														id="comment-form" modelAttribute="comment"
														class="sky-form sky-form-panel comment-form">
														<div class="hidden">
															<form:input path="user"
																value="${pageContext.request.userPrincipal.name}" />
															<form:input path="level" value="0" />
															<form:input path="feed" value="{{feed.id}}" />
														</div>
														<div class="text-center">
															<label class="textarea"> <form:textarea row="2"
																	id="comment" name="comment" placeholder="Coméntalo!"
																	path="comment" cssClass="rounded comment" />
															</label>
														</div>
														<div class="text-center">
															<button type=submit class="btn-u btn-u-xs rounded">Enviar</button>
														</div>
													</form:form>
													<hr class="devider devider-dashed hr-custom">
													<div id="testimonials-4"
														class="testimonials testimonials-v2 testimonials-bg-default">
														<div class="carousel-inner">
															<div ng-repeat="comment in feed.comments"
																class="item active">
																<p class="rounded-3x">{{comment.comment}}</p>
																<div class="testimonial-info">
																	<i class="fa fa-user"></i><span
																		class="testimonial-author">{{comment.user}}</span> <em>{{comment.createdDate
																		| date:'medium'}}</em>
																</div>
															</div>
														</div>
													</div>
												</div>
												<div class="tab-pane fade in" id="messages-{{feed.id}}">
													<div id="onwerTags">
													 	<p>Tags del creador</p>
														<button ng-repeat="tag in feed.tagsData"
															class="btn-u rounded-2x btn-u-sm"
															ng-class="{'btn-u-blue':tag.rootTag,'btn-u-default':!tag.rootTag }"
															type="button">
															<i class="fa fa-tag"></i> {{tag.name}}
														</button>
													</div>
													<hr class="devider devider-dashed hr-custom">
													<div id="othersTags">
														<p>Tags de otros usuarios</p>
														<button ng-repeat="tag in feed.othersTagsData"
															class="btn-u rounded-2x btn-u-sm"
															ng-class="{'btn-u-blue':tag.rootTag,'btn-u-default':!tag.rootTag }"
															type="button">
															<i class="fa fa-tag"></i> {{tag.name}}
														</button>
													</div>
													<hr class="devider devider-dashed hr-custom">
													<form:form
														action='${pageContext.request.contextPath}/ajax/add_tag'
														modelAttribute="feed"
														class="sky-form sky-form-panel tag-form">
														<div class="hidden">
															<form:input path="user"
																value="${pageContext.request.userPrincipal.name}" />
															<form:input path="id" value="{{feed.id}}" />
														</div>
														<div class="tags-form">
															<div class="tag-info">
																<input type="text" list="list" class="newTag">
																<datalist id="list">
																	<c:forEach items="${listTags}" var="tag">
																		<option value="${tag.name}"></option>
																	</c:forEach>
																</datalist>
																<button class="btn rounded-2x btn-sm btn-add-tag"
																	type="button">
																	<i class="fa fa-plus"></i>
																</button>
															</div>
															<ul class="tag-cloud-form"></ul>
														</div>
														<br/>
														<div class="text-center">
															<button type=submit class="btn-u btn-u-xs rounded">Asociar</button>
														</div>
													</form:form>
												</div>
											</div>
										</div>
									</div>
									<div class="timeline-footer">
										<div class="text-right">
											<form:form method="post" class="form-inline-home like-form"
												modelAttribute="rating"
												action="${pageContext.request.contextPath}/ajax/vote">
												<form:input name="feed" path="feed" value="{{feed.id}}"
													class="hidden" />
												<form:input name="score" path="score" value="1"
													class="hidden" />
												<form:input name="user" path="user"
													value="${pageContext.request.userPrincipal.name}"
													class="hidden" />
												<button class="btn-link likes" type="submit">
													<i class="fa fa-thumbs-up">{{feed.totalLikes}} </i>
												</button>
											</form:form>
											<form:form method="post"
												class="form-inline-home dislike-form"
												modelAttribute="rating"
												action="${pageContext.request.contextPath}/ajax/vote">
												<form:input name="feed" path="feed" value="{{feed.id}}"
													class="hidden" />
												<form:input name="score" path="score" value="-1"
													class="hidden" />
												<form:input name="user" path="user"
													value="${pageContext.request.userPrincipal.name}"
													class="hidden" />
												<button class="btn-link likes" type="submit">
													<i class="fa fa-thumbs-down">{{feed.totalDislikes}}</i>
												</button>
											</form:form>
										</div>
									</div>
								</div></li>
							<li class="clearfix" style="float: none;"></li>
						</ul>
					</div>
				</div>
				<!-- End Sidebar Menu -->

				<!-- Begin Content -->
				<div class="col-md-8">
					<div id="map" class="map"></div>
					<canvas id="sitemap"></canvas>
				</div>
				<!-- End Content -->
			</div>

		</div>
		<!--/container-->
		<!--=== End Content Part ===-->
		<!--=== Copyright ===-->
		<jsp:include page="copyright.jsp"></jsp:include>
		<!--=== End Copyright ===-->
	</div>
	<!--/End Wrapepr-->

	<!-- JS Global Compulsory -->
	<script type="text/javascript"
		src="<c:url value="/resources/assets/plugins/jquery-1.10.2.js"/>"></script>
	<script type="text/javascript"
		src="<c:url value="/resources/assets/plugins/jquery-1.10.2.min.js"/>"></script>
	<script type="text/javascript"
		src="<c:url value="/resources/assets/plugins/jquery-migrate-1.2.1.min.js"/>"></script>
	<script type="text/javascript"
		src="<c:url value="/resources/assets/plugins/bootstrap/js/bootstrap.min.js"/>"></script>
	<!-- JS Implementing Plugins -->
	<!-- Scrollbar -->
	<script
		src="<c:url value="/resources/assets/plugins/scrollbar/src/jquery.mousewheel.js"/>"></script>
	<script
		src="<c:url value="/resources/assets/plugins/scrollbar/src/perfect-scrollbar.js"/>"></script>
	<!-- Checkout Form -->
	<script
		src="<c:url value="/resources/assets/plugins/sky-forms/version-2.0.1/js/jquery.validate.min.js"/>"></script>
	<script
		src="<c:url value="/resources/assets/plugins/sky-forms/version-2.0.1/js/jquery.maskedinput.min.js"/>"></script>
	<!-- Order Form -->
	<script
		src="<c:url value="/resources/assets/plugins/sky-forms/version-2.0.1/js/jquery-ui.min.js"/>"></script>
	<script
		src="<c:url value="/resources/assets/plugins/sky-forms/version-2.0.1/js/jquery.form.min.js"/>"></script>

	<script type="text/javascript"
		src="<c:url value="/resources/assets/js/soa/jquery.json-2.3.min.js"/>"></script>
	<script type="text/javascript"
		src="/CoupledObjectWebServer/resources/js/sync.js"></script>
	<!-- JS Page Level -->
	<script type="text/javascript"
		src="<c:url value="/resources/assets/js/app.js"/>"></script>
	<script
		src="<c:url value="/resources/assets/js/forms/publish_feed.js"/>"></script>
	<script
		src="<c:url value="/resources/assets/js/forms/comment_feed.js"/>"></script>
	<script type="text/javascript">
		jQuery(document).ready(function() {
			App.init();
			FeedForm.initFeedForm();
			CommentForm.initCommentForm();
			"use strict";
			$('.contentHolder').perfectScrollbar();
		});
	</script>
	<script
		src="https://maps.googleapis.com/maps/api/js?key=AIzaSyA3kOYMhNbartGm75c_O2XioYUTnbiEsu4&sensor=true&libraries=drawing,places"></script>
	<script type="text/javascript"
		src="<c:url value="/resources/assets/js/microblogging/home.js"/>"></script>
	<script type="text/javascript"
		src="<c:url value="/resources/assets/js/microblogging/microblogging.js"/>"></script>
	<script type="text/javascript"
		src="<c:url value="/resources/assets/js/microblogging/forms.js"/>"></script>
	<script type="text/javascript"
		src="<c:url value="/resources/assets/js/microblogging/appAngular.js"/>"></script>
	<script type="text/javascript"
		src="<c:url value="/resources/assets/js/plugins/jquery.tagcloud.js"/>"></script>
	<script type="text/javascript"
		src="<c:url value="/resources/assets/js/plugins/bootstrap-tag-cloud.js"/>"></script>
	<script type="text/javascript"
		src="<c:url value="/resources/assets/js/arbor/arbor-graphics.js"/>"></script>
	<script type="text/javascript"
		src="<c:url value="/resources/assets/js/arbor/arbor-tween.js"/>"></script>
	<script type="text/javascript"
		src="<c:url value="/resources/assets/js/arbor/arbor.js"/>"></script>
	<script type="text/javascript"
		src="<c:url value="/resources/assets/js/arbor/site.js"/>"></script>
	<script type="text/javascript"
		src="<c:url value="/resources/assets/js/microblogging/synchronizer.js"/>"></script>
	<script type="text/javascript"
		src="<c:url value="/resources/assets/js/plugins/scroll-nav.js"/>"></script>

	<!--[if lt IE 9]>
    <script src="<c:url value="/resources/assets/plugins/respond.js"/>"></script>
<![endif]-->
</body>
</html>