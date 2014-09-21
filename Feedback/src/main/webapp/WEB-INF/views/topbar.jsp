<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="topbar">
	<div class="container">
		<!-- Topbar Navigation -->
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
					<li>Bienvenido ${pageContext.request.userPrincipal.name}</li>
					<li class="topbar-devider"></li>
					<li><a href="javascript:formSubmit()"> Salir</a></li>
				</c:if>
			</sec:authorize>
			<sec:authorize access="!isAuthenticated()">
				<li><a href="<c:url value="/login"/>">Ingresar</a></li>
			</sec:authorize>

		</ul>
		<!-- End Topbar Navigation -->
	</div>
</div>