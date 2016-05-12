var services = angular.module('app.services', [ 'ngResource' ]);
services.factory('ThermometerFactory', function($resource) {
	return $resource('/thermometer', {}, {
		add : {
			method : 'POST',
			url : 'thermometer/add'
		},
		del : {
			method : 'DELETE',
			url : 'thermometer/delete/:id'
		},		
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
		getTemperature : {
			method: 'GET',
			url: 'thermometer/temperature/:id'
		},


	})
});