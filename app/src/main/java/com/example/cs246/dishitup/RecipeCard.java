package com.example.cs246.dishitup;


import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by Kevin on 2/25/2015.
 */
public class RecipeCard {
    private String name;
    private int rating;
    private int id;
    private String comment;
    private String pictureRef;
    private Time cookTime; // note: this requires the time in milliseconds
    private List<String> ingredients;
    private List<String> directions;
    private Set<String> categories;

    RecipeCard() {
        name = "Recipe Name";
        rating = 100;
        id = 1;
        comment = "No comment.";
        pictureRef = null;
        cookTime.setTime(5400000);
        ingredients = new ArrayList<>();
        directions = new ArrayList<>();
        categories.add("All"); // everything is part of the All set
    }

    RecipeCard(int time) {
        name = "Recipe Name with time";
        rating = 100;
        id = 1;
        comment = "No comment at this time.";
        pictureRef = null;
        cookTime.setTime(time);
        ingredients = new ArrayList<>();
        directions = new ArrayList<>();
        categories.add("All"); // everything is part of the All set
        categories.add("Timed");
    }


    public String getName() {return name;}
    public int getRating()  {return rating;}
    public int getId() {return id;}
    public String getComment() {return comment;}
    public String getPictureRef() {return pictureRef;}
    public int getCookTime() { return (int) cookTime.getTime() / 60000;} // gets it in minutes
    public List<String> getIngredients() {return ingredients;}
    public List<String> getDirections() {return directions;}
    public Set<String> getCategories()  {return categories;}

    public void setName(String name) {this.name = name;}
    public void setRating(int rating) {this.rating = rating;}
    public void setId(int id) {this.id = id;}
    public void setComment(String comment) {this.comment = comment;}
    public void setPictureRef(String pictureRef) {this.pictureRef = pictureRef;}
    public void setCookTime(int time) {cookTime.setTime(time * 60000);} //
    public void addIngredient(String ingredient) {ingredients.add(ingredient);}
    public void addDirection(String direction) {directions.add(direction);}
    public void addCategory(String category)  {categories.add(category);}
}
