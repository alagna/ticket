var SINGLE_SEARCH_API_URL = './api/getExchangeRate';
var MULTI_SEARCH_API_URL = './api/listExchangeRate';
var CONVERT_API_URL = './api/convert';

var app = angular.module('currencyApp', []);
app.controller('currencyCtrl', function($scope,$http) {
	
    $scope.singleSearch = function() {
		$scope.execSingleSearch = true;
		$scope.waitSingleSearch = true;
		$http.get(SINGLE_SEARCH_API_URL,{params:{currencyCode:$scope.currencyCode}})
		.success(function(response) {
			$scope.currencyExchange = response.currencyExchange;
			$scope.waitSingleSearch = false;
		});		
	};
    $scope.multiSearch = function() {
		$scope.execMultiSearch = true;
		$scope.waitMultiSearch = true;
		$http.get(MULTI_SEARCH_API_URL,{params:{currencyCodes:$scope.currencyCodes}})
		.success(function(response) {
			$scope.currencyExchanges = response.currencyExchanges;
			$scope.waitMultiSearch = false;
		});		
    };
    $scope.convert = function() {
		$scope.execConvert = true;
		$scope.waitConvert = true;
		$http.get(CONVERT_API_URL,{params:{
			sourceCurrencyCode:$scope.sourceCurrencyCode,
			targetCurrencyCode:$scope.targetCurrencyCode,
			amount:$scope.amount}})
		.success(function(response) {
			$scope.conversion = response.conversion;
			$scope.waitConvert = false;
		});		
    };
});
