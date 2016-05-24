var app = angular.module("app.controllers", [ 'ng-fusioncharts', 'ngStomp' ]);

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
	// $scope.myDataSource = {
	// chart : {
	// caption : "Chose thermometer to generate chart",
	// numberSuffix : "\xB0",
	// xAxisName : "Time",
	// yAxisName : "Temperature",
	// },
	// data : [ {
	// label : "",
	// value : ""
	// } ]
	// };
	// }
	//
	// var osiem = 8;
	// $scope.plot = function(id) {
	//
	// ThermometerFactory.setId({
	// id : id
	// });
	//
	// $scope.myDataSource = {
	// chart : {
	// caption : "Temperature chart",
	// numberSuffix : "\xB0",
	// showValues : "1",
	// xAxisName : "Time",
	// yAxisName : "Temperature",
	// theme : "ocean",
	// },
	// data : [ {
	// label : "Jeden",
	// // value: parseInt($scope.data, 10)
	// value : $scope.data
	// }, {
	// label : "Dwa",
	// value : $scope.data
	// }, {
	// label : "Trzy",
	// value : 6
	// }, {
	// label : "Cztery",
	// value : 5
	// }, {
	// label : "Piec",
	// value : 4
	// }, {
	// label : "Szesc",
	// value : 5
	// }, {
	// label : "Siedem",
	// value : 3
	// } ]
	// };
	// };
});

app.controller('DescriptionController', function($scope, $stomp,
		ThermometerFactory, $log) {

//	 $stomp.setDebug(function(args) {
//	 $log.debug(args)
//	 })
	
	$scope.thermometers = ThermometerFactory.getThermometers();
	
	$scope.init = function() {
		
		if ($stomp.sock == null) {
			
			$stomp.connect('/hello').then(function(frame) {
				$stomp.subscribe('/hello', function(payload, headers, res) {
					fusioncharts.setData(payload.value,payload.time);
					
				})

			})
		}
	};

	FusionCharts.ready(function() {
		fusioncharts = new FusionCharts({
			type : 'realtimeline',
			renderAt : 'chart-container',
			width : '600',
			height : '400',
			dataFormat : 'json',
			dataSource : {
				"chart" : {
					"manageresize" : "1",
					"bgcolor" : "000000",
					"bgalpha" : "100",
					"canvasborderthickness" : "1",
					"canvasbordercolor" : "008040",
					"canvasbgcolor" : "000000",
					"yaxismaxvalue" : "100",
					"decimals" : "0",
					"numdivlines" : "9",
					"numvdivlines" : "28",
					"numdisplaysets" : "30",
					"divlinecolor" : "008040",
					"vdivlinecolor" : "008040",
					"divlinealpha" : "100",
					"chartleftmargin" : "10",
					"basefontcolor" : "00dd00",
					"showrealtimevalue" : "0",
//					"dataurl" : "http://localhost:8080/thermometer/aaa",
//					"datastreamurl" : "http://localhost:8080/hello",
//					"refreshinterval" : "15",
//					"updateinterval" : "5",
//					"dataStamp" : "",
					"numbersuffix" : "%",
					"labeldisplay" : "rotate",
					"slantlabels" : "1",
					"tooltipbgcolor" : "000000",
					"tooltipbordercolor" : "008040",
					"basefontsize" : "11",
					"showalternatehgridcolor" : "0",
					"legendbgcolor" : "000000",
					"legendbordercolor" : "008040",
					"legendpadding" : "35",
					"showlabels" : "1",
					"showborder" : "0"
				},
				"categories" : [ {
					"category" : [ {
						"label" : "Start"
					} ]
				} ],
				"dataset" : [ {
					"color" : "ff5904",
					"showvalues" : "0",
					"alpha" : "100",
					"anchoralpha" : "0",
					"linethickness" : "2",
					"data" : [ {
						"value" : "0"
					} ]
				} ]
//				,
//				data:[{
//				       label: "Bakersfield Central",
//				       value: "880000"
//				   },
//				   {
//				       label: "Garden Groove harbour",
//				       value: "730000"
//				   },
//				   {
//				       label: "Los Angeles Topanga",
//				       value: "590000"
//				   },
//				   {
//				       label: "Compton-Rancho Dom",
//				       value: "520000"
//				   },
//				   {
//				       label: "Daly City Serramonte",
//				       value: "330000"
//				   }]

			}

		})
		fusioncharts.render();
	});

	$scope.show = function(id) {
		ThermometerFactory.setId({id : id });
	};
});
//
// app.controller('IndexController', [function(){
//	
// }]);
