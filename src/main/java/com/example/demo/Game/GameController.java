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
    private final Snake snake; //Snake is a component
    private List<Coordinate> validCoordinates; //List of coordinates is a component
    private boolean hasChosenDirection; //Used to allow only one direction choice at a time

    public GameController(int startLength){
        super(); //AbstractController constructor is used, only methods are inherited as attributes are private
        this.snake = new Snake(startLength);
        validCoordinates = initializeValidCoordinates();
        clearAndCreateLetters(); //1 letter from word and 2 random letters are created and added to ArrayList (belongs to AbstractController)
    }

    private List<Coordinate> initializeValidCoordinates(){
        List<Coordinate> list = new ArrayList<>(); //ArrayList will store all coordinates that snake body/letters can occupy
        //First loop iterates over rows, second loop iterates over columns, so together they iterate over the whole grid
        for (int x = 0; x < GameConfig.ROWS; x++) {
            for (int y = 0; y < GameConfig.COLUMNS; y++) {
                Coordinate coordinate = new Coordinate(x, y);
                if (!containsObstacle(coordinate)) {   //Obstacle coordinates are not 'valid' for other cells
                    list.add(coordinate);
                }
            }
        }
        return list;
    }

    public void handleKeyPress(KeyCode key){
        if(!hasChosenDirection){ //If another key is pressed quickly after the first one, it will be ignored
            snake.changeDirection(key); //Passes KeyCode to snake for direction change
            hasChosenDirection = true;
        }
    }

    public void updateGame(){
        snake.moveSnake();
        hasChosenDirection = false; //After update, new KeyCode will be expected
        handleSnakeCollisionWithItself(); //Checks for collisions with object at specific coordinates
        handleSnakeCollisionWithObstacles();
        handleSnakeCollisionWithLetters();
    }

    public void clearAndCreateLetters(){
        if(!super.getLetters().isEmpty()){
            super.getLetters().clear();
        }
        createLetters();
    }

    public void createLetters(){
        createLetterFromWord(); //Correct letter from word
        createRandomLetter(); //Two random letters
    }

    public void createLetterFromWord(){
        char letter = pickNextLetterFromWord();
        createLetterWithRandomPosition(letter); //Letter is assigned to a random valid coordinate
    }

    public void createRandomLetter(){
        for(int i = 0; i < 2; i++){
            char letter = Util.generateRandomLowercaseLetter(); //Method from Util class that employs Random class is used for letter generation
            createLetterWithRandomPosition(letter);
        }
    }

    public void createLetterWithRandomPosition(char value){
        Coordinate letterPos;
        int randomIndex;

        //Random number is chosen from the amount of cells not occupied by obstacles
        //According coordinate is assigned to a letter
        do {
            randomIndex = Util.generateRandomIndex(validCoordinates.size()-1);
            letterPos = validCoordinates.get(randomIndex);
        } while (!isValidLetterPosition(letterPos)); //If letter is too close to snake's head, process is repeated

        validCoordinates.remove(randomIndex); //Index of a coordinate is removed from the list of valid coordinates
        Letter letter = new Letter(value, letterPos);
        super.add(letter);
    }

    public void handleSnakeCollisionWithItself(){
        if(snake.isCollidedWithItself()){  //When snake has collided with its tail, gameover is triggered
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
        //Loop iterates over all letters and checks if coordinate of a snake head is equal to coordinates of any letter
        for(Letter letter : super.getLetters()){
            if(snakeHead.equals(letter.getPosition())){
                char letterValue = letter.getValue();
                collectLetter(letterValue); //If letter is eaten it is passed to check for correctness
                hasEatenLetter = true;
            }
        }
        if(hasEatenLetter){
            clearAndCreateLetters(); //When letter is eaten, new letters are spawned
        }
    }

    public void collectLetter(char letterValue){
        if(super.checkNextLetter(letterValue)){ //When correct letter is eaten snake body increases
            snake.grow();
            incrementScore(); //Score increases
            SoundPlayer.getInstance().playSFX(
                    Sounds.EAT_1,
                    Sounds.EAT_2,
                    Sounds.EAT_3,
                    Sounds.EAT_4);
        } else{
            decrementScore(); //When incorrect letter is eaten score decreases and new word is spawned
            super.introduceNewWord();
            SoundPlayer.getInstance().playSFX(
                    Sounds.WRONG_LETTER_1,
                    Sounds.WRONG_LETTER_2,
                    Sounds.WRONG_LETTER_3,
                    Sounds.WRONG_LETTER_4);
        }
    }

    public void saveScore(String filePath){
        score.saveScore(filePath);
    }

    public boolean isValidLetterPosition(Coordinate cord){ //Check to ensure that the letter is not spawned inside snake or near head
        return  !snake.containsCoordinate(cord) &&
                !isTooCloseToSnakeHead(cord);
    }
    
    public boolean isTooCloseToSnakeHead(Coordinate coordinate){
        Coordinate head = snake.getHead();
        int minDistance = 3;

        int realDistanceX = Math.abs(head.getX() - coordinate.getX()); //Calculates distance (difference in coordinates) in x-direction
        int realDistanceY = Math.abs(head.getY() - coordinate.getY()); //In y-direction
        return (realDistanceX < minDistance && realDistanceY < minDistance); //For true: both distances are less than 3
    }

    public void handleSnakeCollisionWithObstacles(){
        Coordinate snakeHead = snake.getHead();
        //Loop iterates over all obstacle coordinates
        for(Coordinate coordinate : GameRenderer.getObstacleCoordinates()) {
            if (coordinate.equals(snakeHead)) { //When snake head is on the coordinate of the obstacle, gameover is triggered
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
        //Loop iterates over all obstacle coordinates and checks if any of them is equal to the passed coordinate
        for (Coordinate coordinate1 : obstacleCoordinates) {
            if (coordinate1.equals(coordinate)) {
                return true;
            }
        }
        return false;
    }
    public Snake getSnake(){
        return this.snake;
    }

}

