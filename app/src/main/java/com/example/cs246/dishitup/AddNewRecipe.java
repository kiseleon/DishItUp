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
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TooManyListenersException;

/**
 * A class for getting input from the user to create a recipe
 */
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
    EditText amount;
    ImageView picture;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_recipe);

        // get the passed-in recipe card
        Bundle b = getIntent().getExtras();
        recipeCard = b.getParcelable("RecipeCard");

        recipeDatabase = new DatabaseControl(getApplicationContext());

        name = (EditText)findViewById(R.id.editName);
        time = (EditText)findViewById(R.id.editTime);
        rating = (RatingBar)findViewById(R.id.editRating);
        amount = (EditText) findViewById(R.id.amountField);
        ingredients = (EditText)findViewById(R.id.ingredientField);
        instructions = (EditText)findViewById(R.id.editInstructions);
        comments = (EditText)findViewById(R.id.editComments);
        categories = (EditText)findViewById(R.id.categoriesField);
        picture = (ImageView) findViewById(R.id.recipePicture);

        Log.e("Received recipe", "name: " + recipeCard.getName());

        // if you got a filled out recipeCard passed to you, fill the appropriate parts of list
        if (recipeCard.getName() != null) {
            name.setText(recipeCard.getName());
            time.setText(Integer.toString(recipeCard.getCookTime()));
            rating.setRating(recipeCard.getRating());
            instructions.setText(recipeCard.getDirections());
            comments.setText(recipeCard.getComment());
            updateCategories();
            updateIngredients();
            // TODO: Make this set the picture
            picture.setImageResource(R.drawable.placeholder_image);
        }
    }

    /**
     * Adds the category from the category field to the RecipeCard
     * @param view The view calling the function
     */
    public void addCategory(View view) {
        // Add category to the recipe card
        recipeCard.addCategory(categories.getText().toString());

        // update list of categories
        updateCategories();
    }

    /**
     * Removes the category from the category field from the RecipeCard
     * @param view The view calling the function
     */
    public void removeCategory(View view) {
        // Remove category from recipe card
        recipeCard.removeCategory(categories.getText().toString());

        // update list of categories
        updateCategories();
    }

    private void updateCategories() {
        TextView categoryView = (TextView) findViewById(R.id.categoriesView);
        // retrieve categories
        Set<String> categorylist = recipeCard.getCategories();

        String output = "";

        // update the view
        for (String category : categorylist) {
            output += category + "\n";
        }

        categoryView.setText(output);
    }

    /**
     * Adds the ingredient and amount from their respective fields to the RecipeCard
     * @param view The view calling the function
     */
    public void addIngredient(View view) {
        // add the ingredient
        recipeCard.addIngredient(amount.getText().toString(), ingredients.getText().toString());

        // update the ingredient field
        updateIngredients();
    }

    /**
     * Removes the ingredient and amount from their respective fields from the RecipeCard
     * @param view takes a view as a parameter
     */
    public void removeIngredient(View view) {
        // remove the ingredient
        recipeCard.removeIngredient(ingredients.getText().toString());

        // update the ingredient field
        updateIngredients();
    }


    private void updateIngredients() {
        TextView ingredientView = (TextView) findViewById(R.id.ingredientsView);
        List<String> ingredientList = recipeCard.getIngredients();
        List<String> amountList = recipeCard.getAmounts();
        String output = "";

        for (int i = 0; i < ingredientList.size(); i++) {
            output += amountList.get(i) + " " + ingredientList.get(i) + "\n";
        }

        ingredientView.setText(output);
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

        recipeCard.setName(name.getText().toString());
        recipeCard.setCookTime(Integer.valueOf(time.getText().toString()));

        recipeCard.setRating((int) rating.getRating());

        recipeCard.setDirections(instructions.getText().toString());
        recipeCard.setComment(comments.getText().toString());

        // RecipeCard already has the ingredients/amounts/etc, so we don't need to add them here

        if(recipeCard.getName() == null || recipeCard.getName().equals("") ||
                recipeCard.getCookTime() < 1||
                recipeCard.getDirections() == null || recipeCard.getDirections().equals("")){
            Log.e("Empty Card", "You did not fill out the card");
            CharSequence text = ("The Recipe Card was not added you must include all " +
                "recipe information");
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            finish();
        }
        List<String> list = recipeCard.getIngredients();
        if(list.size() < 1){

            Log.e("Empty Card", "You did not fill out the card");
            CharSequence text = ("The Recipe Card was not added you must include all " +
                    "recipe information");
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            finish();

        }
        list = recipeCard.getAmounts();
        if(list.size() < 1){

            Log.e("Empty Card", "You did not fill out the card");
            CharSequence text = ("The Recipe Card was not added you must include all " +
                    "recipe information");
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            finish();
        }
        Set<String> categories = recipeCard.getCategories();
        if(categories.size() < 1){
            recipeCard.addCategory("None");
        }
        if (recipeCard.getComment().equals("") || recipeCard.getComment() == null){
            recipeCard.setComment("No Comment");
        }
        if (recipeCard.getRating() <= 0){
            recipeCard.setRating(0);
        }
        recipeDatabase.createRecipe(recipeCard);

        // if this is an existing recipe you are editing, delete the old version
        if (recipeCard.getId() != -1) {
            recipeDatabase.deleteRecipeCard(recipeCard);
        }
        Log.i("Recipe card added", "Recipe card added to the database");
        CharSequence text = ("The Recipe Card "+ recipeCard.getName() +" was added to your " +
                "recipes.");
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
        finish();
    }
}
