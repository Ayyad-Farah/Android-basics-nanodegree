package com.example.android.cometotoronto;

/**
 * Created by ayyad on 3/4/2017.
 * this class represent the structure of a place.
 */

public class Place {
    private String mTitle;
    private String mDescription;
    private int mPhoto;

    public Place(String title, String description, int photo) {
        mTitle = title;
        mDescription = description;
        mPhoto = photo;
    }

    //getters methods
    public String getTitle() {
        return mTitle;
    }

    public String getmDescription() {
        return mDescription;
    }

    public int getPhoto() {
        return mPhoto;
    }
}
