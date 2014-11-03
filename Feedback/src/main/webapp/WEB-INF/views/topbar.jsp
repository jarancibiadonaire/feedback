<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="topbar">
	<div class="container margin-bottom-20">
		<!-- Topbar Navigation -->
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target=".navbar-responsive-collapse">
				<span class="sr-only">Toggle navigation</span> <span
					class="fa fa-bars"></span>
			</button>
			<a class="navbar-brand" href='<c:url value="/"/>'> <img
				id="logo-header"
				src="<c:url value="/resources/assets/img/logo1-default.png"/>"
				alt="Logo" class="logo-header">
			</a>
		</div>
		<sec:authorize access="isAuthenticated()">
			<div class="input-group search-bar">
				<input type="text" class="form-control"
					placeholder="Busca lo que necesitas"> <span
					class="input-group-btn">
					<button class="btn-u" type="button">
						<i class="fa fa-search"></i>
					</button>
				</span>
			</div>
		</sec:authorize>
		<ul class="loginbar pull-right">
			<sec:authorize access="isAuthenticated()">
				<!-- For login user -->
				<c:url value="/j_spring_security_logout" var="logoutUrl" />
				<form action="${logoutUrl}" method="post" id="logoutForm">
					<input type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token}" />
				</form>
				<script>
					function formSubmit() {
						document.getElementById("logoutForm").submit();
					}
				</script>
				<c:if test="${pageContext.request.userPrincipal.name != null}">
					<i class="fa fa-user"></i>
					<li>Bienvenid@ <span class="green">${pageContext.request.userPrincipal.name}</span></li>
					<li class="topbar-devider"></li>
					<li><a href="javascript:formSubmit()" class="green"> Salir</a></li>
				</c:if>

			</sec:authorize>
			<sec:authorize access="!isAuthenticated()">
				<div class="text-right login-index">
					<form class="form-inline sky-form"
						action="<c:url value='/j_spring_security_check' />" method='POST'>
						<div class="form-group">
							<input type="text" class="form-control" name="username"
								placeholder="Nombre usuario">
						</div>
						<div class="form-group">
							<input type="password" class="form-control" name="password"
								placeholder="Contraseña">
						</div>
						<button type="submit" class="form-group btn-u">Ingresar</button>
						<input type="hidden" name="${_csrf.parameterName}"
							value="${_csrf.token}" />
					</form>
				</div>
			</sec:authorize>

		</ul>
		<!-- End Topbar Navigation -->
	</div>
</div>