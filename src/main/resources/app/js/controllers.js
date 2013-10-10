/**
 * Created by spinetrak on 10/2/13.
 */

'use strict';

function CiidCtrl($scope, CIIDS, $rootScope) {
    $scope.selected = undefined;
    $scope.ciids = CIIDS.query();

    $scope.onSelect = function ($ciid) {
        $scope.ciid = $ciid.ciid;
        $rootScope.$broadcast('ciidSelected', $ciid.ciid);
    }
}


function CirListCtrl($scope, CIRSforCIID) {
    $scope.$on('ciidSelected', function (event, ciid) {
        $scope.showCirs = false;
        CIRSforCIID.query({ciid: ciid}, function (cirs) {
            $scope.cirsForCiid = cirs;
            $scope.showCirs = cirs.length > 0;
        });
    });
}

cirManager.controller('CiidCtrl', ['$scope', 'CIIDS', '$rootScope', CiidCtrl]);
cirManager.controller('CirListCtrl', ['$scope', 'CIRSforCIID', CirListCtrl]);


