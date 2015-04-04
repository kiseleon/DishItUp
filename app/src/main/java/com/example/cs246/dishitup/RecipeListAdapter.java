package com.example.cs246.dishitup;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import java.io.File;

/**
 * Created by Kevin on 4/4/2015.
 */
public class RecipeListAdapter extends SimpleCursorAdapter {

    private Context context;
    private int layout;
    private LayoutInflater layoutInflater;

    private class ViewHolder {
        TextView id;
        TextView name;
        TextView rating;
        TextView cooktime;
        ImageView imageView;

        ViewHolder(View v) {
            id = (TextView) v.findViewById(R.id.idTab);
            name = (TextView) v.findViewById(R.id.nameTab);
            rating = (TextView) v.findViewById(R.id.ratingTab);
            cooktime = (TextView) v.findViewById(R.id.timeTab);
            imageView = (ImageView) v.findViewById(R.id.imageTab);

        }
    }

    public RecipeListAdapter(Context context, int layout, Cursor c, String[] from, int[] to, int flags) {
        super(context, layout, c, from, to, flags);
        this.context = context;
        this.layout = layout;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public View newView(Context ctx, Cursor cursor, ViewGroup parent) {
        View vView = layoutInflater.inflate(layout, parent, false);
        vView.setTag( new ViewHolder(vView) );
        // no need to bind data here. you do in later
        return vView;// **EDITED:**need to return the view
    }

    @Override
    public void bindView(View v, Context ctx, Cursor c) {
        // you might want to cache these too
        int iCol_ID = c.getColumnIndex(DatabaseControl.KEY_ID);
        int iCol_Name = c.getColumnIndex(DatabaseControl.KEY_NAME);
        int iCol_Rating = c.getColumnIndex(DatabaseControl.KEY_RATING);
        int iCol_Time = c.getColumnIndex(DatabaseControl.KEY_COOKTIME);
        int iCol_Image = c.getColumnIndex(DatabaseControl.KEY_IMGEREF);

        String sID = c.getString(iCol_ID);
        String sName = c.getString(iCol_Name);
        String sRating = c.getString(iCol_Rating) + "/5";
        String sTime = c.getString(iCol_Time) + " minutes";
        String sImage = c.getString (iCol_Image);  //// path & file

        ViewHolder vh = (ViewHolder) v.getTag();

        vh.id.setText(sID);
        vh.name.setText(sName);
        vh.rating.setText(sRating);
        vh.cooktime.setText(sTime);

        if (sImage.equals("@drawable/placeholder_image")) {
            int imageResource = context.getResources().getIdentifier(sImage, null, context.getPackageName());
            Drawable res = context.getResources().getDrawable(imageResource);
            vh.imageView.setImageDrawable(res);

        } else {
            Bitmap thumbnail = (BitmapFactory.decodeFile(sImage));
            Log.w("image path from gallery", sImage + "");

            vh.imageView.setImageBitmap(thumbnail);
        }
    }

}
