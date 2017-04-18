package com.example.android.newsapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {


    Button mSearchButton;
    EditText mQuery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSearchButton = (Button) findViewById(R.id.search_button);
        mQuery = (EditText) findViewById(R.id.query);

        mSearchButton.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                String text = mQuery.getText().toString();
                Intent myIntent = new Intent(MainActivity.this, StoriesListActivity.class);
                myIntent.putExtra("myQuery", text);
                startActivity(myIntent);
            }
        });
    }
}
