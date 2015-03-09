package com.example.cs246.dishitup;

/**
 * Created by Kevin on 2/25/2015.
 */
public class MissionControl {

    Rolodex rolodex;

    MissionControl(Rolodex rolodex){
        this.rolodex = rolodex;
    }

    public Rolodex getRolodex() {return rolodex;}

    public boolean createRecipeCard(){
        return true;
    } // when we implement this we need to pass a message to rolodex informing it that it needs to add a RecipeCard to the database
    public boolean removeRecipeCard(){
        return true;
    }
    public void sortCards(){ }

    public RecipeCard searchByName(String name){
        RecipeCard recipe;
        recipe = new RecipeCard();

        return recipe;
    }

}
