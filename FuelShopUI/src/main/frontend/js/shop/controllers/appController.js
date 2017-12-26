angular.module('shopControllers').controller('AppController',
	function($q, $scope, $rootScope, $route, config, $log, $http, $locale, $translate, $timeout, $location) {

	    $rootScope.notification = {};
	    initRoutes($route);

	    $scope.successMessageHandler = function(message) {
		$log.debug("AppController successMessageHandler message: " + message);
		if (!$rootScope.notification.error) {
		    $rootScope.notification.message = message;
		    $rootScope.notification.error = false;

		    $timeout(function() {
			$rootScope.notification.message = '';
			$rootScope.notification.error = '';
		    }, 5000);
		}
	    };

	    $scope.ServerErrorHandler = function(error) {
		$rootScope.notification.message = $translate.instant('service_Error') + error;
		$rootScope.notification.error = true;
		$log.error("ServerErrorHandler error :" + error);
	    };
	});