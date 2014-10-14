<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9"> <![endif]-->
<!--[if !IE]><!-->
<html lang="es" xmlns:th="http://www.thymeleaf.org"
	xmlns:social="http://spring.io/springsocial"
	xmlns:layout="http://www.ultraq.net.nz/thymeleaf/layout"
	layout:decorator="layout">
<!--<![endif]-->
<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<head>
<title>Ingresar Feedback</title>

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
	href="<c:url value="/resources/assets/css/pages/page_log_reg_v2.css"/>">

<!-- CSS Theme -->
<link rel="stylesheet"
	href="<c:url value="/resources/assets/css/themes/default.css"/>"
	id="style_color">

<!-- CSS Customization -->
<link rel="stylesheet"
	href="<c:url value="/resources/assets/css/custom.css"/>">
</head>
<body>
	<sec:authorize access="isAuthenticated()">
		<c:redirect url="/welcome" />
	</sec:authorize>
	<!--=== Content Part ===-->
	<div class="container">
		<a class="navbar-brand" href="index"> <img id="logo-header"
			src="<c:url value="/resources/assets/img/logo1-default.png"/>"
			alt="Logo">
		</a>
		<!--Reg Block-->
		<div class="reg-block">
			<div class="reg-block-header">
				<h2>Ingresa a Feedback</h2>
				<ul class="social-icons text-center">
					<li><a class="rounded-x social_facebook"
						data-original-title="Facebook"
						href="<c:url value="/auth/facebook"/>"></a></li>
					<li><a class="rounded-x social_twitter"
						data-original-title="Twitter"
						href="<c:url value="/auth/twitter"/>"></a></li>
					<li><a class="rounded-x social_googleplus"
						data-original-title="Google Plus" href="#"></a></li>
					<li><a class="rounded-x social_linkedin"
						data-original-title="Linkedin" href="#"></a></li>
				</ul>
				<p>
					¿No tienes cuenta aún? <a class="color-green"
						href="<c:url value="/registration"/>">Regístrate aquí.</a>
				</p>
			</div>

			<form name='loginForm'
				action="<c:url value='/j_spring_security_check' />" method='POST'>
				<c:if test="${not empty success}">
					<div class="alert alert-success fade in">${success}</div>
				</c:if>
				<div class="input-group margin-bottom-20">
					<span class="input-group-addon"><i class="fa fa-user"></i></span> <input
						type="text" class="form-control" placeholder="Nombre de usuario"
						name="username">
				</div>
				<div class="input-group margin-bottom-20">
					<span class="input-group-addon"><i class="fa fa-lock"></i></span> <input
						type="password" class="form-control" placeholder="Contraseña"
						name="password">
				</div>
				<c:if test="${not empty error}">
					<div class="alert alert-danger fade in">${error}</div>
				</c:if>
				<hr>
				<div class="row">
					<div class="col-md-10 col-md-offset-1">
						<button type="submit" class="btn-u btn-block">Ingresar</button>
					</div>
				</div>
				<input type="hidden" name="${_csrf.parameterName}"
					value="${_csrf.token}" />

			</form>
		</div>
		<!--End Reg Block-->
	</div>
	<!--/container-->
	<!--=== End Content Part ===-->

	<!-- JS Global Compulsory -->
	<script
		src="<c:url value="/resources/assets/plugins/jquery-1.10.2.min.js"/>"></script>
	<script
		src="<c:url value="/resources/assets/plugins/jquery-migrate-1.2.1.min.js"/>"></script>
	<script
		src="<c:url value="/resources/assets/plugins/bootstrap/js/bootstrap.min.js"/>"></script>

	<!-- JS Implementing Plugins -->
	<script type="text/javascript"
		src="<c:url value="/resources/assets/plugins/countdown/jquery.countdown.js"/>"></script>
	<script type="text/javascript"
		src="<c:url value="/resources/assets/plugins/backstretch/jquery.backstretch.min.js"/>"></script>
	<script type="text/javascript">
		$
				.backstretch(
						[
								"<c:url value='/resources/assets/img/city/1035906164_bcb96a45ba_o.jpg'/>",
								"<c:url value='/resources/assets/img/city/7188510829_1b062674c2_k.jpg'/>", ],
						{
							fade : 1000,
							duration : 7000
						});
	</script>

	<!-- JS Page Level -->
	<script src="<c:url value="/resources/assets/js/app.js"/>"></script>
	<script>
		jQuery(document).ready(function() {
			App.init();
		});
	</script>

	<!--[if lt IE 9]>
    <script src="<c:url value="/resources/assets/js/respond.js"/>"></script>
	<![endif]-->
	<script type="text/javascript">
		var _gaq = _gaq || [];
		_gaq.push([ '_setAccount', 'UA-29166220-1' ]);
		_gaq.push([ '_setDomainName', 'htmlstream.com' ]);
		_gaq.push([ '_trackPageview' ]);

		(function() {
			var ga = document.createElement('script');
			ga.type = 'text/javascript';
			ga.async = true;
			ga.src = ('https:' == document.location.protocol ? 'https://ssl'
					: 'http://www')
					+ '.google-analytics.com/ga.js';
			var s = document.getElementsByTagName('script')[0];
			s.parentNode.insertBefore(ga, s);
		})();
	</script>
</body>
</html>