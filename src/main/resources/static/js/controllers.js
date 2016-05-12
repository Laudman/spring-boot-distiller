var app = angular.module("app.controllers", ['ng-fusioncharts']);

app.controller('ThermometerController', [ '$scope', 'ThermometerFactory',
		'$compile', '$rootScope',
		function($scope, ThermometerFactory, $compile, $rootScope) {

			$scope.init = function() {
					$rootScope.$broadcast("updateAddresses");
			},

			$scope.del = function(number) {
				ThermometerFactory.del({id :number}, function(value, responseHeaders){
					$rootScope.$broadcast("updateAddresses");
				});
			},
			
			$scope.submit = function() {
				ThermometerFactory.add({}, $scope.thermometer, function(value, responseHeaders) {
					$rootScope.$broadcast("updateAddresses");
					$scope.thermometerForm.$setPristine();
					$scope.thermometer = {};
				});
			},
			$rootScope.$on("updateAddresses", function() {
				$scope.response = ThermometerFactory.getTwiAddresses();
				$scope.thermometers = ThermometerFactory.getThermometers();
			});;
		} ]);
app.controller('PlotController', ['$scope', 'ThermometerFactory',  function ($scope, ThermometerFactory) {
	 
	$scope.init = function() {
		$scope.names=ThermometerFactory.getThermometers();
		
		$scope.myDataSource = {
			    chart: {
			        caption: "Chose thermometer to generate chart",
			        numberSuffix: "\xB0",
			        xAxisName: "Time",
			        yAxisName: "Temperature",
			    },
			    data: [{
			        label: "",
			        value: ""
			    }]
		};
	}

	var osiem = 8;
	$scope.plot=function(id) {
		
		ThermometerFactory.getTemperature({id:id});
		
		$scope.myDataSource = {
		    chart: {
		        caption: "Temperature chart",
		        numberSuffix: "\xB0",
		        showValues: "1",
		        xAxisName: "Time",
		        yAxisName: "Temperature",
		        theme: "ocean",
		    },
		    data: [{
		        label: "Jeden",
		        value: osiem
		    }, {
		        label: "Dwa",
		        value: "37"
		    }, {
		        label: "Trzy",
		        value: "32"
		    }, {
		        label: "Cztery",
		        value: "4"
		    }, {
		        label: "Piec",
		        value: "26"
		    }, {
		        label: "Szesc",
		        value: "36"
		    }, {
		        label: "Siedem",
		        value: "12"
		    }]
		};
	};
	

	
	
  }]);

//
//app.controller('IndexController', [function(){
//	
//}]);