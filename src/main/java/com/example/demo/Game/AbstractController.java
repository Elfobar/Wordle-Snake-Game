package com.example.demo.Game;

import java.util.ArrayList;

import com.example.demo.Game.WordManager;
import com.example.demo.GameCore.GameConfig;
import com.example.demo.GameCore.Letter;
import com.example.demo.GameCore.Score;
import javafx.scene.input.KeyCode;

//Seves as the base for the both main and mini game.
public abstract class AbstractController {
    private WordManager wordManager;
    private ArrayList<Letter> letters;
    private boolean gameOver;
    protected Score score;

    public AbstractController() {
        this.score = new Score();
        this.wordManager = new WordManager(score);
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

    //Determines a letter that should come from the word
    public char pickNextLetterFromWord(){
        String currentWord = wordManager.getTargetWord();
        int nextLetterIndex = wordManager.getLettersCollected();
        return currentWord.charAt(nextLetterIndex);
    }

    public String getCurrentInput() {
        return wordManager.getCurrentInput();
    }
    public void incrementScore() {
        score.increaseScore(GameConfig.INCREASING_POINTS_RATE);
    }
    public void decrementScore(){
        score.decreaseScore(GameConfig.DECREASING_POINTS_RATE);
    }
    public int getScore(){
        return score.getScore();
    }
}
