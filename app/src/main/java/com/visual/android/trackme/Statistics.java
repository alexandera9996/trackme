package com.visual.android.trackme;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import static com.visual.android.trackme.Storage.getMiles;
import static com.visual.android.trackme.Storage.pairings;

/**
 * Created by andrewgelman on 11/12/17.
 */

public class Statistics extends AppCompatActivity{

    private ListView lv;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stats_layout);
        ListView displayer = findViewById(R.id.StatList);
        ArrayAdapter<Container> adapt = new ArrayAdapter<Container>(this,android.R.layout.simple_list_item_1,Storage.getArrayZero());
        displayer.setAdapter(adapt);

        TextView textView = (TextView) findViewById(R.id.textView2);
        DecimalFormat df = new DecimalFormat("#.##");
        textView.setText("Mileage: " + df.format(getMiles()));

        final Button homeButton = (Button) findViewById(R.id.home_button);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Intent intent = new Intent (Statistics.this, Intro.class);
                startActivity(intent);
                finish();
            }
        });


      /*  //experimenting with adding to stats page starts here
        lv = (ListView) findViewById(R.id.StatList);

        // Here we will add the info from storage class into the arraylist,
        List<Container> StatList = new ArrayList<>();
        for(Container item : pairings){
            if(item.getCount()>0){
                StatList.add(item);
            }
        }




        // This is the array adapter, it takes the context of the activity as a
        // first parameter, the type of list view as a second parameter and your
        // array as a third parameter.
        ArrayAdapter<Container> arrayAdapter = new ArrayAdapter<Container>(
                this,
                android.R.layout.simple_list_item_1,
                StatList );

        lv.setAdapter(arrayAdapter);

        //experimenting with adding to stats page ends here*/
    }
}
