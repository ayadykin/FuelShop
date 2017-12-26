(function() {

    'use strict';
    
    var shopApp = angular.module('shopApp', [
	    'pascalprecht.translate',
	    'ngLocale',
	    'ngRoute',
	    'ngCookies',
	    'ui.bootstrap',
	    'ngAnimate',
	    'shopControllers',
	    'shopServices' ]);

    angular.module('shopControllers', []);
    angular.module('shopServices', [ 'ngResource' ]);

    shopApp.config(function($translateProvider, $routeProvider, $httpProvider) {
	$translateProvider.useLoader('i18nLoader');

	$httpProvider.defaults.headers.common['Access-Control-Allow-Origin'] = '*';
	$httpProvider.interceptors.push('restErrorInterceptor');
    });
}());