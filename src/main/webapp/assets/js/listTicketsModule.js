var TICKET_API_URL = './api/purchase/list';

var app = angular.module('listTicketsApp', []);

app.controller('listTicketsCtrl', function($scope,$http) {	

	var dateToString = function(date){
		var dd = date.getDate(); 
		var mm = date.getMonth()+1; 
		var yyyy = date.getFullYear(); 

		return dd+"/"+mm+"/"+ yyyy;
	}

	$scope.tickets=[];
	$scope.startDate=dateToString(new Date());
	$scope.endDateDate=null;
	
	var toMinus = function(date){
		return !date ? undefined : date.replace(/\//g, "-");
	}
	
	var calcTicketList = function(startDate, endDate){
		$scope.tickets=[];
		$scope.totalAmount=0;
		$scope.totalNumber=0;
		$http.get(TICKET_API_URL, 
				{params: {startDate: toMinus(startDate), endDate: toMinus(endDate)}})
				.success(function(response) {
					console.log("got as response: " + JSON.stringify(response));
					for(var i=0; i<response.purchaseList.length; i++){
						var purchase = response.purchaseList[i];
						$scope.totalAmount=$scope.totalAmount+purchase.totalAmount;
						$scope.totalNumber=$scope.totalNumber+purchase.ticketList.length;
						for (var j=0; j<purchase.ticketList.length; j++)
							$scope.tickets.push(purchase.ticketList[j]);
					}
			});	
	}
	
	
	$scope.listTickets = function() {
		console.log("calling listTickets");
		calcTicketList($scope.startDate, $scope.endDate);
	};
	
	$scope.listTodayTickets = function() {
		calcTicketList(dateToString(new Date()), null);		
	};

});
