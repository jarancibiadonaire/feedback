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
	href="<c:url value="/resources/assets/plugins/font-awesome/css/font-awesome.css"/>">
<link rel="stylesheet"
	href="<c:url value="/resources/assets/plugins/owl-carousel/owl-carousel/owl.carousel.css"/>">
<link rel="stylesheet"
	href="<c:url value="/resources/assets/plugins/revolution-slider/examples/rs-plugin/css/settings.css"/>">
<!--[if lt IE 9]>
        <link rel="stylesheet" href="<c:url value="/resources/assets/css/sky-forms-ie8.css"/>">
    <![endif]-->

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
	<div class="wrapper">
		<!--=== topbar ===-->
		<div class="header">
			<jsp:include page="topbar.jsp"></jsp:include>
		</div>
		<!-- End topbar -->
		<!--=== Slider ===-->
		<div class="tp-banner-container">
			<div class="tp-banner">
				<ul>
					<!-- SLIDE -->
					<li class="revolution-mch-1" data-transition="fade"
						data-slotamount="5" data-masterspeed="1000">
						<!-- MAIN IMAGE --> <img                      
						src="<c:url value="/resources/assets/img/city/4444205136_c014dd5f26_o.jpg"/>"
						alt="darkblurbg" data-bgfit="cover" data-bgposition="left center"
						data-bgrepeat="no-repeat">

						<div class="tp-caption revolution-ch1 sft start" data-x="center"
							data-hoffset="0" data-y="100" data-speed="1500" data-start="500"
							data-easing="Back.easeInOut" data-endeasing="Power1.easeIn"
							data-endspeed="300">&#161;Descubre tu ciudad&#33;</div> <!-- LAYER -->
						<div class="tp-caption revolution-ch2 sft" data-x="center"
							data-hoffset="0" data-y="190" data-speed="1400" data-start="2000"
							data-easing="Power4.easeOut" data-endspeed="300"
							data-endeasing="Power1.easeIn" data-captionhidden="off"
							style="z-index: 6">
							En feedback podr&#225;s encontrar distintos puntos de inter&#233;s.<br />
							Adem&#225;s agrega los lugares que desees y compartir con tus amigos.
						</div> <!-- LAYER -->
						<div class="tp-caption sft" data-x="center" data-hoffset="0"
							data-y="310" data-speed="1600" data-start="2800"
							data-easing="Power4.easeOut" data-endspeed="300"
							data-endeasing="Power1.easeIn" data-captionhidden="off"
							style="z-index: 6">
							<a href="login" class="btn-u btn-brd btn-brd-hover btn-u-light">Ingresa</a>
							<a href="registration"
								class="btn-u btn-brd btn-brd-hover btn-u-light">Reg&#237;strate</a>
						</div>
					</li>
					<!-- END SLIDE -->
					<!-- SLIDE -->
					<li class="revolution-mch-1" data-transition="fade"
						data-slotamount="5" data-masterspeed="1000">
						<!-- MAIN IMAGE --> <img
						src="<c:url value="/resources/assets/img/city/9866770624_6751e90a89_o.jpg"/>"
						alt="darkblurbg" data-bgfit="cover" data-bgposition="left center"
						data-bgrepeat="no-repeat">

						<div class="tp-caption revolution-ch1 sft start" data-x="center"
							data-hoffset="0" data-y="100" data-speed="1500" data-start="500"
							data-easing="Back.easeInOut" data-endeasing="Power1.easeIn"
							data-endspeed="300">&#161;Descubre tu ciudad&#33;</div> <!-- LAYER -->
						<div class="tp-caption revolution-ch2 sft" data-x="center"
							data-hoffset="0" data-y="190" data-speed="1400" data-start="2000"
							data-easing="Power4.easeOut" data-endspeed="300"
							data-endeasing="Power1.easeIn" data-captionhidden="off"
							style="z-index: 6">
							En feedback podr&#225;s encontrar distintos puntos de inter&#233;s.<br />
							Adem&#225;s agrega los lugares que desees y compartir con tus amigos.
						</div> <!-- LAYER -->
						<div class="tp-caption sft" data-x="center" data-hoffset="0"
							data-y="310" data-speed="1600" data-start="2800"
							data-easing="Power4.easeOut" data-endspeed="300"
							data-endeasing="Power1.easeIn" data-captionhidden="off"
							style="z-index: 6">
							<a href="login" class="btn-u btn-brd btn-brd-hover btn-u-light">Ingresa</a>
							<a href="registration"
								class="btn-u btn-brd btn-brd-hover btn-u-light">Reg&#237;strate</a>
						</div>
					</li>
					<!-- END SLIDE -->
					<!-- SLIDE -->
					<li class="revolution-mch-1" data-transition="fade"
						data-slotamount="5" data-masterspeed="1000">
						<!-- MAIN IMAGE --> <img
						src="<c:url value="/resources/assets/img/city/3028986230_079327b9e5_o.jpg"/>"
						alt="darkblurbg" data-bgfit="cover" data-bgposition="left bottom"
						data-bgrepeat="no-repeat">

						<div class="tp-caption revolution-ch1 sft start" data-x="center"
							data-hoffset="0" data-y="100" data-speed="1500" data-start="500"
							data-easing="Back.easeInOut" data-endeasing="Power1.easeIn"
							data-endspeed="300">&#161;Descubre tu ciudad&#33;</div> <!-- LAYER -->
						<div class="tp-caption revolution-ch2 sft" data-x="center"
							data-hoffset="0" data-y="190" data-speed="1400" data-start="2000"
							data-easing="Power4.easeOut" data-endspeed="300"
							data-endeasing="Power1.easeIn" data-captionhidden="off"
							style="z-index: 6">
							En feedback podr&#225;s encontrar distintos puntos de inter&#233;s.<br />
							Adem&#225;s agrega los lugares que desees y compartir con tus amigos.
						</div> <!-- LAYER -->
						<div class="tp-caption sft" data-x="center" data-hoffset="0"
							data-y="310" data-speed="1600" data-start="2800"
							data-easing="Power4.easeOut" data-endspeed="300"
							data-endeasing="Power1.easeIn" data-captionhidden="off"
							style="z-index: 6">
							<a href="login" class="btn-u btn-brd btn-brd-hover btn-u-light">Ingresa</a>
							<a href="registration"
								class="btn-u btn-brd btn-brd-hover btn-u-light">Reg&#237;strate</a>
						</div>
					</li>
					<!-- END SLIDE -->
				</ul>
				<div class="tp-bannertimer tp-bottom"></div>
			</div>
		</div>
		<!--=== End Slider ===-->

		<!--=== Content Part ===-->
		<div class="container content">
			<div class="clearfix margin-bottom-30"></div>
			<div class="shadow-wrapper">
				<div class="tag-box tag-box-v1 box-shadow shadow-effect-2">
					<h2>Feeds</h2>
					<p>Comparte los puntos de inter&#233;s de tu ciudad a con los feeds.
						Puedes asociarle fotos, comentar, rankear, compartir, etc. Tu
						creas tu propia ciudad de feeds.</p>
				</div>
			</div>

			<div class="clearfix margin-bottom-40"></div>

			<!-- Service Blocks -->
			<div class="margin-bottom-20"></div>
			<div class="row margin-bottom-40">
				<div class="col-md-3 col-sm-6">
					<div class="servive-block servive-block-default">
						<i class="icon-custom rounded-x icon-bg-dark fa fa-map-marker"></i>
						<h2 class="heading-md">Microblogging</h2>
						<p>Publicar nuevos sitios de inter&#233;s desde el mapa de Google
							Maps.</p>
					</div>
				</div>
				<div class="col-md-3 col-sm-6">
					<div class="servive-block servive-block-default">
						<i class="icon-custom rounded-x icon-bg-dark fa fa-history"></i>
						<h2 class="heading-md">Timeline</h2>
						<p>Visualiza los &#250;ltimo feeds registrados en feedback desde el
							Timeline, tal como Facebook o Twitter.</p>
					</div>
				</div>
				<div class="col-md-3 col-sm-6">
					<div class="servive-block servive-block-default">
						<i class="icon-custom rounded-x icon-bg-dark fa fa-link"></i>
						<h2 class="heading-md">ConceptMap</h2>
						<p>Relaciona los feeds con etiquetas para personalizar tus
							puntos de inter&#233;s.</p>
					</div>
				</div>
				<div class="col-md-3 col-sm-6">
					<div class="servive-block servive-block-default">
						<i class="icon-custom rounded-x icon-bg-dark icon-line icon-graph"></i>
						<h2 class="heading-md">Estad&#237;stica</h2>
						<p>Te informamos de la estad&#237;stica de los puntos de inter&#233;s
							publicado por los usuarios.</p>
					</div>
				</div>
			</div>
			<!-- End Service Blokcs -->
			<!--=== Parallax Counter ===-->
			<div class="parallax-counter parallaxBg margin-bottom-50">
				<div class="container">
					<div class="row">
						<div class="col-sm-3 col-xs-6">
							<div class="counters">
								<span class="counter">${indexSummary.totalFeeds}</span>
								<h4>Feeds</h4>
							</div>
						</div>
						<div class="col-sm-3 col-xs-6">
							<div class="counters">
								<span class="counter">${indexSummary.totalComments}</span>
								<h4>Comentarios</h4>
							</div>
						</div>
						<div class="col-sm-3 col-xs-6">
							<div class="counters">
								<span class="counter">${indexSummary.totalUsers}</span>
								<h4>Usuarios</h4>
							</div>
						</div>
						<div class="col-sm-3 col-xs-6">
							<div class="counters">
								<span class="counter">${indexSummary.totalTags}</span>
								<h4>Tags</h4>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!--=== End Parallax Counter ===-->

		</div>
		<!--/container-->
		<!-- End Content Part -->
		<!--=== Footer Version 2 ===-->
		<div class="footer-v2">
			<div class="copyright">
				<div class="container">
					<p class="text-center">
						<a rel="license"
							href="http://creativecommons.org/licenses/by/4.0/"><img
							alt="Licencia de Creative Commons" style="border-width: 0"
							src="https://i.creativecommons.org/l/by/4.0/88x31.png" /></a> Este
						obra est&#225; bajo una <a rel="license"
							href="http://creativecommons.org/licenses/by/4.0/">licencia
							de Creative Commons Reconocimiento 4.0 Internacional</a>.
					</p>
				</div>
			</div>
			<!--/copyright-->
		</div>
		<!--=== End Footer Version 2 ===-->
	</div>
	<!--/wrapper-->

	<!-- JS Global Compulsory -->
	<script type="text/javascript"
		src="<c:url value="/resources/assets/plugins/jquery-1.10.2.min.js"/>"></script>
	<script type="text/javascript"
		src="<c:url value="/resources/assets/plugins/jquery-migrate-1.2.1.min.js"/>"></script>
	<script type="text/javascript"
		src="<c:url value="/resources/assets/plugins/bootstrap/js/bootstrap.min.js"/>"></script>
	<!-- JS Implementing Plugins -->
	<script type="text/javascript"
		src="<c:url value="/resources/assets/plugins/owl-carousel/owl-carousel/owl.carousel.js"/>"></script>
	<script type="text/javascript"
		src="<c:url value="/resources/assets/plugins/jquery.parallax.js"/>"></script>
	<script type="text/javascript"
		src="<c:url value="/resources/assets/plugins/counter/waypoints.min.js"/>"></script>
	<script type="text/javascript"
		src="<c:url value="/resources/assets/plugins/counter/jquery.counterup.min.js"/>"></script>
	<!-- SLIDER REVOLUTION 4.x SCRIPTS  -->
	<script type="text/javascript"
		src="<c:url value="/resources/assets/plugins/revolution-slider/examples/rs-plugin/js/jquery.themepunch.plugins.min.js"/>"></script>
	<script type="text/javascript"
		src="<c:url value="/resources/assets/plugins/revolution-slider/examples/rs-plugin/js/jquery.themepunch.revolution.min.js"/>"></script>
	<!-- JS Page Level -->
	<script type="text/javascript"
		src="<c:url value="/resources/assets/js/app.js"/>"></script>
	<script type="text/javascript"
		src="<c:url value="/resources/assets/js/pages/index.js"/>"></script>
	<script type="text/javascript"
		src="<c:url value="/resources/assets/js/plugins/owl-carousel.js"/>"></script>
	<script type="text/javascript">
		jQuery(document).ready(function() {
			App.init();
			App.initCounter();
			App.initParallaxBg();
			Index.initRevolutionSlider();
			OwlCarousel.initOwlCarousel();

		});
	</script>
	<!--[if lt IE 9]>
    <script src="<c:url value="/resources/assets/plugins/respond.js"/>"></script>
<![endif]-->

</body>
</html>