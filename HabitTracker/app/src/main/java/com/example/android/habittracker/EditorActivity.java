package com.example.android.habittracker;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.android.habittracker.data.HabitContract.HabitEntry;
import com.example.android.habittracker.data.HabitDbHelper;

public class EditorActivity extends AppCompatActivity {

    private HabitDbHelper mDbHelper;

    /**
     * EditText field to enter the Habit's name
     */
    private EditText mNameEditText;

    /**
     * EditText field to enter the Habit's weakly repeated
     */
    private EditText mWeaklyRepeatedEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        mDbHelper = new HabitDbHelper(this);
        mNameEditText = (EditText) findViewById(R.id.name);
        mWeaklyRepeatedEditText = (EditText) findViewById(R.id.time_repeated);

        Button addData = (Button) findViewById(R.id.add_data);
        addData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = mNameEditText.getText().toString().trim();
                int weaklyRepeated = Integer.parseInt(mWeaklyRepeatedEditText.getText().toString().trim());
                insetHabit(name, weaklyRepeated);
                finish();
            }
        });

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
