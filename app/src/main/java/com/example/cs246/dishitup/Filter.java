package com.example.cs246.dishitup;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

/**
 * add or remove recipes form rolodex biased on user input
 *
 * @author Trenton
 */
public class Filter extends ActionBarActivity implements AdapterView.OnItemSelectedListener{

//    Spinner STime;


//    String[] time = {"1 min", "5 min", "10 min", "30 min", "60min", "no time limit"};

    public void searchTime(){
        Intent intent = new Intent(this, Search.class);
        EditText time = (EditText) findViewById(R.id.editTextTime);

        // this need to go through the database and get all the time that is =<

        startActivity(intent);
    }

    public void searchCategory(){
        Intent intent = new Intent(this, Search.class);
        EditText category = (EditText) findViewById(R.id.editTextCategory);

        // this needs to go through the database and get all the time that is ==

        startActivity(intent);
    }
     public void searchMainIngredient(){
         Intent intent = new Intent(this, Search.class);
         EditText ingredient = (EditText) findViewById(R.id.editTextIngredient);

         // this needs to go through the database and get all the ingredient that is ==

         startActivity(intent);
     }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);


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

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
