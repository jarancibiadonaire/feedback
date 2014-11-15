<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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