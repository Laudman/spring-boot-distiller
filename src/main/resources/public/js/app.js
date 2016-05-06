var app = angular.module("app",
		[ 'ngRoute', 'app.services', 'app.controllers' ]).config(
		function($routeProvider, $locationProvider, $httpProvider) {

			var baseUrl = "html/"

			$routeProvider.when('/', {
				templateUrl : baseUrl + 'home.html',
				controller : 'ThermometerController'
			});
			$routeProvider.when('/home', {
				templateUrl : baseUrl + 'home.html',
				controller : 'ThermometerController'
			});
			$routeProvider.when('/thermometers', {
				templateUrl : baseUrl + 'thermometers.html',
				controller : 'ThermometerController'
			});
			$routeProvider.when('/settings', {
				templateUrl : 'contact.html',
				controller : 'ContactCtrl'
			});
			$routeProvider.otherwise({
				redirectTo : '/home',
				controller : 'HomeCtrl',
			});
		});
