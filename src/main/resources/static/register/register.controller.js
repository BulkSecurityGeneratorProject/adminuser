(function () {
    'use strict';

    angular
        .module('asadmin')
        .controller('RegisterController', RegisterController);

    RegisterController.$inject = ['UserService', '$location', '$rootScope', 'FlashService','$scope', '$state','$http'];
    function RegisterController(UserService, $location, $rootScope, FlashService, $scope, $state, $http) {
        var vm = this;

        vm.register = register;
        //vm.checkConfirmPassword = checkConfirmPassword;

        $scope.checkConfirmPassword = function (confirm){
            if(confirm !== undefined){
                if(confirm != vm.user.password){
                    vm.confirmError = true;
                    vm.confirmMessage = "Password does not match";
                } else{
                    vm.confirmError = false;
                }
            } else{
                vm.confirmError = false;
            }
        }

        function register() {
            if(!vm.confirmError){
                vm.dataLoading = true;
                UserService.CreateUser(vm.user)
                .then(function callback(response){
                    if (response.status == 201) {
                        FlashService.Success('Registration successful', true);
                        vm.dataLoading = false;
                        vm.registerHide = true;
                        //$location.path('/login');
                    } else{
                        if(response.status == 406){
                            FlashService.Error("email address is invalid");
                        } else{
                            FlashService.Error(response.data);
                        }
                        vm.dataLoading = false;
                        vm.registerHide = false;
                    }
                });
            } else{
                vm.confirmMessage = "Password does not match";
            }
        }
    }

})();
