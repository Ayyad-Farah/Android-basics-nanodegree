package com.example.android.tarab;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SongsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_songs);


        // Find song TextView
        TextView song = (TextView) findViewById(R.id.song1);

        //add onclick listener to play it
        song.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent songIntent = new Intent(SongsActivity.this, NowPlayActivity.class);
                startActivity(songIntent);
            }
        });

        // Find song TextView
        song = (TextView) findViewById(R.id.song2);

        //add onclick listener to play it
        song.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent songIntent = new Intent(SongsActivity.this, NowPlayActivity.class);
                startActivity(songIntent);
            }
        });

        // Find song TextView
        song = (TextView) findViewById(R.id.song3);

        //add onclick listener to play it
        song.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent songIntent = new Intent(SongsActivity.this, NowPlayActivity.class);
                startActivity(songIntent);
            }
        });
    }
}
