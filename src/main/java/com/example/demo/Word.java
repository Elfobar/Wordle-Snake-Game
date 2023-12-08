package com.example.demo;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Word {
    private List<String> words;
    private List<Character> pickedLetters;

    private String currentWord;
    private int currentIndex;

    public Word() {
        words = new ArrayList<>();
        words.add("DOG");
        words.add("CAT");
        words.add("BALL");
        words.add("FISH");
        words.add("BIRD");
        words.add("TREE");
        words.add("BOOK");
        words.add("CAR");
        words.add("CUP");
        words.add("PEN");

        chooseRandomWord();
        pickedLetters = new ArrayList<>();
    }

    public void chooseRandomWord() {
        Random random = new Random();
        currentWord = words.get(random.nextInt(words.size()));
        currentIndex = 0;
        System.out.println("Current Word: " + currentWord);
    }

    public String getCurrentWord() {
        return currentWord;
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
}


