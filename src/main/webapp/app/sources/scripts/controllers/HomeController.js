app.controller('HomeController', function ($scope, Home) {
    $scope.vm = this;
    $scope.vm.homes = Home.query();
});