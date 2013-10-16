/**
 * Created by spinetrak on 10/2/13.
 */

'use strict';

function CiidCtrl($scope, CIIDS, $rootScope, $location) {
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
            $scope.totalItems = cirs.length;
            $scope.numPages = $scope.totalItems / $scope.itemsPerPage;
        });
    });

    $scope.sortField = undefined;
    $scope.reverse = false;
    $scope.currentPage = 1;
    $scope.showCirs = false;
    $scope.itemsPerPage = 5;

    $scope.sort = function (fieldName) {
        if ($scope.sortField === fieldName) {
            $scope.reverse = !$scope.reverse
        } else {
            $scope.sortField = fieldName;
            $scope.reverse = false;
        }
    };

    $scope.isSortUp = function (fieldName) {
        return $scope.sortField === fieldName && !$scope.reverse;
    };

    $scope.isSortDown = function (fieldName) {
        return $scope.sortField === fieldName && $scope.reverse;
    };

    $scope.viewCir = function ($cir) {
        $scope.cirid = $cir.cirid;
        $rootScope.$broadcast('viewCiridSelected', $cir.cirid);
    };
}

function CirDetailsCtrl($scope, CIR) {
    $scope.$on('viewCiridSelected', function (event, cirid) {
    });
}

cirManager.controller('CiidCtrl', ['$scope', 'CIIDS', '$rootScope', '$location', CiidCtrl]);
cirManager.controller('CirListCtrl', ['$scope', 'CIRSforCIID', CirListCtrl]);
cirManager.controller('CirDetailsCtrl', ['$scope', 'CIR', CirDetailsCtrl]);

