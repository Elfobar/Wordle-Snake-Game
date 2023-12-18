package com.example.demo;

import javafx.scene.input.KeyCode;
import java.util.ArrayList;

public class GameController extends AbstractController{
    private Snake snake;

    public GameController(int startLength){
        super();
        this.snake = new Snake(startLength);
        createThreeLetters();
    }

    public void handleKeyPress(KeyCode key){
        snake.changeDirection(key);
    }

    public void updateGame(){
        int snakeLength = snake.getBody().size();
        String currentWord = super.getTargetWord();
        snake.moveSnake();
        handleSnakeCollisionWithItself();
        handleSnakeCollisionWithLetters();
        if(isSnakeLengthIncreased(snakeLength) || super.hasWordChanged(currentWord)){
            createThreeLetters();
        }
    }

    public void createLetters(){
        createLetterFromWord();
        createRandomLetter();
        createRandomLetter();
    }

    public void createThreeLetters(){
        if(super.getLetters().isEmpty()){
            createLetters();
        }
        super.getLetters().clear();
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
        super.add(letter);
    }

    public void handleSnakeCollisionWithItself(){
        if(snake.isCollidedWithItself()){
            super.setGameOver(true);
        }
    }

    public void handleSnakeCollisionWithLetters(){
        Coordinate snakeHead = snake.getHead();
        for(Letter letter : super.getLetters()){
            if(snakeHead.equals(letter.getPosition())){
                char letterValue = letter.getValue();
                collectLetterFromWord(letterValue);
            }
        }
    }

    public void collectLetterFromWord(char letterValue){
        if(super.checkNextLetter(letterValue)){
            snake.grow();
        } else{
            super.introduceNewWord();
        }
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
        for(Letter letter : super.getLetters()){
            if(letter.getPosition().equals(coordinate)){
                return true;
            }
        }
        return false;
    }

    private boolean isSnakeLengthIncreased(int currentLength) {
        return currentLength < snake.getBody().size();
    }

    public Snake getSnake(){
        return this.snake;
    }
}

