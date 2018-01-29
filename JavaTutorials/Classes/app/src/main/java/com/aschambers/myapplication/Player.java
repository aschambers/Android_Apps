package com.aschambers.myapplication;

import java.util.ArrayList;

/**
 * Created by metabou on 1/29/18.
 */

public class Player {
    // make private so that only player class can change these values (encapsulation)
    private String handleName;
    private int lives;
    private int level;
    private int score;
    private Weapon weapon;
    // restricting arraylist to only hold objects of type Loot
    private ArrayList<Loot> inventory;

    // encapsulation (principal of oop)
    // a class should contain its objects and be responsible for updating them, only the player class
    // should be able to change the class variables, etc.

    // consctructor method
    public Player() {
        this.handleName = "Unkown Player";
        this.lives = 3;
        this.level = 1;
        this.score = 0;
        setDefaultWeapon();
        // initialize arrayList to an empty list, so it's not null
        inventory = new ArrayList<>();
    }


    public String getHandleName() {
        return handleName;
    }

    public void setHandleName(String handle) {
        if(handle.length() < 4) {
            // if handleName is 4 characters or less, return unknown player
            return;
        } else {
            // set handleName
            this.handleName = handle;
        }
    }

    private void setDefaultWeapon() {
        this.weapon = new Weapon("Sword", 10, 20);
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public ArrayList<Loot> getInventory() {
        return inventory;
    }

//    public void setInventory(ArrayList<Loot> inventory) {
//        this.inventory = inventory;
//    }

    // pickup loot
    public void pickUpLoot(Loot newLoot) {
        inventory.add(newLoot);
    }

    // drop loot
    public boolean dropLoot(Loot loot) {
        if (this.inventory.contains(loot)) {
            inventory.remove(loot);
            return true;
        }
        // if we don't have any loot to start with
        return false;
    }
}
