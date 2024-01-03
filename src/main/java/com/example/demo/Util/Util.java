package com.example.demo.Util; //package name/placement


import com.example.demo.GameCore.AppConfig;
import com.example.demo.GameCore.GameConfig;
import javafx.scene.text.Font;
import java.util.Random;

public class Util { // this is our Util class
    private static final Random rand = new Random(); // we'll use this for random stuff

    public static char generateRandomLowercaseLetter() { // this gives us a random lowercase letter
        int randomNumber = rand.nextInt(26) + 'a'; // random number between 0 and 25, then add 'a'
        return (char) randomNumber; // convert to char and return
    }

    public static int generateRandomIndex(int size) { // this gives us a random index
        return rand.nextInt(size); // random number between 0 and size
    }

    public static Font loadCustomFont() {
        try {
            return Font.loadFont(AppConfig.getCustomFontPathFile(), GameConfig.TARGET_WORD_FONT_SIZE);
        } catch (Exception e) {
            System.out.println(e.getMessage()); // if something goes wrong, print error message
            return Font.getDefault(); //return the default font
        }
    }
}

