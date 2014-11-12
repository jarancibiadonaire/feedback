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
<title>Feedback - Estadística</title>

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
		<div class="modal fade" id="responsive" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModalLabel">Configuración de
							tags</h4>
					</div>
					<form:form
						action='${pageContext.request.contextPath}/statistics/config_tag'
						class="sky-form without-border" id="config-tag-form"
						modelAttribute="configTag">
						<div class="modal-body">
							<div class="row">
								<form:input path="username"
									value="${pageContext.request.userPrincipal.name}"
									class="hidden" />
								<h5>Selecciona los tags que deseas seguir:</h5>
								<ul id="pageStuff" class="ul-tags">
									<c:forEach items="${listTags}" var="tag">
										<li><div class="margin-bottom-5 inline">
												<form:checkbox name="tag-config" value="${tag.id}"
													path="tagsIds" />
												<button
													class="btn-u rounded-2x btn-u-sm ${tag.rootTag ? 'btn-u-blue' : 'btn-u-default'}"
													type="button">
													<i class="fa fa-tag"></i>${tag.name}
												</button>
											</div></li>
									</c:forEach>
								</ul>
								<div class="span6 text-center">
									<div class="pagination">
										<ul class="pager"></ul>
									</div>
								</div>
							</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn-u btn-u-default"
								data-dismiss="modal">Cerrar</button>
							<button type="submit" class="btn-u btn-u-primary">Guardar
								cambios</button>
						</div>
					</form:form>
				</div>
			</div>
		</div>
		<div class="container content content-without-padding home-container"
			ng-app="myApp" ng-controller="homeController">
			<div class="row">
				<nav id="menu" class="panel" role="navigation">
					<ul class="panel-options nav nav-pills nav-stacked">
						<li><a href='<c:url value="/welcome"/>'> <i
								class="fa fa-home"></i> Inicio
						</a></li>
						<li><a href='<c:url value="/statistics"/>'> <i
								class="fa fa-bar-chart-o"></i> Estadística
						</a></li>
						<li><a href="#" data-toggle="modal" data-target="#responsive">
								<i class="fa fa-cogs"></i> Tags
						</a></li>
					</ul>
				</nav>
				<div class="col-md-12">
					<div class="tag-box tag-box-v3">
						<div class="headline">
							<h2>Pie Charts</h2>
						</div>
						<p>
							Please note you should use four pie charts in the first version.
							Otherwise you need to change settings from
							<code>circles-master.js</code>
							However, you are able use second version of pie charts for easy
							self control.
						</p>
						<br> <br>

						<!-- Pie Charts v1 -->
						<div class="row pie-progress-charts margin-bottom-60">
							<div class="inner-pchart col-md-3">
								<div class="circle" id="circles-1"></div>
								<h3 class="circle-title">Consulting</h3>
								<p>Lorem ipsum dolor sit amet, consectetur adipiscing</p>
							</div>
							<div class="inner-pchart col-md-3">
								<div class="circle" id="circles-2"></div>
								<h3 class="circle-title">Marketing</h3>
								<p>Lorem ipsum dolor sit amet, consectetur adipiscing</p>
							</div>
							<div class="inner-pchart col-md-3">
								<div class="circle" id="circles-3"></div>
								<h3 class="circle-title">Branding</h3>
								<p>Lorem ipsum dolor sit amet, consectetur adipiscing</p>
							</div>
							<div class="inner-pchart col-md-3">
								<div class="circle" id="circles-4"></div>
								<h3 class="circle-title">Copywriting</h3>
								<p>Lorem ipsum dolor sit amet, consectetur adipiscing</p>
							</div>
						</div>
						<!-- End Pie Charts v1 -->

						<div class="margin-bottom-60">
							<hr>
						</div>

						<!-- Pie Charts v2 -->
						<div class="row pie-progress-charts margin-bottom-60">
							<div class="inner-pchart col-md-4">
								<div class="circle" id="circle-1"></div>
								<h3 class="circle-title">UI Design</h3>
								<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit.</p>
							</div>
							<div class="inner-pchart col-md-4">
								<div class="circle" id="circle-2"></div>
								<h3 class="circle-title">Web Development</h3>
								<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit.</p>
							</div>
							<div class="inner-pchart col-md-4">
								<div class="circle" id="circle-3"></div>
								<h3 class="circle-title">JavaScript</h3>
								<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit.</p>
							</div>
						</div>
						<!-- End Pie Charts v2 -->

						<!-- Counters -->
						<div class="main-counters margin-bottom-40">
							<div class="headline">
								<h2>Counters</h2>
							</div>

							<div class="row margin-bottom-40">
								<div class="counters col-md-3 col-sm-3">
									<span class="counter">52147</span>
									<h4>Code Lines</h4>
								</div>
								<div class="counters col-md-3 col-sm-3">
									<span class="counter">24583</span>
									<h4>Projects</h4>
								</div>
								<div class="counters col-md-3 col-sm-3">
									<span class="counter">7349</span>
									<h4>Working Hours</h4>
								</div>
								<div class="counters col-md-3 col-sm-3">
									<span class="counter">87904</span>
									<h4>Job Offers</h4>
								</div>
							</div>

							<div class="margin-bottom-50">
								<hr>
							</div>

							<div class="row">
								<div class="counters col-md-3 col-sm-3">
									<span class="counter-icon"><i class="fa fa-gift rounded"></i></span>
									<span class="counter">3254</span>
								</div>
								<div class="counters col-md-3 col-sm-3">
									<span class="counter-icon"><i
										class="fa fa-coffee rounded"></i></span> <span class="counter">7068</span>
								</div>
								<div class="counters col-md-3 col-sm-3">
									<span class="counter-icon"><i
										class="fa fa-thumbs-up rounded"></i></span> <span class="counter">5389</span>
								</div>
								<div class="counters col-md-3 col-sm-3">
									<span class="counter-icon"><i
										class="fa fa-comments rounded"></i></span> <span class="counter">9172</span>
								</div>
							</div>
						</div>
						<!-- End Counters -->
					</div>
				</div>

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
		src="<c:url value="/resources/assets/plugins/counter/waypoints.min.js"/>"></script>
	<script type="text/javascript"
		src="<c:url value="/resources/assets/plugins/counter/jquery.counterup.min.js"/>"></script>
	<script type="text/javascript"
		src="<c:url value="/resources/assets/plugins/circles-master/circles.js"/>"></script>
	<!-- JS Page Level -->
	<script type="text/javascript"
		src="<c:url value="/resources/assets/js/app.js"/>"></script>
	<script type="text/javascript"
		src="<c:url value="/resources/assets/js/plugins/circles-master.js"/>"></script>
	<script type="text/javascript">
		jQuery(document).ready(function() {
			App.init();
			App.initCounter();
			CirclesMaster.initCirclesMaster1();
			CirclesMaster.initCirclesMaster2();
			$('.menu-link').bigSlide();
		});
	</script>
	<script type="text/javascript"
		src="<c:url value="/resources/assets/js/microblogging/appAngular.js"/>"></script>
	<script type="text/javascript"
		src="<c:url value="/resources/assets/js/plugins/jquery.tagcloud.js"/>"></script>
	<script type="text/javascript"
		src="<c:url value="/resources/assets/js/plugins/bootstrap-tag-cloud.js"/>"></script>
	<script type="text/javascript"
		src="<c:url value="/resources/assets/js/plugins/scroll-nav.js"/>"></script>
	<script type="text/javascript"
		src="<c:url value="/resources/assets/js/plugins/bigSlide.js"/>"></script>
	<script type="text/javascript"
		src="<c:url value="/resources/assets/js/plugins/paginator.js"/>"></script>

	<!--[if lt IE 9]>
    <script src="<c:url value="/resources/assets/plugins/respond.js"/>"></script>
<![endif]-->
</body>
</html>