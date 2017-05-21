'use strict';

angular.module('office').factory('Manager',
		[ '$resource', function($resource) {
			return $resource('http://localhost:8080/office/api/managers/:id', {
				id : '@id'
			},{
				update_manager : {
					method : 'PUT'
				}
			,
			delete_manager: {
		        method: 'DELETE'
		    }
			});

		} ]);
