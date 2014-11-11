var app = angular.module("myApp", []);

app.controller("homeController", function($scope) {
	$scope.show_panel = false;
	$scope.allFeeds = undefined;
	$scope.feeds = undefined;
	$scope.followingTags = undefined;
	$scope.ownTags = undefined;
	$scope.currentFeed = undefined;
	$scope.currentMarker = undefined;
	$scope.btnClass = 'fa-plus';
	$scope.rated=undefined;
	$scope.clickAddFeed = function() {
		$("#newFeed").removeClass("hidden");
		if (!$scope.show_panel) {
			$scope.btnClass = 'fa-minus';
			$scope.show_panel = true;
		} else {
			$scope.btnClass = 'fa-plus';
			$scope.show_panel = false;
		}
		activeDrawingManager();
	};
	$scope.toggle = function(id, panel) {
		for (var i = 0; i < $scope.feeds.length; i++) {
			if ($scope.feeds[i].id == id) {
				$scope.currentFeed = $scope.feeds[i];
				break;
			}
		}
		for (var j = 0; j < markers.length; j++) {
			if (markers[j].feed == id) {
				$scope.currentMarker = markers[j].marker;
				break;
			}
		}
		infowindow.setContent("<div class='scrollFix'>"
				+ $scope.currentFeed.title + "</div>");
		infowindow.open(map, $scope.currentMarker);
		map.panTo($scope.currentMarker.getPosition());
		$(".tab-v2").find(".tab-content").removeClass("select-div");
		$("#feed-" + $scope.currentFeed.id).find(".tab-content").addClass(
				"select-div");
		//click desde concept map, panel!=undefined click desde el panel
		if (panel == undefined){
			$("#feed-" + $scope.currentFeed.id).closest("li")[0]
					.scrollIntoView(true);
			window.scrollTo(0,0);
		}
		clickNode($scope.currentFeed.title, $scope.currentFeed.id);
	};
	$scope.reset = function() {
		$(".tab-content").removeClass("select-div");
		infowindow.close();
		$scope.currentFeed = undefined;
		clickNode(undefined, undefined);
	};
	$scope.addFeed = function(feed) {
		$scope.feeds.unshift(feed);
		addNode(feed);
	};
	$scope.addComment = function(comment) {
		for (var i = 0; i < $scope.feeds.length; i++) {
			if ($scope.feeds[i].id == comment.feed) {
				$scope.feeds[i].comments.unshift(comment);
				return;
			}
		}
	};
	$scope.reloadRating = function(feed) {
		for (var i = 0; i < $scope.feeds.length; i++) {
			if ($scope.feeds[i].id == feed.id) {
				$scope.feeds[i].totalLikes = feed.totalLikes;
				$scope.feeds[i].totalDislikes = feed.totalDislikes;
				$scope.rated.push(feed.id);
				return;
			}
		}
	};
	$scope.addTag = function(feed){
		for (var i = 0; i < $scope.feeds.length; i++) {
			if ($scope.feeds[i].id == feed.id) {
				$scope.feeds[i]=feed;
				break;
			}
		}
		addNode(feed);
	}
	$scope.checkRated = function(id){
		for(var i=0; i<$scope.rated.length;i++){
			if($scope.rated[i]==id)
				return true;
		}
		return false;
	}
	$scope.filterByTag = function(option){
		if(option=='all'){
			$scope.feeds=$scope.allFeeds;
		}else if(option=='following'){
			//si no seguimos ningun tags
			if($scope.followingTags==undefined || $scope.followingTags.length<1){ 
				$scope.feeds=[];
				updateMap($scope.feeds);
				return;
			}
			var aux=[];
			for(var i=0;i<$scope.allFeeds.length;i++){
				var ctrl=false;
				for(var j=0;j<$scope.allFeeds[i].tagsData.length;j++){
					if($scope.followingTags.indexOf($scope.allFeeds[i].tagsData[j].id)!=-1){
						aux.push($scope.allFeeds[i]);
						ctrl=true;
						break;
					}
				}
				if(!ctrl){
					for(var j=0;j<$scope.allFeeds[i].othersTagsData.length;j++){
						if($scope.followingTags.indexOf($scope.allFeeds[i].othersTagsData[j].id)!=-1){
							aux.push($scope.allFeeds[i]);
							break;
						}
					}
				}
				
			}
			$scope.feeds=aux;
		}else if(option=='own'){
			var aux=[];
			for(var i=0;i<$scope.allFeeds.length;i++){
				var ctrl=false;
				for(var j=0;j<$scope.allFeeds[i].tagsData.length;j++){
					if($scope.ownTags.indexOf($scope.allFeeds[i].tagsData[j].id)!=-1){
						aux.push($scope.allFeeds[i]);
						ctrl=true;
						break;
					}
				}
				if(!ctrl){
					for(var j=0;j<$scope.allFeeds[i].othersTagsData.length;j++){
						if($scope.ownTags.indexOf($scope.allFeeds[i].othersTagsData[j].id)!=-1){
							aux.push($scope.allFeeds[i]);
							break;
						}
					}
				}
				
			}
			$scope.feeds=aux;
		}
		updateMap($scope.feeds);
	}
});