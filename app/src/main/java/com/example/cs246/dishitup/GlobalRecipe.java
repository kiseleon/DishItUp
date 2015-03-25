package com.example.cs246.dishitup;

/**
 * This is used to pass a recipeCard between activities.
 *
 * Created by Kevin Johnson on 3/25/2015.
 */
public class GlobalRecipe {
    private static GlobalRecipe instance = new GlobalRecipe();
    public static RecipeCard recipeCard = new RecipeCard();

    public static GlobalRecipe getInstance() {
        return instance;
    }



    private GlobalRecipe() {;}


}
