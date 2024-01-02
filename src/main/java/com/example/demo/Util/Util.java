package com.example.demo.Util; //package name/placement

//imports
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

public class Util { // this is our Util class

    private static final Random rand = new Random(); // we'll use this for random stuff

    public static char generateRandomLowercaseLetter(){ // this gives us a random lowercase letter
        int randomNumber = rand.nextInt(26) + 'a'; // random number between 0 and 25, then add 'a'
        return (char) randomNumber; // convert to char and return
    }

    public static int generateRandomIndex(int size){ // this gives us a random index
        return rand.nextInt(size); // random number between 0 and size
    }

    public static Font loadCustomFont(Class<?> clas) { // this loads cyber font
        try {
            return Font.loadFont(clas.getResourceAsStream(AppConfig.FONT_RELATIVE_PATH), AppConfig.WORD_FONT_SIZE); // try catch block to load the font
        } catch (Exception e) {
            System.out.println(e.getMessage()); // if something goes wrong, print error message
            return Font.getDefault(); //return the default font
        }
    }

    public int generateRandomIndex(){ // this gives us a random index
        return rand.nextInt(2); // random number between 0 and 2
    }
}
