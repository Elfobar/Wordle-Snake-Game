package com.example.demo.Util; //package name/placement


import com.example.demo.GameCore.AppConfig;
import com.example.demo.GameCore.GameConfig;
import javafx.scene.text.Font;
import org.json.JSONArray;

import java.io.*;
import java.util.*;

public class Util {
    private static final Random rand = new Random();

    public static char generateRandomLowercaseLetter() {
        int randomNumber = rand.nextInt(26) + 'a';
        return (char) randomNumber;
    }

    public static int generateRandomIndex(int size) {
        return rand.nextInt(size);
    }

    public static Font loadCustomFont() {
        try {
            return Font.loadFont(AppConfig.getCustomFontPathFile(), GameConfig.TARGET_WORD_FONT_SIZE);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return Font.getDefault();
        }
    }

    public static void saveToJSONFile(int score, String filePath){
        JSONArray jsonArray = readJSONArrayFromFile(filePath);
        jsonArray.put(score);
        writeJSONArrayToFile(jsonArray, filePath);
    }

    public static ArrayList<Integer> readIntFromFile(String filePath) {
        ArrayList<Integer> scores = new ArrayList<>();
        JSONArray jsonArray = readJSONArrayFromFile(filePath);
        for (int i = 0; i < jsonArray.length(); i++) {
            int score = jsonArray.getInt(i);
            scores.add(score);
        }
        return scores;
    }

    public static ArrayList<String> readStringFromFile(String filePath) {
        ArrayList<String> words = new ArrayList<>();
        JSONArray jsonArray = readJSONArrayFromFile(filePath);
        for (int i = 0; i < jsonArray.length(); i++) {
            String word = jsonArray.getString(i);
            words.add(word);
        }
        return words;
    }

    public static ArrayList<Integer> getHighestScoresFromFile() {
        ArrayList<Integer> scores = readIntFromFile(AppConfig.getScorePathFile());
        selectionSort(scores);
        return removeDuplicates(scores);
    }

    private static JSONArray readJSONArrayFromFile(String filePath){
        JSONArray jsonArray = new JSONArray();
        try{
            FileReader reader = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String currentLine = bufferedReader.readLine();
            if(currentLine != null){
                jsonArray = new JSONArray(currentLine);
            }
            bufferedReader.close();
        } catch (IOException e){
            System.out.println("Error reading JSON array from file: " + e.getMessage());
        }
        return jsonArray;
    }

    private static void writeJSONArrayToFile(JSONArray jsonArray, String filePath){
        try{
            FileWriter writer = new FileWriter(filePath);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            bufferedWriter.write(jsonArray.toString());
            bufferedWriter.close();
        } catch (IOException e){
            System.out.println("Error writing JSON array to file: " + e.getMessage());
        }
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

    private static ArrayList<Integer> removeDuplicates(ArrayList<Integer> list) {
        return new ArrayList<>(new LinkedHashSet<>(list));
    }
}

