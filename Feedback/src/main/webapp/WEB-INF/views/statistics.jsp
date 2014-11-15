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
		<!-- modal tags -->
		<jsp:include page="tags.jsp">
			<jsp:param name="url" value="statistics/config_tag" />
		</jsp:include>
		<!-- modal tags -->
		<div class="container content content-without-padding home-container">
			<div class="row">
				<!-- left panel -->
				<jsp:include page="panel.jsp"></jsp:include>
				<!-- left panel -->
				<div class="col-md-12">
					<div class="tag-box tag-box-v3">
						<div class="margin-bottom-40">
							<div class="headline">
								<h2>Tags</h2>
							</div>
							<div>
								<table id="datatable" class="hidden">
									<thead>
										<tr>
											<th></th>
											<th>Frecuencia</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${statSummary.tagsData.tagsFrecuency}"
											var="tagFrec">
											<tr>
												<th>${tagFrec.data}</th>
												<td>${tagFrec.value}</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
								<div id="chart"></div>
								<table id="datatable2" class="hidden">
									<thead>
										<tr>
											<th></th>
											<th>Frecuencia</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${statSummary.tagsData.tagsCommented}"
											var="tagComment">
											<tr>
												<th>${tagComment.data}</th>
												<td>${tagComment.value}</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
								<div id="chart2"></div>
							</div>
						</div>
						<div class="margin-bottom-40">
							<div class="headline">
								<h2>Feeds</h2>
							</div>
							<p>
								Please note you should use four pie charts in the first version.
								Otherwise you need to change settings from
								<code>circles-master.js</code>
								However, you are able use second version of pie charts for easy
								self control.
							</p>
						</div>
						<div class="margin-bottom-40">
							<div class="headline">
								<h2>Usuarios</h2>
							</div>
							<p>
								Please note you should use four pie charts in the first version.
								Otherwise you need to change settings from
								<code>circles-master.js</code>
								However, you are able use second version of pie charts for easy
								self control.
							</p>
						</div>
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

	<!-- JS Page Level -->
	<script type="text/javascript"
		src="<c:url value="/resources/assets/js/app.js"/>"></script>
	<script type="text/javascript"
		src="<c:url value="/resources/assets/js/plugins/circles-master.js"/>"></script>
	<script type="text/javascript">
		jQuery(document).ready(function() {
			App.init();
			$('.menu-link').bigSlide();
			$('#chart').highcharts({
		        data: {
		            table: document.getElementById('datatable')
		        },
		        chart: {
		            type: 'column'
		        },
		        title: {
		            text: 'Tags más asociados en Feedback'
		        },
		        xAxis: {
		            type: 'category',
		            labels: {
		                rotation: -45
		            }
		        },
		        yAxis: {
		            allowDecimals: false,
		            title: {
		                text: 'Frecuencia'
		            }
		        },
		        tooltip: {
		            formatter: function () {
		                return '<b>' + this.series.name + '</b><br/>' +
		                    this.point.y + ' asociaciones';
		            }
		        }
		    });
			$('#chart2').highcharts({
		        data: {
		            table: document.getElementById('datatable2')
		        },
		        chart: {
		            type: 'column'
		        },
		        title: {
		            text: 'Tags más comentados en Feedback'
		        },
		        xAxis: {
		            type: 'category',
		            labels: {
		                rotation: -45
		            }
		        },
		        yAxis: {
		            allowDecimals: false,
		            title: {
		                text: 'N° Comentarios'
		            }
		        },
		        tooltip: {
		            formatter: function () {
		                return '<b>' + this.series.name + '</b><br/>' +
		                    this.point.y + ' comentarios';
		            }
		        }
		    });
			
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
	<script src="http://code.highcharts.com/highcharts.js"></script>
	<script src="http://code.highcharts.com/modules/data.js"></script>
	<script src="http://code.highcharts.com/modules/exporting.js"></script>

	<!--[if lt IE 9]>
    <script src="<c:url value="/resources/assets/plugins/respond.js"/>"></script>
<![endif]-->
</body>
</html>