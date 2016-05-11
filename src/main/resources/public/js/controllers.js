var app = angular.module("app.controllers", []);

//app.controller('NavCtrl', [ '$scope', '$location', function($scope, $location) {
//	$scope.navClass = function(page) {
//		var currentRoute = $location.path().substring(1) || 'home';
//		return page === currentRoute ? 'active' : '';
//	};
//} ]);

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
//app.controller('HomeController', [function(){
//	
//}]);
//
//app.controller('IndexController', [function(){
//	
//}]);