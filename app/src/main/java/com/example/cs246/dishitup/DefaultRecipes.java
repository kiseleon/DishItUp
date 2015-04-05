package com.example.cs246.dishitup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jason on 4/1/2015.
 *
 * This class will build a list of recipes to use as default recipes.
 */
public class DefaultRecipes {

    private List<RecipeCard> recipeCards = new ArrayList<>();

    public List<RecipeCard> getDefaultCards(){return recipeCards;}

    //this constructor builds all the recipes and adds them to the list of recipes
    DefaultRecipes(){
        RecipeCard recipe = new RecipeCard();
        recipe.setName("One Pan Mexican Quinoa");
        recipe.setComment("None");
        recipe.setCookTime(30);
        recipe.setDirections("Heat oil on medium" +
                "\nAdd garlic and sautee until fragrant, about 1 minute." +
                "\nAdd remaining ingredients and bring to a boil." +
                "\nReduce heat and cover." +
                "\nSimmer for 20-25 minutes or until liquid is absorbed and quinoa is fully" +
                " cooked." +
                "\nRemove from heat and fluff." +
                "\nAdd desired toppings\n");
        recipe.setRating(4);
        recipe.addIngredient("1 Tbsp", "Olive Oil");
        recipe.addIngredient("2 Cloves", "Garlic, Minced");
        recipe.addIngredient("1 Cup", "Quinoa, Rinsed");
        recipe.addIngredient("1 1/4 Cup", "Chicken Broth");
        recipe.addIngredient("1 Can", "Black Beans, Drained");
        recipe.addIngredient("1 Can", "Tomatoes, Diced");
        recipe.addIngredient("1 1/2 Cup", "Corn");
        recipe.addIngredient("1 1/2 tsp", "Cumin");
        recipe.addIngredient("To Taste", "Salt");
        recipe.addCategory("Dinners");
        recipeCards.add(recipe);

        recipe = new RecipeCard();
        recipe.setName("Lemon Cheesecake");
        recipe.setComment("So Good!");
        recipe.setCookTime(600);
        recipe.setDirections("Preheat oven to 325 degrees." +
                "\nPrepare spring form pan by wrapping the outside in aluminum foil." +
                "\nMake graham cracker crust in spring form pan." +
                "\nWhen complete, place spring form pan in a large casserole dish" +
                "\nTo make cheesecake, beat cream cheese and sugar until smooth." +
                "\nAdd sour cream and mix until combined." +
                "\nAdd eggs one at a time, beating until just combined." +
                "\nStir in lemon juice and zest." +
                "\nPour over graham cracker crust in spring form pan." +
                "\nAdd watter to the casserole dish until it reaches halfway up the spring form" +
                " pan." +
                "\nBake cheesecake for 45 to 50 minutes, or until outside is set but center " +
                "remains jiggly." +
                "\nRemove from the oven and leave in the water bath for one hour." +
                "\nRemove from water bath and cover with plastic wrap." +
                "\nChill for at least 8 hours or up to 24 hours before serving.\n");
        recipe.setRating(5);
        recipe.addIngredient("1", "Graham Cracker Crust Recipe");
        recipe.addIngredient("3 8oz packages", "Cream Cheese");
        recipe.addIngredient("1 Cup", "Sugar");
        recipe.addIngredient("1 Cup", "Sour Cream");
        recipe.addIngredient("3", "Eggs");
        recipe.addIngredient("1 lemon", "Zest and Juice");
        recipe.addCategory("Desserts");

        recipe = new RecipeCard();
        recipe.setName("German Pancakes");
        recipe.setComment("Great with applesauce");
        recipe.setCookTime(25);
        recipe.setDirections("Preheat oven to 400 degrees." +
                "\nPlace butter in a 9x13 backing dish and place in heating oven until melted." +
                "\nMeanwhile, beat eggs, milk, flour, and salt until combined and smooth." +
                "\nPour over melted butter and bake for 20 minutes or until set and puffy." +
                "\nTop with fruit, jam, syrup, or other favorite toppings and serve.\n");
        recipe.setRating(4);
        recipe.addIngredient("3 Tbsp", "Butter");
        recipe.addIngredient("6", "Eggs");
        recipe.addIngredient("1 Cup", "Milk");
        recipe.addIngredient("1 Cup", "Flour");
        recipe.addIngredient("1/2 tsp", "Salt");
        recipe.addCategory("Breakfast");
        recipeCards.add(recipe);

        recipe = new RecipeCard();
        recipe.setName("Alfredo Sauce");
        recipe.setComment("No Comment");
        recipe.setCookTime(10);
        recipe.setDirections("Melt butter in sauce pan over medium heat." +
                "\nAdd garlic and sautee until fragrant, about 1 minute." +
                "\nWhisk in flour and let cook 1-2 minutes." +
                "\nWhisk in cream slowly until flour mixture is completely dissolved." +
                "\nContinue to cook, stirring frequently, until mixture thickens." +
                "\nRemove form heat and add cheeses, stirring until completely melted." +
                "\nAdd salt and pepper." +
                "\nServe over plain pasta or pasta with chicken.\n");
        recipe.setRating(2);
        recipe.addIngredient("2 Tbsp", "Butter");
        recipe.addIngredient("2 Cloves", "Garlic");
        recipe.addIngredient("3 Tbsp", "Flour");
        recipe.addIngredient("2 Cups", "Heavy Cream");
        recipe.addIngredient("1 Cup", "Mozzarella Cheese");
        recipe.addIngredient("1/2 Cup", "Parmesan Cheese");
        recipe.addIngredient("To Taste", "Salt/Pepper");
        recipe.addCategory("Dinners");
        recipe.addCategory("Sauces");
        recipeCards.add(recipe);

        recipe = new RecipeCard();
        recipe.setName("Copycat Wingers Sticky Fingers");
        recipe.setComment("No Comment");
        recipe.setCookTime(20);
        recipe.setDirections("Preheat oven to 425 and grease a baking sheet with cooking spray." +
                "\nSlice chicken breasts into strips and add with flour to a large ziploc bag. " +
                "Seal and toss to coat chicken in flour." +
                "\nPlace panko crumbs in a bowl. In another bowl, whisk together eggs and 2 " +
                "tablespoons of water. Dip flour coated chicken in egg mixture, then toss in " +
                "panko crumbs to coat." +
                "\nPlace coated chicken pieces on greased baking sheet and spray with cooking " +
                "spray or drizzle with oil or melted butter. Bake 15-20 minutes until chicken is" +
                " browned and cooked through." +
                "\nWhile chicken is cooking, prepare sauce by adding brown sugar and hot sauce to" +
                " a medium sauce pan. Stir over medium-high heat until melted and mixture comes " +
                "to a boil. Remove from heat and stir in garlic powder and water until smooth." +
                "\nWhen chicken strips are done cooking, toss them in the sauce. Serve warm with" +
                " ranch or blue cheese dressing.\n");
        recipe.setRating(4);
        recipe.addIngredient("3-4", "chicken breasts, pounded to 1/2 inch thickness");
        recipe.addIngredient("1/2 cup", "flour");
        recipe.addIngredient("3", "eggs");
        recipe.addIngredient("2 T", "water");
        recipe.addIngredient("1 cup", "panko bread crumbs");
        recipe.addIngredient("1 1/2 cup", "brown sugar");
        recipe.addIngredient("1/3 cup", "Frank's hot sauce (original or buffalo flavor)");
        recipe.addIngredient("1/2 t", "garlic powder");
        recipe.addIngredient("2 T", "more water");
        recipe.addCategory("Dinners");
        recipe.addCategory("Chicken");
        recipeCards.add(recipe);

        recipe = new RecipeCard();
        recipe.setName("Garlic Parmesan Roasted Broccoli");
        recipe.setComment("No Comment");
        recipe.setCookTime(10);
        recipe.setDirections("Preheat oven to 425 and lightly oil a baking sheet or coat with " +
                "nonstick spray." +
                "\nPlace broccoli florets in a single layer onto the prepared baking sheet." +
                " Add olive oil and garlic; season with salt and pepper to taste. Gently toss" +
                " to combine." +
                "\nBake for 10-12 minutes, or until tender." +
                "\nSprinkle with parmesan cheese and drizzle with lemon juice. " +
                "\nServe immediately.\n");
        recipe.setRating(2);
        recipe.addIngredient("5 cups", "broccoli florets");
        recipe.addIngredient("3 T", "olive oil");
        recipe.addIngredient("4 cloves", "garlic, minced");
        recipe.addIngredient("", "kosher salt and ground black pepper to taste");
        recipe.addIngredient("1/4 cup", "grated parmesan cheese");
        recipe.addIngredient("", "juice of 1 lemon");
        recipe.addCategory("Side dishes");
        recipe.addCategory("Vegetables");
        recipeCards.add(recipe);

        recipe = new RecipeCard();
        recipe.setName("Banana Chocolate Chip Pancakes");
        recipe.setComment("Dummy Comment");
        recipe.setCookTime(20);
        recipe.setDirections("Lightly grease frying pan." +
                "\nWhisk together dry ingredients in a bowl. Set aside." +
                "\nbeat egg in a bowl. Add milk and oil and whisk together." +
                "\nMash the bananas with a fork and add to wet ingredients. Pour wet ingredients " +
                "into dry ingredients and mix until just combined. Batter will be lumpy." +
                "\nFold in chocolate chips." +
                "\nPour about 1/4 cup batter into the pan per each pancake. Cook until edges " +
                "begin to set and top is bubbly. Flip pancake and cook for about one more " +
                "minute, or until pancake is completely set." +
                "\nRemove from pan and serve with favorite topping.\n");
        recipe.setRating(2);
        recipe.addIngredient("1 3/4 cup", "flour");
        recipe.addIngredient("2 T", "sugar");
        recipe.addIngredient("1 T", "baking powder");
        recipe.addIngredient("1/4 t", "salt");
        recipe.addIngredient("1", "egg");
        recipe.addIngredient("1 1/2 cup", "milk");
        recipe.addIngredient("3 T", "oil");
        recipe.addIngredient("1-2", "ripe bananas");
        recipe.addIngredient("1/2 cup", "chocolate chips");
        recipe.addCategory("breakfast");
        recipeCards.add(recipe);

        recipe = new RecipeCard();
        recipe.setName("Easy Beef Stroganoff");
        recipe.setComment("Dummy Comment");
        recipe.setCookTime(60);
        recipe.setDirections("Cook the pasta according to the directions on the package." +
                "\nMeanwhile, brown the beef in a skillet over medium heat. When beef is nearly " +
                "cooked, add the chopped onions and cook until soft." +
                "\nReduce the heat and add the cream of mushroom soup and sour cream to the beef." +
                "\nWhen pasta is finished cooking, drain the water and add the noodles to the beef"+
                "mixture. Mix and continue to heat until sauce is warm and thick.\n");
        recipe.setRating(2);
        recipe.addIngredient("8 oz", "pasta (rotini or shells are best)");
        recipe.addIngredient("1/2 lb", "ground beef");
        recipe.addIngredient("1/2", "onion, chopped");
        recipe.addIngredient("1 can", "cream of mushroom soup");
        recipe.addIngredient("1/2 cup", "sour cream");
        recipe.addCategory("Dinners");
        recipe.addCategory("beef");
        recipeCards.add(recipe);

        recipe = new RecipeCard();
        recipe.setName("Banana Bread Brownies");
        recipe.setComment("Dummy Comment");
        recipe.setCookTime(25);
        recipe.setDirections("Preheat oven to 375. Grease and flour 15x10-inch jelly roll pan." +
                "\nBeat sugar, sour cream, butter, and eggs in a large bowl until creamy." +
                "Blend in bananas and 2 t vanilla. Add the flour, baking soda, and salt and blend for" +
                "1 minute. Stir in walnuts (if desired)." +
                "\nSpread batter evenly in pan. bake for 20-25 minutes or until golden brown." +
                "\nMeanwhile for frosting, heat butter in a large saucepan over medium heat until" +
                "boiling. Let the butter turn a delicate golden brown (it should smell nutty as " +
                "well and remove from heat immediately. " +
                "\nAdd in the sugar, milk, and 1 1/2 t vanilla and mix until smooth. (It should be " +
                "thicker than glaze but thinner than frosting.)Spread immediately over warm" +
                " brownies using a spatula.");
        recipe.setRating(2);
        recipe.addIngredient("1 1/2 cup", "sugar");
        recipe.addIngredient("1 cup", "sour cream");
        recipe.addIngredient("1/2 cup", "butter, softened");
        recipe.addIngredient("2", "eggs");
        recipe.addIngredient("3 or 4", "ripe bananas");
        recipe.addIngredient("3 1/2 t", "vanilla extract");
        recipe.addIngredient("2 cups", "flour");
        recipe.addIngredient("1 t", "baking soda");
        recipe.addIngredient("3/4 t", "salt");
        recipe.addIngredient("1/2 cup", "chopped walnuts (optional)");
        recipe.addIngredient("1/2 cup", "butter");
        recipe.addIngredient("4 cups", "powdered sugar");
        recipe.addIngredient("3 T", "milk");
        recipe.addCategory("desserts");
        recipeCards.add(recipe);

        recipe = new RecipeCard();
        recipe.setName("Roasted Red Pepper Sauce");
        recipe.setComment("Dummy Comment");
        recipe.setCookTime(30);
        recipe.setDirections("Preheat broiler. Cut peppers in half and remove the seeds. Place" +
                "peppers cut side down on a cookie sheet and lightly coat with olive oil. Place " +
                "under boiler until skin is blackened and softened. Remove from oven and place" +
                "peppers in a paper bag to steam for about 5-10 minutes. (This makes removing the "+
                "skin easier.)" +
                "\nRemove the skin from the peppers and cut into small pieces." +
                "\nIn a frying pan, sautee the garlic, basil, onion, and red pepper flakes in the "+
                "olive oil until onions are soft. Pour into a blender and add the chopped " +
                "red peppers. Blend until smooth, then pour mixture back into the frying pan." +
                "\nAdd half and half to the pepper mixture until desired consistency is" +
                "reached. Add in cheese and cook until cheese melts. Stir in butter. " +
                "Season to taste with salt and pepper. Serve over pasta.\n");
        recipe.setRating(2);
        recipe.addIngredient("3", "red bell peppers");
        recipe.addIngredient("2-3 T", "onion");
        recipe.addIngredient("2 T", "garlic, minced");
        recipe.addIngredient("1/4 cup", "fresh basil");
        recipe.addIngredient("3 T", "olive oil");
        recipe.addIngredient("1 t", "red pepper flakes");
        recipe.addIngredient("1/4 - 1/2 cup", "half and half");
        recipe.addIngredient("1/3 cup", "grated parmesan cheese");
        recipe.addIngredient("2 T", "butter");
        recipe.addIngredient("", "salt and pepper to taste");
        recipe.addCategory("Dinners");
        recipe.addCategory("sauces");
        recipeCards.add(recipe);

        recipe = new RecipeCard();
        recipe.setName("Buckeyes");
        recipe.setComment("Dummy Comment");
        recipe.setCookTime(60);
        recipe.setDirections("Mix peanut butter, butter, and vanilla extract in a large bowl," +
                " mixer or food processor until fully combined. Add in 2 cups of the powdered " +
                "sugar and mix until fully incorporated. Check dough for sweetness and " +
                "consistency (should be slightly more firm than playdough). Add more powdered " +
                "sugar as needed." +
                "\nRoll the peanut butter dough into 3/4 inch balls and place on a lined cookie" +
                "sheet. Stick a toothpick into each ball and place sheet in the freezer for at" +
                "least 30 minutes or overnight." +
                "\nMeanwhile, melt chocolate chips in a double boiler or in the microwave in 1" +
                "minute intervals, stirring between each minute, until chocolate is smooth. " +
                "(If you choose to use the microwave, you may have to reheat the chocolate" +
                " during dipping.)" +
                "\nRemove peanut butter balls from the freezer. One at a time, dip the balls into" +
                "the chocolate until 2/3 of the ball is covered. Replace on the cookie sheet to " +
                "set. " +
                "\nRemove the toothpicks from the balls. Dip your finger in water and smooth " +
                "each toothpick hole. Replace buckeyes in the freezer to set.\n");
        recipe.setRating(2);
        recipe.addIngredient("1 cup", "creamy peanut butter");
        recipe.addIngredient("31/4 cup", "butter, softened");
        recipe.addIngredient("1 t", "vanilla extract");
        recipe.addIngredient("2-3 cups", "powdered sugar");
        recipe.addIngredient("12-16 ounces", "semi-sweet chocolate chips");
        recipe.addIngredient("", "toothpicks");
        recipe.addCategory("Desserts");
        recipe.addCategory("Shareable treats");
        recipeCards.add(recipe);

        recipe = new RecipeCard();
        recipe.setName("Dummy Name");
        recipe.setComment("Dummy Comment");
        recipe.setCookTime(60);
        recipe.setDirections("This\nis\ndummy\ninstructions\n");
        recipe.setRating(2);
        recipe.addIngredient("1/2 whole", "Dummy");
        recipe.addIngredient("3/8 T", "Essence of Dummy");
        recipe.addCategory("Dummy Category");
        recipe.addCategory("Second Category");
        recipeCards.add(recipe);
    }
}
