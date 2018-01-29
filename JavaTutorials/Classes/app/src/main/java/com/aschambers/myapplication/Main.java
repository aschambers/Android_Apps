package com.aschambers.myapplication;

/**
 * Created by metabou on 1/29/18.
 */

public class Main {
    public static void main(String[] args) {
        int lives = 3;
        boolean isGameOver = (lives < 1);
        System.out.println(isGameOver);

        if(isGameOver) {
            System.out.println("Game Over!");
        } else {
            System.out.println("You are still alive!");
        }
    }
}
