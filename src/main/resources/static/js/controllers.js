var app = angular.module("app.controllers", [ 'chart.js']).config(['ChartJsProvider', function (ChartJsProvider) {
    // Configure all charts
    ChartJsProvider.setOptions({
      colours: ['#FF5252', '#FF8A80'],
      responsive: false
    });
    // Configure all line charts
    ChartJsProvider.setOptions('Line', {
      datasetFill: false
    });
  }]);

app.controller('ThermometerController', [
		'$scope',
		'ThermometerFactory',
		'$compile',
		'$rootScope',
		function($scope, ThermometerFactory, $compile, $rootScope) {

			$scope.init = function() {
				$rootScope.$broadcast("updateAddresses");
			},

			$scope.del = function(number) {
				ThermometerFactory.del({
					id : number
				}, function(value, responseHeaders) {
					$rootScope.$broadcast("updateAddresses");
				});
			},

			$scope.submit = function() {
				ThermometerFactory.add({}, $scope.thermometer, function(value,
						responseHeaders) {

					$rootScope.$broadcast("updateAddresses");
					$scope.thermometerForm.$setPristine();
					$scope.thermometer = {};
				});
			}
			$rootScope.$on("updateAddresses", function() {
				$scope.response = ThermometerFactory.getTwiAddresses();

				$scope.thermometers = ThermometerFactory.getThermometers();
			});
			;
		} ]);

app.controller('PlotController', function($scope, $stomp, ThermometerFactory,
		$log) {

	$scope.names = ThermometerFactory.getThermometers();

	$scope.init = function() {
		if ($stomp.sock == null) {
		}
		$stomp.connect('/hello').then(function(frame) {
			$stomp.subscribe('/hello', function(payload, headers, res) {
				fusioncharts.setData(payload.value, payload.time);
			})
		})
	};

	ThermometerFactory.getMeasurements(function(response) {
		delete response.$promise;
		delete response.$resolved;
		angular.forEach(response, function(measurementList, thermometer) {

			angular.forEach(measurementList, function(measurement, key) {
			});
		});
	});

});


app.controller("LineCtrl", function ($scope) {
	  $scope.labels = ["January", "February", "March", "April", "May", "June", "July"];
	  $scope.series = ['Series A', 'Series B'];
	  $scope.data = [
	                 [65, 59, 80, 81, 56, 55, 40],
	                 [28, 48, 40, 19, 86, 27, 90]
	               ];
	  $scope.onClick = function (points, evt) {
	    console.log(points, evt);
	  };
	});








app.controller('DescriptionController', function($scope, $stomp,
		ThermometerFactory, $log) {

	// $stomp.setDebug(function(args) {
	// $log.debug(args)
	// })
	//
	// $scope.init = function() {
	// $scope.names = ThermometerFactory.getThermometers();
	// if ($stomp.connect != null) {
	// $stomp.connect('/hello').then(
	// function(frame) {
	// var subscription = $stomp.subscribe('/hello', function(
	// payload, headers, res) {
	// $scope.data = payload;
	// console.log("meeeee");
	// })
	// })
	// };
	//
	// var info = ThermometerFactory.getTemperature();
	// console.log(info);
	// //
	// // var tablica = [];
	//
	// angular.forEach(info, function(value, key) {
	// console.log(value);
	// });
	//

});
