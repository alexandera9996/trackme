package com.visual.android.trackme;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.LocationManager;
import android.net.wifi.WifiManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    public static final int PERMISSION_REQ_LOC = 99;

    private GoogleMap mMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        Storage.map=mMap;

        final Button back =(Button) findViewById(R.id.mapButton);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent =  new Intent(MapsActivity.this, Intro.class);
               startActivity(intent);
            //   finish();
            }
        });
    }
    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */

    //Help obtained from my other project LocSilence on setting up Permissions
    @Override
    public void onMapReady(GoogleMap googleMap) {
        Storage.map = googleMap;
        if(checkPermissions()){
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission. ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {
                if (Storage.map != null) {
                    Storage.map.setMyLocationEnabled(true);
                }
            }
        }

        LocationManager locationManager = (LocationManager)
                getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        android.location.Location loc = locationManager.getLastKnownLocation(locationManager
               .getBestProvider(criteria, false));
        if(loc != null){
            LatLng actualLoc = new LatLng(loc.getLatitude(),loc.getLongitude());
            Storage.addToArray(actualLoc);
        }
        else{
            Storage.path();
        }
       /* Storage.addToArray(santaCruz);
        Storage.addToArray(new LatLng(40,-120));
        if(l!=null) {
            Log.e("Real", " found location" );
            Storage.addToArray(new LatLng(l.getLatitude(), l.getLongitude()));

        }*/
        // Add a marker in Santa Cruz and move the camera
        LatLng santaCruz = new LatLng(36.997213, -122.056893);
        Storage.map.addMarker(new MarkerOptions().position(santaCruz).title("Marker in Santa Cruz"));
        Storage.map.moveCamera(CameraUpdateFactory.newLatLngZoom(santaCruz, 14.5f));
        Graphics.startDraw(Storage.map);
        Storage.recursiveBackgroundCheck = new RecursiveBackgroundCheck(locationManager,this);
        if(loc != null) {
            Storage.recursiveBackgroundCheck.execute(locationManager);
        }
    }


    //Deals with making sure all permissions have been dealt with
    public boolean checkPermissions() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
                new AlertDialog.Builder(this)
                        .setTitle("TITLE")
                        .setMessage("TEXT")
                        .setPositiveButton("OKAY", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //Prompt the user once explanation has been shown
                                ActivityCompat.requestPermissions(MapsActivity.this,
                                        new String[]{
                                                Manifest.permission.ACCESS_FINE_LOCATION},
                                        PERMISSION_REQ_LOC);
                            }
                        })
                        .create()
                        .show();


            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this,
                        new String[]{
                                Manifest.permission.ACCESS_FINE_LOCATION},
                        PERMISSION_REQ_LOC);
            }
            return false;
        } else {
            return true;
        }
    }

}
