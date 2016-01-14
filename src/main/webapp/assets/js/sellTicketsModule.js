var PURCHASE_API_URL = './api/purchase';

var app = angular.module('sellTicketsApp', []);

app.controller('sellTicketsCtrl', function($scope,$http) {	
	$scope.hideMinus=true;
	
	/** input ticketList */
	$scope.ticketList=[{
	    "buyerName" : "",
	    "subscription" : {
	      "id" : 0
	    },
	    "service" : {
	      "id" : 1,
	    }
	  }];

	$scope.resTotalAmount=0;
	$scope.resTicketList=[];
	
	/** adds a row of tickets */
	$scope.addRow = function(){
		$scope.ticketList.push({
		    "buyerName" : "",
		    "subscription" : {
		    },
		    "service" : {
		      "id" : 1,
		    }
		});
		if ($scope.ticketList.length>1)
			$scope.hideMinus=false;
	};
	
	/** removes a row of tickets */
	$scope.rmRow = function(){
		$scope.ticketList.pop();
		if ($scope.ticketList.length==1)
			$scope.hideMinus=true;
	};
	
	$scope.savePurchase = function() {
		var purchase = {date: new Date()}
		purchase.ticketList = $scope.ticketList.slice();
		
		for(i=0; i<purchase.ticketList.length; i++){
			var ticket = purchase.ticketList[i];
			var birthDate = new Date(ticket.buyerBirthDate.replace( /(\d{2})\/(\d{2})\/(\d{4})/, "$3/$2/$1") );
			ticket.buyerBirthDate = birthDate.getTime();
		}
		
		console.log("saving purchase: " + JSON.stringify(purchase));
		$http.post(PURCHASE_API_URL, purchase)
			.success(function(response) {
				console.log("got as response: " + JSON.stringify(response));
				$scope.resTicketList = response.purchase.ticketList;
				$scope.resTotalAmount = response.purchase.totalAmount;
				$scope.clearPurchase();
		});	
	};
	
    $scope.clearPurchase = function() {
    	$scope.ticketList=[{
    	    "buyerName" : "",
    	    "subscription" : {
    	      "id" : 0
    	    },
    	    "service" : {
    	      "id" : 1,
    	    }
    	  }];
	};

});
