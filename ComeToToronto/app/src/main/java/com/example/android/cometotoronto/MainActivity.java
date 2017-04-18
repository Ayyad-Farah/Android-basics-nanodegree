package com.example.android.cometotoronto;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find the View that shows resturants
        TextView topic = (TextView) findViewById(R.id.restaurants);

        // Set a click listener on that View
        topic.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RestaurantsActivity.class);
                startActivity(intent);
            }
        });


        // Find the View that shows beaches
        topic = (TextView) findViewById(R.id.beaches);

        // Set a click listener on that View
        topic.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, BeachesActivity.class);
                startActivity(intent);
            }
        });


        // Find the View that shows hotels
        topic = (TextView) findViewById(R.id.hotels);

        // Set a click listener on that View
        topic.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, HotelsActivity.class);
                startActivity(intent);
            }
        });

        // Find the View that shows things to do in toronto
        topic = (TextView) findViewById(R.id.things);

        // Set a click listener on that View
        topic.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ThingsToDoInTorontoActivity.class);
                startActivity(intent);
            }
        });
    }
}
