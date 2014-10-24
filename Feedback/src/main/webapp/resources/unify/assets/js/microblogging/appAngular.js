var app = angular.module("myApp", []); 

app.controller("mapController",function($scope) {	
    $scope.show_panel = false;
    $scope.feeds=undefined;
    $scope.currentFeed=undefined;
    $scope.toggle = function(i) {
    	$scope.currentFeed=$scope.feeds[i];
    	$("#commentFeed").val($scope.currentFeed.id);
    	$(".panel-map").removeClass("hidden");
        $scope.show_panel = true;
    };
    $scope.reset=function(){
    	$scope.currentFeed=undefined;
    	$scope.show_panel=false;
    };
});

app.controller("homeController",function($scope) {	
	$scope.show_panel = false;
    $scope.classTimeline = "timeline-inverted";
    $scope.feeds=undefined;
    $scope.currentFeed=undefined;
    $scope.select = function(i) {
    	$.ajax({
    		  url: 'http://localhost:8080/feedback/home/feed/'+i,
    		  type: 'get',
    		  async: true,
    		  success: function(message){
    			  console.log(message);
    			  var a=angular.element($(".panel-home")).scope();
    			  a.$apply(function(){
    				  a.currentFeed=message;
    				  a.classTimeline = "";
  		       		  a.show_panel = true;
  		       		}); 
    			  $("#commentFeed").val(i);
    	          $(".panel-home").removeClass("hidden");
    		  },
    		  error: function(message){
    			  console.log(message);
    		  }
    	});   
    };
    $scope.reset=function(){
    	$scope.currentFeed=undefined;
    	$scope.classTimeline = "timeline-inverted";
    	$scope.show_panel=false;
    };
});