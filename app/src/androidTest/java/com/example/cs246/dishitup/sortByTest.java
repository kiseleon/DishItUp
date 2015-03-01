package com.example.cs246.dishitup;

/**
 * This will test the sortByClass
 * Created by Trenton on 2/25/2015.
 */
public class sortByTest {
    SortBy sortBy = new SortBy();



    void runTest() {

         TestTheName();
         testTheString();

    }

boolean testTheString(){
    boolean string = true;
    sortBy.sortTheString();
    assert sortBy.strings[3].equals("is");


    return string;
}

    boolean TestTheName(){
        boolean name = true;
        String getname;
        getname = sortBy.GetName("this is trenton");
        assert getname.equals("this is trenton");
      return   name;
    }

}
