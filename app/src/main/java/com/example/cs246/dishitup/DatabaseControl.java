package com.example.cs246.dishitup;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jason on 3/5/2015.
 * I used a tutorial from Youtube to help me create this database control
 * https://www.youtube.com/watch?v=xKuM3cHO7G8
 */
public class DatabaseControl extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "recipeCardManager",
            TABLE_RECIPES = "recipes",
            KEY_ID = "id",
            KEY_NAME = "name",
            KEY_RATING = "rating",
            KEY_COMMENT = "comment",
            KEY_IMGEREF = "img",
            KEY_COOKTIME = "cookTime",
            KEY_INGREDIENTS = "ingredients",
            KEY_DIRECTIONS = "directions",
            KEY_CATEGORIES = "categories";

    public DatabaseControl(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database){
        database.execSQL("CREATE TABLE " + TABLE_RECIPES + "(" +
                KEY_ID + "INTEGER PRIMARY KEY AUTOINCREMENT, " +
                KEY_NAME + " TEXT," + KEY_RATING + " TEXT," + KEY_COMMENT + " TEXT," +
                KEY_IMGEREF + " TEXT," + KEY_COOKTIME + " TEXT," +
                KEY_INGREDIENTS + " TEXT," + KEY_DIRECTIONS + " TEXT," + KEY_CATEGORIES + " TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion){
        database.execSQL("DROP TABLE IF EXISTS " + TABLE_RECIPES);

        onCreate(database);
    }

    public void createRecipe(RecipeCard recipeCard){
        SQLiteDatabase database = getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(KEY_NAME, recipeCard.getName());
        values.put(KEY_RATING, recipeCard.getRating());
        values.put(KEY_COMMENT, recipeCard.getComment());
        values.put(KEY_IMGEREF, recipeCard.getPictureRef());
        values.put(KEY_COOKTIME, recipeCard.getCookTime());
        values.put(KEY_INGREDIENTS, recipeCard.getIngredients().toString());
        values.put(KEY_DIRECTIONS, recipeCard.getDirections().toString());
        values.put(KEY_CATEGORIES, recipeCard.getCategories().toString());

        database.insert(TABLE_RECIPES, null, values);
        database.close();
    }

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
        //for the next three we need to look through the string and pars them into individual parts instead of one big string
        recipeCard.addIngredient(cursor.getString(6));
        recipeCard.addDirection(cursor.getString(7));
        recipeCard.addCategory(cursor.getString(8));

        return recipeCard;
    }

    public void deleteRecipeCard(RecipeCard recipeCard){
        SQLiteDatabase database = getWritableDatabase();
        database.delete(TABLE_RECIPES, KEY_ID + "=?", new String[]
                {String.valueOf(recipeCard.getId())});
        database.close();
    }

    public int getRecipeCount(){
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM " + TABLE_RECIPES, null);
        cursor.close();
        database.close();

        return cursor.getCount();
    }

    public int updateRecipe(RecipeCard recipeCard){
        SQLiteDatabase database = getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(KEY_NAME, recipeCard.getName());
        values.put(KEY_RATING, recipeCard.getRating());
        values.put(KEY_COMMENT, recipeCard.getComment());
        values.put(KEY_IMGEREF, recipeCard.getPictureRef());
        values.put(KEY_COOKTIME, recipeCard.getCookTime());
        values.put(KEY_INGREDIENTS, recipeCard.getIngredients().toString());
        values.put(KEY_DIRECTIONS, recipeCard.getDirections().toString());
        values.put(KEY_CATEGORIES, recipeCard.getCategories().toString());

        return database.update(TABLE_RECIPES, values, KEY_ID + "=?", new String[]
                {String.valueOf(recipeCard.getId())});
    }

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
                //for the next three we need to look through the string add multiple of each item
                recipeCard.addIngredient(cursor.getString(6));
                recipeCard.addDirection(cursor.getString(7));
                recipeCard.addCategory(cursor.getString(8));
                recipeCards.add(recipeCard);
            }while (cursor.moveToNext());
        }
        return recipeCards;
    }
}
