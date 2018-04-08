'use strict';

/**
 * Route configuration for the RDash module.
 */
angular.module('asadmin').config(['$stateProvider', '$urlRouterProvider',
    function($stateProvider, $urlRouterProvider) {

        $stateProvider
            .state('app.dashboard', {
                url: '/dashboard',
                templateUrl: 'dashboard/templates/dashboard.html',
                controller: 'MasterCtrl'
            })
            .state('app.tables', {
                url: '/tables',
                templateUrl: 'dashboard/templates/tables.html',
                controller: 'MasterCtrl'
            });
    }
]);