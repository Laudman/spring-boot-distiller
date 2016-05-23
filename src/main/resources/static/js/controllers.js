var app = angular.module("app.controllers", ['ng-fusioncharts', 'ngStomp']);

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

app.controller('PlotController', function ($scope, $stomp, ThermometerFactory, $log) {
	 
	
	$stomp.setDebug(function(args) {
		$log.debug(args)
	})

	$scope.init = function() {
		$scope.names=ThermometerFactory.getThermometers();
		if($stomp.connect != null) {
		$stomp.connect('/hello').then(
				function(frame) {
					var subscription = $stomp.subscribe('/hello', function(payload,
							headers, res) {
						$scope.data=payload;
						console.log("meeeee");
					})
		})};
		
		$scope.myDataSource = {
			    chart: {
			        caption: "Chose thermometer to generate chart",
			        numberSuffix: "\xB0",
			        xAxisName: "Time",
			        yAxisName: "Temperature",
			        refreshinterval: "2",
			        labeldisplay: "rotate"
			    },
			    data: [{
			        label: "",
			        value: ""
			    }]
		};
	}

	var osiem = 8;
	$scope.plot=function(id) {
		
		ThermometerFactory.setId({id:id});
		
		$scope.myDataSource = {
		    chart: {
		        caption: "Temperature chart",
		        numberSuffix: "\xB0",
		        showValues: "1",
		        xAxisName: "Time",
		        yAxisName: "Temperature",
		        refreshinterval: "2",
		        labeldisplay: "rotate"
		    },
		    data: [{
		        label: "Jeden",
//		        value: parseInt($scope.data, 10)
		        value: $scope.data
		    }, {
		        label: "Dwa",
		        value: $scope.data
		    }, {
		        label: "Trzy",
		        value: 6
		    }, {
		        label: "Cztery",
		        value: 5
		    }, {
		        label: "Piec",
		        value: 4
		    }, {
		        label: "Szesc",
		        value: 5
		    }, {
		        label: "Siedem",
		        value: 3
		    }]
		};
	};
  });





//
//app.controller('IndexController', [function(){
//	
//}]);