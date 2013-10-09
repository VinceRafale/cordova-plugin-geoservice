

var exec = exec = require('cordova/exec');

var geoservice = {

    show: function() {
        return console.log("show");
    },

    hide: function() {
        return console.log("hide");
    },

    watch: function() {

        var success = function() {
            console.log('GeoService.js - success');
        }

        var error = function() {
            console.log('GeoService.js - error');
        }

        exec(success, error, 'GeoService', 'watch', []);
    }

};

module.exports = geoservice;