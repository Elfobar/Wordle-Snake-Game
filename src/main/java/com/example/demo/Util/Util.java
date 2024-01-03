package com.example.demo.Util; //package name/placement

import org.json.JSONArray;
import com.example.demo.GameCore.AppConfig;
import com.example.demo.GameCore.GameConfig;
import javafx.scene.text.Font;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

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

    public static void saveScore(int score, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(Integer.toString(score));
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error saving score: " + e.getMessage());
        }
    }

    private static ArrayList<Integer> initScore() {
        ArrayList<Integer> integerList = new ArrayList<>();
        try (FileInputStream fileInputStream = new FileInputStream(AppConfig.getScorePathFile());
             Scanner scanner = new Scanner(fileInputStream)) {

            integerList = readIntegersFromFile(scanner);
            System.out.println("Integers read from file: " + integerList);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return integerList;

    }

    private static ArrayList<Integer> readIntegersFromFile(Scanner scanner) {
        ArrayList<Integer> integerList = new ArrayList<>();

        while (scanner.hasNextInt()) {
            int value = scanner.nextInt();
            integerList.add(value);
        }
        return integerList;
    }

    public static ArrayList<Integer> getHighestScoresFromFile() {
        ArrayList<Integer> scores = initScore();
        scores.sort((a, b) -> Integer.compare(b, a));
        return scores;
    }

    public static JSONArray initializeWordsAsJsonArray() {
        JSONArray wordsArray = new JSONArray();
        try {
            FileReader reader = new FileReader(AppConfig.getWordsPathFile());
            BufferedReader buffReader = new BufferedReader(reader);
            String strCurrentLine;
            while ((strCurrentLine = buffReader.readLine()) != null) {
                wordsArray.put(strCurrentLine);
            }
            buffReader.close();
        } catch (IOException e) {
            System.out.println("Error initializing the list of words: \n" + e.getMessage());
        }
        return wordsArray;
    }

}



