/**
 * Created by spinetrak on 10/2/13.
 */

'use strict';

function CiidCtrl($scope, CIID) {
    $scope.selected = undefined;
    $scope.ciids = CIID.query();

    $scope.onSelect = function ($ciid) {
        $scope.cirsForCiid = $ciid;
    }
}

cirManager.controller('CiidCtrl', ['$scope', 'CIID', CiidCtrl]);