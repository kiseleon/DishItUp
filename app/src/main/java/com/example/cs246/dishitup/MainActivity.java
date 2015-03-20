package com.example.cs246.dishitup;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

/**
 * allows you to navigate the app, creates the database
 *
 * @author Trenton Kevin Jason
 */
public class MainActivity extends ActionBarActivity {

    DatabaseControl recipeDatabase;
    //MissionControl missionControl;
    ///Rolodex rolodex;            //these are the three parts we need to get our recipe cards from the database into the Rolodex

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recipeDatabase = new DatabaseControl(getApplicationContext());  //this gets or database of recipeCards
        //rolodex = new Rolodex(recipeDatabase); // this makes a rolodex with all of our existing recipeCards
        //missionControl = new MissionControl(rolodex); // makes our mission control object with a populated rolodex

        //recipeDatabase.deleteRecipeCard( );// the recipeCare that you would like to delete as a peramiter
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1)//if an activity returns a 1 the Rolodex will update.
        {
            // rolodex.update(recipeDatabase);
        }
    }

    public void launchSearch(View view) {
        Intent intent = new Intent(MainActivity.this, Search.class);
        startActivity(intent);
    }

    public void launchFavorites(View view) {
        Intent intent = new Intent(MainActivity.this, Favorites.class);
        startActivity(intent);
    }

    public void launchRecentlyAdded(View view) {
        Intent intent = new Intent(MainActivity.this, RecentlyAdded.class);
        startActivity(intent);
    }


    public void launchRecentlyViewed(View view) {
        Intent intent = new Intent(MainActivity.this, RecentlyViewed.class);
        startActivity(intent);
    }

    public void launchAddNewRecipe(View view) {
        Intent intent = new Intent(MainActivity.this, AddNewRecipe.class);
        startActivity(intent);
        //rolodex.update(recipeDatabase);
    }
}
