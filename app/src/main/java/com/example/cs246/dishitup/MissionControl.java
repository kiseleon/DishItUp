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

    public boolean create(){
        return true;
    }
    public boolean remove(){
        return true;
    }
    public void sort(){ }

    public RecipeCard searchByName(String name){
        RecipeCard recipe;
        recipe = new RecipeCard();

        return recipe;
    }
}
