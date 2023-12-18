package com.example.demo;

import java.util.ArrayList;
import javafx.scene.input.KeyCode;

public abstract class AbstractController {
    private WordManager wordManager;
    private ArrayList<Letter> letters;
    private boolean gameOver;

    public AbstractController() {
        this.wordManager = new WordManager();
        this.letters = new ArrayList<>();
        this.gameOver = false;
    }

    abstract void handleKeyPress(KeyCode code);

    abstract void updateGame();
    abstract void createLetters();

    public boolean getGameOverStatus() {
        return gameOver;
    }

    public String getTargetWord(){
        return wordManager.getTargetWord();
    }

    public ArrayList<Letter> getLetters(){
        return this.letters;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public boolean hasWordChanged(String currentWord) {
        return !currentWord.equals(wordManager.getTargetWord());
    }

    public void introduceNewWord() {
        wordManager.introduceNewWord();
    }

    public boolean checkNextLetter(char letter) {
        return wordManager.checkNextLetter(letter);
    }

    public void add(Letter letter) {
        letters.add(letter);
    }

    public char pickNextLetterFromWord(){
        String currentWord = wordManager.getTargetWord();
        int nextLetterIndex = wordManager.getLettersCollected();
        return currentWord.charAt(nextLetterIndex);
    }

}
