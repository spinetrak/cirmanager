/**
 * Created by spinetrak on 10/18/13.
 */

'use strict';

angular.module('ciids', [])

    .factory('CIIDS', ['$resource', function ($resource) {
        return $resource('/restapi/ciids',
            {}, {});
    }])

    .controller('CiidCtrl', ['$scope', 'CIIDS', '$rootScope', '$location', function ($scope, CIIDS, $rootScope, $location) {
        $scope.selected = undefined;
        $scope.ciids = CIIDS.query();

        $scope.onSelect = function ($ciid) {
            $scope.ciid = $ciid.ciid;
            $rootScope.$broadcast('ciidSelected', $ciid.ciid);
        }
    }]);


