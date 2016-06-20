var services = angular.module('app.services', ['ngResource',]);

services.factory('ThermometerFactory', function ($resource) {
    return $resource('/thermometer', {}, {
        add: {
            method: 'POST',
            url: 'thermometer/add'
        },
        del: {
            method: 'DELETE',
            url: 'thermometer/delete/:id'
        },
        getTwiAddresses: {
            method: 'GET',
            url: 'thermometer/addresses',
            isArray: true
        },
        getThermometers: {
            method: 'GET',
            url: 'thermometer/thermometers',
            isArray: true
        },
        setId: {
            method: 'GET',
            url: 'thermometer/temperature/:id'
        },
        getMeasurements: {
            method: 'GET',
            url: 'thermometer/measurements',
            transformResponse: function (data, headers) {
                return {
                    measurementData: angular.fromJson(data)
                };
            }
        },
        getTemperature: {
            method: 'GET',
            url: 'thermometer/temperatures',
            isArray: true
        }
    })
});

app.service('stopwatch', function ($timeout, $filter) {
    var stopwatch = function () {
        this.startTime = null;
        this.endTime = null;
        this.delay = 1000; // How often the UI is updated.

        // Return the elapsed time in ms of the stopwatch.
        this.elapsedTime = function () {
            if (timer)
                return new Date() - this.startTime;
            return this.endTime - this.startTime;
        };

        // Format the elapsed time into a string format.
        this.elapsedStr = function (format) {
            return $filter('date')(this.elapsedTime(), format);
        };

        var timer = null;

        // Bind this in order to keep the scope correct.
        var tick = (function () {
            this.endTime = new Date();
            timer = $timeout(tick, this.delay);
        }).bind(this);


        this.start = function () {
            // If the timer has already started
            // we don't need to start again.
            if (timer)
                return;

            // If we are starting for the first time,
            // set the time to the current time.
            if (this.startTime == null)
                this.startTime = new Date();

            // If we are paused, then we need to offset
            // the date by the previously elapsed time.
            else
                this.startTime = new Date() - this.elapsedTime();

            // Begin the timer.
            tick();
        };

        this.stop = function () {
            // Nothing to stop.
            if (!timer)
                return;
            this.endTime = new Date();
            $timeout.cancel(timer);
            timer = null;
        };

        this.reset = function () {
            this.stop();
            this.startTime = null;
            this.endTime = null
            this.timer = null;
        };
    };

    return {
        getInstance: function () {
            return new stopwatch();
        }
    };
});