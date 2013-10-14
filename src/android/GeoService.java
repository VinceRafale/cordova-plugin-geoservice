package org.apache.cordova.tickk;

import android.location.Location;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;
import android.content.Context;


public class GeoService extends CordovaPlugin {

    private GeoListener geoListener;
    private CallbackContext mcallbackContext;

    private boolean watching = false;

    /**
     * Constructor.
     */
    public GeoService() {
    }

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        Log.d("[Cordova GeoService]", "execute: " + action);

        if (this.geoListener == null) {
            this.geoListener = new GeoListener(this);
            this.mcallbackContext = callbackContext;
        }

        if (this.servicesConnected()) {

            if (action.equals("get")) {
                this.geoListener.get();
            }
            if (action.equals("watch")) {
                this.watching = true;
                this.geoListener.watch();
            }
            if (action.equals("stop")) {
                this.geoListener.stop();
            }

        }


        return true;
    }

    public void win(Location location) {
        Log.d("[Cordova GeoService]", "win");
        JSONObject loc = new JSONObject();

        try {
            loc.put("latitude", location.getLatitude());
            loc.put("longitude", location.getLongitude());
            loc.put("altitude", (location.hasAltitude() ? location.getAltitude() : null));
            loc.put("accuracy", location.getAccuracy());
            loc.put("heading", (location.hasBearing() ? (location.hasSpeed() ? location.getBearing() : null) : null));
            loc.put("velocity", location.getSpeed());
            loc.put("timestamp", location.getTime());
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            Log.d("[Cordova GeoService]", "win - exception");
            e.printStackTrace();
        }

        
        PluginResult result = new PluginResult(PluginResult.Status.OK, loc);
        result.setKeepCallback(true);
        mcallbackContext.sendPluginResult(result);
    }

    private boolean servicesConnected() {
        Context context = this.cordova.getActivity().getApplicationContext();
        // Check that Google Play services is available
        int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(context);

        // If Google Play services is available
        if (ConnectionResult.SUCCESS == resultCode) {
            // In debug mode, log the status
            Log.d("[Cordova GeoService]", "Google Play services is available.");
            // Continue
            return true;
        // Google Play services was not available for some reason
        } else {
            Log.d("[Cordova GeoService]", "Google Play services unavailable.");
            return false;
        }
    }

    @Override
    public void onPause(boolean multitasking) {
        Log.d("[Cordova GeoBroker]", "onPause");
        if (this.watching) {
            this.geoListener.stop();
        }
    }

    @Override
    public void onResume(boolean multitasking) {
        Log.d("[Cordova GeoBroker]", "onResume");
        if (this.watching) {
            this.geoListener.watch();
        }
    }

}
