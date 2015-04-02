package com.example.cs246.dishitup;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;


/**
 * This is a database manipulator that will store recipe cards
 *
 * @author Jason
 * @see android.database.sqlite.SQLiteOpenHelper
 */

public class DatabaseControl extends SQLiteOpenHelper {
    //database version
    private static final int DATABASE_VERSION = 10;
    //database name
    private static final String DATABASE_NAME = "recipeCardManager";
    //table name
    public static final String TABLE_INGREDIENTS = "ingredientsTable";
    public static final String TABLE_CATEGORIES = "categoriesTable";
    public static final String TABLE_RECIPES = "recipes";
    public static final String TABLE_SHOPPINGLIST = "shoppingList";

    //Recipes column names
    public static final String KEY_ID = "_id";
    public static final String KEY_NAME = "name";
    public static final String KEY_RATING = "rating";
    public static final String KEY_COMMENT = "comment";
    public static final String KEY_IMGEREF = "img";
    public static final String KEY_COOKTIME = "cookTime";
    public static final String KEY_DIRECTIONS = "directions";

    //ingredientsTable column names
    public static final String KEY_INGID = "_id";
    public static final String KEY_LOOKUPINGREDENTS = "ingredientsID";
    public static final String KEY_INGREDIENT = "ingredients";
    public static final String KEY_AMOUNTS = "amounts";

    //categoriesTable column names
    public static final String KEY_CATID = "_id";
    public static final String KEY_LOOKUPCATEGORIES = "categoriesID";
    public static final String KEY_CATEGORIES = "categories";

    //shoppingTable column names
    public static final String KEY_SHOPINGID = "_id";
    public static final String KEY_ITEM = "item";

    //Recipe table
    private static final String CREATE_TABLE_RECIPES = "CREATE TABLE "
            + TABLE_RECIPES + " (" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + KEY_NAME + " TEXT, " + KEY_RATING + " INTEGER, " + KEY_COMMENT + " TEXT, "
            + KEY_IMGEREF + " TEXT, " + KEY_COOKTIME + " INTEGER, " + KEY_DIRECTIONS
            + " TEXT);";
    //Ingredients table
    private static final String CREATE_TABLE_INGREDIENTS = "CREATE TABLE "
            + TABLE_INGREDIENTS + " (" + KEY_INGID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + KEY_LOOKUPINGREDENTS + " INTEGER, " + KEY_INGREDIENT + " TEXT, "
            + KEY_AMOUNTS + " TEXT);";

    //Categories table
    private static final String CREATE_TABLE_CATEGORIES = "CREATE TABLE "
            + TABLE_CATEGORIES + " (" + KEY_CATID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + KEY_LOOKUPCATEGORIES + " INTEGER, " + KEY_CATEGORIES + " TEXT);";

    //Shopping list table
    private static final String CREATE_TABLE_SHOPPINGLIST = "CREATE TABLE "
            + TABLE_SHOPPINGLIST + " (" + KEY_SHOPINGID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + KEY_ITEM + " TEXT);";

    public DatabaseControl(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * On create requires you to pass the getApplicationContext() in order to setup the database
     * @param database needs the location of the database you want to use.
     */
    @Override
    public void onCreate(SQLiteDatabase database){
        database.execSQL(CREATE_TABLE_RECIPES);
        database.execSQL(CREATE_TABLE_INGREDIENTS);
        database.execSQL(CREATE_TABLE_CATEGORIES);
        database.execSQL(CREATE_TABLE_SHOPPINGLIST);
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion){
        database.execSQL("DROP TABLE IF EXISTS " + TABLE_RECIPES);
        database.execSQL("DROP TABLE IF EXISTS " + TABLE_CATEGORIES);
        database.execSQL("DROP TABLE IF EXISTS " + TABLE_INGREDIENTS);
        database.execSQL("DROP TABLE IF EXISTS " + TABLE_SHOPPINGLIST);

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

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, recipeCard.getName());
        System.out.println("Putting " + recipeCard.getName());
        values.put(KEY_RATING, recipeCard.getRating());
        values.put(KEY_COMMENT, recipeCard.getComment());
        values.put(KEY_IMGEREF, recipeCard.getPictureRef());
        values.put(KEY_COOKTIME, recipeCard.getCookTime());
        values.put(KEY_DIRECTIONS, recipeCard.getDirections());
        // the database insert give you the row ID as a return value
        long id = database.insert(TABLE_RECIPES, null, values);

        List<String> ingredientList = recipeCard.getIngredients();
        List<String> amountList = recipeCard.getAmounts();

        //for every ingredient add a row to the ingredients table and make the LOOKUPINGREDENTS the same as the key id for the recipe it belongs to.
        for(int i = 0; i < ingredientList.size(); i++){
            ContentValues ingredientValues = new ContentValues();
            ingredientValues.put(KEY_LOOKUPINGREDENTS, id);
            ingredientValues.put(KEY_INGREDIENT, ingredientList.get(i));
            ingredientValues.put(KEY_AMOUNTS, amountList.get(i));
            //insert row
            database.insert(TABLE_INGREDIENTS, null, ingredientValues);
        }

        // add each category as its own row in the categories table like we did above with the ingredients
        for (String category : recipeCard.getCategories()) {
            ContentValues categoryValues = new ContentValues();
            categoryValues.put(KEY_LOOKUPCATEGORIES, id);
            categoryValues.put(KEY_CATEGORIES, category);
            //insert row
            database.insert(TABLE_CATEGORIES, null, categoryValues);
        }

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
        RecipeCard recipeCard = new RecipeCard();

        String selectQuery = "SELECT * FROM " + TABLE_RECIPES + " WHERE "
                + KEY_ID + " = " + id;

        Cursor cursor = database.rawQuery(selectQuery, null);
        if (cursor != null)
            cursor.moveToFirst();

        assert cursor != null;
        recipeCard.setId(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
        recipeCard.setName(cursor.getString(cursor.getColumnIndex(KEY_NAME)));
        recipeCard.setRating(cursor.getInt(cursor.getColumnIndex(KEY_RATING)));
        recipeCard.setComment(cursor.getString(cursor.getColumnIndex(KEY_COMMENT)));
        recipeCard.setPictureRef(cursor.getString(cursor.getColumnIndex(KEY_IMGEREF)));
        recipeCard.setCookTime(cursor.getInt(cursor.getColumnIndex(KEY_COOKTIME)));
        recipeCard.setDirections(cursor.getString(cursor.getColumnIndex(KEY_DIRECTIONS)));

        //get ingredients
        selectQuery = "SELECT * FROM " + TABLE_INGREDIENTS + " WHERE "
                + KEY_LOOKUPINGREDENTS + " = " + id;

        cursor = database.rawQuery(selectQuery, null);

        if(cursor != null)
            cursor.moveToFirst();

        assert cursor != null;
        for(int i = 0; i < cursor.getCount(); i++) {
            recipeCard.addIngredient(cursor.getString(cursor.getColumnIndex(KEY_INGREDIENT))
                    , cursor.getString(cursor.getColumnIndex(KEY_AMOUNTS)));
            cursor.moveToNext();
        }

        //get categories
        selectQuery = "SELECT * FROM " + TABLE_CATEGORIES + " WHERE "
                + KEY_LOOKUPCATEGORIES + " = " + id;

        cursor = database.rawQuery(selectQuery, null);

        if(cursor != null)
            cursor.moveToFirst();

        assert cursor != null;
        for(int i = 0; i < cursor.getCount(); i++) {
            recipeCard.addCategory(cursor.getString(cursor.getColumnIndex(KEY_CATEGORIES)));
            cursor.moveToNext();
        }

        database.close();
        return recipeCard;
    }

    /**
     * Will delete any RecipeCard in the database given the RecipeCard you want to delete.
     * @param recipeCard The RecipeCard that you want to delete must be passed in
     */
    public void deleteRecipeCard(RecipeCard recipeCard){
        SQLiteDatabase database = getWritableDatabase();
        database.delete(TABLE_RECIPES, KEY_ID + " =? ", new String[]
                {String.valueOf(recipeCard.getId())});

        //Delete ingredients
        database.delete(TABLE_INGREDIENTS, KEY_LOOKUPINGREDENTS + " =? ", new String[]
                {String.valueOf(recipeCard.getId())});

        //Delete categories
        database.delete(TABLE_CATEGORIES, KEY_LOOKUPCATEGORIES + " =? ", new String[]
                {String.valueOf(recipeCard.getId())});

        database.close();
    }

    /**
     * Will return the number of RecipeCards in the database
     * @return returns an int of the number of RecipesCards in the database
     */
    public int getRecipeCount(){
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM " + TABLE_RECIPES, null);
        int cursorCount = cursor.getCount();
        cursor.close();
        database.close();

        return cursorCount;
    }

    /**
     * Pass in a RecipeCard that you have made changes to and it will update itself in the database
     * @param recipeCard takes a RecipeCard
     */
    public void updateRecipe(RecipeCard recipeCard){
        SQLiteDatabase database = getWritableDatabase();

        this.deleteRecipeCard(recipeCard);
        this.createRecipe(recipeCard);

        database.close();
    }

    /**
     * This class will return a list of all RecipeCard objects in the database
     *
     * @return List of RecipeCards
     */
    public List<RecipeCard> getAllRecipeCards(){
        List<RecipeCard> recipeCards = new ArrayList<>();

        int cardCount = this.getRecipeCount();
        //this will iterate through the database adding all of it to a list of recipe cards
        for (int i = 1; i <= cardCount; i++){
            RecipeCard recipe = this.getRecipeCard(i);
            recipeCards.add(recipe);
        }

        return recipeCards;
    }

    /**
     *
     * @param recipeCard takes a RecipeCard object and adds its ingredients to the shopping list
     *                   table
     */
    public void addItemsToShoppingList(RecipeCard recipeCard){
        SQLiteDatabase database = getWritableDatabase();

        List<String> ingredientList = recipeCard.getIngredients();

        Cursor cursor = database.rawQuery("SELECT * FROM " + TABLE_SHOPPINGLIST, null);
        int cursorCount = cursor.getCount();
        cursor.close();

        for(int i = 0; i < ingredientList.size(); i++){
            boolean contains = false;
            for(int x = 1; x < cursorCount; x++){//checks to see if the ingredient is in the table
                String rowIngredient;
                String ingredientToAdd = ingredientList.get(i);
                String selectQuery = "SELECT * FROM " + TABLE_SHOPPINGLIST + " WHERE "
                        + KEY_ID + " = " + x;

                Cursor cursor2 = database.rawQuery(selectQuery, null);
                if (cursor2 != null)
                    cursor2.moveToFirst();
                assert cursor2 != null;
                rowIngredient = cursor2.getString(cursor2.getColumnIndex(KEY_ITEM));
                cursor2.close();

                contains = rowIngredient.equals(ingredientToAdd);
            }
            if(!contains){//adds ingredient if it was not in the table
                ContentValues values = new ContentValues();
                values.put(KEY_ITEM, ingredientList.get(i));
                //insert row
                database.insert(TABLE_SHOPPINGLIST, null, values);
            }
        }

        database.close();
    }
}