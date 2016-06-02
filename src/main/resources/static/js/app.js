var app = angular.module("app",
		[ 'ngRoute', 'app.services', 'app.controllers']).config(
		function($routeProvider, $locationProvider, $httpProvider) {

			var baseUrl = "html/"

			$routeProvider.when('/', {
				templateUrl : baseUrl + 'home.html',
			})
			.when('/home', {
				templateUrl : baseUrl + 'home.html',
			})
			.when('/thermometers', {
				templateUrl : baseUrl + 'thermometers.html',
				controller : 'ThermometerController'
			})
			.when('/plot', {
				templateUrl : baseUrl + 'plot.html',
				controller : 'PlotController'
			})
			.when('/description', {
				templateUrl: baseUrl + 'description.html',
				controller : 'DescriptionController'
			})
			.otherwise({
				redirectTo : '/',
				controller : 'HomeController',
			});
		});
