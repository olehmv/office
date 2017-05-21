'use strict';

angular.module('officeApp').config(
		function config($locationProvider, $routeProvider) {
			$locationProvider.hashPrefix('!');

			$routeProvider.when('/managers', {
				template : '<manager-list></manager-list>'
			}).when('/managers/:id', {
				template : '<manager-detail></manager-detail>'
			}).when('/managers/:id/employees', {
				template : '<manager-employees></manager-employees>'
			}).when('/managers/:id/employees/:id', {
				template : '<employee-detail></employee-detail>'
			}).otherwise('/managers');

		});
