package com.example.android.habittracker.data;

import android.provider.BaseColumns;

/**
 * contract class for SQLITE database.
 * Created by ayyad on 4/12/2017.
 */

public class HabitContract {

    public static class HabitEntry implements BaseColumns {
        public static final String TABLE_NAME = "habits";
        public static final String _ID = "_ID";
        public static final String COLUMN_HABIT_NAME = "name";
        public static final String COLUMN_HABIT_WEAKLY_REPEAT = "repeats";
    }
}
