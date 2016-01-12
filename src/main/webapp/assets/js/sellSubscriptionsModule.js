var SUBSCRIPTION_API_URL = './api/subscription';

var app = angular.module('sellSubscriptionsApp', []);

app.controller('sellSubscriptionsCtrl', function($scope,$http) {	
    
	$scope.saveSubscription = function(subscription) {
		console.log("saving subscription: " + JSON.stringify(subscription));
		$http.post(SUBSCRIPTION_API_URL,subscription)
			.success(function(response) {
				console.log("got as response: " + JSON.stringify(response));
				$scope.newSubscription = response.subscription;
		$scope.clearSubscription(subscription);
		});		
	};
	
    $scope.clearSubscription = function(subscription) {
    	subscription.subscriber.firstName="";
    	subscription.subscriber.lastName="";
	};

});
