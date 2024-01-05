package com.example.demo.Util;


import com.example.demo.GameCore.AppConfig;
import com.example.demo.GameCore.GameConfig;
import javafx.scene.text.Font;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.util.*;

public class Util {
    private static final Random rand = new Random();

    /*rand.nextInt(26) generates random integer between 0(inclusive) and 26(exclusive). Char 'a' is the ASCII value of the lowercase 'a'.
    By adding this to the random number, the result is an integer in the range of ASCII values corresponding to lowercase letters
    from 'a' to 'z'.

    So, for example, if the random number is 0, the calculation will be 0 + 97, resulting in 97, which is the ASCII value for 'a'.
    If the random number is 1, the calculation would be 1 + 97, resulting in 98, which is the ASCII value for 'b'.
    Similarly, if the random number is 25, the calculation will be 25 + 97, resulting in 122, which is the ASCII value for 'z'.
    26 is used because there are 26 letters in the alphabet. 'a' is used because its the first letter in the alphabet, and by adding
    a number to this letter you can get every other letter.

    int randomNumber = rand.nextInt(26) + 'a' - stores the integer because when you add a char to an int, java implicitly turns the
    char to the int. This means that when we return, we need to cast the value to a char again.*/
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
    //Returns ArrayList sorted from highest to lowest without duplicate elements.
    public static ArrayList<Integer> getHighestScoresFromFile() {
        ArrayList<Integer> scores = readIntFromFile(AppConfig.getScorePathFile());
        selectionSort(scores);
        return removeDuplicates(scores);
    }

    //jsonArray.toString() is effectively converts JSONArray to string of this json format ["elem1", "elem2",...];
    public static void writeToFile(JSONArray jsonArray, String filePath) {
        writeToJSONFile(jsonArray.toString(), filePath);
    }

    //Saves new score by getting all the previous scores in jsonArray, adding new score to it and writing updated array to a file.
    public static void saveScoreToFile(int score, String filePath){
        JSONArray jsonArray = readJSONArrayFromFile(filePath);
        jsonArray.put(score);
        writeToFile(jsonArray, filePath);
    }

    public static void writeToFile(Object object, String filePath) {
        writeToJSONFile(object.toString(), filePath);
    }

    public static JSONObject readObjectFromFile(String filePath){
        return readJSONObjectFromFile(filePath);
    }

    //Converts the jsonArray to ArrayList of ints by going through each element of the first type of array and adding it to the next one
    public static ArrayList<Integer> readIntFromFile(String filePath) {
        ArrayList<Integer> scores = new ArrayList<>();
        JSONArray jsonArray = readJSONArrayFromFile(filePath);
        for (int i = 0; i < jsonArray.length(); i++) {
            int score = jsonArray.getInt(i);
            scores.add(score);
        }
        return scores;
    }
    //Converts the jsonArray to ArrayList of strings by going through each element of the first type of array and adding it to the next one
    public static ArrayList<String> readStringFromFile(String filePath) {
        ArrayList<String> words = new ArrayList<>();
        JSONArray jsonArray = readJSONArrayFromFile(filePath);
        for (int i = 0; i < jsonArray.length(); i++) {
            String word = jsonArray.getString(i);
            words.add(word);
        }
        return words;
    }

    /*JSONArray is used to parse the data from json file that is stored in specific way ["element1", "element2",...]. This way
    its not needed to deal with signs like [, " and , since JSONArray automatically converts all the data to an array.
    FileReader is used to create a stream for reading characters. Buffered reader is then used to efficiently read the data
    from that stream(file reader). Method only reads one line as its enough to get all the data due to how it stored.
    bufferedReader.close() is needed to free up system resources.
     */
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

    /*JSONObject is used to parse data from json file that is stored in specific way: {data: value, ...}. This way we dont need
    to get rid of the signs like { and : because its automatically converted to a json object. JSONObject has convenient constructor
    to parse the string. Buffered reader is used due to its good performance and convenient method readLine()*/
    private static JSONObject readJSONObjectFromFile(String filePath){
        JSONObject jsonObject = new JSONObject();
        try{
            FileReader reader = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String currentLine = bufferedReader.readLine();
            if(currentLine != null){
                jsonObject = new JSONObject(currentLine);
            }
            bufferedReader.close();
        } catch (IOException e){
            System.out.println("Error reading JSON array from file: " + e.getMessage());
        }
        return jsonObject;
    }

    //Buffered writer is used here because its improves performance by using internal buffer to store data before writing to a file.
    //Also it has some convenient methods like newLine(), write(String s).
    private static void writeToJSONFile(String data, String filePath){
        try{
            FileWriter writer = new FileWriter(filePath);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            bufferedWriter.write(data);
            bufferedWriter.close();
        } catch (IOException e){
            System.out.println("Error writing JSON array to file: " + e.getMessage());
        }
    }

    //Simple selection sort that looks for the highest number and sorts all the other values accordingly(from highest to lowest)
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

    //Removes duplicates by converting the array list to a set, which only has unique elements. LinkedHashSet is used because
    //the order of the elements needs to be preserved.
    private static ArrayList<Integer> removeDuplicates(ArrayList<Integer> list) {
        return new ArrayList<>(new LinkedHashSet<>(list));
    }
}

