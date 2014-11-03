<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en">
<!--<![endif]-->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<head>
<title>Error!</title>

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

<!-- CSS Page Style -->
<link rel="stylesheet"
	href="<c:url value="/resources/assets/css/pages/page_error4_404.css"/>">

<!-- CSS Theme -->
<link rel="stylesheet"
	href="<c:url value="/resources/assets/css/themes/default.css"/>"
	id="style_color">

<!-- CSS Customization -->
<link rel="stylesheet"
	href="<c:url value="/resources/assets/css/custom.css"/>">
</head>

<body>

	<!--=== Error V4 ===-->
	<div class="container content">
		<!--Error Block-->
		<div class="row">
			<div class="col-md-8 col-md-offset-2">
				<div class="error-v4">
					<div>
						<a href="#"><img
							src="<c:url value="/resources/assets/img/logo1-default.png"/>"
							alt=""></a>
					</div>
					<h1>404</h1>
					<span class="sorry">Ups, la página que buscas no se
						encuentra!</span>
					<div class="row">
						<div class="col-md-6 col-md-offset-3">
							<a class="btn-u btn-brd btn-u-light" href="<c:url value="/"/>">
								Volver al Inicio</a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!--/container-->
	<!--End Error Block-->

	<!--=== Sticky Footer ===-->
	<div class="container sticky-footer">
		<p class="copyright-space">2014 &copy; Feedback. Todos los
			derechos reservados.</p>
	</div>
	<!--=== End Sticky Footer ===-->

	<!-- JS Global Compulsory -->
	<script type="text/javascript"
		src="<c:url value="/resources/assets/plugins/jquery-1.10.2.min.js"/>"></script>
	<script type="text/javascript"
		src="<c:url value="/resources/assets/plugins/jquery-migrate-1.2.1.min.js"/>"></script>
	<script type="text/javascript"
		src="<c:url value="/resources/assets/plugins/bootstrap/js/bootstrap.min.js"/>"></script>
	<!-- JS Implementing Plugins -->
	<script type="text/javascript"
		src="<c:url value="/resources/assets/plugins/backstretch/jquery.backstretch.min.js"/>"></script>
	<script type="text/javascript">
		$
				.backstretch([ "<c:url value='/resources/assets/img/blur/img2.jpg'/>" ])
	</script>
	<!-- JS Page Level -->
	<script type="text/javascript"
		src="<c:url value="/resources/assets/js/app.js"/>"></script>
	<script type="text/javascript">
		jQuery(document).ready(function() {
			App.init();
		});
	</script>
	<!--[if lt IE 9]>
    <script src="<c:url value="/resources/assets/plugins/respond.js"/>"></script>
    <script src="<c:url value="/resources/assets/plugins/html5shiv.js"/>"></script>
	<![endif]-->
</body>
</html>
