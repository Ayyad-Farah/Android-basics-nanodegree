package com.example.android.cometotoronto;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class HotelsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.place_list);

        // creating arraylist for all places we have in Hotels section.
        final ArrayList<Place> places = new ArrayList<>();

        //add our places in the arraylist
        places.add(new Place(getString(R.string.grand_title), getString(R.string.grand), R.drawable.grand));
        places.add(new Place(getString(R.string.tree_title), getString(R.string.tree), R.drawable.tree));
        places.add(new Place(getString(R.string.rena_title), getString(R.string.rena), R.drawable.rena));
        places.add(new Place(getString(R.string.marriott_title), getString(R.string.marriott), R.drawable.marriott));
        places.add(new Place(getString(R.string.shang_title), getString(R.string.shang), R.drawable.shang));

        //show the places
        PlaceAdapter adapter = new PlaceAdapter(this, places, R.color.hotels);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(adapter);
    }
}
