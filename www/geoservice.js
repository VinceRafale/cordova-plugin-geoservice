

var exec = exec = require('cordova/exec');

var geoservice = {

    get: function(successCallback) {

        var success = function(position) {

            console.log('GeoService.js - success');

            pos = {
                coords: position
            };
            
            successCallback(pos);
        }

        var error = function() {
            console.log('GeoService.js - error');
        }

        exec(success, error, 'GeoService', 'get', []);
    }, 

    watch: function(successCallback) {

        var success = function(position) {

            console.log('GeoService.js - success');

            pos = {
                coords: position
            };
            
            successCallback(pos);
        }

        var error = function() {
            console.log('GeoService.js - error');
        }

        exec(success, error, 'GeoService', 'watch', []);
    },

    stop: function() {
        exec(null, null, 'GeoService', 'stop', []);
    }


};

module.exports = geoservice;