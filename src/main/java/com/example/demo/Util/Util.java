package com.example.demo.Util; //package name/placement


import com.example.demo.GameCore.AppConfig;
import com.example.demo.GameCore.GameConfig;
import javafx.scene.text.Font;
import org.json.JSONArray;

import java.io.*;
import java.util.*;

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

    public static void saveToFile(int score, String filePath) {
        try {
            FileWriter fileWriter = new FileWriter(filePath);
            BufferedWriter writer = new BufferedWriter(fileWriter);
            JSONArray jsonArray = readJSONFromFile(AppConfig.getScorePathFile());
            System.out.println("initial array:" + jsonArray);
            jsonArray.put(score);
            writer.write(jsonArray.toString());
            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving to a file: \n " + e.getMessage());
        }
    }

    public static ArrayList<String> readStringFromFile(String filePath){
        ArrayList<String> words = new ArrayList<>();
        try{
            FileReader reader = new FileReader(filePath);
            BufferedReader buffReader = new BufferedReader(reader);
            String strCurrentLine;
            while((strCurrentLine = buffReader.readLine()) != null){
                words.add(strCurrentLine);
            }
            buffReader.close();
        } catch (IOException e) {
            System.out.println("Error initializing the list of words: \n"+ e.getMessage());
        }
        return words;
    }

    public static ArrayList<Integer> readIntFromFile(String filePath){
        ArrayList<Integer> scores = new ArrayList<>();
        try{
            FileReader reader = new FileReader(filePath);
            BufferedReader buffReader = new BufferedReader(reader);
            String strCurrentLine;
            while((strCurrentLine = buffReader.readLine()) != null){
                JSONArray jsonArray = new JSONArray(strCurrentLine);
                for(int i = 0; i < jsonArray.length(); i++){
                    int score = jsonArray.getInt(i);
                    scores.add(score);
                }
            }
            buffReader.close();
        } catch (IOException e) {
            System.out.println("Error initializing the list of scores: \n"+ e.getMessage());
        }
        return scores;
    }

    private static JSONArray readJSONFromFile(String filePath){
        JSONArray jsonArray = new JSONArray();
        try{
            FileReader fileReader = new FileReader(filePath);
            BufferedReader buffReader = new BufferedReader(fileReader);
            String strCurrentLine;
            while((strCurrentLine = buffReader.readLine()) != null) {
                System.out.println("IM WORKING");
                jsonArray = new JSONArray(strCurrentLine);
            }
        } catch (IOException e) {
            System.out.println("Error initializing the list of scores: \n"+ e.getMessage());
        }
        return jsonArray;
    }

    public static ArrayList<Integer> getHighestScoresFromFile() {
        ArrayList<Integer> scores = readIntFromFile(AppConfig.getScorePathFile());
        selectionSort(scores);
        LinkedHashSet<Integer> uniqueScores = convertArrayListToSet(scores);
        ArrayList<Integer> uniqueArrayList = convertSetToArrayList(uniqueScores);
        return uniqueArrayList;
    }

    private static void selectionSort(ArrayList<Integer> array) {
        int size = array.size();
        int highestIndex;
        for(int i = 0; i < size - 1; i++){
            highestIndex = i;
            for(int j = i + 1; j < size; j++){
                if(array.get(j) > array.get(highestIndex)){
                    highestIndex = j;
                }
            }
            int temp = array.get(highestIndex);
            array.set(highestIndex, array.get(i));
            array.set(i, temp);
        }
    }

    private static LinkedHashSet<Integer> convertArrayListToSet(ArrayList<Integer> array){
        return new LinkedHashSet<>(array);
    }

    private static ArrayList<Integer> convertSetToArrayList(LinkedHashSet<Integer> set){
        return new ArrayList<>(set);
    }
}

