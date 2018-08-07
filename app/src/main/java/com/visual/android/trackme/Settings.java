package com.visual.android.trackme;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;

/**
 * Created by andrewgelman on 11/12/17.
 */

public class Settings extends AppCompatActivity {

    private static boolean daily;
    private static boolean weekly;
    private static boolean monthly;
    private static boolean drawOn;
    private static boolean drawOff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_layout);
        final Button homeButton = (Button) findViewById(R.id.home);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Intent intent = new Intent (Settings.this, Intro.class);
                startActivity(intent);
                finish();
            }
        });
        // daily
        final CheckBox check = (CheckBox) findViewById(R.id.checkBox);
        // weekly
        final CheckBox check2 = (CheckBox) findViewById(R.id.checkBox2);
        // monthly
        final CheckBox check3 = (CheckBox) findViewById(R.id.checkBox3);

        check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener () {
            @Override
            public void onCheckedChanged (CompoundButton view, boolean isChecked){
                check2.setChecked(false);
                check3.setChecked(false);
                daily = true;
                weekly = false;
                monthly = false;
            }
        });
        check2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener () {
            @Override
            public void onCheckedChanged (CompoundButton view, boolean isChecked){
                check.setChecked(false);
                check3.setChecked(false);
                weekly = true;
                daily = false;
                monthly = false;
            }
        });
        check3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener () {
            @Override
            public void onCheckedChanged (CompoundButton view, boolean isChecked){
                check.setChecked(false);
                check2.setChecked(false);
                monthly = true;
                daily = false;
                weekly = false;
            }
        });

        // on/off for drawing mode
        final CheckBox on = (CheckBox) findViewById(R.id.checkBox4);
        final CheckBox off = (CheckBox) findViewById(R.id.checkBox5);

        on.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener () {
            @Override
            public void onCheckedChanged (CompoundButton view, boolean isChecked){
                off.setChecked(false);
                drawOn = true;
                drawOff = false;
            }
        });
        off.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener () {
            @Override
            public void onCheckedChanged (CompoundButton view, boolean isChecked){
                on.setChecked(false);
                drawOff = true;
                drawOn = false;
            }
        });
    }

    // get boolean functions
    public static boolean isDaily(){
        if(daily){ return true; }
        else{ return false; }
    }

    public static boolean isWeekly(){
        if(weekly){ return true; }
        else{ return false; }
    }

    public static boolean isMonthly(){
        if (monthly) { return true; }
        else{ return false; }
    }

    public static boolean isDrawOn(){
        if(drawOn){ return true; }
        else{ return false; }
    }

    public static boolean isDrawOff(){
        if(drawOff){ return true; }
        else{ return false; }
    }
}
