package com.example.demo;

import javafx.geometry.HPos;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

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
        this.obstacle = Obstacle.getMap2(); //  get current level and map
    }

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
        drawObstacle();
    }

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

    public Text createVisualLetter(Letter letter){
        char letterValue = letter.getValue();
        String visualLetter = letterValue + "";
        Font customFont = Util.loadCustomFont(getClass());
        Text text = new Text(visualLetter);
        text.setFont(customFont);
        text.setFill(Color.LIGHTBLUE);
        text.setStyle("-fx-font-size: 25pt");
        return text;
    }

    public static List<Coordinate> getObstacleCoordinates() {
        List<Coordinate> obstacleCoordinates = new ArrayList<>();
        int[][] map = Obstacle.getMap2();

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] == 1) {
                    obstacleCoordinates.add(new Coordinate(i, j));
                }
            }
        }
        return obstacleCoordinates;
    }

    public void drawObstacle(){
        //if level equals 1 getMap1(); if equals 2 getMap2()
        List<Coordinate> obstacleCoordinates = getObstacleCoordinates();
        for(Coordinate coordinate : obstacleCoordinates){
            //Obstacle obstacle = new Obstacle();
            //grid.addObstacle(obstacle, coordinate.getX(),coordinate.getY());
            Cell obstacleCell = CellFactory.createObstacle();
            grid.add(obstacleCell, coordinate.getX(),coordinate.getY());
            //when image is used for obstacles game slows down
        }
    }

    public ArrayList<Text> getVisualLetters(){
        return this.visualLetters;
    }

    public LinkedList<Cell> getVisualSnakeBody(){
        return this.visualSnakeBody;
    }
}
