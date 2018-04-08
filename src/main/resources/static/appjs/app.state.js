(function () {
    'use strict';

    angular
        .module('asadmin')
        .config(config);

    config.$inject = ['$stateProvider', '$urlRouterProvider'];
    function config($stateProvider, $urlRouterProvider) {
        $stateProvider
            .state('app',{
                abstract: true,
                templateUrl :'dashboard/home.html',
                controller: 'MasterCtrl'
            });
            // $urlRouterProvider.otherwise('/login');
    }

})();