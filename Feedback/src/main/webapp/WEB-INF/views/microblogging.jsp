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
<!--[if lt IE 9]>
        <link rel="stylesheet" href="<c:url value="/resources/assets/css/sky-forms-ie8.css"/>">
    <![endif]-->

<!-- CSS Page Style -->
<link rel="stylesheet"
	href="<c:url value="/resources/assets/css/pages/page_search.css"/>">

<!-- CSS Theme -->
<link rel="stylesheet"
	href="<c:url value="/resources/assets/css/themes/default.css"/>"
	id="style_color">

<!-- CSS Customization -->
<link rel="stylesheet"
	href="<c:url value="/resources/assets/css/custom.css"/>">

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
		<div class="container content content-without-padding">
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
					<form:form action='${pageContext.request.contextPath}/publish_feed'
						class="sky-form" id="feed-form">
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
	<!-- JS Page Level -->
	<script type="text/javascript"
		src="<c:url value="/resources/assets/js/app.js"/>"></script>
	<script type="text/javascript">
		jQuery(document).ready(function() {
			App.init();
			activeNavbar();
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
	<script src="https://maps.googleapis.com/maps/api/js?v=3.exp"></script>
	<script type="text/javascript"
		src="<c:url value="/resources/assets/js/microblogging/microblogging.js"/>"></script>
</body>
</html>