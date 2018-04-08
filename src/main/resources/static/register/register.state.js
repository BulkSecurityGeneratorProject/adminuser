(function() {

    'use strict';

    angular
        .module('asadmin')
        .config(registerConfig);

    /** @ngInject */
    registerConfig.$inject = ['$stateProvider'];
    function registerConfig($stateProvider) {
        $stateProvider
            .state('register', {
                url: '/register',
                templateUrl: 'register/register.html',
                controller: 'RegisterController',
                controllerAs: 'vm'
            });
    }
})();