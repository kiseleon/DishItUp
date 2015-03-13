package com.example.cs246.dishitup;

import java.util.Collections;
import java.util.List;

/**
 * Created by Kevin on 2/25/2015.
 */
public class Rolodex {

    private List<RecipeCard> recipeCardList;

    //this constructor uses our database to populate the rolodex list
    Rolodex(DatabaseControl databaseControl){
        recipeCardList = databaseControl.getAllRecipeCards();
    }

    public void sort (String sortBy, boolean descending) {
        switch(sortBy) {
            case "name":
                sortByName(descending);
                break;
            default:
                assert(false);
        }


    }

    private void sortByName(boolean descending) {

        boolean done = false;

        while (!done) {
            done = true;
            for (int i = 0; i < recipeCardList.size() - 1; ++i) {
                int result =  recipeCardList.get(i + 1).getName().compareToIgnoreCase(recipeCardList.get(i).getName());
                if ((result > 0 && !descending) || (result < 0 && descending)) {
                    done = false;
                    Collections.swap(recipeCardList, i, i + 1);
                }
            }
        }
    }

    public void update(DatabaseControl databaseControl) {
        recipeCardList = databaseControl.getAllRecipeCards();
    }
}
