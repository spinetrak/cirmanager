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
}

function CirDetailsCtrl($scope, CIR, $routeParams) {
    $scope.cirid = $routeParams.cirid;
    if ($scope.cirid && !$scope.cir) {
        CIR.get({cirid: $scope.cirid}, function (cir) {
            $scope.cir = angular.copy(cir);
            $scope.revert = function () {
                $scope.cir = angular.copy(cir);
                $scope.cirForm.$pristine = true;
                $scope.cirForm.$dirty = false;
            }
        });
    }
}

cirManager.controller('CiidCtrl', ['$scope', 'CIIDS', '$rootScope', '$location', CiidCtrl]);
cirManager.controller('CirListCtrl', ['$scope', 'CIRSforCIID', CirListCtrl]);
cirManager.controller('CirDetailsCtrl', ['$scope', 'CIR', '$routeParams', CirDetailsCtrl]);

