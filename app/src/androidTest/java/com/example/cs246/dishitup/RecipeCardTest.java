package com.example.cs246.dishitup;

import junit.framework.TestCase;

public class RecipeCardTest extends TestCase {

    public void testGetComment() throws Exception {
        RecipeCard testing = new RecipeCard(); // testing default parameter
        assertEquals("No comment.", testing.getComment());
        testing.setComment("Setting comment...");
        assertEquals("Setting comment...", testing.getComment());
    }

    public void testGetCookTime() throws Exception {
        RecipeCard testing = new RecipeCard(); // testing default parameter
        assertEquals(90, testing.getCookTime());
        testing.setCookTime(100);
        assertEquals(100, testing.getCookTime());
    }

    public void testAddCategory() throws Exception {
        RecipeCard testing = new RecipeCard();
        assertEquals(true, testing.hasCategory("all"));
        testing.addCategory("testing");
        assertEquals(true, testing.hasCategory("testing"));
        testing.removeCategory("testing");
        assertEquals(false, testing.hasCategory("testing"));
    }
}