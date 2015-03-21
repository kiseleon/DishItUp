package com.example.cs246.dishitup;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/*
 * Created by Jason on 3/5/2015.
 * I used a tutorial from Youtube to help me create this database control
 * https://www.youtube.com/watch?v=xKuM3cHO7G8
 */

/**
 * This is a database manipulator that will store recipe cards
 *
 * @author Jason
 * @see android.database.sqlite.SQLiteOpenHelper
 */

public class DatabaseControl extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 2;

    private static final String DATABASE_NAME = "recipeCardManager",
            TABLE_RECIPES = "recipes",
            KEY_ID = "id",
            KEY_NAME = "name",
            KEY_RATING = "rating",
            KEY_COMMENT = "comment",
            KEY_IMGEREF = "img",
            KEY_COOKTIME = "cookTime",
            KEY_DIRECTIONS = "directions",
            TABLE_INGREDIENTS = "ingredientstable",
            KEY_INGREDIENTS = "ingredients",
            KEY_AMOUNTS = "amounts",
            TABLE_CATEGORIES = "categoriestable",
            KEY_CATEGORIES = "categories";

    public DatabaseControl(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * On create requires you to pass the getApplicationContext() in order to setup the database
     * @param database needs the location of the database you want to use.
     */
    @Override
    public void onCreate(SQLiteDatabase database){
        database.execSQL("CREATE TABLE " +
                TABLE_RECIPES + "(" +
                KEY_ID +             "INTEGER PRIMARY KEY, " +
                KEY_NAME +           " TEXT," +
                KEY_RATING +         " INTEGER," +
                KEY_COMMENT +        " TEXT," +
                KEY_IMGEREF +        " TEXT," +
                KEY_COOKTIME +       " INTEGER," +
                KEY_DIRECTIONS +     " TEXT)");

        database.execSQL("CREATE TABLE " + TABLE_CATEGORIES + "(" +
                KEY_ID + "INTEGER PRIMARY KEY, " + KEY_CATEGORIES + " TEXT)");

        database.execSQL("CREATE TABLE " + TABLE_INGREDIENTS + "(" +
                KEY_ID + "INTEGER PRIMARY KEY, " + KEY_INGREDIENTS + " TEXT, " +
                KEY_AMOUNTS + " TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion){
        database.execSQL("DROP TABLE IF EXISTS " + TABLE_RECIPES);
        database.execSQL("DROP TABLE IF EXISTS " + TABLE_CATEGORIES);
        database.execSQL("DROP TABLE IF EXISTS " + TABLE_INGREDIENTS);

        onCreate(database);
    }

    /**
     * Takes a RecipeCard and uses the data from the recipe card to fill out a new row in the
     * database.
     *
     * @param recipeCard A filled out recipe card should be passed to add a recipe card to the
     *                   database
     */
    public void createRecipe(RecipeCard recipeCard){
        SQLiteDatabase database = getWritableDatabase();

        // For creating the main table
        ContentValues values = new ContentValues();

        values.put(KEY_ID, "null");
        values.put(KEY_NAME, recipeCard.getName());
        values.put(KEY_RATING, recipeCard.getRating());
        values.put(KEY_COMMENT, recipeCard.getComment());
        values.put(KEY_IMGEREF, recipeCard.getPictureRef());
        values.put(KEY_COOKTIME, recipeCard.getCookTime());
        //values.put(KEY_INGREDIENTS, recipeCard.getIngredients().toString());
        values.put(KEY_DIRECTIONS, recipeCard.getDirections());
        //values.put(KEY_CATEGORIES, recipeCard.getCategories().toString());

        // the database insert give you the row ID as a return value
        long id = database.insert(TABLE_RECIPES, null, values);

        // add each category as its own row
        for (String category : recipeCard.getCategories()) {
            values = new ContentValues();
            values.put(KEY_ID, id);
            values.put(KEY_CATEGORIES, category);
            database.insert(TABLE_CATEGORIES, null, values);
        }

        // TODO: Add the ingredients/amounts to the ingredients table



        database.close();
    }

    /**
     * Will take an int id of a RecipeCard that is in the database and returns the RecipeCard
     * at the id you have passed
     *
     * @param id the key id of the recipe card you want to find should be passed in
     * @return returns a recipeCard object
     */

    public RecipeCard getRecipeCard(int id){
        SQLiteDatabase database = getReadableDatabase();

        Cursor cursor = database.query(TABLE_RECIPES, new String [] {KEY_ID, KEY_NAME,
                        KEY_RATING,KEY_COMMENT, KEY_IMGEREF, KEY_COOKTIME, KEY_INGREDIENTS,
                        KEY_DIRECTIONS, KEY_CATEGORIES}, KEY_ID + "=?", new String[]
                        {String.valueOf(id)},null,null,null,null);
        if (cursor != null)
            cursor.moveToFirst();

        RecipeCard recipeCard = new RecipeCard();
        assert cursor != null;
        recipeCard.setId(Integer.parseInt(cursor.getString(0)));
        recipeCard.setName(cursor.getString(1));
        recipeCard.setRating(Integer.parseInt(cursor.getString(2)));
        recipeCard.setComment(cursor.getString(3));
        recipeCard.setPictureRef(cursor.getString(4));
        recipeCard.setCookTime(Integer.parseInt(cursor.getString(5)));
        //recipeCard.addIngredient(cursor.getString(6));
        recipeCard.setDirections(cursor.getString(7));
        //recipeCard.addCategory(cursor.getString(8));

        //TODO: get the ingredients/amounts and the categories from the respective tables

        return recipeCard;
    }

    /**
     * Will delete any RecipeCard in the database given the RecipeCard you want to delete.
     * @param recipeCard The RecipeCard that you want to delete must be passed in
     */

    public void deleteRecipeCard(RecipeCard recipeCard){
        SQLiteDatabase database = getWritableDatabase();
        database.delete(TABLE_RECIPES, KEY_ID + "=?", new String[]
                {String.valueOf(recipeCard.getId())});
        // TODO: Delete the associated ingredient table rows and category table rows
        database.close();
    }

    /**
     * Will return the number of RecipeCards in the database
     * @return returns an int of the number of RecipesCards in the database
     */

    public int getRecipeCount(){
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM " + TABLE_RECIPES, null);
        cursor.close();
        database.close();

        return cursor.getCount();
    }

    /**
     * Pass in a RecipeCard that you have made changes to and it will update itself in the database
     * @param recipeCard takes a RecipeCard
     * @return returns the id of the RecipeCard
     */

    public int updateRecipe(RecipeCard recipeCard){
        SQLiteDatabase database = getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(KEY_NAME, recipeCard.getName());
        values.put(KEY_RATING, recipeCard.getRating());
        values.put(KEY_COMMENT, recipeCard.getComment());
        values.put(KEY_IMGEREF, recipeCard.getPictureRef());
        values.put(KEY_COOKTIME, recipeCard.getCookTime());
        values.put(KEY_INGREDIENTS, recipeCard.getIngredients().toString());
        values.put(KEY_DIRECTIONS, recipeCard.getDirections());
        values.put(KEY_CATEGORIES, recipeCard.getCategories().toString());
        // TODO: Make this work with the two new tables

        return database.update(TABLE_RECIPES, values, KEY_ID + "=?", new String[]
                {String.valueOf(recipeCard.getId())});
    }

    /**
     * This class will return a list of all RecipeCard objects in the database
     *
     * @return List of RecipeCards
     */
    public List<RecipeCard> getAllRecipeCards(){
        List<RecipeCard> recipeCards = new ArrayList<>();

        SQLiteDatabase database = getWritableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM " + TABLE_RECIPES, null);

        if (cursor.moveToFirst()) {
            do {
                RecipeCard recipeCard = new RecipeCard();
                recipeCard.setId(Integer.parseInt(cursor.getString(0)));
                recipeCard.setName(cursor.getString(1));
                recipeCard.setRating(Integer.parseInt(cursor.getString(2)));
                recipeCard.setComment(cursor.getString(3));
                recipeCard.setPictureRef(cursor.getString(4));
                recipeCard.setCookTime(Integer.parseInt(cursor.getString(5)));
                //recipeCard.addIngredient(cursor.getString(6));
                recipeCard.setDirections(cursor.getString(7));
                //recipeCard.addCategory(cursor.getString(8));
                recipeCards.add(recipeCard);

                //TODO: make this set the categories and ingredients/amounts
            }while (cursor.moveToNext());
        }
        return recipeCards;
    }
}
