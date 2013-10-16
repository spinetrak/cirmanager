/**
 * Created by spinetrak on 10/2/13.
 */

'use strict';

/* App Module */

var cirManager = angular.module('cirmanager', ['ngResource', 'ui.bootstrap']);

cirManager.config(function ($locationProvider) {
    $locationProvider.html5Mode(true);
});

cirManager.config(function ($routeProvider) {
    $routeProvider
        .when('/cirs/approve', {
            templateUrl: '/app/partials/cirs/approve.htm'
        })
        .when('/cirs/list/:ciid', {
            templateUrl: '/app/partials/cirs/list.htm',
            controller: 'CirListCtrl'
        })
        .when('/cirs/create', {
            templateUrl: '/app/partials/cirs/create.htm',
            controller: 'CirDetailsCtrl'
        })
        .when('/cirs/search', {
            templateUrl: '/app/partials/cirs/search.htm',
            controller: 'CiidCtrl'
        })
        .otherwise({redirectTo: '/cirs/search'});
});