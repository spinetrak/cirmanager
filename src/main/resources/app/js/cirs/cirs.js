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

        $scope.canView = function (cirid) {
            return true;
        };
        $scope.canEdit = function (cirid) {
            return false;
        };
        $scope.canApprove = function (cirid) {
            return false;
        };
        $scope.canReject = function (cirid) {
            return false;
        };
    }])

    .controller('CirDetailsCtrl', ['$scope', 'CIR', '$routeParams', function ($scope, CIR, $routeParams) {

        $scope.cirid = $routeParams.cirid;
        var original;

        if ($scope.cirid && !$scope.cir) {
            CIR.get({cirid: $scope.cirid}, function (cir) {
                $scope.cir = cir;
                original = angular.copy(cir);
            });
        }

        $scope.revertCIR = function () {
            $scope.cir = angular.copy(original);
            $scope.cirForm.$pristine = true;
            $scope.cirForm.$dirty = false;
        }

        $scope.canCancel = function () {
            return false;
        };
        $scope.canEdit = function () {
            return true;
        };
        $scope.canApprove = function () {
            return false;
        };
        $scope.canReject = function () {
            return false;
        };
        $scope.canRevert = function () {
            return !angular.equals($scope.cir, original);
        };
        $scope.canSave = function () {
            return $scope.canRevert();
        };
        $scope.canSubmit = function () {
            return $scope.canEdit() && $scope.canSave();
        };

        $scope.editCIR = function () {
            //TBD
        }

        $scope.cancelCIR = function () {
            //TBD
        }

        $scope.approveCIR = function () {
            //TBD
        }

        $scope.rejectCIR = function () {
            //TBD
        }

        $scope.submitCIR = function () {
            //TBD
        }
    }]);
