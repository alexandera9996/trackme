package com.visual.android.trackme;

import android.location.Criteria;
import android.location.LocationManager;
import android.os.AsyncTask;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by ErikL on 11/16/2017.
 */

public abstract class Locations extends AsyncTask<LocationManager,Void,android.location.Location>{
    @Override
    protected android.location.Location doInBackground(LocationManager... locationManagers) {
        System.out.println("DOING IN BACKGROUND");

        Criteria criteria = new Criteria();
        android.location.Location location = null;
        try {
            location = locationManagers[0].getLastKnownLocation(locationManagers[0]
                    .getBestProvider(criteria, false));
        } catch (SecurityException e) {
            e.printStackTrace();
        }

        //double latitude = location.getLatitude();
        //double longitude = location.getLongitude();

        if (!Storage.firstRecursiveExecution) {
            try {
                Thread.sleep(600000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            Storage.firstRecursiveExecution = false;
        }

        return location;
    }
}
