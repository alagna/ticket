var PURCHASE_API_URL = './api/purchase';
var SUBSCRIPTION_API_URL = './api/subscription';
var TICKET_API_URL = './api/ticket';

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
	
	var dateToString = function(date){
		var dd = date.getDate(); 
		var mm = date.getMonth()+1; 
		var yyyy = date.getFullYear(); 

		return dd+"/"+mm+"/"+ yyyy;
	}

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
	
	var mergeTicketNames = function(ticket){
		ticket.buyerName = ticket.firstName.split() + " " + ticket.lastName.split();
		delete ticket.firstName;
		delete ticket.lastName;
	}
	
	// --- start integration with the ranking webapp ---
	
	var RANKING_URL = "http://"+ window.location.host +"/ranking/do"
	/**
	 * Function used to integrate with the ranking webapp
	 */
	var integrateWithRanking = function(ticket){
		if (ticket.buyerBibNumber!=null && ticket.buyerSex!=null) {
			console.log("subscribing " + ticket.buyerName + " to ranking");
			var nome=ticket.firstName;
			var cognome=ticket.lastName;
			var annoDiNascita=ticket.buyerBirthDate.substring(ticket.buyerBirthDate.length-4);
			var sesso=ticket.buyerSex;
			var pettorale=ticket.buyerBibNumber;
			var cmd="create";
			
			$http({
			    method: 'POST',
			    url: RANKING_URL,
			    data: $.param({nome: nome, cognome: cognome, annoDiNascita: annoDiNascita, sesso: sesso,
			    	pettorale: pettorale, cmd: cmd}),
			    headers: {'Content-Type': 'application/x-www-form-urlencoded'}
			})
			.success(function(response) {
				console.log("got as response: " +response);
				window.alert("atleta " + ticket.buyerName + " anche iscritto alla gara");
			});
		}
		delete ticket.buyerBibNumber;
		delete ticket.buyerSex;
	}
	// --- end integration with the ranking webapp ---
	
	
	$scope.savePurchase = function() {
		var purchase = {date: new Date()}
		purchase.ticketList = $scope.ticketList.slice();
		
		for(i=0; i<purchase.ticketList.length; i++){
			var ticket = purchase.ticketList[i];
			integrateWithRanking(ticket);
			mergeTicketNames(ticket);
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
	
	/**
	 * Called dynamically when the firstLastName input field loose focus
	 */
	$scope.loadFirstLastname = function(firstName, lastName, serviceId){
		var firstLastName = firstName + " " + lastName;
		console.log("blur firstLastName = " + firstLastName);
		
		$http.get(SUBSCRIPTION_API_URL, {params: {subscriberFirstLastName: firstLastName}})
			.success(function(response) {
				console.log("got as response: " + JSON.stringify(response));
				
				// the buyer has a subscription
				if (response.subscriptionList.length>0){
					var subscription = response.subscriptionList[0];
					var ticket ={
						firstName: firstName,
						lastName: lastName,
						buyerName: firstLastName,
						buyerBirthDate: dateToString(new Date(subscription.subscriber.birthDate)),
						subscription: {
							progressiveNumber: subscription.progressiveNumber
						},
						service :{
							id: serviceId
						}
					}
					$scope.ticketList.pop();
					$scope.ticketList.push(ticket);
				} 
				
				else {
					// let's try if the buyer has ever bought a ticket
					$http.get(TICKET_API_URL, {params: {buyerName: firstLastName}})
					.success(function(response) {
						console.log("got as response: " + JSON.stringify(response));
						if (response.ticketList.length>0){
							var oldTicket = response.ticketList[0];
							var ticket ={
								firstName: firstName,
								lastName: lastName,
								buyerName: firstLastName,
								buyerBirthDate: dateToString(new Date(oldTicket.buyerBirthDate)),
								service :{
									id: serviceId
								}
							}
							$scope.ticketList.pop();
							$scope.ticketList.push(ticket);
						}
					});
				}				
		});	
	}
	
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
