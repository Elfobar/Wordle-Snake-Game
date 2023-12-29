package com.example.demo;

import javafx.scene.text.Font;

import java.util.Random;

public class Util {

    private static final Random rand = new Random();

    public static Coordinate generateRandomCoordinate(){
        int x = rand.nextInt(GameConfig.ROWS);
        int y = rand.nextInt(GameConfig.COLUMNS);
        return new Coordinate(x,y);
    }

    public static char generateRandomLowercaseLetter(){
        int randomNumber = rand.nextInt(26) + 'a';
        return (char) randomNumber;
    }

    public static int generateRandomIndex(int size){
        return rand.nextInt(size);
    }

    public static Font loadCustomFont(Class<?> clas) {
        try {
            return Font.loadFont(clas.getResourceAsStream(AppConfig.FONT_RELATIVE_PATH), AppConfig.WORD_FONT_SIZE);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return Font.getDefault();
        }
    }
    public int generateRandomIndex(){
        return rand.nextInt(2);
    }
}
