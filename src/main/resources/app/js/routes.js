/**
 * Created by spinetrak on 10/18/13.
 */
'use strict';


angular.module('cirRoutes', [])


    .config(function ($routeProvider) {
        $routeProvider
            .when('/cirs/approve', {
                templateUrl: '/app/partials/cirs/approve.htm'
            })
            .when('/cirs/list/:ciid', {
                templateUrl: '/app/partials/cirs/list.htm',
                controller: 'CirListCtrl'
            })
            .when('/cirs/create', {
                templateUrl: '/app/partials/cirs/list.htm',
                controller: 'CirDetailsCtrl'
            })
            .when('/cirs/view/:cirid', {
                templateUrl: '/app/partials/cirs/view.htm',
                controller: 'CirDetailsCtrl'
            })
            .when('/cirs/edit/:cirid', {
                templateUrl: '/app/partials/cirs/edit.htm',
                controller: 'CirDetailsCtrl'
            })
            .when('/cirs/search', {
                templateUrl: '/app/partials/cirs/search.htm',
                controller: 'CiidCtrl'
            })
            .otherwise({redirectTo: '/cirs/search'});
    });