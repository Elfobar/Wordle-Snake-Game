package com.example.demo.Util;

import com.example.demo.Game.GameController;
import com.example.demo.GameCore.AppConfig;
import com.example.demo.GameCore.Cell;
import com.example.demo.GameCore.Coordinate;
import com.example.demo.GameCore.GameConfig;
import com.example.demo.UI.Grid;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Util {

    private static final Random rand = new Random();

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
