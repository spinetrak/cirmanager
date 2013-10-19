/**
 * Created by spinetrak on 10/2/13.
 */

'use strict';


angular.module('cirPagination', [])

    .filter('pagination', function () {
        return function (inputArray, currentPage, itemsPerPage) {
            var start = (currentPage - 1) * itemsPerPage;
            return inputArray && inputArray.slice(start, start + itemsPerPage);
        };
    });