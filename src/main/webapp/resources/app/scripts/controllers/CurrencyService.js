'use strict';

angular.module('webappApp')
  .service('currencyservice', function ($http, $log) {
    // AngularJS will instantiate a singleton by calling "new" on this function
   return {

        getList: function() {
	        $log.debug("viewList");
	        var request = $http.get('http://localhost:8080/exchange/api/currency');
	        return request;
	    },

	    search: function(currencyName) {
	        $log.debug("search");
	        var request = $http.get('http://localhost:8080/exchange/api/currency/'+currencyName);
	        return request;
	    },

	    convert: function(source, target, amount) {
	        $log.debug("convert");
	        var request = $http.get('http://localhost:8080/exchange/api/currency/convert?source='+source+'&target='+target+'&amount='+amount);
	        return request;
		},

	    syncWithCurrencyServer: function() {
		   $log.debug("convert");
		   var request = $http.get('http://localhost:8080/exchange/api/sync');
		   return request;
	    }

   };

  });
