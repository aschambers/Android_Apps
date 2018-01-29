package com.aschambers.myapplication;

/**
 * Created by metabou on 1/29/18.
 */

public class Demo {

    public static void main(String[] args) {
        // use the player class to create a new instance of the player class using new keyword
        Player Alan = new Player();
        System.out.println(Alan.getHandleName());
        // we want to use setters and getters so that we are not changing the variables in the player class
        // it could potentially evade validation rules, etc. set in that class, which would be a problem
        Alan.setHandleName("Alan");
        System.out.println(Alan.getHandleName());
    }
}
