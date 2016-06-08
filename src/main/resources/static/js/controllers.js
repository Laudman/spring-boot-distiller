var app = angular.module("app.controllers", [ 'ngStomp' ])

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

app.controller("PlotController", function($scope, ThermometerFactory, $stomp) {

	var dataSets = [];

	$scope.init = function() {

		getMeasurementData();

		function getMeasurementData() {
			$scope.data = ThermometerFactory.getMeasurements()
			$scope.data.$promise.then(function(success) {
				measurementData = success.measurementData;
				prepareChartData();
			});
		}

		function prepareChartData() {

			angular.forEach(measurementData, function(measurementList, thermometer) {

				var measurementForThermometer = [];
				
				
//				if(measurementList.length==0) {
//					var d = new Date();
//					measurementForThermometer.push({
//						date : "2016-06-08T14:26:16.644",
//						value : 100
//					});
//				}
				
				angular.forEach(measurementList, function(measurement, key) {
					measurementForThermometer.push({
						date : measurement.time,
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

				
				
				dataSets.push(dataSet);
			});
			generateChart();
		}

		function generateChart() {
			AmCharts.useUTC=true;
			var chart = new AmCharts.AmStockChart();
			chart.glueToTheEnd = true;
			chart.dataSets = dataSets;

			var stockPanel = new AmCharts.StockPanel();
			stockPanel.showCategoryAxis = true;
			stockPanel.title = "Temperature Chart";
			stockPanel.percentHeight = 60;

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
				period : "mm",
				count : 1,
				label : "1 h"
			}, {
				period : "MAX",
				selected : true,
				label : "MAX"
			} ];
			chart.periodSelector = periodSelector;

			var dataSetSelector = new AmCharts.DataSetSelector();
			dataSetSelector.position = "left";
			dataSetSelector.selectText = "Thermometer";
			dataSetSelector.color = "black";
			dataSetSelector.enabled = true;

			chart.dataSetSelector = dataSetSelector;

			chart.write("chartdiv");
			getRealTimeData();

			function getRealTimeData() {
				if ($stomp.sock == null) {
					$stomp.connect('/hello').then(function(frame) {
						$stomp.subscribe('/hello', function(measurementMap, headers, res) {
							var i = 0;
							angular.forEach(measurementMap, function(measurement, key) {
								chart.dataSets[i].dataProvider.push({
									date : measurement.time,
									value : measurement.value.toFixed(2),
								});
								i++;

							});

							chart.validateData();
						});

					});
				}
			}

		}
	}

});

app.controller('DescriptionController', function($scope, ThermometerFactory, $rootScope) {

});
