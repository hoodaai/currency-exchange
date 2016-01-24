'use strict';

/**
 * @ngdoc function
 * @name webappApp.controller:AboutCtrl
 * @description
 * # AboutCtrl
 * Controller of the webappApp
 */

angular.module('webappApp')
  .controller('CurrencyController', function ($scope, $location, currencyservice) {

      $scope.currencies = [
            {name:'AED', id:'AED'},


          ];

            currencyservice.getList().success(function(data) {
    		  	console.log("Success");
    		  	$scope.currencyList = data.result;
    		}).error(function(data) {
    		  	console.log("error");
            });

              $scope.search = function(currencyName) {
                  currencyservice.search(currencyName).success(function(data) {
                     console.log("Success" + data);
                     $scope.searchResult = data;
                   }).error(function(data) {
                     console.log("error");
                 });
             };

             $scope.convert = function(source, target, amount) {
                 currencyservice.convert(source, target, amount).success(function(data) {
                    console.log("Success" + data);
                    $scope.convertedCurrency = data;
                  }).error(function(data) {
                    console.log("error");
                });
            };

            $scope.syncWithCurrencyServer = function(source, target, amount) {
                currencyservice.syncWithCurrencyServer().success(function(data) {
                    console.log("Sync Success");
                    currencyservice.getList().success(function(data) {
                        console.log("getList Success");
                        $scope.currencyList = data.result;
                    }).error(function(data) {
                        console.log("error");
                    });
                }).error(function(data) {
                    console.log("error");
                });
            };

  });
