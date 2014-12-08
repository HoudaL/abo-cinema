var angularUserApp = angular.module('myAdminApp', ['ngRoute']);



angularUserApp.config(function($routeProvider) {

	$routeProvider
    .when('/list', {
        templateUrl: 'list.html',
        controller : 'listController'
    })
    .when('/user/:userId', {
        templateUrl: 'user.html',
        controller : 'editUserController'
    })
    .when('/edit', {
        templateUrl: 'edit.html',
        controller : 'listController'
    })
    .when('/edit/:userId', {
        templateUrl: 'edit.html',
        controller : 'editUserController'
    })
    .otherwise({
        redirectTo: '/list'
    });


});


angularUserApp.controller('editUserController', function($scope, $http, $routeParams, $location){

    var userId = $routeParams.userId;

    $http.get('/abocine/rest/users/projects/' + userId).success(function(user){
       $scope.user = user;
    });

    $scope.addUser = function( user){
    //	$http.get('/abocine/rest/users/update/' + userId +'/' + user.nom +'/'+user.prenom +'/'+user.email)
    var user = {
      "id": userId ,
    "nom": user.nom,
     "prenom" : user.prenom , 
     "email" : user.email 
  } ;
    $http.put('/abocine/rest/users/update/'+  userId, user)
           .success(function(){
               $location.path('/users/projects/');
           })
           .error(function(resp){
               console.log(resp);
           });


    }

    $scope.deleteUser= function(){

    	$http.delete('/abocine/rest/users/delete/' + userId).success(function(){
               $location.path('/users/projects/');
           })
           ;
    }
    });

angularUserApp.controller('listController' , function($scope , $http , $location){
   
     $http.get('/abocine/rest/users/projects/').success(function(resp){
        $scope.users = resp;
    });

    $scope.search= function(){

      $http.get('/abocine/rest/users/search/' + $scope.input).success(function(resp){
        $scope.users = resp;
    })  .error(function(resp){
                console.log(resp);
            });
           
    }

 $scope.addUser = function(user){
  var user = {
     
    "nom": user.nom,
     "prenom" : user.prenom , 
     "email" : user.email 
  } ;
   $http.post('/abocine/rest/users/add' , user)
           .success(function(){
               $location.path('/users/projects/');
           })
           .error(function(resp){
               console.log(resp);
           });


    }

}) ;







