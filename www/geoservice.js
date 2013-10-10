

var exec = exec = require('cordova/exec');

var geoservice = {

    show: function() {
        return console.log("show");
    },

    hide: function() {
        return console.log("hide");
    },

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
    }

};

module.exports = geoservice;