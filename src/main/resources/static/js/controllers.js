var app = angular.module("app.controllers", [  ])

app.controller('ThermometerController', [ '$scope', 'ThermometerFactory', '$compile', '$rootScope',
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
				ThermometerFactory.add({}, $scope.thermometer, function(value, responseHeaders) {

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

// $scope.init = function() {
// if ($stomp.sock == null) {
// }
// $stomp.connect('/hello').then(function(frame) {
// $stomp.subscribe('/hello', function(payload, headers, res) {
// fusioncharts.setData(payload.value, payload.time);
// })
// })
// };

app.controller("PlotController", function($scope, ThermometerFactory, $rootScope) {
	$scope.init = function() {

		var chart = AmCharts.makeChart("chartdiv", {
			"type" : "stock",
			"theme" : "dark",
			"categoryField" : "date",

			"categoryAxesSettings" : {
				"minPeriod" : "ss",
				"axisColor" : "b0de09"
			},

			"dataSets" : [],

			"panels" : [ {
				"showCategoryAxis" : true,
				"title" : "Temperature",
				"percentHeight" : 70,

				"stockGraphs" : [ {
					"id" : "g1",
					"valueField" : "value",
					"comparable" : true,
					"compareField" : "value",
					"lineThickness" : 3,
					"fillToGraph" : "comparedGraph_g1_ds2",
				} ],

				"stockLegend" : {

					"periodValueTextComparing" : "[[percents.value.close]]%",
					"periodValueTextRegular" : "[[value.close]]"
				}
			} ],

			"chartScrollbarSettings" : {
				"backgroundColor" : "#ebebeb",
				"selectedBackgroundColor" : "#f96e5b",

				"graph" : "",
				"usePeriod" : "10mm",
				"position" : "bottom"
			},

			"panelsSettings" : {
				"recalculateToPercents" : "never",
				"usePrefixes": true
			},

			"chartCursorSettings" : {
				"valueBalloonsEnabled" : true,
				"fullWidth" : true,
				"cursorAlpha" : 0.1,
				"valueLineBalloonEnabled" : true,
				"valueLineEnabled" : true,
				"valueLineAlpha" : 0.5
			},

			"periodSelector" : {
				"position" : "top",
				"dateFormat" : "YYYY-MM-DD JJ:NN",
				"inputFieldWidth" : 150,
				"periods" : [ {
					"period" : "ss",
					"count" : 10,
					"label" : "10s"
				}, {
					"period" : "mm",
					"count" : 1,
					"label" : "1m"
				}, {
					"period" : "MAX",
					"label" : "MAX"

				} ]
			},

			"dataSetSelector" : {
				"enabled" : true
			}

		});

		ThermometerFactory.getMeasurements(function(measurementData) {
			angular.forEach(measurementData.thermometers, function(measurementList, thermometer) {

				var chartData = [];

				angular.forEach(measurementList, function(measurement, key) {
					chartData.push({
						date : measurement.time,
						value : measurement.value.toFixed(2)
					});
				});

				chart.dataSets.push({
					title : thermometer,
					fieldMappings : [ {
						fromField : "value",
						toField : "value"
					}, {
						fromField : "volume",
						toField : "volume"
					} ],
					dataProvider : chartData,
					categoryField : "date"
				});
			});
		});
		chart.validateData();
	}
});

app.controller('DescriptionController', function($scope, ThermometerFactory, $rootScope) {

});
