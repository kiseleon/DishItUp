package com.example.cs246.dishitup;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import java.util.Arrays;


public class SortBy extends ActionBarActivity {
    Button mainMenu;
    Button Rating;
    Button NameAz;
    Button NameZa;
    Button TimeSl;
    Button TimeLs;
    Button BackToSearch;

    String[] strings = {" Hello ", " This ", "Is ", "Sorting ", "Example"};

    void sortTheString() {

        Arrays.sort(strings);

    }

    public String GetName(String TheName){
        return TheName;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sort_by);

        mainMenu = (Button) findViewById(R.id.mainMenu);
        Rating = (Button) findViewById(R.id.Rating);
        NameAz = (Button) findViewById(R.id.NameAZ);
        NameZa = (Button) findViewById(R.id.NameZA);
        TimeSl = (Button) findViewById(R.id.TimeSl);
        TimeLs = (Button) findViewById(R.id.TimeLs);
        BackToSearch = (Button) findViewById(R.id.BackToSearch);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sort_by, menu);
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
}
