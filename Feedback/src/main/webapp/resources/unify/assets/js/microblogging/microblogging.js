var marker;
var map;
var feeds;
var markers=[];
var infowindow = new google.maps.InfoWindow();
var bounds = new google.maps.LatLngBounds();
var geocoder = new google.maps.Geocoder();
var santiago = new google.maps.LatLng(-33.43711, -70.634185);

function initialize() {
	var mapOptions = {
		zoom : 13,
		center : santiago
	};
	map = new google.maps.Map(document.getElementById('map'), mapOptions);
	google.maps.event.addListener(map, 'click', function(event) {
		var a=angular.element($(".panel-map")).scope();
		a.$apply(function(){a.reset();});
	  });
	getCurrentPosition();
	getFeeds();
}
function getFeeds(){
	$.ajax({
		  url: 'http://localhost:8080/feedback/microblogging/feeds/1',
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
		markers[i]= new google.maps.Marker({
			map : map,
			animation : google.maps.Animation.DROP,
			position : ll
		});
		putHandlers(markers[i],i);
	}
	var a=angular.element($(".panel-map")).scope();
	a.$apply(function(){a.feeds=feeds;});
}
function error(message){
	console.log("error",message);
}
function putHandlers(marker,i){
	google.maps.event.addListener(marker, 'click', function(event) {
		var a=angular.element($(".panel-map")).scope();
		a.$apply(function(){a.toggle(i);});
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
google.maps.event.addDomListener(window, 'load', initialize);