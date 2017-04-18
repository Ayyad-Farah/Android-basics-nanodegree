package com.example.android.inventoryapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.example.android.inventoryapp.data.ProductContract;

import static android.R.id.edit;
import static com.example.android.inventoryapp.R.id.price;
import static com.example.android.inventoryapp.R.id.quantity;

/**
 * {@link ProductCursorAdapter} is an adapter for a list or grid view
 * that uses a {@link Cursor} of product data as its data source. This adapter knows
 * how to create list items for each row of product data in the {@link Cursor}.
 * <p>
 * Created by ayyad on 4/14/2017.
 */

public class ProductCursorAdapter extends CursorAdapter {


    /**
     * Constructs a new {@link ProductCursorAdapter}.
     *
     * @param context The context
     * @param c       The cursor from which to get the data.
     */
    public ProductCursorAdapter(Context context, Cursor c) {
        super(context, c, 0 /* flags */);
    }


    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        // Inflate a list item view using the layout specified in list_item.xml
        return LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
    }

    @Override
    public void bindView(final View view, final Context context, final Cursor cursor) {

        // Find individual views that we want to modify in the list item layout
        TextView nameTextView = (TextView) view.findViewById(R.id.name);
        TextView quantityTextView = (TextView) view.findViewById(quantity);
        TextView priceTextView = (TextView) view.findViewById(price);
        Button addQuantity = (Button) view.findViewById(R.id.add_quantity_button);
        Button removeQuantity = (Button) view.findViewById(R.id.remove_quantity_button);


        int nameColumnIndex = cursor.getColumnIndex(ProductContract.ProductEntry.COLUMN_PRODUCT_NAME);
        int quantityColumnIndex = cursor.getColumnIndex(ProductContract.ProductEntry.COLUMN_PRODUCT_QUANTITY);
        int priceColumnIndex = cursor.getColumnIndex(ProductContract.ProductEntry.COLUMN_PRODUCT_PRICE);
        int _IDColumnIndex = cursor.getColumnIndex(ProductContract.ProductEntry._ID);

        // Read the producr attributes from the Cursor for the current product
        final String productName = cursor.getString(nameColumnIndex);
        final String productQuantity = cursor.getString(quantityColumnIndex);
        final String productPrice = cursor.getString(priceColumnIndex);
        final String _ID = cursor.getString(_IDColumnIndex);

        nameTextView.setText(productName);
        quantityTextView.setText(productQuantity);
        priceTextView.setText(productPrice);

        addQuantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //
                int productAvalibleQuantity = Integer.parseInt(productQuantity);
                int priceValue = Integer.parseInt(productPrice);

                ContentValues values = new ContentValues();
                values.put(ProductContract.ProductEntry.COLUMN_PRODUCT_NAME, productName);
                values.put(ProductContract.ProductEntry.COLUMN_PRODUCT_QUANTITY, productAvalibleQuantity + 1);
                values.put(ProductContract.ProductEntry.COLUMN_PRODUCT_PRICE, priceValue);
                Uri uri = Uri.withAppendedPath(ProductContract.ProductEntry.CONTENT_URI, _ID);

                int rowsAffected = context.getContentResolver().update(uri, values, null, null);

                if (rowsAffected == 0) {
                    // If no rows were affected, then there was an error with the update.
                    Toast.makeText(context, "failed to add", Toast.LENGTH_SHORT).show();
                } else {
                    // Otherwise, the update was successful and we can display a toast.
                    Toast.makeText(context, "Order one item from supplier", Toast.LENGTH_SHORT).show();
                }
            }
        });

        removeQuantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //
                int productAvalibleQuantity = Integer.parseInt(productQuantity);
                if (productAvalibleQuantity < 1) {
                    Toast.makeText(context, "you have no quantity to sale", Toast.LENGTH_SHORT).show();
                    return;
                }
                int priceValue = Integer.parseInt(productPrice);

                ContentValues values = new ContentValues();
                values.put(ProductContract.ProductEntry.COLUMN_PRODUCT_NAME, productName);
                values.put(ProductContract.ProductEntry.COLUMN_PRODUCT_QUANTITY, productAvalibleQuantity - 1);
                values.put(ProductContract.ProductEntry.COLUMN_PRODUCT_PRICE, priceValue);
                Uri uri = Uri.withAppendedPath(ProductContract.ProductEntry.CONTENT_URI, _ID);

                int rowsAffected = context.getContentResolver().update(uri, values, null, null);

                if (rowsAffected == 0) {
                    // If no rows were affected, then there was an error with the update.
                    Toast.makeText(context, "failed to sale", Toast.LENGTH_SHORT).show();
                } else {
                    // Otherwise, the update was successful and we can display a toast.
                    Toast.makeText(context, "sale one item", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
