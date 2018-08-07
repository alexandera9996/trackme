package com.visual.android.trackme;

import android.content.Context;
import android.location.LocationManager;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

/**
 * Created by ErikL on 11/30/2017.
 */

public class RecursiveBackgroundCheck extends Locations {
    private LocationManager locationManager;
    private Context context;

    public RecursiveBackgroundCheck(LocationManager locationManager, Context context) {
        this.locationManager=locationManager;
        this.context=context;
    }

    @Override
    protected void onPostExecute(android.location.Location currentloc){
        super.onPostExecute(currentloc);
        double currentLat = currentloc.getLatitude();
        double currentLon = currentloc.getLongitude();
        double currentAccuracy = currentloc.getAccuracy();
        ArrayList<Container> all = Storage.getArray();
        ArrayList<LatLng> locs = new ArrayList<LatLng>();
        for(Container one : all){
            locs.add(one.getLoc());
        }
        boolean newentry = true;
        for(int x=0; x<all.size();x++) {
            float[] results = new float[5];
            android.location.Location.distanceBetween(locs.get(x).latitude, locs.get(x).longitude, currentLat, currentLon, results);
            if(results[0]<currentAccuracy && newentry == true){
                Log.i("Name", all.get(x).getName() + " is being incremented");
                all.get(x).incCount();
                all.get(x).decColor();
                newentry = false;
                if(x != all.size()-1) {
                    Storage.addToArrayZero(new LatLng(currentLat,currentLon));
                    Graphics.startDraw(Storage.map);
                }
            }
        }
        if (newentry == true){
            Storage.addToArray(new LatLng(currentLat,currentLon));
            Graphics.startDraw(Storage.map);
        }
        Storage.setArray(all);
        Storage.recursiveBackgroundCheck = new RecursiveBackgroundCheck(locationManager,context);
        Storage.recursiveBackgroundCheck.execute(locationManager);
    }
}
