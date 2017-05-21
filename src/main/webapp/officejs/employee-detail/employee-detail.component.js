/**
 * 
 */
angular
		.module('employeeDetail')
		.component(
				'employeeDetail',
				{
					templateUrl : '/office/officejs/employee-detail/employee-detail.template.html',
					controller : [
							'$routeParams',
							'Employee',
							'$scope',
							'Manager',
							function EmployeeDetailController($routeParams,
									Employee, $scope, Manager) {
								var self = this;
								
								
								self.managers = Manager.query();
								self.fetchEmployee = function() {
									self.employee = Employee.get({
										id : $routeParams.id
									},function(){
										//success
									},function(error){
						            	console.log(error);
						            	self.error=error;
						            });
								};

								self.updateEmployee = function() {
									delete self.employee.manager.list;
									console.log(self.employee.manager.list);
									console.log(self.employee);
									Employee.update_employee({
										id : $routeParams.id
									}, self.employee, function() {
										self.fetchEmployee();
									});
								};

								self.deleteEmployee = function(identity) {
									Employee.delete_employee({
										id : $routeParams.id
									}, function() {
										self.fetchEmployee();
									});
								};

								self.fetchEmployee();

								self.submit = function() {
									self.updateEmployee();
								};

								// self.edit = function() {
								// self.employee = angular.copy(self.employee);
								// };

								self.reset = function() {
									$scope.myForm.$setPristine(); // reset
									// Form
								};

							} ]
				});