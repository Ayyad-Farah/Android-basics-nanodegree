package com.example.android.inventoryapp;

import android.Manifest;
import android.app.AlertDialog;
import android.app.LoaderManager;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.Loader;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NavUtils;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.android.inventoryapp.data.ProductContract;

import java.io.ByteArrayOutputStream;
import java.io.IOException;


public class EditorActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private static int RESULT_LOAD_IMAGE = 1;

    /**
     * EditText field to enter the products's name
     */
    private EditText mNameEditText;

    /**
     * EditText field to enter the product's price
     */
    private EditText mPriceEditText;

    /**
     * EditText field to enter the product's quantity
     */
    private EditText mQuantityEditText;

    /**
     * Image tools to make user upload photo
     */

    private ImageButton mImageButton;

    private ImageView mImageView;

    private Bitmap mProductPicture;

    private static final int EXISTING_PRODUCT_LOADER = 0;
    private Uri mCurrentProductUri;

    private boolean mProductHasChanged = false;

    private View.OnTouchListener mTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            mProductHasChanged = true;
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        Intent intent = getIntent();
        mCurrentProductUri = intent.getData();

        if (mCurrentProductUri == null) {
            setTitle("Add a Product");
            LinearLayout linearLayout = (LinearLayout) findViewById(R.id.order_or_sale);
            linearLayout.setVisibility(View.GONE);

        } else {
            setTitle("Edit Product");
            getLoaderManager().initLoader(EXISTING_PRODUCT_LOADER, null, this);
            LinearLayout linearLayout = (LinearLayout) findViewById(R.id.order_or_sale);
            linearLayout.setVisibility(View.VISIBLE);
        }

        mNameEditText = (EditText) findViewById(R.id.edit_Product_name);
        mPriceEditText = (EditText) findViewById(R.id.edit_Product_price);
        mQuantityEditText = (EditText) findViewById(R.id.edit_Product_quantity);
        mImageButton = (ImageButton) findViewById(R.id.select_photo);
        mImageView = (ImageView) findViewById(R.id.product_photo);

        mNameEditText.setOnTouchListener(mTouchListener);
        mPriceEditText.setOnTouchListener(mTouchListener);
        mQuantityEditText.setOnTouchListener(mTouchListener);
        mImageView.setOnTouchListener(mTouchListener);

        mProductPicture = null;

        if (mCurrentProductUri == null) {
            // This is a new product, so change the app bar to say "Add a product"
            setTitle("Add a Product");

            // Invalidate the options menu, so the "Delete" menu option can be hidden.
            // (It doesn't make sense to delete a product that hasn't been created yet.)

            invalidateOptionsMenu();
        }


        mImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int permissionCheck = ContextCompat.checkSelfPermission(EditorActivity.this,
                        Manifest.permission.READ_EXTERNAL_STORAGE);
                if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
                    Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                            android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(pickPhoto, 1);
                } else {
                    ActivityCompat.requestPermissions(EditorActivity.this,
                            new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
                }
            }
        });

        Button order = (Button) findViewById(R.id.order_button);
        Button sale = (Button) findViewById(R.id.sale_button);


        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int order = getOrderOrSaledQuantity();
                if (order < 1) {
                    Toast.makeText(EditorActivity.this, "Please insert valid quantity to order from supplier.", Toast.LENGTH_SHORT).show();
                } else {
                    int productQuantity = Integer.parseInt(mQuantityEditText.getText().toString().trim());
                    String productName = mNameEditText.getText().toString().trim();
                    String productPrice = mPriceEditText.getText().toString().trim();
                    int priceValueSale = 0;
                    if (!TextUtils.isEmpty(productPrice)) {
                        priceValueSale = Integer.parseInt(productPrice);
                    }
                    saveProduct(productName, productQuantity + order, priceValueSale);
                    Toast.makeText(EditorActivity.this, "Order " + order + " item from supplier", Toast.LENGTH_SHORT).show();

                }
            }
        });

        sale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int sale = getOrderOrSaledQuantity();
                if (sale < 1) {
                    Toast.makeText(EditorActivity.this, "Please insert valid quantity to sale.", Toast.LENGTH_SHORT).show();
                } else {
                    int productQuantity = Integer.parseInt(mQuantityEditText.getText().toString().trim());
                    String productName = mNameEditText.getText().toString().trim();
                    String productPrice = mPriceEditText.getText().toString().trim();
                    int priceValueSale = 0;
                    if (productQuantity < sale) {
                        Toast.makeText(EditorActivity.this, "there is enough quantity of this product.", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (!TextUtils.isEmpty(productPrice)) {
                        priceValueSale = Integer.parseInt(productPrice);
                    }
                    saveProduct(productName, productQuantity - sale, priceValueSale);
                    Toast.makeText(EditorActivity.this, "sale " + sale + " items", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private int getOrderOrSaledQuantity() {
        EditText orderdOrSaledQuantityEditText = (EditText) findViewById(R.id.quantity_ordered_or_saled);
        String orderdOrSaledQuantityString = orderdOrSaledQuantityEditText.getText().toString().trim();
        if (TextUtils.isEmpty(orderdOrSaledQuantityString)) {
            return -1;
        }
        int orderOrSaledQuantity = Integer.parseInt(orderdOrSaledQuantityString);
        return orderOrSaledQuantity;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 &&
                resultCode == RESULT_OK && null != data) {
            Toast.makeText(this, "Uploading", Toast.LENGTH_LONG).show();
            Uri selectedImageUri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedImageUri);
                mImageView = (ImageView) findViewById(R.id.product_photo);
                mImageView.setImageBitmap(bitmap);

                mProductPicture = bitmap;

            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(this, "Could not load this image", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    finish();
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        // If this is a new product, hide the "Delete" menu item.
        if (mCurrentProductUri == null) {
            MenuItem menuItem = menu.findItem(R.id.action_delete);
            menuItem.setVisible(false);
            MenuItem menuItem1 = menu.findItem(R.id.sale);
            menuItem1.setVisible(false);
        }
        return true;
    }

    private void showUnsavedChangesDialog(
            DialogInterface.OnClickListener discardButtonClickListener) {
        // Create an AlertDialog.Builder and set the message, and click listeners
        // for the positive and negative buttons on the dialog.
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Discared your changes and quit editing?");
        builder.setPositiveButton("Discared", discardButtonClickListener);
        builder.setNegativeButton("Keep editing", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked the "Keep editing" button, so dismiss the dialog
                // and continue editing the product.
                if (dialog != null) {
                    dialog.dismiss();
                }
            }
        });
        // Create and show the AlertDialog
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    @Override
    public void onBackPressed() {
        // If the product hasn't changed, continue with handling back button press
        if (!mProductHasChanged) {
            super.onBackPressed();
            return;
        }

        // Otherwise if there are unsaved changes, setup a dialog to warn the user.
        // Create a click listener to handle the user confirming that changes should be discarded.
        DialogInterface.OnClickListener discardButtonClickListener =
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // User clicked "Discard" button, close the current activity.
                        finish();
                    }
                };

        // Show dialog that there are unsaved changes
        showUnsavedChangesDialog(discardButtonClickListener);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_editor.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.menu_editor, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            // Respond to a click on the "Save" menu option
            case R.id.action_save:
                // Do nothing for now
                String name = mNameEditText.getText().toString().trim();
                String quantity = mQuantityEditText.getText().toString().trim();
                String price = mPriceEditText.getText().toString().trim();

                if (mCurrentProductUri == null &&
                        TextUtils.isEmpty(name) && TextUtils.isEmpty(quantity) &&
                        TextUtils.isEmpty(price) && mProductPicture == null) {
                    finish();
                    return true;
                }

                int quantityValue = 0;
                if (!TextUtils.isEmpty(quantity)) {
                    quantityValue = Integer.parseInt(quantity);
                }

                int priceValue = 0;

                if (!TextUtils.isEmpty(price)) {
                    priceValue = Integer.parseInt(price);
                }

                saveProduct(name, quantityValue, priceValue);
                finish();
                return true;

            case R.id.sale:
                int productQuantity = Integer.parseInt(mQuantityEditText.getText().toString().trim());
                if (productQuantity == 0) {
                    Toast.makeText(EditorActivity.this, "Failed, there is no " + mNameEditText.getText(),
                            Toast.LENGTH_SHORT).show();
                    return true;
                } else {
                    String productName = mNameEditText.getText().toString().trim();
                    String productPrice = mPriceEditText.getText().toString().trim();

                    int priceValueSale = 0;

                    if (!TextUtils.isEmpty(productPrice)) {
                        priceValueSale = Integer.parseInt(productPrice);
                    }
                    saveProduct(productName, productQuantity - 1, priceValueSale);
                    Toast.makeText(EditorActivity.this, "Sale one item", Toast.LENGTH_SHORT).show();
                    return true;
                }
                // Respond to a click on the "Delete" menu option
            case R.id.action_delete:
                // Do nothing for now
                showDeleteConfirmationDialog();
                return true;
            // Respond to a click on the "Up" arrow button in the app bar
            case android.R.id.home:
                // Navigate back to parent activity (CatalogActivity)
                if (!mProductHasChanged) {
                    NavUtils.navigateUpFromSameTask(EditorActivity.this);
                    return true;
                }

                // Otherwise if there are unsaved changes, setup a dialog to warn the user.
                // Create a click listener to handle the user confirming that
                // changes should be discarded.
                DialogInterface.OnClickListener discardButtonClickListener =
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                // User clicked "Discard" button, navigate to parent activity.
                                NavUtils.navigateUpFromSameTask(EditorActivity.this);
                            }
                        };

                // Show a dialog that notifies the user they have unsaved changes
                showUnsavedChangesDialog(discardButtonClickListener);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void showDeleteConfirmationDialog() {
        // Create an AlertDialog.Builder and set the message, and click listeners
        // for the postivie and negative buttons on the dialog.
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Delete this product?");
        builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked the "Delete" button, so delete the product.
                deleteProduct();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked the "Cancel" button, so dismiss the dialog
                // and continue editing the product.
                if (dialog != null) {
                    dialog.dismiss();
                }
            }
        });

        // Create and show the AlertDialog
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void deleteProduct() {
        // Only perform the delete if this is an existing product.
        if (mCurrentProductUri != null) {
            // Call the ContentResolver to delete the product at the given content URI.
            // Pass in null for the selection and selection args because the mCurrentProductUri
            // content URI already identifies the product that we want.
            int rowsDeleted = getContentResolver().delete(mCurrentProductUri, null, null);
            // Show a toast message depending on whether or not the delete was successful.
            if (rowsDeleted == 0) {
                // If no rows were deleted, then there was an error with the delete.
                Toast.makeText(this, "Error with deleting product", Toast.LENGTH_SHORT).show();
            } else {
                // Otherwise, the delete was successful and we can display a toast.
                Toast.makeText(this, "Product deleted", Toast.LENGTH_SHORT).show();
            }
            finish();
        }
    }


    private void saveProduct(String name, int quantity, int price) {
        ContentValues values = new ContentValues();
        values.put(ProductContract.ProductEntry.COLUMN_PRODUCT_NAME, name);
        values.put(ProductContract.ProductEntry.COLUMN_PRODUCT_QUANTITY, quantity);
        values.put(ProductContract.ProductEntry.COLUMN_PRODUCT_PRICE, price);


        if (mProductPicture != null) {
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            mProductPicture.compress(Bitmap.CompressFormat.PNG, 100, stream);
            byte[] byteArray = stream.toByteArray();
            values.put(ProductContract.ProductEntry.COLUMN_PRODUCT_IMAGE, byteArray);
        }

        if (mCurrentProductUri == null) {
            Uri newUri = getContentResolver().insert(ProductContract.ProductEntry.CONTENT_URI, values);
            if (newUri == null) {
                // If the new content URI is null, then there was an error with insertion.
                Toast.makeText(this, "error with saving product", Toast.LENGTH_SHORT).show();
            } else {
                // Otherwise, the insertion was successful and we can display a toast.
                Toast.makeText(this, "Product saved", Toast.LENGTH_SHORT).show();
            }
        } else {
            int rowsAffected = getContentResolver().update(mCurrentProductUri, values, null, null);

            if (rowsAffected == 0) {
                // If no rows were affected, then there was an error with the update.
                Toast.makeText(this, "update product field", Toast.LENGTH_SHORT).show();
            } else {
                // Otherwise, the update was successful and we can display a toast.
                Toast.makeText(this, "update product successful", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        String[] projection = {
                ProductContract.ProductEntry._ID,
                ProductContract.ProductEntry.COLUMN_PRODUCT_NAME,
                ProductContract.ProductEntry.COLUMN_PRODUCT_QUANTITY,
                ProductContract.ProductEntry.COLUMN_PRODUCT_PRICE,
                ProductContract.ProductEntry.COLUMN_PRODUCT_IMAGE};

        return new CursorLoader(this,
                mCurrentProductUri,
                projection,
                null,
                null,
                null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        if (cursor == null || cursor.getCount() < 1) {
            return;
        }

        if (cursor.moveToFirst()) {
            // Find the columns of mPriceEditText attributes that we're interested in
            int nameColumnIndex = cursor.getColumnIndex(ProductContract.ProductEntry.COLUMN_PRODUCT_NAME);
            int quantityColumnIndex = cursor.getColumnIndex(ProductContract.ProductEntry.COLUMN_PRODUCT_QUANTITY);
            int priceColumnIndex = cursor.getColumnIndex(ProductContract.ProductEntry.COLUMN_PRODUCT_PRICE);
            int imagetColumnIndex = cursor.getColumnIndex(ProductContract.ProductEntry.COLUMN_PRODUCT_IMAGE);

            // Extract out the value from the Cursor for the given column index

            String name = cursor.getString(nameColumnIndex);

            byte[] image = cursor.getBlob(imagetColumnIndex);
            int quantity = cursor.getInt(quantityColumnIndex);

            int price = cursor.getInt(priceColumnIndex);
            // Update the views on the screen with the values from the database

            mNameEditText.setText(name);
            mQuantityEditText.setText(Integer.toString(quantity));
            mPriceEditText.setText(Integer.toString(price));
            Bitmap picture = BitmapFactory.decodeByteArray(image, 0, image.length);
            mImageView.setImageBitmap(picture);

        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        // If the loader is invalidated, clear out all the data from the input fields.
        mNameEditText.setText("");
        mImageView.setVisibility(View.GONE);
        mPriceEditText.setText("");
        mQuantityEditText.setText("");
    }
}
