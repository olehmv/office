/**
 * 
 */
angular.
  module('managerDetail').
  component('managerDetail', {
    templateUrl: '/office/officejs/manager-detail/manager-detail.template.html',
    controller: ['$routeParams', 'Manager','Employee',
      function ManagerDetailController($routeParams, Manager,Employee) {
        var self = this;
        self.manager = Manager.get({id: $routeParams.id});
        self.employee={};
        self.error={};
        self.employees=Employee.query();
        self.departments=["credite","hr","it"];
        self.show=false;
        self.addEmployee=false;
        self.newEmployee=false;
        self.reset=function(){
        	self.employee={};
            self.newEmployee=false;

        }
        self.showAddEmployeeFrom=function(){
        	addEmployee=true;
        }
        self.addEmployee=function(){
        	if(self.employee.id==null){
          	self.employee.manager=self.manager;
        	delete self.employee.manager.list;
        	Employee.save(self.employee,function(data){
        		self.employee=data;
				self.fetchManager();
				self.newEmployee=true;
        	});
        	}else{
        		self.employee.manager=self.manager;
            	delete self.employee.manager.list;
            	Employee.update_employee(self.employee,function(data){
            		self.employee=data;
    				self.fetchManager();
    				self.newEmployee=true;
        	});
        	}
        }
        
        self.edit=function(){
        	self.editeManager=true;
        }
        self.updateManager=function(){
        	delete self.manager.list;
				console.log(self.manager);
				Manager.update_manager({
					id : $routeParams.id
				}, self.manager, function() {
					self.fetchManager();
				});
        }
        self.fetchManager=function(){
            self.manager = Manager.get({id: $routeParams.id},function() {
            },function(error){
            	console.log(error);
            	self.error=error;
            });
        }
        self.deleteManager=function(){
        	Manager.delete_manager({id: $routeParams.id},function(){
				self.fetchManager();
        	},function(error){
            	console.log(error);
            	self.error=error;
            });
        }
        
    }
    ]
  });