/**
 * Created by spinetrak on 10/18/13.
 */

'use strict';


angular.module('cirs', [])

    .factory('CIR', ['$resource', function ($resource) {
        return $resource('/restapi/cirs/:cirid',
            { cirid: '@cirid' }, {});
    }])

    .factory('CIRSforCIID', ['$resource', function ($resource) {
        return $resource('/restapi/cirs/ciids/:ciid',
            { ciid: '@ciid' }, {});
    }])

    .controller('CirListCtrl', ['$scope', 'CIRSforCIID', function ($scope, CIRSforCIID) {
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
    }])

    .controller('CirDetailsCtrl', ['$scope', 'CIR', '$routeParams', function ($scope, CIR, $routeParams) {
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
    }]);
