function Synchronizer(session) {
	this.adapter = new ClientAdapter("/CoupledObjectWebServer/");
	this.adapter.joinSession(session);
	this.adapter.start(500);
	this.couple();
}

Synchronizer.prototype.syncMessage = function(data, type) {
	var a = angular.element($(".home-container")).scope();
	if (type == 'feed') {
		var newMark=addMarker(data);
		if(newMark.marker!=null){			
			markerCluster.addMarker(newMark.marker,false);				
		}
		a.$apply(function() {
			a.addFeed(data)
		});	
		if(data.location!=null)
			putHandlers(newMark);
	} else if (type == 'comment')
		a.$apply(function() {
			a.addComment(data)
		});
	else if (type == 'rating')
		a.$apply(function() {
			a.reloadRating(data)
		});
	else if (type == 'tag')
		a.$apply(function() {
			a.addTag(data)
		});
};

Synchronizer.prototype.couple = function() {
	this.adapter.coupleObject("synchronizer", this, {
		messageType : "EVENT",
		explicitMapping : [ "syncMessage" ],
		initMessage : false
	});
};