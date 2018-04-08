/*(function() {
    'use strict';


    var app = angular
                .module('asadmin',['ui.router','ngStorage'])
                .config(stateConfig);

    stateConfig.$inject = ['$stateProvider','$urlRouterProvider','$qProvider'];

    app.constant('urls', {
        BASE: '/',
        USER_SERVICE_API : '/api/users/'
    });

    function stateConfig($stateProvider, $urlRouterProvider,$qProvider) {
        //$locationProvider.html5Mode(true);
        
        //$qProvider.errorOnUnhandledRejections(false);
        $stateProvider
            .state('home', {
                url: '/',
                templateUrl: 'pages/list.html',
                controller:'UserController',
                controllerAs:'ctrl',
                resolve: {
                    users: function ($q, UserServiceGet) {
                        console.log('Load all users');
                        var deferred = $q.defer();
                        UserServiceGet.loadAllUsers().then(deferred.resolve, deferred.resolve);
                        return deferred.promise;
                    }
                }
            });
        $urlRouterProvider.otherwise('/');
    };

})();*/