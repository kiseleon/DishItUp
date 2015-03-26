package com.example.cs246.dishitup;

import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.text.style.TextAppearanceSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;


public class Recipe extends ActionBarActivity {
    RecipeCard recipe;


    TextView recipeName; // Also holds the cook time
    ImageView recipeImage;
    RatingBar recipeRating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        Bundle b = getIntent().getExtras();
        recipe = b.getParcelable("RecipeCard");

        recipeName = (TextView) findViewById(R.id.recipeName);
        recipeImage = (ImageView)   findViewById(R.id.recipeImage);
        recipeRating = (RatingBar) findViewById(R.id.ratingBar);

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

        //TODO: Finish displaying the recipe
        // set the cook time - //TODO: Why isn't this in the layout?

        // set the instructions

        //


        // Set the image
        int imageResource = getResources().getIdentifier(recipe.getPictureRef(), null, getPackageName());
        Drawable res = getResources().getDrawable(imageResource);
        recipeImage.setImageDrawable(res);




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
}
