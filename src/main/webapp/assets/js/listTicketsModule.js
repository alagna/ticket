var TICKET_API_URL = './api/purchase/list';

var app = angular.module('listTicketsApp', []);

app.controller('listTicketsCtrl', function($scope,$http) {	
	$scope.tickets=[];
	
	$scope.listTickets = function() {
		console.log("calling listTickets");
		$http.get(TICKET_API_URL)
			.success(function(response) {
				console.log("got as response: " + JSON.stringify(response));
				for(var i=0; i<response.purchaseList.length; i++){
					var purchase = response.purchaseList[i];
					for (var j=0; j<purchase.ticketList.length; j++)
						$scope.tickets.push(purchase.ticketList[j]);
				}
		});		
	};

});
