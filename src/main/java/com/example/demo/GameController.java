package com.example.demo;

import javafx.scene.input.KeyCode;
import java.util.ArrayList;
import java.util.LinkedList;

public class GameController {

    private WordManager wordManager;
    private Snake snake;
    private ArrayList<Letter> letters;

    private boolean gameOver;

    public GameController(int startLength){
        this.snake = new Snake(startLength);
        this.letters = new ArrayList<>();
        this.wordManager = new WordManager("snake");
        this.gameOver = false;
        createThreeLetters();
    }

    public void handleKeyPress(KeyCode key){
        snake.changeDirection(key);
    }

    public void updateGame(){
        int snakeLength = snake.getBody().size();
        String currentWord = wordManager.getTargetWord();
        snake.moveSnake();
        handleSnakeCollisionWithItself();
        handleSnakeCollisionWithLetters();
        if(isSnakeLengthIncreased(snakeLength) || hasWordChanged(currentWord)){
            createThreeLetters();
        }
    }

    public void createLetters(){
        createLetterFromWord();
        createRandomLetter();
        createRandomLetter();
    }

    public void createThreeLetters(){
        if(letters.isEmpty()){
            createLetters();
        }
        letters.clear();
        createLetters();
    }

    public void createLetterFromWord(){
        createLetterWithRandomPosition(pickNextLetterFromWord());
    }

    public void createRandomLetter(){
        createLetterWithRandomPosition(Util.generateRandomLowercaseLetter());
    }

    public void createLetterWithRandomPosition(char value){
        Coordinate letterPos;

        do{
            letterPos = Util.generateRandomCoordinate();
        } while(isValidLetterPosition(letterPos));

        Letter letter = new Letter(value, letterPos);
        letters.add(letter);
    }

    public char pickNextLetterFromWord(){
        String currentWord = wordManager.getTargetWord();
        int nextLetterIndex = wordManager.getLettersCollected();
        return currentWord.charAt(nextLetterIndex);
    }

    public void handleSnakeCollisionWithItself(){
        if(snake.isCollidedWithItself()){
            this.gameOver = true;
        }
    }

    public void handleSnakeCollisionWithLetters(){
        Coordinate snakeHead = snake.getHead();
        for(Letter letter :letters){
            if(snakeHead.equals(letter.getPosition())){
                char letterValue = letter.getValue();
                collectLetterFromWord(letterValue);
            }
        }
    }

    public void collectLetterFromWord(char letterValue){
        if(wordManager.checkNextLetter(letterValue)){
            snake.grow();
        } else{
            wordManager.introduceNewWord();
        }
    }

    public boolean getGameOverStatus(){
        return gameOver;
    }

    public boolean isValidLetterPosition(Coordinate cord){
        return !snake.containsCoordinate(cord) && !isTooCloseToSnakeHead(cord) && !containsLetter(cord);
    }
    
    public boolean isTooCloseToSnakeHead(Coordinate coordinate){
        Coordinate head = snake.getHead();
        int minDistance = 3;

        int realDistanceX = Math.abs(head.getX() - coordinate.getX());
        int realDistanceY = Math.abs(head.getY() - coordinate.getY());
        return !(realDistanceX < minDistance && realDistanceY < minDistance);
    }

    public boolean containsLetter(Coordinate coordinate){
        for(Letter letter : letters){
            if(letter.getPosition().equals(coordinate)){
                return true;
            }
        }
        return false;
    }

    private boolean isSnakeLengthIncreased(int currentLength) {
        return currentLength < snake.getBody().size();
    }

    private boolean hasWordChanged(String currentWord) {
        return !currentWord.equals(wordManager.getTargetWord());
    }

    public ArrayList<Letter> getLetters(){
        return this.letters;
    }

    public Snake getSnake(){
        return this.snake;
    }

    public String getTargetWord(){
        return wordManager.getTargetWord();
    }
}

