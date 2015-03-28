package com.example.cs246.dishitup;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

//this is what casey did
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * add or remove recipes form rolodex biased on user input
 *
 * @author Trenton
 */
public class Filter extends ActionBarActivity implements AdapterView.OnItemSelectedListener{

    Button BTime;
    Button BIngredient;
    Button BCategory;

    EditText editTextTime;
    EditText editTextIngredient;
    EditText editTextCategory;

    public void searchTime(SQLiteDatabase database){
        Intent intent = new Intent(this, Search.class);
        EditText time = (EditText) findViewById(R.id.editTextTime);

        String message = time.getText().toString();

        // this need to go through the database and get all the time that is =<
        // the vatrialbe userInputTime is to select the time and below

        database.execSQL("SELECT KEY_COOKTIME FROM recipeCardManager.TABLE_RECIPES WHERE KEY_COOKTIME = " + message); //ORDER BY KEY_COOKTIME >= userInputTime");

        startActivity(intent);
    }

    public void searchCategory(SQLiteDatabase database){
        Intent intent = new Intent(this, Search.class);
        EditText category = (EditText) findViewById(R.id.editTextCategory);

        String message = category.getText().toString();
      //  intent.putExtra(EXTRA_MESSAGE, message);

        // this needs to go through the database and get all the time that is ==
        database.execSQL("SELECT KEY_CATEGORIES FROM recipeCardManager.TABLE_CATEGORIES WHERE  KEY_CATEGORIES = " +  message);
        startActivity(intent);
    }
     public void searchIngredient(SQLiteDatabase database){
         Intent intent = new Intent(this, Search.class);
         EditText ingredient = (EditText) findViewById(R.id.editTextIngredient);

         String message = ingredient.getText().toString();

         // this needs to go through the database and get all the ingredient that is ==
         database.execSQL("SELECT KEY_INGREDIENTS FROM recipeCardManager.TABLE_INGREDIENTS WHERE KEY_INGREDIENTS = " + message);

         startActivity(intent);
     }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);



         BTime = (Button) findViewById(R.id.BTime);
         BIngredient = (Button) findViewById(R.id.BIngredient);
         BCategory = (Button) findViewById(R.id.BCategory);

         editTextTime = (EditText)findViewById(R.id.editTextTime);
         editTextIngredient = (EditText)findViewById(R.id.editTextIngredient);
         editTextCategory = (EditText)findViewById(R.id.editTextCategory);


//        STime = (Spinner) findViewById(R.id.STime);
//        //ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.sort, android.R.layout.simple_spinner_item);
//        ArrayAdapter<String> Tadapter = new ArrayAdapter <String> (this, android.R.layout.simple_spinner_item, time);
//        STime.setAdapter(Tadapter);
//        STime.setOnItemSelectedListener(this);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_filter, menu);
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
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        TextView myText = (TextView) view;
        Toast.makeText(this, "you selected " + myText.getText(), Toast.LENGTH_SHORT).show();
    }

    public void goToMenu(View view) {
        finish();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
