package com.example.cs246.dishitup;


import android.content.Intent;

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
    private Set<String> categories;

    RecipeCard() {
        name = null;
        rating = -1;
        id = -1;
        comment = null;
        pictureRef = null;
        cookTime = -1;
        ingredients = new ArrayList<>();
        directions = new ArrayList<>();
        categories = new TreeSet<>();
        categories.add("all"); // everything is part of the All set
    }



    public String getName() {return name;}
    public int getRating()  {return rating;}
    public int getId() {return id;}
    public String getComment() {return comment;}
    public String getPictureRef() {return pictureRef;}
    public int getCookTime() { return cookTime;} // gets it in minutes
    public List<String> getIngredients() {return ingredients;}
    public List<String> getDirections() {return directions;}
    public Set<String> getCategories()  {return categories;}

    public boolean hasCategory(String category) {return categories.contains(category);}

    public void setName(String name) {this.name = name;}
    public void setRating(int rating) {this.rating = rating;}
    public void setId(int id) {this.id = id;}
    public void setComment(String comment) {this.comment = comment;}
    public void setPictureRef(String pictureRef) {this.pictureRef = pictureRef;}
    public void setCookTime(int time) { cookTime = time;}
    public void addIngredient(String ingredient) {ingredients.add(ingredient);}
    public void addDirection(String direction) {directions.add(direction);}
    public void addCategory(String category)  {categories.add(category);}
    public void removeCategory(String category) {
        if (hasCategory(category)) {
            categories.remove(category);
        }
    }
}
