package org.apache.cordova.tickk;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;

import org.json.JSONArray;
import org.json.JSONException;

import android.util.Log;


public class GeoService extends CordovaPlugin {

    /**
     * Constructor.
     */
    public GeoService() {
    }

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        Log.d("[Cordova GeoService]", "execute: " + action);



        return true;
    }

}
