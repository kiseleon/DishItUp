package com.example.cs246.dishitup;

/**
 * This will test the sortByClass
 * Created by Trenton on 2/25/2015.
 */
public class sortByTest {
    SortBy sortBy = new SortBy();

    void runTest() {

        if( TestTheName()){
            System.out.println("it worked!!!");
        }
        else{
            System.out.println("didn't work");
        }

    }



    boolean TestTheName(){
        boolean name = false;
        String getname;
        getname = sortBy.GetName("this is trenton");
        assert getname.equals("this is trenton");
      return   name;
    }

}
