/**
 * 
 */
angular.
  module('managerEmployees').
  component('managerEmployees', {
    templateUrl: '/office/officejs/manager-employees/manager-employees.template.html',
    controller: ['$scope','$routeParams', 'Manager','Employee',
      function ManagerDetailController($scope ,$routeParams, Manager, Employee) {
        var self = this;
        self.manager = Manager.get({id: $routeParams.id});
    }
    ]
  });