package com.example.cs246.dishitup;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import java.util.TooManyListenersException;


public class AddNewRecipe extends ActionBarActivity {

    RecipeCard recipeCard;
    DatabaseControl recipeDatabase;
    EditText name;
    EditText time;
    RatingBar rating;
    EditText ingredients;
    EditText instructions;
    EditText comments;
    EditText categories;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_recipe);
        recipeDatabase = new DatabaseControl(getApplicationContext());
        name = (EditText)findViewById(R.id.editName);
        time = (EditText)findViewById(R.id.editTime);
        rating = (RatingBar)findViewById(R.id.editRating);
        ingredients = (EditText)findViewById(R.id.editIngredients);
        instructions = (EditText)findViewById(R.id.editInstructions);
        comments = (EditText)findViewById(R.id.editComments);
        categories = (EditText)findViewById(R.id.editCategories);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_new_recipe, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void addRecipeToDatabase(View view) {
        Log.i("Add Recipe Card", "Starting the add recipe to Database method");

        Context context = getApplicationContext();
        int duration = Toast.LENGTH_LONG;

        recipeCard = new RecipeCard();
        recipeCard.setName(name.getText().toString());
        recipeCard.setCookTime(Integer.valueOf(time.getText().toString()));
        recipeCard.addIngredient(ingredients.getText().toString());
        recipeCard.addDirection(instructions.getText().toString());
        recipeCard.setComment(comments.getText().toString());
        recipeCard.setCategory(categories.getText().toString());


        if(recipeCard.getName() == null || recipeCard.getName().equals("") ||// this is not working we need to verify that we have been given all the information that we need.
                recipeCard.getCookTime() < 0 || recipeCard.getIngredients() == null ||
                recipeCard.getDirections() == null){
            Log.e("Empty Card", "You did not fill out the card");
            CharSequence text = ("The Recipe Card was not added you must include all " +
                "recipe information");
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            finish();
        }
        recipeDatabase.createRecipe(recipeCard);
        Log.i("Recipe card added", "Recipe card added to the database");
        CharSequence text = ("The Recipe Card "+ recipeCard.getName() +" was added to your " +
                "recipe rolodex.");
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
        finish();
    }
}
