package com.example.cs246.dishitup;


import android.content.Intent;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import static android.support.v4.app.ActivityCompat.startActivity;

/**
 * Created by Kevin on 2/25/2015.
 */
public class RecipeCard {
    private String name;
    private int rating;
    private int id;
    private String comment;
    private String pictureRef;
    private int cookTime; // note: this is time in minutes
    private List<String> ingredients;
    private List<String> directions;
    private String category;

    private static final String RECIPE_CARD_TAG = "RecipeCard";

    RecipeCard() {
        name = null;
        rating = -1;
        id = -1;
        comment = null;
        pictureRef = null;
        cookTime = -1;
        ingredients = new ArrayList<>();
        directions = new ArrayList<>();
        category = null;

        Log.i(RECIPE_CARD_TAG, "Created a blank RecipeCard.");
    }



    public String getName() {return name;}
    public int getRating()  {return rating;}
    public int getId() {return id;}
    public String getComment() {return comment;}
    public String getPictureRef() {return pictureRef;}
    public int getCookTime() { return cookTime;} // gets it in minutes
    public List<String> getIngredients() {return ingredients;}
    public List<String> getDirections() {return directions;}
    public String getCategory()  {return category;}


    public void setName(String name) {this.name = name;}
    public void setRating(int rating) {this.rating = rating;}
    public void setId(int id) {this.id = id;}
    public void setComment(String comment) {this.comment = comment;}
    public void setPictureRef(String pictureRef) {this.pictureRef = pictureRef;}
    public void setCookTime(int time) { cookTime = time;}
    public void addIngredient(String ingredient) {ingredients.add(ingredient);}
    public void addDirection(String direction) {directions.add(direction);}
    public void setCategory(String category)  {this.category = category;}

}
