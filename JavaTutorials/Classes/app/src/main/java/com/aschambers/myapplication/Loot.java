package com.aschambers.myapplication;

/**
 * Created by metabou on 1/29/18.
 */

// enum is a way of having multiple objects for something
// if we only have a few values a field can have, we use enum
// can only be a potion, ring or armor
enum LootType { POTION, RING, ARMOR }

public class Loot {
    private String name;
    private LootType type;
    private int value;

    public Loot(String name, LootType type, int value) {
        this.name = name;
        this.type = type;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LootType getType() {
        return type;
    }

    public void setType(LootType type) {
        this.type = type;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
