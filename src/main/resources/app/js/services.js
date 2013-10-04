/**
 * Created by spinetrak on 10/2/13.
 */

'use strict';

/* Services */

angular.module('cirManagerServices', ['ngResource']).
    factory('CIID', function ($resource) {
        return $resource('/cis', {}, {
            query: {method: 'GET', isArray: true}
        });
    });