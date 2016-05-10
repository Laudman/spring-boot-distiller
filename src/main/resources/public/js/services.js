var services = angular.module('app.services', [ 'ngResource' ]);
services.factory('ThermometerFactory', function($resource) {
	return $resource('/thermometer', {}, {
		getTwiAddresses : {
			method : 'GET',
			url : 'thermometer/addresses',
			isArray : true
		},
		getThermometers : {
			method : 'GET',
			url : 'thermometer/thermometers',
			isArray : true
		},
		add : {
			method : 'POST',
			url : 'thermometer/add'
		},
		del : {
			method : 'DELETE',
			url : 'thermometer/delete/:id'
		},

	})
});