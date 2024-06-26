package com.example.demo.Game;

import com.example.demo.GameCore.*;
import com.example.demo.UI.Grid;
import com.example.demo.Util.Util;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
//The GameRenderer class is responsible for updating the visual state of the game
public class GameRenderer {
    private GameController gameController;
    private LinkedList<Cell> visualSnakeBody;
    private ArrayList<Text> visualLetters;
    private Grid grid;
    final int obstacle[][];


    public GameRenderer(GameController gameController, Grid grid){
        this.gameController = gameController;
        this.grid = grid;
        this.visualSnakeBody = initializeVisualSnake();
        this.visualLetters = initializeVisualLetters();
        this.obstacle = Obstacle.getMap1(); //  get current level and map
        drawObstacle();
    }

    //creates LinkedList of images of the snake
    public LinkedList<Cell> initializeVisualSnake() {
        LinkedList<Coordinate> snakeBodyPos = gameController.getSnake().getBody();
        LinkedList<Cell> visualSnakeBody = new LinkedList<>();
        Cell snakeCell;
        for (int i = 0; i < snakeBodyPos.size(); i++) {
            if (i == 0) {
                snakeCell = CellFactory.createSnakeHead();
            } else if (i == snakeBodyPos.size() - 1) {
                snakeCell = CellFactory.createSnakeTail();
            } else {
                snakeCell = CellFactory.createSnakeSegment();
            }
            visualSnakeBody.add(snakeCell);
        }
        return visualSnakeBody;
    }

    //creates ArrayList of text representing letters
    public ArrayList<Text> initializeVisualLetters(){
        ArrayList<Text> visualLetters = new ArrayList<>();
        ArrayList<Letter> letters = gameController.getLetters();
        for(Letter letter : letters){
            Text visualLetter = createVisualLetter(letter);
            visualLetters.add(visualLetter);
        }
        return visualLetters;
    }

    public void renderGame(){
        drawSnake();
        rotateSnakeHead();
        rotateSnakeTail();
        drawLetters();
    }

    //draws the snake accordingly to changes in gameController
    public void drawSnake(){
            LinkedList<Coordinate> snakeBodyPos = gameController.getSnake().getBody();

            if (snakeBodyPos.size() > visualSnakeBody.size()) {
                visualSnakeBody.add(visualSnakeBody.size() - 1, CellFactory.createSnakeSegment());
            }

            for (int i = 0; i < visualSnakeBody.size(); i++) {
                Coordinate coordinate = snakeBodyPos.get(i);
                Cell cell = visualSnakeBody.get(i);

                grid.remove(cell);
                grid.add(cell, coordinate.getX(), coordinate.getY());
            }
    }

    //draws the letters accordingly to changes in gameController
    public void drawLetters(){
        ArrayList<Letter> letters = gameController.getLetters();

        if (letters.size() != visualLetters.size()) {
            visualLetters = initializeVisualLetters();
        }

        for(int i = 0; i < letters.size(); i++){
            Coordinate coordinate = letters.get(i).getPosition();
            Text text = visualLetters.get(i);

            char letterValue = letters.get(i).getValue();
            text.setText(String.valueOf(letterValue));

            grid.remove(text);
            grid.add(text, coordinate.getX(), coordinate.getY());
        }
    }

    public void rotateSnakeHead(){
        Cell snakeHead = visualSnakeBody.getFirst();
        Snake snake = gameController.getSnake();
        int rotationAngle = snake.determineHeadRotation();
        snakeHead.setRotate(rotationAngle);
    }

    public void rotateSnakeTail(){
        Cell snakeTail = visualSnakeBody.getLast();
        Snake snake = gameController.getSnake();
        int rotationAngle = snake.determineTailRotation();
        snakeTail.setRotate(rotationAngle);
    }

    //creates a letter and sets appropriate font
    public Text createVisualLetter(Letter letter){
        char letterValue = letter.getValue();
        String visualLetter = letterValue + "";
        Font customFont = Util.loadCustomFont();
        Text text = new Text(visualLetter);
        text.setFont(customFont);
        text.setFill(Color.LIGHTBLUE);
        text.setStyle("-fx-font-size: 25pt");
        return text;
    }

    //fetches the coordinate of each obstacle and creates the List
    public static List<Coordinate> getObstacleCoordinates() {
        List<Coordinate> obstacleCoordinates = new ArrayList<>();
        int[][] map = Obstacle.getMap1();

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] == 1) {
                    obstacleCoordinates.add(new Coordinate(i, j));
                }
            }
        }
        return obstacleCoordinates;
    }

    //creates cell for each obstancle on the grid
    public void drawObstacle(){
        List<Coordinate> obstacleCoordinates = getObstacleCoordinates();
        for(Coordinate coordinate : obstacleCoordinates){
            Cell obstacleCell = CellFactory.createObstacle();
            grid.add(obstacleCell, coordinate.getX(),coordinate.getY());
        }
    }

    public ArrayList<Text> getVisualLetters(){
        return this.visualLetters;
    }

    public LinkedList<Cell> getVisualSnakeBody(){
        return this.visualSnakeBody;
    }
}
