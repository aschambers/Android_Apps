package com.aschambers.myapplication;

/**
 * Created by metabou on 1/29/18.
 */

public class Player {
    // make private so that only player class can change these values (encapsulation)
    private String handleName;
    private int lives;
    private int level;
    private int score;
    // encapsulation (principal of oop)
    // a class should contain its objects and be responsible for updating them, only the player class
    // should be able to change the class variables, etc.

    // consctructor method
    public Player() {
        handleName = "Unknown Player";
        lives = 3;
        level = 1;
        score = 0;
    }

    public String getHandleName() {
        return handleName;
    }

    public void setHandleName(String handleName) {
        if(handleName.length() < 4) {
            // if handleName is 4 characters or less, return unknown player
            return;
        } else {
            // set handleName
            this.handleName = handleName;
        }
    }


}
