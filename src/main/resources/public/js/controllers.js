var app = angular.module("app.controllers", [])

app.controller('NavCtrl', [ '$scope', '$location', function($scope, $location) {
	$scope.navClass = function(page) {
		var currentRoute = $location.path().substring(1) || 'home';
		return page === currentRoute ? 'active' : '';
	};
} ]);

app.controller('ThermometerController', [
		'$scope',
		'ThermometerFactory',
		'$compile',
		'$rootScope',
		function($scope, ThermometerFactory, $compile, $rootScope) {
			
			$scope.init=function() {
				$scope.response = ThermometerFactory.getTwiAddresses();
				$scope.thermometers= ThermometerFactory.getThermometers();
				console.log("init")
			};
			
			$rootScope.$on("updateAddresses", function() {
				$scope.response = ThermometerFactory.getTwiAddresses();
				$scope.thermometers= ThermometerFactory.getThermometers();
				console.log("broadcast");
			});
			
//			console.log("srodek");
//			$scope.response = ThermometerFactory.getTwiAddresses();
//			$scope.thermometers= ThermometerFactory.getThermometers();
			// $scope.chosen = $scope.response[0];
			
			$scope.del=function(number) {
				ThermometerFactory.del({id:number});
				$rootScope.$broadcast("updateAddresses");
			};
			
			$scope.submit = function() {
				console.log("submit");
				ThermometerFactory.add($scope.thermometer);
				$rootScope.$broadcast("updateAddresses");
//				$scope.thermometers.push($scope.thermometer),
				$scope.thermometerForm.$setPristine();
//				console.log($scope.thermometer);
				$scope.thermometer = {};
			};
		} ]);