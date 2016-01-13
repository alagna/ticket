var PURCHASE_API_URL = './api/purchase';

var app = angular.module('sellTicketsApp', []);

app.controller('sellTicketsCtrl', function($scope,$http) {	
	$scope.hideMinus=true;
	$scope.ticketList=[{
	    "buyerName" : "",
	    "subscription" : {
	      "id" : 0
	    },
	    "service" : {
	      "id" : 1,
	    }
	  }];
	$scope.totalAmount=0;
	
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
		var purchase = {}
		purchase.ticketList = $scope.ticketList.slice();
		
		console.log("saving purchase: " + JSON.stringify(purchase));
		$http.post(PURCHASE_API_URL, purchase)
			.success(function(response) {
				console.log("got as response: " + JSON.stringify(response));
				$scope.ticketList = response.purchase.ticketList;
				$scope.totalAmount = response.purchase.totalAmount;
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
    	$scope.totalAmount=0;
	};

});
