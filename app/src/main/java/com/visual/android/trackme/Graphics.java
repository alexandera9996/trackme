package com.visual.android.trackme;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;

/**
 * Created by ErikL on 11/15/2017.
 */

public class Graphics extends AppCompatActivity {
    private static Polyline dayTravels;
    public static void startDraw(GoogleMap map){
        map.clear();
        float widthVal = 5;
        ArrayList<Container> points = Storage.getArray();
        ArrayList<LatLng> locs = new ArrayList<LatLng>();
        for(int x =0; x < points.size(); x++){
            locs.add(points.get(x).getLoc());
            CircleOptions opt = new CircleOptions().center(points.get(x).getLoc()).radius(20).strokeColor(Color.BLACK).fillColor(points.get(x).getColor());
            final Circle circle = map.addCircle(opt);
        }
        dayTravels = map.addPolyline(new PolylineOptions().addAll(locs).color(Color.BLUE).width(widthVal).visible(true));
        dayTravels.setTag("Productivity");
    }
 //   public static void addSegment(){
    //    dayTravels.setPoints(Storage.getArray());
    //}

}
