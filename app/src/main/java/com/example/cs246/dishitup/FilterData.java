package com.example.cs246.dishitup;

/**
 * Created by Kevin on 3/28/2015.
 */
public class FilterData {
    private static FilterData ourInstance = new FilterData();

    public static FilterData getInstance() {
        return ourInstance;
    }

    private FilterData() {
    }
}
