package com.example.cs246.dishitup;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.app.Activity;
import android.os.Environment;
import android.provider.MediaStore;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TooManyListenersException;

// take a photo
// code was based on this website
//http://www.c-sharpcorner.com/UploadFile/e14021/capture-image-from-camera-and-selecting-image-from-gallery-o/


/**
 * A class for getting input from the user to create a recipe
 */
public class AddNewRecipe extends ActionBarActivity {

    RecipeCard recipeCard;
    DatabaseControl recipeDatabase;
    EditText name;
    EditText time;
    RatingBar rating;
    EditText ingredients;
    EditText instructions;
    EditText comments;
    EditText categories;
    EditText amount;
//trenton added this
    ImageView recipePicture;
    Button addImageButton;
    ImageView picture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_recipe);

        // get the passed-in recipe card
        Bundle b = getIntent().getExtras();
        recipeCard = b.getParcelable("RecipeCard");

        recipeDatabase = new DatabaseControl(getApplicationContext());
        name = (EditText)findViewById(R.id.editName);
        time = (EditText)findViewById(R.id.editTime);
        rating = (RatingBar)findViewById(R.id.editRating);
        amount = (EditText) findViewById(R.id.amountField);
        ingredients = (EditText)findViewById(R.id.ingredientField);
        instructions = (EditText)findViewById(R.id.editInstructions);
        comments = (EditText)findViewById(R.id.editComments);
        categories = (EditText)findViewById(R.id.categoriesField);
        picture = (ImageView) findViewById(R.id.recipePicture);

        Log.e("Received recipe", "name: " + recipeCard.getName());

        // if you got a filled out recipeCard passed to you, fill the appropriate parts of list
        if (recipeCard.getName() != null) {
            name.setText(recipeCard.getName());
            time.setText(Integer.toString(recipeCard.getCookTime()));
            rating.setRating(recipeCard.getRating());
            instructions.setText(recipeCard.getDirections());
            comments.setText(recipeCard.getComment());
            updateCategories();
            updateIngredients();
            // TODO: Make this set the picture reference
            picture.setImageResource(R.drawable.placeholder_image);
        }
    }








    private void selectImage() {

        final CharSequence[] options = { "Take Photo", "Choose from Gallery","Cancel" };

        AlertDialog.Builder builder = new AlertDialog.Builder(AddNewRecipe.this);
        builder.setTitle("Add Photo!");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (options[item].equals("Take Photo"))
                {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    File f = new File(android.os.Environment.getExternalStorageDirectory(), "temp.jpg");
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
                    startActivityForResult(intent, 1);
                }
                else if (options[item].equals("Choose from Gallery"))
                {
                    Intent intent = new   Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(intent, 2);

                }
                else if (options[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
    //if they hit take photo
            if (requestCode == 1) {
                File f = new File(Environment.getExternalStorageDirectory().toString());
                for (File temp : f.listFiles()) {
                    if (temp.getName().equals("temp.jpg")) {
                        f = temp;
                        break;
                    }
                }
                try {
                    Bitmap bitmap;
                    BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();

                    bitmap = BitmapFactory.decodeFile(f.getAbsolutePath(),
                            bitmapOptions);

                    recipePicture.setImageBitmap(bitmap);

                    String path = android.os.Environment
                            .getExternalStorageDirectory()
                            + File.separator
                            + "Phoenix" + File.separator + "default";
                    f.delete();
                    OutputStream outFile = null;
                    File file = new File(path, String.valueOf(System.currentTimeMillis()) + ".jpg");

                    //need to get the string path here and pass it in... no idea how yet
                    // this is static setPickref is not == problem
                    //RecipeCard.setPictureRef(String.valueOf(System.currentTimeMillis()) + ".jpg");
                   // RecipeCard.setPictureRef(file.getName());


                    try {
                        outFile = new FileOutputStream(file);
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 85, outFile);
                        outFile.flush();
                        outFile.close();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
    // if they hit galorie
            } else if (requestCode == 2) {

                Uri selectedImage = data.getData();
                String[] filePath = { MediaStore.Images.Media.DATA };
                Cursor c = getContentResolver().query(selectedImage,filePath, null, null, null);
                c.moveToFirst();
                int columnIndex = c.getColumnIndex(filePath[0]);
                String picturePath = c.getString(columnIndex);

                //this is what i added the problem is picturePath is static...
                //RecipeCard.setPictureRef(picturePath);

                c.close();
                Bitmap thumbnail = (BitmapFactory.decodeFile(picturePath));
                Log.w("path of image from gallery......******************.........", picturePath+"");
                recipePicture.setImageBitmap(thumbnail);
            }
        }
    }







    /**
     * Adds the category from the category field to the RecipeCard
     * @param view The view calling the function
     */
    public void addCategory(View view) {
        // Add category to the recipe card
        recipeCard.addCategory(categories.getText().toString());

        // update list of categories
        updateCategories();
    }

    /**
     * Removes the category from the category field from the RecipeCard
     * @param view The view calling the function
     */
    public void removeCategory(View view) {
        // Remove category from recipe card
        recipeCard.removeCategory(categories.getText().toString());

        // update list of categories
        updateCategories();
    }

    private void updateCategories() {
        TextView categoryView = (TextView) findViewById(R.id.categoriesView);
        // retrieve categories
        Set<String> categoryList = recipeCard.getCategories();

        String output = "";

        // update the view
        for (String category : categoryList) {
            output += category + "\n";
        }

        categoryView.setText(output);
    }

    /**
     * Adds the ingredient and amount from their respective fields to the RecipeCard
     * @param view The view calling the function
     */
    public void addIngredient(View view) {
        // add the ingredient
        recipeCard.addIngredient(amount.getText().toString(), ingredients.getText().toString());

        // update the ingredient field
        updateIngredients();
    }

    /**
     * Removes the ingredient and amount from their respective fields from the RecipeCard
     * @param view takes a view as a parameter
     */
    public void removeIngredient(View view) {
        // remove the ingredient
        recipeCard.removeIngredient(ingredients.getText().toString());

        // update the ingredient field
        updateIngredients();
    }


    private void updateIngredients() {
        TextView ingredientView = (TextView) findViewById(R.id.ingredientsView);
        List<String> ingredientList = recipeCard.getIngredients();
        List<String> amountList = recipeCard.getAmounts();
        String output = "";

        for (int i = 0; i < ingredientList.size(); i++) {
            output += amountList.get(i) + " " + ingredientList.get(i) + "\n";
        }

        ingredientView.setText(output);
    }

private void addImage(){

}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_new_recipe, menu);
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

    private boolean areFieldsFilled() {
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_LONG;

        if (name.getText().toString().equals("")) {
            String text = "Please fill in the recipe name before continuing.";
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            name.requestFocus();
            return false;
        } else if (time.getText().toString().equals("")) {
            String text = "Please fill in the cooking time before continuing.";
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            time.requestFocus();
            return false;
        } else if (instructions.getText().toString().equals("")) {
            String text = "Please fill in the instructions before continuing.";
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            instructions.requestFocus();
            return false;
        } else if (recipeCard.getIngredients().size() == 0) {
            String text = "Please fill in the ingredients before continuing.";
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            ingredients.requestFocus();
            return false;
        } else if (recipeCard.getCategories().size() == 0) {
            String text = "Please fill in the categories before continuing.";
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            categories.requestFocus();
            return false;
        } else {
            return true;
        }
    }

    public void addRecipeToDatabase(View view) {
        Log.i("Add Recipe Card", "Starting the add recipe to Database method");

        if (areFieldsFilled()) {
            Log.e("Add Recipe Card", "Fields are filled, adding recipe...");

            Context context = getApplicationContext();
            int duration = Toast.LENGTH_LONG;

            recipeCard.setName(name.getText().toString());
            recipeCard.setCookTime(Integer.valueOf(time.getText().toString()));
            recipeCard.setRating((int) rating.getRating());
            recipeCard.setDirections(instructions.getText().toString());
            recipeCard.setComment(comments.getText().toString());

            // RecipeCard already has the ingredients/amounts/etc, so we don't need to add them here

            List<String> list = recipeCard.getIngredients();

            list = recipeCard.getAmounts();

            Set<String> categories = recipeCard.getCategories();
            if(categories.size() < 1){
                recipeCard.addCategory("None");
            }
            if (recipeCard.getComment().equals("") || recipeCard.getComment() == null){
                recipeCard.setComment("No Comment");
            }
            if (recipeCard.getRating() <= 0){
                recipeCard.setRating(0);
            }
            recipeDatabase.createRecipe(recipeCard);

            // if this is an existing recipe you are editing, delete the old version
            if (recipeCard.getId() != -1) {
                recipeDatabase.deleteRecipeCard(recipeCard);
            }
            Log.i("Recipe card added", "Recipe card added to the database");
            CharSequence text = ("The Recipe Card "+ recipeCard.getName() +" was added to your " +
                    "recipes.");
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            finish();

        }
        list = recipeCard.getAmounts();
        if(list.size() < 1){

            Log.e("Empty Card", "You did not fill out the card");
            CharSequence text = ("The Recipe Card was not added you must include all " +
                    "recipe information");
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            finish();
        }
        Set<String> categories = recipeCard.getCategories();
        if(categories.size() < 1){
            recipeCard.addCategory("None");
        }
        if (recipeCard.getComment().equals("") || recipeCard.getComment() == null){
            recipeCard.setComment("No Comment");
        }
        if (recipeCard.getRating() <= 0){
            recipeCard.setRating(0);
        }
        recipeDatabase.createRecipe(recipeCard);
        Log.i("Recipe card added", "Recipe card added to the database");
        CharSequence text = ("The Recipe Card "+ recipeCard.getName() +" was added to your " +
                "recipes.");
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
        finish();
    }
}
