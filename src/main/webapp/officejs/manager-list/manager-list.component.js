
'use strict';

angular.module('managerList').component('managerList', {
	templateUrl : '/office/officejs/manager-list/manager-list.template.html',
	controller : [ 'Manager', function ManagerListController(Manager) {
		this.managers = Manager.query();
		
		/* var self = this;
         self.manager= new Manager();
          
         self.managers=[];
              
         self.fetchAllManagers = function(){
        	 self.managers = Manager.query();
         };
           
         self.createManager = function(){
             self.manager.$save(function(){
                 self.fetchAllManagers();
             });
         };

         self.updateManager= function(){
             self.manager.$update(function(){
                 self.fetchAllManagers();
             });
         };

        self.deleteManager = function(identity){
            var manager = Manager.get({id:identity}, function() {
            	manager.$delete(function(){
                     console.log('Deleting manager with id ', identity);
                     self.fetchAllManagers();
                 });
            });
         };

         self.fetchAllManagers();

         self.submit = function() {
             if(self.manager.id==null){
                 console.log('Saving New Manager', self.manager);    
                 self.createManager();
             }else{
                 console.log('Upddating manager with id ', self.manager.id);
                 self.updateManager();
                 console.log('Mnanager updated with id ', self.manager.id);
             }
             self.reset();
         };
              
         self.edit = function(id){
             console.log('id to be edited', id);
             for(var i = 0; i < self.managers.length; i++){
                 if(self.managers[i].id === id) {
                    self.manager = angular.copy(self.managers[i]);
                    break;
                 }
             }
         };
              
         self.remove = function(id){
             console.log('id to be deleted', id);
             if(self.manager.id === id) {//If it is the one shown on screen, reset screen
                self.reset();
             }
             self.deleteManager(id);
         };

          
         self.reset = function(){
             self.manager= new Manager();
             $scope.myForm.$setPristine(); //reset Form
         };
*/

	}

	]

});