var geocoder = new google.maps.Geocoder();
var myLat=undefined;
var myLng=undefined;
var myAddress=undefined;
var myComuna=undefined;
function initializeHome(){
	if (navigator.geolocation) {
	    navigator.geolocation.getCurrentPosition(loadPosition);
	} else { 
	   alert("Su navegador no soporta Geolocalización.");
	}
}

function loadPosition(position) {
	var latlng = new google.maps.LatLng(position.coords.latitude, position.coords.longitude);
	geocoder.geocode({'latLng' : latlng}, function(results, status) {
		if (status == google.maps.GeocoderStatus.OK) {
			var comuna;
			var address;
			if (results[0]) {
				for(var i = 0;results[0].address_components.length;i++){
					if(results[0].address_components[i].types[0]=="administrative_area_level_3" ||
							results[0].address_components[i].types[0]=="locality"){
						comuna=results[0].address_components[i].long_name;
						address=results[0].formatted_address;
						break;
					}						
				}
				myLat=latlng.lat();
				myLng=latlng.lng();
				myAddress=address;
				myComuna=comuna;
//				$("#lat").val(latlng.lat());
//				$("#lng").val(latlng.lng());
//				$("#address").val(address);
//				$("#comuna").val(comuna);
			}
		} else {
			alert("Problemas al realizar geocoding: " + status);
		}
	});
}
google.maps.event.addDomListener(window, 'load', initializeHome);