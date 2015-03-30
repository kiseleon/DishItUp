package com.example.cs246.dishitup;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kevin on 3/28/2015.
 */
public class FilterData {
    private static FilterData ourInstance = new FilterData();
    private static DatabaseControl databaseControl;
    private static SQLiteDatabase database;

    private static String filter = "";

    public static void clearFilter() {
        filter = "";
    }

    public static void timeFilter(int time) {
        filter = " WHERE " + DatabaseControl.KEY_COOKTIME + "<" + time + " ";
    }

    public static void ingredientFilter(String ingredient) {

        //get ingredients
        String selectQuery = "SELECT * FROM " + DatabaseControl.TABLE_INGREDIENTS + " WHERE "
                + DatabaseControl.KEY_INGREDIENT + "=\"" + ingredient + "\"";

        database = databaseControl.getReadableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);

        cursor.moveToFirst();

        List<Integer> idList = new ArrayList<>();

        // Get the list of ids
        for (int i = 0; i < cursor.getCount(); i++) {
            idList.add(cursor.getInt(cursor.getColumnIndex(DatabaseControl.KEY_LOOKUPINGREDENTS)));
            cursor.moveToNext();
        }

        String toAdd = "";

        // construct the string
        if (idList.size() > 0)
            toAdd += " WHERE ";

        for (int i = 0; i < idList.size(); i++) {
            toAdd += DatabaseControl.KEY_ID + "=" + idList.get(i) + " ";
            if (i < (idList.size() - 1))
                toAdd += " OR ";
        }

        cursor.close();
        database.close();

        filter = toAdd;

    }

    public static void categoryFilter(String category) {
        // Works basically the same as ingredientFilter

        //get categories
        String selectQuery = "SELECT * FROM " + DatabaseControl.TABLE_CATEGORIES + " WHERE "
                + DatabaseControl.KEY_CATEGORIES + " =\"" + category + "\"";

        database = databaseControl.getReadableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);

        cursor.moveToFirst();

        List<Integer> idList = new ArrayList<>();

        // Get the list of ids
        for (int i = 0; i < cursor.getCount(); i++) {
            idList.add(cursor.getInt(cursor.getColumnIndex(DatabaseControl.KEY_LOOKUPCATEGORIES)));
            cursor.moveToNext();
        }

        String toAdd = "";

        // construct the string
        if (idList.size() > 0)
            toAdd += " WHERE ";

        for (int i = 0; i < idList.size(); i++) {
           toAdd += DatabaseControl.KEY_ID + "=" + idList.get(i) + " ";
            if (i < (idList.size() - 1))
                toAdd += " OR ";
        }

        cursor.close();
        database.close();

        filter = toAdd;

    }

    public static String getFilter() {
        return filter;
    }

    public static void initializeFilter(Context context) {
        databaseControl = new DatabaseControl(context);
    }

    public static FilterData getInstance() {
        return ourInstance;
    }

    private FilterData() {
    }
}
