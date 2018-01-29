package com.aschambers.myapplication;

import java.lang.reflect.Array;
import java.util.ArrayList;

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
        System.out.println("Level: " + Alan.getLevel());
        System.out.println("Lives: " + Alan.getLives());
        System.out.println(Alan.getWeapon().getName());
        Alan.getWeapon().setName("Gun");
        System.out.println(Alan.getWeapon().getName());

        Loot redPotion = new Loot("Red Potion", LootType.POTION, 7);
        Alan.pickUpLoot(redPotion);

        ArrayList<Loot> allItems = Alan.getInventory();

        for(Loot item : allItems) {
            System.out.println(item.getName());
        }
    }
}
