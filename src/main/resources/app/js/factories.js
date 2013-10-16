/**
 * Created by spinetrak on 10/2/13.
 */

'use strict';

/* Factories */

cirManager.factory('CIIDS', ['$resource', function ($resource) {
    return $resource('/restapi/ciids',
        {}, {});
}]);

cirManager.factory('CIRSforCIID', ['$resource', function ($resource) {
    return $resource('/restapi/cirs/ciids/:ciid',
        { ciid: '@ciid' }, {});
}]);

cirManager.factory('CIR', ['$resource', function ($resource) {
    return $resource('/restapi/cirs/:cirid',
        { cirid: '@cirid' }, {});
}]);
