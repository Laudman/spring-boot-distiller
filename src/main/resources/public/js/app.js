var app = angular.module("app",['app.services']);

app.controller('ThermometerController', ['$scope', 'ThermometerFactory', function($scope, ThermometerFactory) {
	$scope.response=ThermometerFactory.getAll();
//	$scope.chosen = $scope.response[0];
	$scope.submit = function() {
		$scope.thermometerForm.$setPristine();
		console.log($scope.thermometer);
		$scope.thermometer={};
	};
}
]);