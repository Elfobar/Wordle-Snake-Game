package com.example.demo;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.io.FileReader;
import java.io.BufferedReader;

public class Word {
    private List<String> words;
    private List<Character> pickedLetters;
    private String currentWord;
    private int currentIndex;

    public Word(String pathToFile) {
        words = initializeWords(pathToFile);
        chooseRandomWord();
        pickedLetters = new ArrayList<>();
    }

    public ArrayList<String> initializeWords(String pathToFile){
        ArrayList<String> words = new ArrayList<>();
        try{
            BufferedReader buffReader = new BufferedReader(new FileReader(pathToFile));
            String strCurrentLine;
            while((strCurrentLine = buffReader.readLine()) != null){
                words.add(strCurrentLine);
            }
            buffReader.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return words;
    }

    public void chooseRandomWord() {
        Random random = new Random();
        currentWord = words.get(random.nextInt(words.size()));
        currentIndex = 0;
        System.out.println("Current Word: " + currentWord);
    }

    public void incrementIndex() {
        if (currentIndex < currentWord.length()) {
            currentIndex++;
        }
    }

    public char getNextLetter() {
        if (currentIndex < currentWord.length()) {
            char nextLetter = currentWord.charAt(currentIndex);
            pickedLetters.add(nextLetter);

            return nextLetter;
        } else {
            return '\0';
        }
    }
    public boolean isWordCompleted() {
        return currentIndex == currentWord.length();
    }

    public String getCurrentWord(){
        return this.currentWord;
    }
}


