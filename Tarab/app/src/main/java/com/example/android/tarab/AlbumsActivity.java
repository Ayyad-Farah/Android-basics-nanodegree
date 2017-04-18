package com.example.android.tarab;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class AlbumsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_albums);

        // Find Album TextView
        TextView album = (TextView) findViewById(R.id.album1);

        //add onclick listener to open it
        album.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent albumIntent = new Intent(AlbumsActivity.this, AlbumContentActivity.class);
                startActivity(albumIntent);
            }
        });

        // Find Album TextView
        album = (TextView) findViewById(R.id.album2);

        //add onclick listener to open it
        album.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent albumIntent = new Intent(AlbumsActivity.this, AlbumContentActivity.class);
                startActivity(albumIntent);
            }
        });

        // Find Album TextView
        album = (TextView) findViewById(R.id.album3);

        //add onclick listener to open it
        album.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent albumIntent = new Intent(AlbumsActivity.this, AlbumContentActivity.class);
                startActivity(albumIntent);
            }
        });
    }
}
