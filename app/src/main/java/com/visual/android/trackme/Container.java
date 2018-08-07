package com.visual.android.trackme;

import android.graphics.Color;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by andrewgelman on 11/29/17.
 */

public class Container {
    private LatLng loc;
    private String name;
    private int count;
    private int color;

    public Container(String name, LatLng loc , int count, int color){
        this.loc=loc;
        this.name=name;
        this.count=count;
        this.color=color;
    }

    // ----- set functions -----

    public void setLoc (LatLng loc){
        this.loc = loc;
    }

    public void decColor (){
        color--;
    }

    public void setName (String name){
        this.name = name;
    }

    public void incCount () {
        count++;
    }

    // ----- get functions -----

    public LatLng getLoc(){
        return loc;
    }

    public int getColor(){
        return color;
    }

    public String getName(){
        return name;
    }

    public int getCount(){
        return count;
    }

    public String toString(){
        return ("Name: " + name + ", Coordinates: " + ((Double) loc.latitude).toString() + "," + ((Double) loc.longitude).toString());

    }

}
