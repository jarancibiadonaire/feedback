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
			<div id="map" class="map"></div>
		</div>
		<div class="modal fade" id="responsive" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title text-center" id="myModalLabel">Nuevo
							feed</h4>
					</div>
					<form id="addFeed" action="add_feed" class="sky-form" method="get">
						<div class="modal-body">
							<div>
								<div class="servive-block servive-block-default">
									<fieldset>
										<section>
											<label class="label">Título</label> <label class="input">
												<input type="text" name="title">
											</label>
										</section>
										<section>
											<label class="label">Descripción</label> <label
												class="textarea textarea-expandable"> <textarea
													rows="3" name="description"></textarea>
											</label>
										</section>
										<section>
											<label class="label">Visibilidad del feed</label>
											<div class="inline-group">
												<label class="radio"><input type="radio"
													value="public" name="visibility" checked><i
													class="rounded-x"></i>Público</label> <label class="radio"><input
													type="radio" value="private" name="visibility"><i
													class="rounded-x"></i>Privado</label>
											</div>
										</section>
									</fieldset>
								</div>
							</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn-u btn-u-default"
								data-dismiss="modal">Salir</button>
							<button type="submit" class="btn-u btn-u-primary">Ingresar</button>
						</div>
					</form>
				</div>
			</div>
		</div>

		<!--/container-->
		<!--=== End Content ===-->

		<!--=== Copyright ===-->
		<%-- <jsp:include page="copyright.jsp"></jsp:include> --%>
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