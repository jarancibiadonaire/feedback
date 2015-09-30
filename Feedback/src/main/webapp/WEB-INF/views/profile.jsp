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
<title>Feedback</title>

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
		<!-- modal tags -->	
		<jsp:include page="tags.jsp">
			<jsp:param name="url" value="config_tag"/>
		</jsp:include>	
		<!-- modal tags -->
		<!--=== Content Part ===-->
		<div class="profile">
			<div class="container content">
				<div class="row">
				<!-- left panel -->
				<jsp:include page="panel.jsp"></jsp:include>
                <!--Left Sidebar-->
                <div class="col-md-3 md-margin-bottom-40">
                    <img class="img-responsive profile-img margin-bottom-20" src="<c:url value="/resources/assets/img/user.jpg"/>" alt="">

                    <div>
                        <div class="servive-block servive-block-u">
                            <i class="icon-custom icon-color-light rounded-x fa fa-thumbs-o-up"></i>
                            <h2 class="heading-md">${user.userName}</h2>
                            <p>Usuario desde:</p>
                            <p><i><fmt:formatDate value="${user.createdDate}" dateStyle="long"/></i></p>                      
                        </div>
                    </div>   

                    <div class="panel-heading-v2 overflow-h">
                        <h2 class="heading-xs pull-left"><i class="fa fa-bar-chart-o"></i> Estad&#237;stica</h2>
                        <a href="#"><i class="fa fa-cog pull-right"></i></a>
                    </div>
                    <h3 class="heading-xs">Feeds <span class="pull-right">15</span></h3>
                    <div class="progress progress-u progress-xxs">
                        <div style="width: 100%" aria-valuemax="100" aria-valuemin="0" aria-valuenow="100" role="progressbar" class="progress-bar progress-bar-u">
                        </div>
                    </div>
                    <h3 class="heading-xs">Comentarios <span class="pull-right">7</span></h3>
                    <div class="progress progress-u progress-xxs">
                        <div style="width: 100%" aria-valuemax="100" aria-valuemin="0" aria-valuenow="100" role="progressbar" class="progress-bar progress-bar-blue">
                        </div>
                    </div>
                    <h3 class="heading-xs">Sesiones <span class="pull-right">2</span></h3>
                    <div class="progress progress-u progress-xxs margin-bottom-40">
                        <div style="width: 100%" aria-valuemax="100" aria-valuemin="0" aria-valuenow="100" role="progressbar" class="progress-bar progress-bar-dark">
                        </div>
                    </div>                    
                </div>
                <!--End Left Sidebar-->
                
                <div class="col-md-9">
                    <!--Profile Body-->
                    <div class="profile-body margin-bottom-20">
                        <div class="tab-v1">      
                            <div class="tab-content">
                                <div id="profile" class="profile-edit tab-pane fade in active">
                                    <h2 class="heading-md">Configure su nombre, correo y contrase&#241;a.</h2>
                                    <p>Recuerda guardar los cambios para actualizar los datos.</p>
                                    </br>
                                    <dl class="dl-horizontal">
                                        <dt><strong>Nombre </strong></dt>
                                        <dd>
                                            ${user.firstName} 
                                            <span>
                                                <a class="pull-right" href="#">
                                                    <i class="fa fa-pencil"></i>
                                                </a>
                                            </span>
                                        </dd>
                                        <hr>
                                        <dt><strong>Apellido </strong></dt>
                                        <dd>
                                            ${user.lastName} 
                                            <span>
                                                <a class="pull-right" href="#">
                                                    <i class="fa fa-pencil"></i>
                                                </a>
                                            </span>
                                        </dd>
                                        <hr>
                                        <dt><strong>Sexo </strong></dt>
                                        <dd>
                                            ${user.sexName}  
                                            <span>
                                                <a class="pull-right" href="#">
                                                    <i class="fa fa-pencil"></i>
                                                </a>
                                            </span>
                                        </dd>
                                        <hr>
                                        <dt><strong>Email </strong></dt>
                                        <dd>
                                            ${user.email}  
                                            <span>
                                                <a class="pull-right" href="#">
                                                    <i class="fa fa-pencil"></i>
                                                </a>
                                            </span>
                                        </dd>
                                        <hr>
                                        <dt><strong>Actual Contrase&#241;a </strong></dt>
                                        <dd>
                                            ****** 
                                            <span>
                                                <a class="pull-right" href="#">
                                                    <i class="fa fa-pencil"></i>
                                                </a>
                                            </span>
                                        </dd>
                                        <hr>
                                        <dt><strong>Nueva Contrase&#241;a </strong></dt>
                                        <dd>
                                            ******
                                            <span>
                                            </span>
                                        </dd>
                                        <hr>
                                        <dt><strong>Repetir contrase&#241;a </strong></dt>
                                        <dd>
                                            ******
                                            <span>
                                            </span>
                                        </dd>
                                        <hr>                                        
                                    </dl>
                                    <button type="button" class="btn-u">Guardar</button>
                                </div>                                
                            </div>
                        </div>    
                    </div>
                    <!--End Profile Body-->
                </div>
            </div><!--/end row-->
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

	<script type="text/javascript"
		src="<c:url value="/resources/assets/js/soa/jquery.json-2.3.min.js"/>"></script>
	<!-- JS Page Level -->
	<script type="text/javascript"
		src="<c:url value="/resources/assets/js/app.js"/>"></script>
	<script type="text/javascript">
		jQuery(document).ready(function() {
			App.init();
			$('.menu-link').bigSlide();
		});		
	</script>
	<script type="text/javascript"
		src="<c:url value="/resources/assets/js/plugins/bigSlide.js"/>"></script>
	<script type="text/javascript"
		src="<c:url value="/resources/assets/js/plugins/paginator.js"/>"></script> 	
	<!--[if lt IE 9]>
    <script src="<c:url value="/resources/assets/plugins/respond.js"/>"></script>
<![endif]-->
</body>
</html>