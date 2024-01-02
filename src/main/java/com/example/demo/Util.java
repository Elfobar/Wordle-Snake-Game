package com.example.demo;

import javafx.scene.text.Font;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Util {

    private static final Random rand = new Random();

    public static Coordinate generateRandomCoordinate() {
        int x = rand.nextInt(GameConfig.ROWS);
        int y = rand.nextInt(GameConfig.COLUMNS);
        return new Coordinate(x, y);
    }

    public static char generateRandomLowercaseLetter() {
        int randomNumber = rand.nextInt(26) + 'a';
        return (char) randomNumber;
    }

    public static int generateRandomIndex(int size) {
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

    public static void saveScore(int score, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(Integer.toString(score));
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error saving score: " + e.getMessage());
        }
    }


    public static ArrayList<Integer> readScores() {
        ArrayList<Integer> scores = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(AppConfig.getScorePath()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                scores.add(Integer.parseInt(line));
                //System.out.println(scores.get(0));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return scores;
    }

    public static ArrayList<String> initializeScores(String pathToFile) {
        ArrayList<String> words = new ArrayList<>();
        try {
            BufferedReader buffReader = new BufferedReader(new FileReader(pathToFile));
            String strCurrentLine;
            while ((strCurrentLine = buffReader.readLine()) != null) {
                words.add(strCurrentLine);
            }
            buffReader.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return words;
    }

//    public static ArrayList<Integer> readScore() {
//        ArrayList<Integer> scores = new ArrayList<>();
//
//        try {
//            InputStream stream = new FileInputStream(AppConfig.getScorePath());
//            Reader reader = new InputStreamReader(stream);
//            // The read method reads the first "chunk" of information
//            // and moves on to the next.
//            int data = reader.read();
//            while (data != -1) { // when the stream has nothing to read, it returns -1,
//                System.out.print((char) data); // printing the data here as a character.
//                // Note that we use print here, and not
//                // println. Why do you think that is?
//                data = reader.read();
//                System.out.println("Adding " + data);
//                scores.add((int) data);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        System.out.println(scores.get(0));
//        return scores;
//    }

    private static ArrayList<Integer> initScore() {
        ArrayList<Integer> integerList = new ArrayList<>();
        try (FileInputStream fileInputStream = new FileInputStream(AppConfig.getScorePath());
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

}
