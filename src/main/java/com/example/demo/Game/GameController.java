package com.example.demo.Game;

import com.example.demo.GameCore.Coordinate;
import com.example.demo.GameCore.GameConfig;
import com.example.demo.GameCore.Letter;
import com.example.demo.GameCore.Snake;
import com.example.demo.Sound.SoundPlayer;
import com.example.demo.Sound.Sounds;
import com.example.demo.Util.Util;
import javafx.scene.input.KeyCode;

import java.util.ArrayList;
import java.util.List;

public class GameController extends AbstractController {
    private final Snake snake;
    private List<Coordinate> validCoordinates;

    public GameController(int startLength){
        super();
        this.snake = new Snake(startLength);
        validCoordinates = initializeValidCoordinates();
        createLetters();
    }

    private List<Coordinate> initializeValidCoordinates(){
        List<Coordinate> list = new ArrayList<>();
        for (int x = 0; x < GameConfig.ROWS; x++) {
            for (int y = 0; y < GameConfig.COLUMNS; y++) {
                Coordinate coordinate = new Coordinate(x, y);
                if (!containsObstacle(coordinate)) {
                    list.add(coordinate);
                }
            }
        }
        return list;
    }

    public void handleKeyPress(KeyCode key){
        snake.changeDirection(key);
    }

    public void updateGame(){
        snake.moveSnake();
        handleSnakeCollisionWithItself();
        handleSnakeCollisionWithObstacles();
        handleSnakeCollisionWithLetters();
    }

    public void createThreeLetters(){
        if(super.getLetters().isEmpty()){
            createLetters();
        }
        super.getLetters().clear();
        createLetters();
    }

    public void createLetters(){
        createLetterFromWord();
        createRandomLetter();
    }

    public void createLetterFromWord(){
        char letter = pickNextLetterFromWord();
        createLetterWithRandomPosition(letter);
    }

    public void createRandomLetter(){
        for(int i = 0; i < 2; i++){
            char letter = Util.generateRandomLowercaseLetter();
            createLetterWithRandomPosition(letter);
        }
    }

    public void createLetterWithRandomPosition(char value){
        Coordinate letterPos;
        int randomIndex;
        do{
            randomIndex = Util.generateRandomIndex(validCoordinates.size()-1);
            letterPos = validCoordinates.get(randomIndex);
        }while (!isValidLetterPosition(letterPos));

        validCoordinates.remove(randomIndex);
        System.out.println(validCoordinates.size());
        Letter letter = new Letter(value, letterPos);
        super.add(letter);
    }

    public void handleSnakeCollisionWithItself(){
        if(snake.isCollidedWithItself()){
            SoundPlayer.getInstance().playSFX(
                    Sounds.COLLIDE_1,
                    Sounds.COLLIDE_2,
                    Sounds.COLLIDE_3,
                    Sounds.COLLIDE_4);
            super.setGameOver(true);
        }
    }

    public void handleSnakeCollisionWithLetters(){
        boolean hasEatenLetter = false;
        Coordinate snakeHead = snake.getHead();
        for(Letter letter : super.getLetters()){
            if(snakeHead.equals(letter.getPosition())){
                char letterValue = letter.getValue();
                collectLetter(letterValue);
                hasEatenLetter = true;
            }
        }
        if(hasEatenLetter){
            createThreeLetters();
        }
    }

    public void collectLetter(char letterValue){
        if(super.checkNextLetter(letterValue)){
            snake.grow();
            incrementScore();
            SoundPlayer.getInstance().playSFX(
                    Sounds.EAT_1,
                    Sounds.EAT_2,
                    Sounds.EAT_3,
                    Sounds.EAT_4);
        } else{

            super.introduceNewWord();
            SoundPlayer.getInstance().playSFX(
                    Sounds.WRONG_LETTER_1,
                    Sounds.WRONG_LETTER_2,
                    Sounds.WRONG_LETTER_3,
                    Sounds.WRONG_LETTER_4);
        }
    }

    public boolean isValidLetterPosition(Coordinate cord){
        return  !snake.containsCoordinate(cord) &&
                !isTooCloseToSnakeHead(cord);
    }
    
    public boolean isTooCloseToSnakeHead(Coordinate coordinate){
        Coordinate head = snake.getHead();
        int minDistance = 3;

        int realDistanceX = Math.abs(head.getX() - coordinate.getX());
        int realDistanceY = Math.abs(head.getY() - coordinate.getY());
        return (realDistanceX < minDistance && realDistanceY < minDistance);
    }

    public void handleSnakeCollisionWithObstacles(){
        Coordinate snakeHead = snake.getHead();
        for(Coordinate coordinate : GameRenderer.getObstacleCoordinates()) {
            if (coordinate.equals(snakeHead)) {
                SoundPlayer.getInstance().playSFX(
                        Sounds.COLLIDE_1,
                        Sounds.COLLIDE_2,
                        Sounds.COLLIDE_3,
                        Sounds.COLLIDE_4);
                super.setGameOver(true);
            }
        }
    }

    public boolean containsObstacle(Coordinate coordinate) {
        List<Coordinate> obstacleCoordinates = GameRenderer.getObstacleCoordinates();
        for (Coordinate coordinate1 : obstacleCoordinates) {
            if (coordinate1.equals(coordinate)) {
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

