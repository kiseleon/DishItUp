package com.example.cs246.dishitup;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class Filter extends ActionBarActivity implements AdapterView.OnItemSelectedListener{

    Spinner STime;
    Spinner SIngredient;
    Spinner SCategory;

    String[] time = {"select time"};
    String[] ingredient = {"select ingredient"};
    String[] category = {"select category"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);


        STime = (Spinner) findViewById(R.id.STime);
        //ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.sort, android.R.layout.simple_spinner_item);
        ArrayAdapter<String> Tadapter = new ArrayAdapter <String> (this, android.R.layout.simple_spinner_item, time);
        STime.setAdapter(Tadapter);
        STime.setOnItemSelectedListener(this);



        SIngredient = (Spinner) findViewById(R.id.SortS);
        //ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.sort, android.R.layout.simple_spinner_item);
        ArrayAdapter <String> Iadapter = new ArrayAdapter <String> (this, android.R.layout.simple_spinner_item, ingredient);
        SIngredient.setAdapter(Iadapter);
        SIngredient.setOnItemSelectedListener(this);




        SCategory = (Spinner) findViewById(R.id.SortS);
        //ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.sort, android.R.layout.simple_spinner_item);
        ArrayAdapter <String> Cadapter = new ArrayAdapter <String> (this, android.R.layout.simple_spinner_item, category);
        SCategory.setAdapter(Cadapter);
        SCategory.setOnItemSelectedListener(this);
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
