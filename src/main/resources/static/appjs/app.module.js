(function() {
    'use strict';

    angular
        .module('asadmin', [
        'ngStorage', 
        'ngResource',
        'ngCookies',
	    'toastr',
	    'ngFileUpload',
	    'ui.select',
	    'ui.bootstrap',
	    'ngAnimate',
	    'slick',
        'ui.bootstrap.datetimepicker',
        'ui.router',
        'infinite-scroll',
	    'spring-data-rest',
        'angular-loading-bar',
        'ngConfirm',
        'angularSpinner',
		'angular-carousel',
		'ngTouch',
        'ngIdle'	
        ])
        .run(run);

    run.$inject = ['$rootScope', '$location', '$cookies', '$http', '$state', '$localStorage'];

    /*Session Management handling in frontend Side */
    function run($rootScope, $location, $cookies, $http, $state, $localStorage){

        // keep user logged in after page refresh
        $rootScope.globals = $cookies.getObject('globals') || {};
        if($rootScope.globals.currentUser){
            $http.defaults.headers.common['Authorization'] = 'Basic' + $rootScope.globals.currentUser.authdata;
        }

        $rootScope.$on('$stateChangeStart',
            function(event,toState, toParams, fromState, fromParams){

            /*redirect to login page if not logged in and trying to access a restricted page
            and not redirect to same if logged in already happened*/
            var restrictedPage = $.inArray(toState.name,['login', 'register']) === -1;
            var loggedIn = $rootScope.globals.currentUser;
            if (restrictedPage && !loggedIn) {
                 event.preventDefault();
                 $state.go('login');
            } else if(!restrictedPage && loggedIn){
                if($location.path() == '/login' || $location.path() == '/register'){
                    event.preventDefault();
                    $state.go(fromState.name);
                }
            }
        });
    }
  
})();
