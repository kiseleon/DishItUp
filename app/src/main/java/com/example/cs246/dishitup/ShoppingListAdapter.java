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
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kevin on 4/4/2015.
 */
public class ShoppingListAdapter extends SimpleCursorAdapter {

    private Context context;
    private int layout;
    private LayoutInflater layoutInflater;
    private List<Boolean> checked;


    private class ViewHolder {
        TextView id;
        CheckBox checkBox;

        ViewHolder(View v) {
            id = (TextView) v.findViewById(R.id.listId);
            checkBox = (CheckBox) v.findViewById(R.id.checkBox);
        }
    }


    public ShoppingListAdapter(Context context, int layout, Cursor c, String[] from, int[] to, int flags) {
        super(context, layout, c, from, to, flags);
        this.context = context;
        this.layout = layout;
        layoutInflater = LayoutInflater.from(context);
        checked = new ArrayList<>();
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
        int iCol_ID = c.getColumnIndex(DatabaseControl.KEY_SHOPINGID);
        int iCol_Item = c.getColumnIndex(DatabaseControl.KEY_ITEM);

        String sID = c.getString(iCol_ID);
        String sItem = c.getString(iCol_Item);

        ViewHolder vh = (ViewHolder) v.getTag();

        vh.id.setText(sID);
        vh.checkBox.setText(sItem);

    }



}
