(function () {
    'use strict';

    angular
        .module('asadmin')
        .controller('LoginController', LoginController);

    LoginController.$inject = ['$location', 'AuthenticationService', 'FlashService', '$state', '$rootScope'];
    function LoginController($location, AuthenticationService, FlashService, $state, $rootScope) {
        var vm = this;

        vm.login = login;

        (function initController() {
            // reset login status
            if(!$rootScope.globals.currentUser){
                AuthenticationService.ClearCredentials();
            }
        })();

        function login() {
            vm.dataLoading = true;
            AuthenticationService.Login({
                username:vm.username,
                password: vm.password
            }).then(function (response) {
                if(response.status == 200){
                    vm.dataLoading = false;
                    AuthenticationService.SetCredentials(vm.username, vm.password);
                    FlashService.clearFlashMessage();
                    $state.go('app.dashboard', null, {
                        reload:true
                    });
                } else {
                    FlashService.Error(response.data.error);
                    vm.dataLoading = false;
                }
            });
        };
    }

})();
