var services = angular.module('app.services', [ 'ngResource' ]);
services.factory('ThermometerFactory', function($resource) {
	return $resource('/thermometer', {}, {
		getAll : {
			method : 'GET',
			url : 'thermometer/addresses',
			isArray : true
		},
		add : {
			method : 'POST',
			url : 'thermometer/add'
		}

	})
});