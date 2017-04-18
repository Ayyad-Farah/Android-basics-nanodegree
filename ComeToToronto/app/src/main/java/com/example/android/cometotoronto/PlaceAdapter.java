package com.example.android.cometotoronto;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by ayyad on 3/4/2017.
 */

public class PlaceAdapter extends ArrayAdapter<Place> {
    private int mBackground;

    public PlaceAdapter(Activity context, ArrayList<Place> places, int color) {
        super(context, 0, places);
        mBackground = color;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.place_item, parent, false);
        }

        final Place currentPlace = getItem(position);

        TextView titleTextView = (TextView) listItemView.findViewById(R.id.title);
        TextView descriptionTextView = (TextView) listItemView.findViewById(R.id.description);
        ImageView imageView = (ImageView) listItemView.findViewById(R.id.icon);
        LinearLayout linearLayout = (LinearLayout) listItemView.findViewById(R.id.item);

        titleTextView.setText(currentPlace.getTitle());
        descriptionTextView.setText(currentPlace.getmDescription());
        imageView.setImageResource(currentPlace.getPhoto());
        linearLayout.setBackgroundResource(mBackground);

        return listItemView;

    }
}
