package com.visual.android.trackme;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Intro extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout);
        final Button statButton = (Button) findViewById(R.id.stats);
        final Button settingsButton = (Button) findViewById(R.id.settings);
        final Button mapButton = (Button) findViewById(R.id.maps);

        mapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
               Intent intent = new Intent (Intro.this, MapsActivity.class);
               startActivity(intent);
            }
        });

        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Intent intent = new Intent (Intro.this, Settings.class);
                startActivity(intent);
            }
        });

        statButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Intent intent = new Intent (Intro.this, Statistics.class);
                startActivity(intent);

            }
        });

    }
}
