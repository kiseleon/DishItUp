package com.example.cs246.dishitup;

import junit.framework.TestCase;
/**
 * Created by Jason on 2/28/2015.
 */
public class MissionControlTest extends TestCase{

    public void testMissionControl() throws Exception {
        MissionControl testControl = new MissionControl(); // verifying that mission control is working
        assertNotNull(testControl.getRolodex()); // testing to see that we have a rolodex more detail on this latter
    }

    public void testSearchByName() throws Exception{         // for this test to work we need to add
        MissionControl testControl = new MissionControl();   // Roasted Chicken to the Rolodex
        RecipeCard testing;
        testing = testControl.searchByName("Roasted Chicken");
        assertEquals("Roasted Chicken.", testing.getName());
    }

}