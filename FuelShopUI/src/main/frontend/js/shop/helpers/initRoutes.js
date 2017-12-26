function initRoutes($route) {
    var k, defaultRoutes = {
	'/friend' : {
	    templateUrl : 'pages/friend.html',	
	},
	'/signin' : {
	    templateUrl : 'pages/signin.html'
	},	
	'' : {
	    templateUrl : 'pages/index.html'
	},
	'/home' : {
	    templateUrl : 'pages/homeSignedIn.html'
	},
	'/' : {
	    templateUrl : 'pages/index.html'
	}

    }, defaultUrl;

    for (k in defaultRoutes) {
	$route.routes[k] = angular.extend({
	    reloadOnSearch : true,
	    regexp : new RegExp('^' + k + '$')
	}, defaultRoutes[k]);

    }
}
