package com.example.android.cometotoronto;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

import static com.example.android.cometotoronto.R.drawable.islands;

public class ThingsToDoInTorontoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.place_list);

        // creating arraylist for all places we have in things to do.
        final ArrayList<Place> places = new ArrayList<>();

        //add our places in the arraylist
        places.add(new Place(getString(R.string.high_title), getString(R.string.high), R.drawable.high));
        places.add(new Place(getString(R.string.islands_title), getString(R.string.islands), R.drawable.islands));
        places.add(new Place(getString(R.string.aqua_title), getString(R.string.aqua), R.drawable.aqua));
        places.add(new Place(getString(R.string.cn_title), getString(R.string.cn), R.drawable.cn));
        places.add(new Place(getString(R.string.rom_title), getString(R.string.rom), R.drawable.rom));

        //show the places
        PlaceAdapter adapter = new PlaceAdapter(this, places, R.color.things);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(adapter);
    }
}
