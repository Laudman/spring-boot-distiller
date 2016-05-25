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
			url : 'thermometer/available',
			isArray : true
		},
		getThermometers : {
			method : 'GET',
			url : 'thermometer/availablethermometers',
			isArray : true
		},
		setId : {
			method: 'GET',
			url: 'thermometer/temperature/:id'
		},
		getTemperature : {
			method: 'GET',
			url: 'thermometer/temperatures',
			isArray : true
		}
	})
});