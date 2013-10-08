/**
 * Created by spinetrak on 10/2/13.
 */

'use strict';

/* Factories */

cirManager.factory('CIIDS', ['$resource', function ($resource) {
    return $resource('/ciids',
        {}, {});
}]);

cirManager.factory('CIRSforCIID', ['$resource', function ($resource) {
    return $resource('/cirs/ciids/:ciid',
        { ciid: '@ciid' }, {});
}]);
