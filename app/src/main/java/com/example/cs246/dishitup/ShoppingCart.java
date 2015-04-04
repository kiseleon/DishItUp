package com.example.cs246.dishitup;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class ShoppingCart extends ActionBarActivity {

    ListView shoppingCartList;
    DatabaseControl databaseControl;
    SQLiteDatabase database;
    List<Boolean> checkedList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);

        databaseControl = new DatabaseControl(getApplicationContext());

        shoppingCartList = (ListView) findViewById(R.id.shoppingListView);

        updateList();
    }

    /**
     * Updates the list view with the current shopping list
     */
    public void updateList() {
        database = databaseControl.getReadableDatabase();

        String query = "SELECT * FROM " + databaseControl.TABLE_SHOPPINGLIST + " ORDER BY " +
                databaseControl.KEY_ITEM + " ASC ;";

        String[] fromColumns = new String[] {databaseControl.KEY_SHOPINGID, databaseControl.KEY_ITEM};
        int[] toControlIDs = new int[] {R.id.listId, R.id.checkBox};

        Cursor cursor = database.rawQuery(query, null);

        shoppingCartList.setAdapter(new SimpleCursorAdapter(this, R.layout.shoppinglistitem, cursor, fromColumns, toControlIDs, 0));

        database.close();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_shoping_cart, menu);
        
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

    public List<String> getIngredientsToDelete () {
        List<String> ingList = new ArrayList<>();

        int numberOfItems = shoppingCartList.getChildCount();

        for (int i = 0; i < numberOfItems; i++) {
            LinearLayout row = (LinearLayout) shoppingCartList.getChildAt(i);
            CheckBox box = (CheckBox) row.findViewById(R.id.checkBox);
            if (box.isChecked()) {
                String ing = box.getText().toString();
                ingList.add(ing);
                Log.d("Getting ingredients", "Adding ingredient: " + ing );
            }
        }


        return ingList;
    }

    public void deleteSelected (View view) {
        List<String> ingredientsToDelete = getIngredientsToDelete();

        for (String ing : ingredientsToDelete) {
            databaseControl.deleteShoppingListItem(ing);
        }

        updateList();
    }

    public void mainMenu(View view) {
        finish();
    }
}
