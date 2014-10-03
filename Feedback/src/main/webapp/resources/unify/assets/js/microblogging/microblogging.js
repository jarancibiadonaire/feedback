var santiago = new google.maps.LatLng(-33.43711, -70.634185);
var marker;
var map;
var infowindow = new google.maps.InfoWindow();
var geocoder;

function initialize() {
	geocoder = new google.maps.Geocoder();
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
google.maps.event.addDomListener(window, 'load', initialize);