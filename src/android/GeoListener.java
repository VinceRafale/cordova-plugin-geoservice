package org.apache.cordova.tickk;

import android.location.Location;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.location.LocationClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;

import android.util.Log;
import android.content.Context;
import android.os.Bundle;

public class GeoListener implements 
    LocationListener,
    GooglePlayServicesClient.ConnectionCallbacks,
    GooglePlayServicesClient.OnConnectionFailedListener {

    // reference to parent
    private GeoService owner;

    // A request to connect to Location Services
    private LocationRequest mLocationRequest;

    // Stores the current instantiation of the location client in this object
    private LocationClient mLocationClient;

    // Global variable to hold the current location
    private Location mCurrentLocation;

    public GeoListener(GeoService service) {
        this.owner = service;
    }

    /**
     * Report location
     *
     * @param location The updated location.
     */
    @Override
    public void onLocationChanged(Location location) {
        Log.d("[Cordova GeoService]", "GeoListener - location has been updated");

        Log.d("[Cordova GeoService]", "GeoListener - " +
                Double.toString(location.getAccuracy()) + " at " +
                Double.toString(location.getLatitude()) + "," +
                Double.toString(location.getLongitude()));

        this.owner.win(location);
    }

    /*
     * Called by Location Services when the request to connect the
     * client finishes successfully. At this point, you can
     * request the current location or start periodic updates
     */
    @Override
    public void onConnected(Bundle bundle) {
        Log.d("[Cordova GeoService]", "GeoListener - connected");
        mLocationClient.requestLocationUpdates(mLocationRequest, this);
    }

    /*
     * Called by Location Services if the connection to the
     * location client drops because of an error.
     */
    @Override
    public void onDisconnected() {
        Log.d("[Cordova GeoService]", "GeoListener - disconnected");
    }

    /*
     * Called by Location Services if the attempt to
     * Location Services fails.
     */
    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Log.d("[Cordova GeoService]", "GeoListener - connection failed");
    }
    
    public void get() {

        // Create the LocationRequest object
        mLocationRequest = LocationRequest.create();
        // Use high accuracy
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        // Set the update interval to 5 seconds
        mLocationRequest.setInterval(5000);
        // Set the fastest update interval to 1 second
        mLocationRequest.setFastestInterval(1000);


        Context context = this.owner.cordova.getActivity().getApplicationContext();
        mLocationClient = new LocationClient(context, this, this);

        mLocationClient.connect();

    }
}