var SUBSCRIPTION_API_URL = './api/subscription/list';

var app = angular.module('listSubscriptionsApp', []);

app.controller('listSubscriptionsCtrl', function($scope,$http) {	
	$scope.breadcrump="Tessere: vendita"
	$scope.subscriptions=[];
	
	$scope.listSubscriptions = function() {
		console.log("calling listSubscriptions");
		$http.get(SUBSCRIPTION_API_URL)
			.success(function(response) {
				console.log("got as response: " + JSON.stringify(response));
				$scope.subscriptions=response.subscriptionList;
		});		
	};

});
