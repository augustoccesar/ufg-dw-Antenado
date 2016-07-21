$(document).ready(function(){
    var self = this;

    // Loading methods on root
    self.placeMarker = placeMarker;
    self.loadPoints = loadPoints;
    self.showMarkerDetails = showMarkerDetails;
    self.createMarker = createMarker;

    // Loading objects
    self.mapArea = $("#map-area");
    self.map = undefined;

    // Creating constants
    self.userCoords = {
        lat: undefined,
        long: undefined
    };
    self.markersCount = 0;
    self.markers = [];

    // Creating shared objects
    self.newMarker = undefined;

    // Methods

    // Load points from web service
    function loadPoints(callback) {
        return $.get("/markers", function(response){
            callback(response);
        });
    }

    // Place a marker on the map
    function placeMarker(marker, map, center) {
        self.markers[self.markersCount] = new google.maps.Marker({
            position: {
                lat: marker.latitude,
                lng: marker.longitude
            },
            map: map
        });
        self.markers[self.markersCount].addListener('click', function () {
            self.showMarkerDetails(marker);
        });
        map.panTo(new google.maps.LatLng(center.lat, center.long));
    }

    // Show the details of a marker
    function showMarkerDetails(marker){
        console.log(marker);
        $('#marker-detail-modal').modal('show');
        $('#marker-detail-modal > div.content > h3.title').html(marker.title);
        $('#marker-detail-modal > div.content > h4.message').html(marker.message);
        $('#marker-detail-modal > div.content > h6.time-ago').html(marker.time_ago);
    }

    // Send a new marker to the server
    function createMarker(){
        var latitude = self.newMarker.getPosition().lat();
        var longitude = self.newMarker.getPosition().lng();

        var values = {};
        $.each($('#new-marker-form').serializeArray(), function(i, field) {
            values[field.name] = field.value;
        });

        var sendData = {
            title: values['title'],
            message: values['description'],
            priority: values.type,
            latitude: latitude,
            longitude: longitude
        };


        $.ajax({
            url: "/markers",
            type: "post",
            data: JSON.stringify(sendData),
            contentType: 'application/json',
            success: function (response) {
                console.log(response);
                $('#new-marker-modal').modal('hide');
                self.newMarker.setMap(null);
                placeMarker(response, self.map, self.userCoords)
            }
        });
    }

    // Initialize the map
    function init_map(center) {
        var myOptions = {
            zoom: 14,
            center: new google.maps.LatLng(center.lat, center.long),
            mapTypeId: google.maps.MapTypeId.ROADMAP
        };

        map = new google.maps.Map(
            document.getElementById('gmap_canvas'),
            myOptions
        );

        self.map = map;

        self.loadPoints(function(points){
            points.forEach(function(point){
                self.placeMarker(point, map, self.userCoords);
                self.markersCount++;
            });
        });

        google.maps.event.addListener(map, 'click', function(event) {
            $('#new-marker-modal').modal({
                onHide: function(){
                    self.newMarker.setMap(null);
                }
            }).modal('show');

            self.newMarker = new google.maps.Marker({
                position: event.latLng,
                map: map
            });
        });
    }

    // Init app

    navigator.geolocation.getCurrentPosition(function(res){
        self.userCoords.lat = res.coords.latitude;
        self.userCoords.long = res.coords.longitude;

        google.maps.event.addDomListener(window, 'load', init_map(self.userCoords));
    });

    // Start dynamic content

    $('.selection.dropdown').dropdown();
    $('#submit-marker-button').on('click', function(){
        self.createMarker();
    });

});