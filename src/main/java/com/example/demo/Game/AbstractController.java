package com.example.demo.Game;

import java.util.ArrayList;

import com.example.demo.Game.WordManager;
import com.example.demo.GameCore.Letter;
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
        System.out.println(currentWord.charAt(nextLetterIndex));
        return currentWord.charAt(nextLetterIndex);
    }

    public String getCurrentInput() {
        return wordManager.getCurrentInput();
    }
}
