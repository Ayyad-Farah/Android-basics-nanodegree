package com.example.android.cometotoronto;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class RestaurantsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.place_list);

        // creating arraylist for all places we have in restaurants section.

        final ArrayList<Place> places = new ArrayList<>();

        //add our places in the arraylist
        places.add(new Place(getString(R.string.alo_title), getString(R.string.alo), R.drawable.alo));
        places.add(new Place(getString(R.string.Scaramouche_title), getString(R.string.scaramouche), R.drawable.scaramouche));
        places.add(new Place(getString(R.string.richmond_title), getString(R.string.richmond), R.drawable.richmond));
        places.add(new Place(getString(R.string.pai_title), getString(R.string.pai), R.drawable.pai));
        places.add(new Place(getString(R.string.jacob_title), getString(R.string.jacob), R.drawable.jacobs));

        //show the places
        PlaceAdapter adapter = new PlaceAdapter(this, places, R.color.restaurants);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(adapter);
    }
}
