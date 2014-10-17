<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en">
<!--<![endif]-->
<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page session="true"%>
<head>
<title>Feedback - Microblogging</title>

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
	href="<c:url value="/resources/assets/plugins/scrollbar/src/perfect-scrollbar.css"/>">
<!--[if lt IE 9]>
        <link rel="stylesheet" href="<c:url value="/resources/assets/css/sky-forms-ie8.css"/>">
    <![endif]-->

<!-- CSS Page Style -->
<link rel="stylesheet"
	href="<c:url value="/resources/assets/css/pages/profile.css"/>">

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
<body>
	<sec:authorize access="!isAuthenticated()">
		<c:redirect url="/" />
	</sec:authorize>
	<div class="wrapper">
		<!--=== Header ===-->
		<div class="header">
			<!-- Topbar -->
			<jsp:include page="topbar.jsp"></jsp:include>
			<!-- End Topbar -->

			<!-- Navbar -->
			<jsp:include page="navbar.jsp"></jsp:include>
			<!-- End Navbar -->
		</div>
		<!--=== End Header ===-->

		<!--=== Content ===-->
		<div class="container content content-without-padding" ng-app="myApp"
			ng-controller="mapController">
			<div class="row">
				<div class="col-md-3 margin-bottom-20">
					<div class="servive-block servive-block-u rounded-2x">
						<i class="icon-custom icon-color-light rounded-x icon-user"></i>
						<h2 class="heading-md">${pageContext.request.userPrincipal.name}</h2>
						<hr class="devider devider-db">
						<div class="grey rounded-2x">
							<ul class="list-inline badge-lists badge-icons">
								<li><a href="#"><i class="fa fa-envelope"></i></a> <span
									class="badge badge-blue rounded-x">2</span></li>
								<li><a href="#"><i class="fa fa-cog"></i></a> <span
									class="badge badge-blue rounded-x">9</span></li>
								<li><a href="#"><i class="fa fa-gift"></i></a> <span
									class="badge badge-blue rounded-x">4</span></li>
								<li><a href="#"><i class="fa fa-exclamation-triangle"></i></a>
									<span class="badge badge-blue rounded-x">3</span></li>
								<li><a href="#"><i class="fa fa-comments"></i></a> <span
									class="badge badge-blue rounded-x">3</span></li>
							</ul>
						</div>
					</div>
					<form:form
						action='${pageContext.request.contextPath}/microblogging/publish_feed'
						class="sky-form" id="feed-form" modelAttribute="feed">
						<header>Ingresa un feed</header>
						<div class="hidden">
							<form:input path="user"
								value="${pageContext.request.userPrincipal.name}" />
							<form:input path="origin" value="feedback" />
							<form:input path="location.lat" id="lat" />
							<form:input path="location.lng" id="lng" />
							<form:input path="location.address" id="address" />
							<form:input path="location.comuna" id="comuna" />
						</div>
						<fieldset>
							<section>
								<label class="input"> <form:input type="text" id="title"
										name="title" placeholder="Título" path="title"
										cssClass="rounded-2x" />
								</label>
							</section>
							<section>
								<label class="textarea textarea-expandable"> <form:textarea
										name="description" rows="3" placeholder="Descripción"
										cssClass="rounded-2x" path="description" />
								</label>
							</section>
							<section>
								<label class="label">Visibilidad</label>
								<div class="inline-group">
									<label class="radio"><form:radiobutton value="Público"
											path="visibility" checked="true"></form:radiobutton><i
										class="rounded-x"></i>Público</label> <label class="radio"><form:radiobutton
											value="Privado" path="visibility"></form:radiobutton><i
										class="rounded-x"></i>Privado</label>
								</div>
							</section>
							<section>
								<div class="text-center">
									<button type="submit" class="btn-u rounded-2x">Publicar</button>
								</div>
							</section>
						</fieldset>
					</form:form>
					<c:if test="${not empty var and var == 'success'}">
						<div class="alert alert-success fade in alert-dismissable">
							<button type="button" class="close" data-dismiss="alert"
								aria-hidden="true">×</button>
							Su feed se a publicado exitosamente
						</div>
					</c:if>
					<c:if test="${not empty var and var == 'error'}">
						<div class="alert alert-danger fade in alert-dismissable">
							<button type="button" class="close" data-dismiss="alert"
								aria-hidden="true">×</button>
							Hubo un error. Vuelva a ingresar un feed
						</div>
					</c:if>
				</div>
				<!-- End Sidebar Menu -->

				<!-- Begin Content -->
				<div class="col-md-9">
					<div id="map" class="map"></div>
					<div ng-show="show_panel"
						class="funny-boxes funny-box-custom funny-boxes-top-sea panel-map hidden">
						<ul
							class="list-unstyled contentHolder margin-bottom-20 ps-container contentHolder-custom">
							<li class="notification">
								<h2>{{currentFeed.title}}</h2>
								<p>{{currentFeed.description}}</p> <i class="fa fa-user"></i>
								{{currentFeed.user}}
								<hr class="devider devider-dashed hr-custom">
								<div>
									<i class="fa fa-clock-o"></i> <span>{{currentFeed.createdDate
										| date:'medium'}}</span>
								</div>
								<div>
									<span class="label rounded label-u likes">{{currentFeed.location.comuna}}</span>
								</div>
								<hr class="devider devider-dashed hr-custom"> <form:form
									action='${pageContext.request.contextPath}/microblogging/comment_feed'
									id="comment-form" modelAttribute="comment"
									class="sky-form sky-form-panel">
									<div class="hidden">
										<form:input path="user"
											value="${pageContext.request.userPrincipal.name}" />
										<form:input path="level" value="0" />
										<form:input path="feed" id="commentFeed" />
									</div>
									<div class="text-center">
										<label class="textarea"> <form:textarea
												row="2" id="comment" name="comment" placeholder="Coméntalo!"
												path="comment" cssClass="rounded" />
										</label>
									</div>
									<div class="text-center">
										<button type="submit" class="btn-u btn-u-xs rounded">Enviar</button>
									</div>
								</form:form>
								<hr class="devider devider-dashed hr-custom">
								<div>
									<i class="fa fa-comments-o"></i> <a href="#">{{currentFeed.totalComments}}
										Comentarios</a>
								</div>
								<div id="testimonials-4"
									class="carousel slide testimonials testimonials-v2 testimonials-bg-default">
									<div class="carousel-inner">
										<div class="item active" ng-repeat="comment in currentFeed.comments">
											<p class="rounded-3x">{{comment.comment}}</p>
											<div class="testimonial-info">
												<i class="fa fa-user"></i><span class="testimonial-author">{{comment.user}}</span>
												<em>{{comment.createdDate | date:'medium'}}</em>
											</div>
										</div>
									</div>
								</div>
							</li>
						</ul>
					</div>
				</div>
			</div>
		</div>
		<!--/container-->
		<!--=== End Content ===-->

		<!--=== Copyright ===-->
		<jsp:include page="copyright.jsp"></jsp:include>
		<!--=== End Copyright ===-->
	</div>
	<!--/End Wrapper-->

	<!-- JS Global Compulsory -->
	<script type="text/javascript"
		src="<c:url value="/resources/assets/plugins/jquery-1.10.2.min.js"/>"></script>
	<script type="text/javascript"
		src="<c:url value="/resources/assets/plugins/jquery-migrate-1.2.1.min.js"/>"></script>
	<script type="text/javascript"
		src="<c:url value="/resources/assets/plugins/bootstrap/js/bootstrap.min.js"/>"></script>
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
	<!-- JS Page Level -->
	<script type="text/javascript"
		src="<c:url value="/resources/assets/js/app.js"/>"></script>
	<script
		src="<c:url value="/resources/assets/js/forms/publish_feed.js"/>"></script>
	<script type="text/javascript">
		jQuery(document).ready(function() {
			App.init();
			activeNavbar();
			FeedForm.initFeedForm();
			"use strict";
			$('.contentHolder').perfectScrollbar();
		});
		function activeNavbar() {
			//borrar los anteriores
			$("#welcome").removeClass("active");
			$("#timeline").removeClass("active");
			$("#microblogging").removeClass("active");
			$("#conceptmap").removeClass("active");
			$("#statistics").removeClass("active");
			//activar el actual
			$("#microblogging").addClass("active");
		}
	</script>
	<!--[if lt IE 9]>
    <script src="<c:url value="/resources/assets/plugins/respond.js"/>"></script>
<![endif]-->
	<script
		src="https://maps.googleapis.com/maps/api/js?key=AIzaSyA3kOYMhNbartGm75c_O2XioYUTnbiEsu4&sensor=true&libraries=drawing,places"></script>
	<script type="text/javascript"
		src="<c:url value="/resources/assets/js/microblogging/home.js"/>"></script>
	<script type="text/javascript"
		src="<c:url value="/resources/assets/js/microblogging/microblogging.js"/>"></script>
	<script type="text/javascript"
		src="<c:url value="/resources/assets/js/microblogging/appAngular.js"/>"></script>
</body>
</html>