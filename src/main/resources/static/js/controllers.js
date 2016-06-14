var app = angular.module("app.controllers", [ 'ngStomp' ])

app.controller("ChartController", function($scope, ThermometerFactory, $stomp) {

	var chart = new AmCharts.AmStockChart();
	var stompSubscription;
	var dataSets = [];

	getMeasurementData();

	function getMeasurementData() {
		$scope.data = ThermometerFactory.getMeasurements()
		$scope.data.$promise.then(function(success) {
			measurementData = success.measurementData;
			prepareChartData();
		});
	}

	function prepareChartData() {
		var comparable = false;
		angular.forEach(measurementData, function(measurementList, thermometer) {
			var measurementForThermometer = [];
			if (measurementList.length == 0) {
				var date = new Date();
				date.setHours(date.getHours() + 2);
				measurementForThermometer.push({
					date : date,
					value : 1.0
				});
			}

			angular.forEach(measurementList, function(measurement, key) {
				measurementForThermometer.push({
					date : new Date(measurement.time),
					value : measurement.value.toFixed(2)
				});
			});

			var dataSet = new AmCharts.DataSet();
			dataSet.dataProvider = measurementForThermometer;
			dataSet.fieldMappings = [ {
				fromField : "value",
				toField : "value"
			} ];
			dataSet.categoryField = "date";
			dataSet.title = thermometer;
			if (comparable) {
				dataSet.compared = true;
			}
			dataSets.push(dataSet);
			comparable = true;
		});
		generateChart();
	}

	function generateChart() {
		AmCharts.useUTC = true;
		chart.autoMarginOffset=110;
		chart.addClassNames=true;
		chart.glueToTheEnd = true;
		chart.dataSets = [];
		chart.dataSets = dataSets;
		var stockPanel = new AmCharts.StockPanel();
		stockPanel.showCategoryAxis = true;
		stockPanel.title = "Temperature Chart";
		stockPanel.percentHeight = 100;

		chart.panels = [ stockPanel ];

		var legend = new AmCharts.StockLegend();
		stockPanel.stockLegend = legend;

		var panelsSettings = new AmCharts.PanelsSettings();
		panelsSettings.startDuration = 1;
		panelsSettings.recalculateToPercents = "never";
		panelsSettings.usePrefixes = true
		chart.panelsSettings = panelsSettings;

		var graph = new AmCharts.StockGraph();
		graph.valueField = "value";
		graph.comparable = true;
		graph.compareField = "value";
		graph.lineThickness = 4;
		stockPanel.addStockGraph(graph);

		var categoryAxesSettings = new AmCharts.CategoryAxesSettings();
		categoryAxesSettings.minPeriod = "ss";
		chart.categoryAxesSettings = categoryAxesSettings;

		var valueAxesSettings = new AmCharts.ValueAxesSettings();
		valueAxesSettings.dashLength = 5;
		chart.valueAxesSettings = valueAxesSettings;

		var chartScrollbarSettings = new AmCharts.ChartScrollbarSettings();
		chartScrollbarSettings.graph = graph;
		chartScrollbarSettings.graphType = "line";
		chartScrollbarSettings.autoGridCount = false;
		chart.chartScrollbarSettings = chartScrollbarSettings;

		var chartCursorSettings = new AmCharts.ChartCursorSettings();
		chartCursorSettings.valueBalloonsEnabled = true;
		chartCursorSettings.cursorAlpha = 0.1, chartCursorSettings.valueLineBalloonEnabled = true;
		chartCursorSettings.valueLineEnabled = true;
		chartCursorSettings.valueLineAlpha = 0.5, chart.chartCursorSettings = chartCursorSettings;

		var periodSelector = new AmCharts.PeriodSelector();
		periodSelector.periods = [ {
			period : "mm",
			count : 5,
			label : "5 m"
		}, {
			period : "hh",
			count : 1,
			label : "1 h"
		}, {
			period : "MAX",
			selected : true,
			label : "MAX"
		} ];
		periodSelector.inputFieldsEnabled=false;
		chart.periodSelector = periodSelector;

		chart.write("chartdiv");

	}

	function appendMeasurement(measurementMap) {

		angular.forEach(measurementMap, function(measurement, key) {
			chart.dataSets[key].dataProvider.push({
				date : measurement.time,
				value : measurement.value.toFixed(2),
			});
		});

		chart.validateData();
	}

	$stomp.connect('/hello').then(function(frame) {
		stompSubscription = $stomp.subscribe('/hello', function(measurementMap, headers, res) {
			appendMeasurement(measurementMap);
		});
	});

	$scope.$on("$destroy", function() {
		stompSubscription.unsubscribe();
	});
});

app.controller('ConfigurationController', [ '$scope', 'ThermometerFactory', '$compile', '$rootScope',
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

app.controller('DescriptionController', function($scope, ThermometerFactory, $rootScope) {

});