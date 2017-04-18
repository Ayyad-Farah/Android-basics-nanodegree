package com.example.android.newsapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Recycle View adapter for stories.
 *
 * Created by ayyad on 4/9/2017.
 */

public class StoriesAdapter extends ArrayAdapter<Story> {


    /**
     * Constructs a new {@link Story}.
     *
     * @param context of the app
     * @param stories   is the list of stories, which is the data source of the adapter
     */
    public StoriesAdapter(Context context, List<Story> stories) {
        super(context, 0, stories);
    }

    /**
     * Returns a list item view that displays information about the Story at the given position
     * in the list of stories.
     */

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Check if there is an existing list item view (called convertView) that we can reuse,
        // otherwise, if convertView is null, then inflate a new list item layout.
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.story_item, parent, false);
        }

        // Find the Story at the given position in the list of stories
        Story currentStory = getItem(position);

        // Find the TextView with view ID title and set the new title
        TextView titleView = (TextView) listItemView.findViewById(R.id.title);
        titleView.setText(currentStory.getTitle());


        // Find the TextView with view ID publication date and set the new date
        TextView publicationDate = (TextView) listItemView.findViewById(R.id.date);
        publicationDate.setText(currentStory.getWebPublicationDate());
        // Return the list item view that is now showing the appropriate data
        return listItemView;
    }
}