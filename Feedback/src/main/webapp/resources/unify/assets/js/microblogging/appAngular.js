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