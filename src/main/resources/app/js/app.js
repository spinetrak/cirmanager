/**
 * Created by spinetrak on 10/2/13.
 */

'use strict';

/* App Module */

var cirManager = angular.module('cirmanager', ['ngResource', 'ui.bootstrap', 'ciids', 'cirs', 'cirRoutes', 'cirPagination']);

cirManager.config(function ($locationProvider) {
    $locationProvider.html5Mode(true);
});

