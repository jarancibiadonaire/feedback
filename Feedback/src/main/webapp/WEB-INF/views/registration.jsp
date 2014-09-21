<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9"> <![endif]-->
<!--[if !IE]><!-->
<html lang="es">
<!--<![endif]-->
<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page session="true"%>
<head>
<title>Registrase en Feedback</title>

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
        <link rel="stylesheet" href="<c:url value="/resources/assets/plugins/sky-forms/version-2.0.1/css/sky-forms-ie8.css"/>">
    <![endif]-->
<!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
        <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
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
		<c:redirect url="/" />
	</sec:authorize>
	<!--=== Content Part ===-->
	<div class="container">
		<a class="navbar-brand" href="welcome"> <img id="logo-header"
			src="<c:url value="/resources/assets/img/logo1-default.png"/>"
			alt="Logo">
		</a>
		<!--Reg Block-->
		<div class="reg-block">
			<div class="reg-block-header">
				<h2>Reg�strate en Feedback</h2>
				<ul class="social-icons text-center">
					<li><a class="rounded-x social_facebook"
						data-original-title="Facebook" href="#"></a></li>
					<li><a class="rounded-x social_twitter"
						data-original-title="Twitter" href="#"></a></li>
					<li><a class="rounded-x social_googleplus"
						data-original-title="Google Plus" href="#"></a></li>
					<li><a class="rounded-x social_linkedin"
						data-original-title="Linkedin" href="#"></a></li>
				</ul>
				<p>
					�Ya est�s registrado? <a class="color-green"
						href="<c:url value="/login"/>">Ingresa aqu�</a> para iniciar
					sesi�n.
				</p>
			</div>
			<form:form
				action='${pageContext.request.contextPath}/check_registration'
				id="registration-form" class="sky-form" method="post">
				<c:if test="${error!=null}">
					<div class="alert alert-danger fade in">${error}</div>
				</c:if>
				<div class="input-group margin-bottom-15">
					<span class="input-group-addon"><i class="fa fa-user"></i></span>
					<form:input name="firstName" id="firstName" path="firstName"
						type="text" class="form-control" placeholder="Nombre" />
				</div>
				<div class="input-group margin-bottom-15">
					<span class="input-group-addon"><i class="fa fa-user"></i></span>
					<form:input name="lastName" id="lastName" path="lastName"
						type="text" class="form-control" placeholder="Apellido" />
				</div>
				<div class="input-group margin-bottom-15">
					<span class="input-group-addon"><i class="fa fa-user"></i></span>
					<form:select path="sex" items="${sexs}" class="form-control" />
				</div>
				<div class="input-group margin-bottom-15">
					<span class="input-group-addon"><i class="fa fa-user"></i></span>
					<form:input name="userName" id="userName" path="userName"
						type="text" class="form-control" placeholder="Nombre de usuario" />
				</div>
				<div class="input-group margin-bottom-15">
					<span class="input-group-addon"><i class="fa fa-envelope"></i></span>
					<form:input type="text" class="form-control" placeholder="Email"
						name="email" id="email" path="email" />
				</div>
				<div class="input-group margin-bottom-15">
					<span class="input-group-addon"><i class="fa fa-lock"></i></span>
					<form:input type="password" class="form-control"
						placeholder="Contrase�a" name="password" id="password"
						path="password" />
				</div>
				<div class="input-group margin-bottom-15">
					<span class="input-group-addon"><i class="fa fa-key"></i></span>
					<form:input type="password" class="form-control"
						placeholder="Repita la contrase�a" name="repassword"
						id="repassword" path="repassword" />
				</div>
				<hr>
				<div class="input-group margin-bottom-15">
					<label class="checkbox"> <input name="terms" id="terms"
						type="checkbox" /> <i></i>Acepto los <a target="_blank"
						href="page_terms.html">T�rminos y condiciones</a></label>
				</div>
				<div class="row">
					<div class="col-md-10 col-md-offset-1">
						<button type="submit" class="btn-u btn-block">Registrar</button>
					</div>
				</div>
			</form:form>
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
		$.backstretch([ "<c:url value='/resources/assets/img/bg/5.jpg'/>",
				"<c:url value='/resources/assets/img/bg/4.jpg'/>", ], {
			fade : 1000,
			duration : 7000
		});
	</script>

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

	<!-- JS Page Level -->
	<script src="<c:url value="/resources/assets/js/app.js"/>"></script>
	<script
		src="<c:url value="/resources/assets/js/forms/registration.js"/>"></script>
	<script>
		jQuery(document).ready(function() {
			App.init();
			RegistrationForm.initRegistrationForm();
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