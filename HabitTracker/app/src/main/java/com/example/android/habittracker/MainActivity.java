
package com.example.android.habittracker;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.android.habittracker.data.HabitContract.HabitEntry;
import com.example.android.habittracker.data.HabitDbHelper;


/**
 * * Displays list of Habits that were entered and stored in the app.
 */

public class MainActivity extends AppCompatActivity {

    private HabitDbHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDbHelper = new HabitDbHelper(this);

        // Setup FAB to open EditorActivity
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, EditorActivity.class);
                startActivity(intent);
            }
        });

        Button insertDummyDataButton = (Button) findViewById(R.id.dummy_data);
        insertDummyDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insetHabit("coding", 7);
                displayDatabaseInfo();
            }
        });

        displayDatabaseInfo();
    }

    @Override
    protected void onStart() {
        super.onStart();
        displayDatabaseInfo();
    }

    /**
     * Temporary helper method to display information in the onscreen TextView about the state of
     * the Habits database.
     */
    private void displayDatabaseInfo() {
        // Create and/or open a database to read from it
        Cursor cursor = getCursor();
        int idIndex = cursor.getColumnIndex(HabitEntry._ID);
        int nameIndex = cursor.getColumnIndex(HabitEntry.COLUMN_HABIT_NAME);
        int weaklyRepeatedIndex = cursor.getColumnIndex(HabitEntry.COLUMN_HABIT_WEAKLY_REPEAT);

        TextView displayView = (TextView) findViewById(R.id.text);
        String text = "";
        try {

            while (cursor.moveToNext()) {
                int id = cursor.getInt(idIndex);
                String name = cursor.getString(nameIndex);
                int weaklyRepeated = cursor.getInt(weaklyRepeatedIndex);

                text += id + " - " + name + " - " + weaklyRepeated + "\n";
            }
        } finally {
            // Always close the cursor when you're done reading from it. This releases all its
            // resources and makes it invalid.
            displayView.setText(text);
            cursor.close();
        }
    }

    private Cursor getCursor() {
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String[] project = {
                HabitEntry._ID,
                HabitEntry.COLUMN_HABIT_NAME,
                HabitEntry.COLUMN_HABIT_WEAKLY_REPEAT
        };

        Cursor cursor = db.query(
                HabitEntry.TABLE_NAME,
                project,
                null,
                null,
                null,
                null,
                null
        );
        return  cursor;
    }

    private void insetHabit(String name, int weaklyRepeated) {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(HabitEntry.COLUMN_HABIT_NAME, name);
        values.put(HabitEntry.COLUMN_HABIT_WEAKLY_REPEAT, weaklyRepeated);

        long newRowId = db.insert(HabitEntry.TABLE_NAME, null, values);
        Log.v("MainActivity", "New row ID " + newRowId);
    }
}
