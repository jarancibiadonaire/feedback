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
	marker = new google.maps.Marker({
		map : map,
		draggable : true,
		animation : google.maps.Animation.DROP,
		position : santiago
	});
	google.maps.event.addListener(marker, 'dragend', toggleBounce);
	getFeeds();
}
function toggleBounce() {
	var latlng = marker.getPosition();
	geocoder.geocode({'latLng' : latlng}, function(results, status) {
		if (status == google.maps.GeocoderStatus.OK) {
			console.log(results);
			var comuna;
			if (results[0]) {
				for(var i = 0;results[0].address_components.length;i++){
					if(results[0].address_components[i].types[0]=="administrative_area_level_3"){
						comuna=results[0].address_components[i].long_name;
						break;
					}						
				}
				var button="<button class='btn-u btn-brd btn-brd-hover btn-u' type='button' data-toggle='modal' data-target='#responsive'>Ingresar</button>";
				infowindow.setContent(results[0].formatted_address+" <br/>"+comuna+"<br/>"+
						button);
				infowindow.open(map, marker);
				map.panTo(latlng);
			}
		} else {
			alert("Geocoder failed due to: " + status);
		}
	});
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
		bounds.extend(ll);
	}
	map.fitBounds(bounds);
}
function error(message){
	console.log("error",message);
}
google.maps.event.addDomListener(window, 'load', initialize);