var marker;
var newMarker;
var map;
var feeds;
var markers=[];
var infowindow = new google.maps.InfoWindow();
var bounds = new google.maps.LatLngBounds();
var drawingManager=new google.maps.drawing.DrawingManager();
var geocoder = new google.maps.Geocoder();
var santiago = new google.maps.LatLng(-33.43711, -70.634185);
var drawingShow=false;
var synchronizer;
var markerCluster;
var mcOptions = {gridSize: 50, maxZoom: 15};
$(window).resize(function(){
	if($(window).width()>992){
		$("#map").width(.55 * $(window).width());
		$("#map").height(.40 * $(window).height());	
		$(".contentHolder-leftPanel").height(.85 * $(window).height());
	}
});

function initialize() {
	if(! /Android|webOS|iPhone|iPad|iPod|BlackBerry|IEMobile|Opera Mini/i.test(navigator.userAgent) ) {
		$("#map").width(.55 * $(window).width());
		$("#map").height(.40 * $(window).height());		
	}
	$(".contentHolder-leftPanel").height(.85 * $(window).height());
	var mapOptions = {
		zoom : 14,
		center : santiago
	};
	map = new google.maps.Map(document.getElementById('map'), mapOptions);
	google.maps.event.addListener(map, 'click', function(event) {
		var a=angular.element($(".home-container")).scope();
		a.$apply(function(){a.reset();});
	  });
	drawingManager = getDrawingManager();
	drawingManager.setMap(map);
	setDrawingListener();
	getCurrentPosition();
	/*obtener el resto de los objetos:feeds,graph,followingfeeds,ownfeeds,etc*/
	getRatingFeeds();
	getFollowingTags();
	getOwnTags();
	var q=getURLParameter('q');
	if(q==null)
		getFeeds();
	else
		searchFeeds(q);
}
function setDrawingListener(){
	 google.maps.event.addListener(drawingManager, 'overlaycomplete', function(event) {
		 newMarker=event.overlay;
         drawingManager.setDrawingMode(null);
         drawingManager.setOptions({
             drawingControl: false
           });
         updatePosition(newMarker.getPosition());
         google.maps.event.addListener(newMarker, 'click', function() {
        	    infowindow.open(map,newMarker);
        	  });
         google.maps.event.addListener(newMarker, 'dragend', updatePosition);
     });
}
function updatePosition(event){
	var latlng=undefined;
	if(event.latLng==undefined)
		latlng=event;
	else
		latlng=event.latLng;
    geocoder.geocode({'latLng' : latlng}, function(results, status) {
		if (status == google.maps.GeocoderStatus.OK) {
			var comuna;
			var address;
			if (results[0]) {
				for(var i = 0;results[0].address_components.length;i++){
					if(results[0].address_components[i].types[0]=="locality"){
						comuna=results[0].address_components[i].long_name;
						address=results[0].formatted_address;
						break;
					}						
				}
				$("#lat").val(latlng.lat());
				$("#lng").val(latlng.lng());
				$("#address").val(address);
				$("#comuna").val(comuna);
			}
		} else {
			alert("Problemas al realizar geocoding: " + status);
		}
	});
}
function activeDrawingManager(){
	drawingShow=!drawingShow;
    drawingManager.setOptions({
        drawingControl: drawingShow
      }); 
    if(!drawingShow){
	   	$("#lat").val(myLat);
		$("#lng").val(myLng);
		$("#address").val(myAddress);
		$("#comuna").val(myComuna);
		if(newMarker!=undefined)
			newMarker.setMap(null);
    }
}
function getDrawingManager(){
	return new google.maps.drawing.DrawingManager({
        drawingControl: false,
        drawingControlOptions: {
          position: google.maps.ControlPosition.TOP_CENTER,
          drawingModes: [
            google.maps.drawing.OverlayType.MARKER
          ]
        },
        markerOptions: {
            icon: 'http://maps.google.com/intl/en_us/mapfiles/ms/micons/blue-dot.png',
            draggable: true,
            clickable: true,
            zIndex: 1
          },
      });
}
function getRatingFeeds(){
	var username = $("#username").val();
	$.ajax({
		url : window.location.protocol + "//" + window.location.host+'/feedback/ajax/get_feeds_rated',
		type : "get",
		data : {username:username},
		success : function(data, textStatus, jqXHR) {
			if (data != "") {
				var a=angular.element($(".home-container")).scope();
				a.$apply(function(){a.rated=data;});
			}
		},
		error : function(jqXHR, textStatus, errorThrown) {
			console.log(jqXHR);
		}
	});
}
function getFollowingTags(){
	var username = $("#username").val();
	$.ajax({
		url : window.location.protocol + "//" + window.location.host+'/feedback/ajax/get_following_tags',
		type : "get",
		data : {username:username},
		success : function(data, textStatus, jqXHR) {
			if (data != "") {
				var a=angular.element($(".home-container")).scope();
				a.$apply(function(){a.followingTags=data;});
			}
		},
		error : function(jqXHR, textStatus, errorThrown) {
			console.log(jqXHR);
		}
	});
}
function getOwnTags(){
	var username = $("#username").val();
	$.ajax({
		url : window.location.protocol + "//" + window.location.host+'/feedback/ajax/get_own_tags',
		type : "get",
		data : {username:username},
		success : function(data, textStatus, jqXHR) {
			if (data != "") {
				var a=angular.element($(".home-container")).scope();
				a.$apply(function(){a.ownTags=data;});
			}
		},
		error : function(jqXHR, textStatus, errorThrown) {
			console.log(jqXHR);
		}
	});
}

function getFeeds(){
	$.ajax({
		  url: window.location.protocol + "//" + window.location.host+'/feedback/home/feeds/1',
		  type: 'get',
		  async: true,
		  success: loadFeeds,
		  error: error
		});
}
function searchFeeds(q){
	$.ajax({
		  url: window.location.protocol + "//" + window.location.host+'/feedback/ajax/search_feeds',
		  type: 'get',
		  data:{q:q},
		  async: true,
		  success: loadFeeds,
		  error: error
		});
}
function loadFeeds(message){
	feeds=message;
	bounds=new google.maps.LatLngBounds();
	bounds.extend(santiago);
	for(var i=0;i<feeds.length;i++){
		var newMark=addMarker(feeds[i]);
		if(feeds[i].location!=null){	
			putHandlers(newMark);
			bounds.extend(newMark.marker.getPosition());				
		}			
	}	
	markerCluster = new MarkerClusterer(map, getMarkers(),mcOptions);
	map.fitBounds(bounds);
	var a=angular.element($(".home-container")).scope();	
	a.$apply(function(){a.feeds=feeds;});
	a.$apply(function(){a.allFeeds=feeds;});
	$(".contentHolder-leftPanel").removeClass("hidden");
	synchronizer=new Synchronizer("feedback");
	$("#sitemap").trigger("reload");
}
function error(message){
	console.log("error",message);
	alert("Ha ocurrido un error, reintente por favor.");
}
function putHandlers(marker,id){
	google.maps.event.addListener(marker.marker, 'click', function(event) {
		var a=angular.element($(".home-container")).scope();
		a.$apply(function(){a.toggle(marker.feed);});				
	  });
}
function updateMap(message){
	markerCluster.clearMarkers();
	markers=[];
	feeds=message;
	bounds=new google.maps.LatLngBounds();
	bounds.extend(santiago);
	for(var i=0;i<feeds.length;i++){
		var newMark=addMarker(feeds[i]);
		putHandlers(newMark);		
		bounds.extend(newMark.marker.getPosition());
	}
	markerCluster = new MarkerClusterer(map, getMarkers(),mcOptions);
	map.fitBounds(bounds);
	$("#sitemap").trigger("delete");
	$("#sitemap").trigger("reloadFilter");
}
function getCurrentPosition(){
    if(navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(function(position) {
          var pos = new google.maps.LatLng(position.coords.latitude,position.coords.longitude);
          if(marker==null)
              marker = new google.maps.Marker({
                        clickable: false,
                        position: pos,
                        icon: new google.maps.MarkerImage('//maps.gstatic.com/mapfiles/mobile/mobileimgs2.png',
                                                                        new google.maps.Size(22,22),
                                                                        new google.maps.Point(0,18),
                                                                        new google.maps.Point(11,11)),
                        shadow: null,
                        zIndex: 999,
                        map: map 
                    });
          else
              marker.setPosition(pos);
        }, function() {
          handleNoGeolocation();
        });
      } else {
        // Browser/GPS doesn't support Geolocation
        handleNoGeolocation();
      }    
}
function handleNoGeolocation(){
	console.log("No es posible obtener la geolocalización");
}
function clickMap(){
	google.maps.event.trigger(map, 'click');
}
function addMarker(data){
	var markerAux=null;
	if(data.location!=null){
		var ll=new google.maps.LatLng(data.location.lat, data.location.lng);
		markerAux=new google.maps.Marker({
			animation : google.maps.Animation.DROP,
			position : ll
		});
	}	
	var newMark={marker:markerAux,
				feed:data.id};
	markers.unshift(newMark);
	return newMark;
}
function getMarkers(){
	var result=[];
	for(var i=0;i<markers.length;i++){
		if(markers[i].marker!=null)
			result.push(markers[i].marker);
	}
	return result;
}
function getFeedIds(){
	var result="";
	for(var i=0;i<markers.length;i++)
		result+=","+markers[i].feed;
	return result.substring(1);	
}
function getURLParameter(name) {
	  return decodeURIComponent((new RegExp('[?|&]' + name + '=' + '([^&;]+?)(&|#|;|$)').exec(location.search)||[,""])[1].replace(/\+/g, '%20'))||null
}
google.maps.event.addDomListener(window, 'load', initialize);