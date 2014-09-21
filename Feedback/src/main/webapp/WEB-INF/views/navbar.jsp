<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="navbar navbar-default" role="navigation">
	<div class="container">
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target=".navbar-responsive-collapse">
				<span class="sr-only">Toggle navigation</span> <span
					class="fa fa-bars"></span>
			</button>
			<a class="navbar-brand" href="welcome"> <img id="logo-header"
				src="<c:url value="/resources/assets/img/logo1-default.png"/>"
				alt="Logo">
			</a>
		</div>

		<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="collapse navbar-collapse navbar-responsive-collapse">
			<ul class="nav navbar-nav">
				<!-- Home -->
				<li id="welcome"><a href="welcome"> Inicio </a></li>
				<!-- End Home -->

				<!-- Microblogging -->
				<li id="microblogging"><a href="microblogging"> Microblogging </a></li>
				<!-- End Microblogging -->

				<!-- Timeline -->
				<li id="timeline"><a href="timeline"> Timeline </a></li>
				<!-- End Timeline -->

				<!-- ConceptMap -->
				<li id="conceptmap"><a href="conceptmap"> ConceptMap </a></li>
				<!-- Ens ConceptMap -->

				<!-- Statistics -->
				<li id="statistics"><a href="statistics"> Estadística </a></li>
				<!-- End Statistics -->
			</ul>
		</div>
		<!--/navbar-collapse-->
	</div>
</div>