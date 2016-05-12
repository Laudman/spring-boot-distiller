var app = angular.module("app",
		[ 'ngRoute', 'app.services', 'app.controllers' ]).config(
		function($routeProvider, $locationProvider, $httpProvider) {

			var baseUrl = "html/"

			$routeProvider.when('/', {
				templateUrl : baseUrl + 'home.html',
//				controller : 'HomeController'
			})
			.when('/home', {
				templateUrl : baseUrl + 'home.html',
//				controller : 'HomeController'
			})
			.when('/thermometers', {
				templateUrl : baseUrl + 'thermometers.html',
				controller : 'ThermometerController'
			})
			.when('/plot', {
				templateUrl : baseUrl + 'plot.html',
				controller : 'PlotController'
			})
			.otherwise({
				redirectTo : '/',
				controller : 'HomeController',
			});
		});
