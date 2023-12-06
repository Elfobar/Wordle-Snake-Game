package com.example.demo;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.LinkedList;

public class Game extends Application{

    private int numOfFruits;
    private ArrayList<Cell> fruits;
    private Grid grid;
    private Snake snake;

    public static final int ROWS = 20;
    public static final int COLUMNS = 20;
    public static final int CELL_SIZE = 40;


    public void showGame(Stage stage){
        this.grid = new Grid();
        this.snake = new Snake( 3);
        this.fruits = new ArrayList<>();

        this.numOfFruits = 0;
        Scene scene = new Scene(grid.getGrid(), ROWS*CELL_SIZE, COLUMNS*CELL_SIZE);
        scene.setOnKeyPressed(event -> handleKeyPress(event.getCode()));
        stage.setTitle("SnakeGame");
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void start(Stage stage) {
        showGame(stage);
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(200), event -> updateGame()));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    public void spawnFruit(){
        int y = (int)(Math.random() * (ROWS));
        int x = (int)(Math.random() * (COLUMNS));

        Cell fruit = new Cell(CELL_SIZE, Color.RED, x, y);
        fruit.setArcHeight(40);
        fruit.setArcWidth(40);
        fruits.add(fruit);
        grid.getGrid().add(fruit, x, y);
    }

    private void updateGame() {
        if (numOfFruits != 3) {
            spawnFruit();
            numOfFruits++;
        }
        snake.moveSnake();
        intersectFruit();
        drawSnake();
        snake.rotateHead();
        snake.rotateTail();

        if(snake.checkIfCollided()){
            System.out.println("Game Over");
            System.exit(0);
        }
    }

    private void intersectFruit(){
        for (Cell fruit : fruits) {
            if (snake.getHead().equals(fruit.getCoordinate())) {
                grid.getGrid().getChildren().remove(fruit);
                snake.grow();
                numOfFruits--;
            }
        }
    }

    public void drawSnake(){
        LinkedList<Cell> snakeBody = snake.getBody();
        grid.getGrid().getChildren().removeAll(snakeBody);
        for (Cell cell : snakeBody) {
            grid.getGrid().add(cell, cell.getCoordinate().getX(), cell.getCoordinate().getY());
        }
    }

    private void handleKeyPress(KeyCode keyCode) {
        snake.changeDirection(keyCode);
    }

    public static void main(String[] args){
        Application.launch();
    }

}