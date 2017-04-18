package com.example.android.newsapp;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

/**
 * background loader for the stories
 *
 * Created by ayyad on 4/9/2017.
 */

public class StoriesLoader extends AsyncTaskLoader<List<Story>> {


    /** Tag for log messages */
    private static final String LOG_TAG = StoriesLoader.class.getName();

    /** Query URL */
    private String mUrl;

    /**
     * Constructs a new {@link StoriesLoader}.
     *
     * @param context of the activity
     * @param url to load data from
     */
    public StoriesLoader (Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    /**
     * This is on a background thread.
     */
    @Override
    public List<Story> loadInBackground() {
        if (mUrl == null) {
            return null;
        }

        // Perform the network request, parse the response, and extract a list of stories.
        List<Story> stories = QueryUtils.fetchStoriesData(mUrl);
        return stories;
    }
}
