package com.example.demo;

import java.util.Random;

public class Util {

    private static final Random rand = new Random();

    public static Coordinate generateRandomCoordinate(){
        int x = rand.nextInt(SnakeGame.ROWS);
        int y = rand.nextInt(SnakeGame.COLUMNS);
        return new Coordinate(x,y);
    }

    public static char generateRandomLowercaseLetter(){
        int randomNumber = rand.nextInt(26) + 'a';
        return (char) randomNumber;
    }

    public static int generateRandomIndex(int size){
        return rand.nextInt(size);
    }
}
