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
		function($scope, ThermometerFactory, $compile) {
			$scope.response = ThermometerFactory.getAll();
			// $scope.chosen = $scope.response[0];
			$scope.submit = function() {
				ThermometerFactory.add($scope.thermometer),
						$scope.thermometerForm.$setPristine();
				console.log($scope.thermometer);
				$scope.thermometer = {};
			};
		} ]);