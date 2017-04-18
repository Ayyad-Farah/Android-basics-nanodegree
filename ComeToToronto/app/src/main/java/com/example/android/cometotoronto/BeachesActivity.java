package com.example.android.cometotoronto;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class BeachesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.place_list);

        // creating arraylist for all places we have in Beaches section.
        final ArrayList<Place> places = new ArrayList<>();

        //add our places in the arraylist
        places.add(new Place(getString(R.string.islands_title), getString(R.string.islands), R.drawable.islands));
        places.add(new Place(getString(R.string.cherry_title), getString(R.string.cherry), R.drawable.cherry));
        places.add(new Place(getString(R.string.sunny_title), getString(R.string.sunny), R.drawable.sunny));
        places.add(new Place(getString(R.string.marie_title), getString(R.string.marie), R.drawable.marie));
        places.add(new Place(getString(R.string.sugar_title), getString(R.string.sugar), R.drawable.sugar));

        //show the places
        PlaceAdapter adapter = new PlaceAdapter(this, places, R.color.beaches);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(adapter);
    }
}