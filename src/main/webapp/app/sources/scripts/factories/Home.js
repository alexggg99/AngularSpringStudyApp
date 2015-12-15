app.factory('Home', function ($resource) {
    return $resource('_data/home.json');
});