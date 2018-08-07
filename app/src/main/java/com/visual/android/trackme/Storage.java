package com.visual.android.trackme;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.Random;

public class Storage extends AppCompatActivity {
    public static RecursiveBackgroundCheck   recursiveBackgroundCheck;
    public static GoogleMap map;
    public static boolean firstRecursiveExecution = true;
    private static double miles =0;
    private static int timemoving=0;
    public static ArrayList<Container> pairings = new ArrayList<Container>();

    private static void setMiles(double m){
        miles=m;
    }

    public static void setTime(int time){
        timemoving=time;
    }

    public static void addToArrayZero(LatLng point){
        pairings.add(new Container("Child ",point ,0, 0xFF000000));
    }

    public static void addToArray(LatLng point){
        pairings.add(new Container("Child ",point ,1, 0xFFFFFFFF));
    }

    public static void setArray(ArrayList<Container> con){
        pairings=con;
    }

    public static double getMiles(){
        double m = miles;
        return m;
    }

    public static Container getLast(){
        return pairings.get(pairings.size()-1);
    }

    public static void updateLast(Container tmp){
        pairings.set(pairings.size()-1,tmp);
    }

    public static int getTime(){
        return timemoving;
    }

    public static ArrayList<Container> getArray(){
        return pairings;
    }

    public static ArrayList<Container> getArrayZero(){
        ArrayList<Container> tmp = new ArrayList<Container>();
        for(Container item : pairings){
            if(item.getCount()>0){
                tmp.add(item);
            }
        }
        return tmp;
    }

    //for the drawing of the line*
    public static double distance (double lat_a, double lng_a, double lat_b, double lng_b )
    {
        double earthRadius = 3958.75;
        double latDiff = Math.toRadians(lat_b-lat_a);
        double lngDiff = Math.toRadians(lng_b-lng_a);
        double a = Math.sin(latDiff /2) * Math.sin(latDiff /2) +
                Math.cos(Math.toRadians(lat_a)) * Math.cos(Math.toRadians(lat_b)) *
                        Math.sin(lngDiff /2) * Math.sin(lngDiff /2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));

        int meterConversion = 1609;
        double newDist =  new Double(c * meterConversion).doubleValue();
        setMiles(miles + newDist);
        return newDist;
    }

    public static void path(){

        LatLng loc1 = new LatLng(36.997213, -122.056893);
        LatLng loc2 = new LatLng(36.997573,-122.055691);
        LatLng loc3 = new LatLng( 36.995654, -122.058932);
        LatLng loc4 = new LatLng( 36.991032, -122.052784);
        LatLng loc5 = new LatLng( 36.996751,  -122.053036);
        LatLng loc6 = new LatLng( 36.999007, -122.060635);
        LatLng loc7 = new LatLng( 37.000359, -122.063169);
        LatLng loc8 = new LatLng(36.997240, -122.066817);
        LatLng loc9 = new LatLng( 36.994395, -122.065229);
        LatLng loc10 = new LatLng( 36.991193, -122.064725);
        LatLng loc11 = new LatLng( 36.989618, -122.062799);
        LatLng loc12 = new LatLng(36.989228, -122.063304);
        LatLng loc13 = new LatLng(36.993846, -122.054582);

        distance(loc1.latitude,loc1.longitude,loc2.latitude,loc2.longitude);
        distance(loc2.latitude,loc2.longitude,loc3.latitude,loc3.longitude);
        distance(loc3.latitude,loc3.longitude,loc4.latitude,loc4.longitude);
        distance(loc4.latitude,loc4.longitude,loc5.latitude,loc5.longitude);
        distance(loc5.latitude,loc5.longitude,loc6.latitude,loc6.longitude);
        distance(loc6.latitude,loc6.longitude,loc7.latitude,loc7.longitude);
        distance(loc7.latitude,loc7.longitude,loc8.latitude,loc8.longitude);
        distance(loc8.latitude,loc8.longitude,loc9.latitude,loc9.longitude);
        distance(loc9.latitude,loc9.longitude,loc10.latitude,loc10.longitude);
        distance(loc10.latitude,loc10.longitude,loc11.latitude,loc11.longitude);
        distance(loc11.latitude,loc11.longitude,loc12.latitude,loc12.longitude);
        distance(loc12.latitude,loc12.longitude,loc13.latitude,loc13.longitude);

        ordinance(loc1,"Classroom Unit 001");
        ordinance(loc2, "McHenry Library");
        ordinance(loc3, "East Remote");
        ordinance(loc4, "Cowell Dining Hall");
        ordinance(loc5, "Bookstore");
        ordinance(loc6, "S&E Library");
        ordinance(loc7, "Jack Baskin");
        ordinance(loc8, "Kresge");
        ordinance(loc9, "Porter");
        ordinance(loc10, "Rachel Carson");
        ordinance(loc11, "Oakes Academic Building");
        ordinance(loc12, "Oakes Cafe");
        ordinance(loc13, "OPERS");
    }

    public static void ordinance(LatLng loc, String name){
        Storage.addToArray(loc);
        colorSet();
        Container tmp = getLast();
        tmp.setName(name);
        updateLast(tmp);
    }

    private static void colorSet(){
        Container tmp =Storage.getLast();
        Random rnd = new Random();
        int choose =rnd.nextInt(30000);
        for(int x=0; x<choose; x++){
            tmp.decColor();
        }
        if(choose > 27000) {
            tmp.setName("Favorite Place " + rnd.nextInt());
        }
        Storage.updateLast(tmp);
    }
}

