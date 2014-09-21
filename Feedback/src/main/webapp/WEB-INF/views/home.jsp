<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en">
<!--<![endif]-->
<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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

		<!--=== Search Block ===-->
		<div class="search-block parallaxBg">
			<div class="container">
				<div class="col-md-6 col-md-offset-3">
					<h1>
						Discover <span class="color-green">new</span> things ...
					</h1>

					<div class="input-group">
						<input type="text" class="form-control"
							placeholder="Search words with regular expressions ...">
						<span class="input-group-btn">
							<button class="btn-u" type="button">
								<i class="fa fa-search"></i>
							</button>
						</span>
					</div>

					<form action="" class="sky-form page-search-form">
						<div class="inline-group">
							<label class="checkbox"><input type="checkbox"
								name="checkbox-inline" checked><i></i>Recent</label> <label
								class="checkbox"><input type="checkbox"
								name="checkbox-inline"><i></i>Related</label> <label
								class="checkbox"><input type="checkbox"
								name="checkbox-inline"><i></i>Popular</label> <label
								class="checkbox"><input type="checkbox"
								name="checkbox-inline"><i></i>Most Common</label>
						</div>
					</form>
				</div>
			</div>
		</div>
		<!--/container-->
		<!--=== End Search Block ===-->

		<!--=== Content ===-->
		<div class="container content">
			<!-- Begin Service Block -->
			<div class="row margin-bottom-40">
				<div class="col-md-4">
					<div class="easy-block-v3 service-or">
						<div class="service-bg"></div>
						<i class="fa fa-globe"></i>
						<div class="inner-faq-b">
							<h2>Web Search</h2>
							<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit.
								Vivamus dapibus justo vel tincidunt consectetur.</p>
						</div>
					</div>
				</div>
				<div class="col-md-4">
					<div class="easy-block-v3 service-or">
						<div class="service-bg"></div>
						<i class="icon-camera"></i>
						<div class="inner-faq-b">
							<h2>Photos &amp; Video</h2>
							<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit.
								Vivamus dapibus justo vel tincidunt consectetur.</p>
						</div>
					</div>
				</div>
				<div class="col-md-4">
					<div class="easy-block-v3 service-or">
						<div class="service-bg"></div>
						<i class="fa fa-map-marker"></i>
						<div class="inner-faq-b">
							<h2>Map Location</h2>
							<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit.
								Vivamus dapibus justo vel tincidunt consectetur.</p>
						</div>
					</div>
				</div>
			</div>
			<!-- End Service Block -->

			<!-- Top Categories -->
			<div class="headline">
				<h2>Top Categories</h2>
			</div>
			<div class="row category margin-bottom-20">
				<!-- Info Blocks -->
				<div class="col-md-4">
					<div class="content-boxes-v3">
						<i
							class="icon-custom icon-sm rounded-x icon-bg-light-grey icon-line icon-badge"></i>
						<div class="content-boxes-in-v3">
							<h3>
								<a href="#"> Top 100 templates</a>
							</h3>
							<p>At vero eos et accusamus et iusto odio dignissimos ducimus
								qui blanditiis praesentium</p>
						</div>
					</div>
					<div class="content-boxes-v3">
						<i
							class="icon-custom icon-sm rounded-x icon-bg-light-grey fa fa-hdd-o"></i>
						<div class="content-boxes-in-v3">
							<h3>
								<a href="#"> Wikipedia</a>
							</h3>
							<p>At vero eos et accusamus et iusto odio dignissimos ducimus
								qui blanditiis praesentium</p>
						</div>
					</div>
					<div class="content-boxes-v3">
						<i
							class="icon-custom icon-sm rounded-x icon-bg-light-grey fa fa-mobile"></i>
						<div class="content-boxes-in-v3">
							<h3>
								<a href="#"> Web design</a>
							</h3>
							<p>At vero eos et accusamus et iusto odio dignissimos ducimus
								qui blanditiis praesentium</p>
						</div>
					</div>
					<div class="content-boxes-v3">
						<i
							class="icon-custom icon-sm rounded-x icon-bg-light-grey icon-line icon-graduation"></i>
						<div class="content-boxes-in-v3">
							<h3>
								<a href="#"> Education</a>
							</h3>
							<p>At vero eos et accusamus et iusto odio dignissimos ducimus
								qui blanditiis praesentium</p>
						</div>
					</div>
				</div>
				<!-- End Info Blocks -->

				<!-- Info Blocks -->
				<div class="col-md-4">
					<div class="content-boxes-v3">
						<i
							class="icon-custom icon-sm rounded-x icon-bg-light-grey fa fa-bell-o"></i>
						<div class="content-boxes-in-v3">
							<h3>
								<a href="#"> Hi-Tech</a>
							</h3>
							<p>At vero eos et accusamus et iusto odio dignissimos ducimus
								qui blanditiis praesentium</p>
						</div>
					</div>
					<div class="content-boxes-v3">
						<i
							class="icon-custom icon-sm rounded-x icon-bg-light-grey fa fa-thumbs-o-up"></i>
						<div class="content-boxes-in-v3">
							<h3>
								<a href="#"> Yahoo</a>
							</h3>
							<p>At vero eos et accusamus et iusto odio dignissimos ducimus
								qui blanditiis praesentium</p>
						</div>
					</div>
					<div class="content-boxes-v3">
						<i
							class="icon-custom icon-sm rounded-x icon-bg-light-grey icon-line icon-playlist"></i>
						<div class="content-boxes-in-v3">
							<h3>
								<a href="#"> Music</a>
							</h3>
							<p>At vero eos et accusamus et iusto odio dignissimos ducimus
								qui blanditiis praesentium</p>
						</div>
					</div>
					<div class="content-boxes-v3">
						<i
							class="icon-custom icon-sm rounded-x icon-bg-light-grey fa fa-lightbulb-o"></i>
						<div class="content-boxes-in-v3">
							<h3>
								<a href="#"> Engineering</a>
							</h3>
							<p>At vero eos et accusamus et iusto odio dignissimos ducimus
								qui blanditiis praesentium</p>
						</div>
					</div>
				</div>
				<!-- End Info Blocks -->

				<div class="col-md-4">
					<!-- Begin Section-Block -->
					<div class="section-block">
						<div class="text-center">
							<i
								class="icon-custom icon-sm icon-bg-light-grey line-icon icon-graph"></i>
							<h2>Popular Search</h2>
							<p>At vero eos et accusamus et iusto odio dignissimos ducimus
								qui blanditiis praesentium</p>
						</div>

						<!-- Progress Bar -->
						<h3 class="heading-xs no-top-space">
							Web Design <span class="pull-right">88%</span>
						</h3>
						<div class="progress progress-u progress-xs">
							<div style="width: 88%" aria-valuemax="100" aria-valuemin="0"
								aria-valuenow="88" role="progressbar"
								class="progress-bar progress-bar-u"></div>
						</div>

						<h3 class="heading-xs no-top-space">
							PHP/WordPress <span class="pull-right">76%</span>
						</h3>
						<div class="progress progress-u progress-xs">
							<div style="width: 76%" aria-valuemax="100" aria-valuemin="0"
								aria-valuenow="76" role="progressbar"
								class="progress-bar progress-bar-u"></div>
						</div>

						<h3 class="heading-xs no-top-space">
							HTML/CSS <span class="pull-right">97%</span>
						</h3>
						<div class="progress progress-u progress-xs">
							<div style="width: 97%" aria-valuemax="100" aria-valuemin="0"
								aria-valuenow="97" role="progressbar"
								class="progress-bar progress-bar-u"></div>
						</div>
						<!-- End Progress Bar -->

						<button type="button"
							class="btn-u btn-u-sm btn-brd btn-brd-hover btn-u-light-grey">View
							More</button>
					</div>
					<!-- End Section-Block -->
				</div>
			</div>
			<!-- End Top Categories -->
		</div>
		<!--/container-->
		<!--=== End Content ===-->

		<!--=== Parallax Counter ===-->
		<div class="parallax-counter parallaxBg margin-bottom-50">
			<div class="container">
				<div class="row">
					<div class="col-sm-3 col-xs-6">
						<div class="counters">
							<span class="counter">52147</span>
							<h4>Images</h4>
						</div>
					</div>
					<div class="col-sm-3 col-xs-6">
						<div class="counters">
							<span class="counter">24583</span>
							<h4>Daily news</h4>
						</div>
					</div>
					<div class="col-sm-3 col-xs-6">
						<div class="counters">
							<span class="counter">7349</span>
							<h4>Visitors per minute</h4>
						</div>
					</div>
					<div class="col-sm-3 col-xs-6">
						<div class="counters">
							<span class="counter">87904</span>
							<h4>Results</h4>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!--=== End Parallax Counter ===-->

		<!--=== Footer ===-->
		<jsp:include page="footer.jsp"></jsp:include>
		<!--=== End Footer ===-->

		<!--=== Copyright ===-->
		<jsp:include page="copyright.jsp"></jsp:include>
		<!--=== End Copyright ===-->
	</div>
	<!--/End Wrapepr-->

	<!-- JS Global Compulsory -->
	<script type="text/javascript"
		src="<c:url value="/resources/assets/plugins/jquery-1.10.2.min.js"/>"></script>
	<script type="text/javascript"
		src="<c:url value="/resources/assets/plugins/jquery-migrate-1.2.1.min.js"/>"></script>
	<script type="text/javascript"
		src="<c:url value="/resources/assets/plugins/bootstrap/js/bootstrap.min.js"/>"></script>
	<!-- JS Implementing Plugins -->
	<script type="text/javascript"
		src="<c:url value="/resources/assets/plugins/jquery.parallax.js"/>"></script>
	<script type="text/javascript"
		src="<c:url value="/resources/assets/plugins/counter/waypoints.min.js"/>"></script>
	<script type="text/javascript"
		src="<c:url value="/resources/assets/plugins/counter/jquery.counterup.min.js"/>"></script>
	<!-- JS Page Level -->
	<script type="text/javascript"
		src="<c:url value="/resources/assets/js/app.js"/>"></script>
	<script type="text/javascript">
		jQuery(document).ready(function() {
			App.init();
			App.initCounter();
			App.initParallaxBg();
			activeNavbar();
		});
		function activeNavbar(){
			//borrar los anteriores
			$("#welcome").removeClass("active");
			$("#timeline").removeClass("active");
			$("#microblogging").removeClass("active");
			$("#conceptmap").removeClass("active");
			$("#statistics").removeClass("active");
			//activar el actual
			$("#welcome").addClass("active");			
		}
	</script>
	<!--[if lt IE 9]>
    <script src="<c:url value="/resources/assets/plugins/respond.js"/>"></script>
<![endif]-->

</body>
</html>