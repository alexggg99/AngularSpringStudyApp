var app = angular.module('app', ['ngMessages', 'ngResource', 'ngRoute']);

app.config(function ($routeProvider, $locationProvider) {
    $routeProvider.otherwise({redirectTo: '/'})
        .when('/', {
            controller: 'HomeController',
            templateUrl: 'app/tpl/home.html'
        })
        .when('/home2', {
            controller: 'HomeController',
            templateUrl: 'app/tpl/home2.html'
        });

    $locationProvider.html5Mode(true).hashPrefix('!');
});