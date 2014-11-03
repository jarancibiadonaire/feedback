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

function initialize() {
	$("#map").width(.55 * $(window).width());
	$("#map").height(.40 * $(window).height());
	var mapOptions = {
		zoom : 13,
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
	getFeeds();	
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
function getFeeds(){
	$.ajax({
		  url: 'http://localhost:8080/feedback/home/feeds/1',
		  type: 'get',
		  async: true,
		  success: loadFeeds,
		  error: error
		});
}
function loadFeeds(message){
	feeds=message;
	for(var i=0;i<feeds.length;i++){
		var ll=new google.maps.LatLng(feeds[i].location.lat, feeds[i].location.lng);
		var newMark={marker:new google.maps.Marker({
								map : map,
								animation : google.maps.Animation.DROP,
								position : ll
							}),
					feed:feeds[i].id};
		markers.push(newMark);
		putHandlers(newMark);		
	}
	var a=angular.element($(".home-container")).scope();
	a.$apply(function(){a.feeds=feeds;});
	$(".contentHolder-leftPanel").removeClass("hidden");
}
function error(message){
	console.log("error",message);
	alert("Ha ocurrido un error, por favor recargue la página.");
}
function putHandlers(marker,id){
	google.maps.event.addListener(marker.marker, 'click', function(event) {
		var a=angular.element($(".home-container")).scope();
		a.$apply(function(){a.toggle(marker.feed);});				
	  });
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
          map.setCenter(marker.getPosition());
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
$("#feed-form").submit(function(e){
    var postData = $(this).serializeArray();
    var formURL = $(this).attr("action");
    $.ajax({
    	url : formURL,
		type: "POST",
		data : postData,
		success:function(data, textStatus, jqXHR){
			if(data!=""){				
				var ll=new google.maps.LatLng(data.location.lat, data.location.lng);
				var newMark={marker:new google.maps.Marker({
										map : map,
										animation : google.maps.Animation.DROP,
										position : ll
									}),
							feed:data.id};
				markers.unshift(newMark);
				var a=angular.element($(".home-container")).scope();
				a.$apply(function(){a.addFeed(data)});
				putHandlers(newMark);				
				$("#title").val("");
				$("#description").val("");
				$("#tag-cloud").empty();
				activeDrawingManager();
			}
		},
		error: function(jqXHR, textStatus, errorThrown){
		    console.log(jqXHR);    
		}
	});
	e.preventDefault();
});
$(".comment-form").live('submit',function(e){
    var postData = $(this).serializeArray();
    var formURL = $(this).attr("action");
    $.ajax({
    	url : formURL,
		type: "POST",
		data : postData,
		success:function(data, textStatus, jqXHR){
			if(data!=""){
				var a=angular.element($(".home-container")).scope();
				a.$apply(function(){a.addComment(data)});
				$(".comment").val("");
			}
		},
		error: function(jqXHR, textStatus, errorThrown){
		    console.log(jqXHR);    
		}
	});
    e.preventDefault();
});
$(".like-form").live('submit',function(e){
    var postData = $(this).serializeArray();
    var formURL = $(this).attr("action");
    $.ajax({
    	url : formURL,
		type: "POST",
		data : postData,
		success:function(data, textStatus, jqXHR){
			if(data!=""){
				var a=angular.element($(".home-container")).scope();
				a.$apply(function(){a.reloadRating(data)});
			}
		},
		error: function(jqXHR, textStatus, errorThrown){
		    console.log(jqXHR);    
		}
	});
    e.preventDefault();
});
$(".dislike-form").live('submit',function(e){
    var postData = $(this).serializeArray();
    var formURL = $(this).attr("action");
    $.ajax({
    	url : formURL,
		type: "POST",
		data : postData,
		success:function(data, textStatus, jqXHR){
			if(data!=""){
				var a=angular.element($(".home-container")).scope();
				a.$apply(function(){a.reloadRating(data)});
			}
		},
		error: function(jqXHR, textStatus, errorThrown){
		    console.log(jqXHR);    
		}
	});
    e.preventDefault();
});
google.maps.event.addDomListener(window, 'load', initialize);