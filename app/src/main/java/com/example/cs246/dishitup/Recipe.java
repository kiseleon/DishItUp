package com.example.cs246.dishitup;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;


public class Recipe extends ActionBarActivity {
    RecipeCard recipe;


    TextView recipeName; // Also holds the cook time
    ImageView recipeImage;
    RatingBar recipeRating;
    TextView recipeIngredients;
    TextView recipeDirections;
    TextView recipeComment;
    TextView recipeCategories;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        Bundle b = getIntent().getExtras();
        recipe = b.getParcelable("RecipeCard");

        recipeName = (TextView) findViewById(R.id.recipeName);
        recipeImage = (ImageView)   findViewById(R.id.recipeImage);
        recipeRating = (RatingBar) findViewById(R.id.ratingBar);
        recipeIngredients = (TextView) findViewById(R.id.recipeIngredients);
        recipeDirections = (TextView) findViewById(R.id.recipeDirections);
        recipeComment = (TextView) findViewById(R.id.recipeComment);
        recipeCategories = (TextView) findViewById(R.id.recipeCategories);


        // set the name and cooktime using spannable strings
        // this enables multiple sizes/colors/etc of text within a single TextView
        SpannableStringBuilder builder = new SpannableStringBuilder();

        String name = recipe.getName();
        SpannableString spannableName = new SpannableString(name);
        spannableName.setSpan(new StyleSpan(Typeface.BOLD), 0, name.length(), 0);
        builder.append(spannableName);

        String time = "\n(" + Integer.toString(recipe.getCookTime()) + " minutes)";
        SpannableString spannableTime = new SpannableString(time);
        spannableTime.setSpan(new ForegroundColorSpan(Color.GRAY), 0, time.length(), 0);
        spannableTime.setSpan(new StyleSpan(Typeface.ITALIC), 0, time.length(), 0);
        builder.append(spannableTime);

        recipeName.setText(builder, TextView.BufferType.SPANNABLE);


        // set the rating
        recipeRating.setRating(recipe.getRating());

        // set the ingredients
        builder.clear();

        String ingredientsTitle = "Ingredients:";
        SpannableString spannableIngredientsTitle = new SpannableString(ingredientsTitle);
        spannableIngredientsTitle.setSpan(new StyleSpan(Typeface.BOLD_ITALIC), 0, ingredientsTitle.length(), 0);

        builder.append(spannableIngredientsTitle);
        builder.append("\n" + recipe.getIngredientListAsString());

        recipeIngredients.setText(builder, TextView.BufferType.SPANNABLE);

        // set the instructions
        builder.clear();

        String directionsTitle = "Directions:";
        SpannableString spannableDirectionsTitle = new SpannableString(directionsTitle);
        spannableDirectionsTitle.setSpan(new StyleSpan(Typeface.BOLD_ITALIC), 0, directionsTitle.length(), 0);

        builder.append(spannableDirectionsTitle);
        builder.append("\n" + recipe.getDirections());

        recipeDirections.setText(builder, TextView.BufferType.SPANNABLE);

        // Set the image
        int imageResource = getResources().getIdentifier(recipe.getPictureRef(), null, getPackageName());
        Drawable res = getResources().getDrawable(imageResource);
        recipeImage.setImageDrawable(res);

        // set the comment
        builder.clear();

        String commentTitle = "Comment:";
        SpannableString spannableCommentTitle = new SpannableString(commentTitle);
        spannableCommentTitle.setSpan(new StyleSpan(Typeface.BOLD_ITALIC), 0, commentTitle.length(), 0);

        builder.append(spannableCommentTitle);
        builder.append("\n" + recipe.getComment());

        recipeComment.setText(builder, TextView.BufferType.SPANNABLE);

        // set the categories
        builder.clear();

        String categoriesTitle = "Categories:";
        SpannableString spannableCategoriesTitle = new SpannableString(categoriesTitle);
        spannableCategoriesTitle.setSpan(new StyleSpan(Typeface.BOLD_ITALIC), 0, categoriesTitle.length(), 0);

        builder.append(spannableCategoriesTitle);

        // get the list of categories
        String categorylist = "";

        for (String s : recipe.getCategories()) {
            categorylist += "\n" + s;
        }

        builder.append(categorylist);

        recipeCategories.setText(builder, TextView.BufferType.SPANNABLE);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_recipe, menu);
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

    public void goBack(View view)  {
        finish();
    }

    public void deleteCard(View view) {
        DatabaseControl recipeDatabase = new DatabaseControl(getApplicationContext());
        recipeDatabase.deleteRecipeCard(recipe);
        goBack(view);
    }

    public void editRecipe(View view) {
        // Create the intent
        Intent intent = new Intent(Recipe.this, AddNewRecipe.class);
        intent.putExtra("RecipeCard", recipe);

        // start the new activity
        startActivity(intent);
    }
}
