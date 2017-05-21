'use strict';

angular.module('office')
.factory('Employee',['$resource',function($resource){
	return $resource('http://localhost:8080/office/api/employees/:id',{
		id:'@id'
	},{
		update_employee : {
			method : 'PUT'
		}
	,
	delete_employee: {
        method: 'DELETE'
    }
	});
	
	
}]);
