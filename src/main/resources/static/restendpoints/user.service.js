(function () {
    'use strict';

    angular
        .module('asadmin')
        .factory('UserService', UserService);

    UserService.$inject = ['$http'];
    function UserService($http) {
        var service = {};

        service.GetAll = GetAll;
        service.GetById = GetById;
        service.GetByUsername = GetByUsername;
        service.Create = CreateUser;
        service.Update = Update;
        service.Delete = Delete;
        service.getUserAuthentication = getUserAuthentication;
        return service;

        function GetAll() {
            return $http.get('/api/users').then(handleSuccess, handleError('Error getting all users'));
        }

        function GetById(id) {
            return $http.get('/api/users/' + id).then(handleSuccess, handleError('Error getting user by id'));
        }

        function GetByUsername(username) {
            return $http.get('/api/users/' + username).then(handleSuccess, handleError('Error getting user by username'));
        }

        function CreateUser(user) {
            return $http.post('/api/register', user).then(handleSuccess, handleError);
        }

        function getUserAuthentication(credentials){
            return $http.post('/api/authentication',credentials, {
                headers:{
                    'Content-Type' : 'application/x-www-form-urlencoded'
                }
            }).then(handleSuccess, handleError);
        }

        function Update(user) {
            return $http.put('/api/users/' + user.id, user).then(handleSuccess, handleError('Error updating user'));
        }

        function Delete(id) {
            return $http.delete('/api/users/' + id).then(handleSuccess, handleError('Error deleting user'));
        }

        // private functions

        function handleSuccess(res) {
            return res;
        }

        function handleError(error) {
            return error;
            /*return function () {
                return { success: false, message: error };
            };*/
        }
    }

})();
