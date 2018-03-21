(function () {
    'use strict';

    angular
        .module('app')
        .controller('CustomerController', CustomerController);

    CustomerController.$inject = ['$http','$scope'];

    function CustomerController($http, $scope) {
        var vm = this;

        $scope.customers = [];
        $scope.getAll = getAll;
        $scope.refresh = refresh;

        init();

        function init(){
            getAll();
        }

        function getAll(){
            var url = "/customers/all";
            var customersPromise = $http.get(url);
            customersPromise.then(function(response){
                var result = response.data;
                console.log(result);
                $scope.customers = response.data.map(convert);
            }).catch(angular.noop);
        }

        function refresh(){
         var url = "/customers/all";
         var customersPromise = $http.get(url);
         customersPromise.then(function(response){
            var result = response.data;
            console.log(result);
            $scope.customers = response.data.map(convert);
            $scope.$apply();
          }).catch(angular.noop);
        }

        function convert(item, index) {
        var person = {
            id:index,
            firstName:item.firstName,
            lastName:item.lastName,
            state:translate(item.state)
        };
        return person;
        }

        function translate(state){
        switch(state){
            case "ONLINE":
                return "ON";
            case "ABSEND":
                return "BUSY";
            default:
                return "OFF";
        }
        }
var stompClient = null;

function connect() {
    var socket = new SockJS('/gs-guide-websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/customer', function (customer) {
           console.log('Update: ' + JSON.parse(customer.body));
           refresh();
        });
    });
}

connect();
    }
})();

