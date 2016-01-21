var SUBSCRIPTION_API_URL = './api/subscription';

var app = angular.module('sellSubscriptionsApp', []);

app.controller('sellSubscriptionsCtrl', function($scope,$http) {	
	$scope.subscriptions=[];
	
	$scope.saveSubscription = function(subscription) {
		var birthDate = new Date(subscription.subscriber.birthDate.replace( /(\d{2})\/(\d{2})\/(\d{4})/, "$3/$2/$1") );
		subscription.subscriber.birthDate = birthDate.getTime();
		
		// merging first and last name
		subscription.subscriber.firstLastName = 
			subscription.subscriber.firstName.split() + " " + subscription.subscriber.lastName.split();
		delete subscription.subscriber.firstName;
		delete subscription.subscriber.lastName;
		
		console.log("saving subscription: " + JSON.stringify(subscription));
		$http.post(SUBSCRIPTION_API_URL,subscription)
			.success(function(response) {
				console.log("got as response: " + JSON.stringify(response));
				$scope.subscriptions.push(response.subscription);
				$scope.clearSubscription(subscription);
		});		
	};
	
    $scope.clearSubscription = function(subscription) {
    	subscription.subscriber.firstLastName=null;
		subscription.subscriber.birthDate=null;
		subscription.subscriber.telephoneNumber=null;
		subscription.subscriber.email=null; 
	};

});
