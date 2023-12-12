package com.example.demo;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.util.LinkedList;

public class Game extends Application{
    private Letters letters;
    private Grid grid;
    private Snake snake;

    public static final int ROWS = 20;
    public static final int COLUMNS = 20;
    public static final int CELL_SIZE = 40;


    public void showGame(Stage stage){
        this.grid = new Grid();
        this.snake = new Snake( 3);
        Scene scene = new Scene(grid.getGrid(), ROWS*CELL_SIZE, COLUMNS*CELL_SIZE);
        scene.setOnKeyPressed(event -> handleKeyPress(event.getCode()));
        stage.setTitle("SnakeGame");
        stage.setScene(scene);
        stage.show();
    }
    @Override
    public void start(Stage stage) {
        showGame(stage);
        letters = new Letters(grid.getGrid(), 3);
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(200), event -> updateGame()));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }
    private void updateGame() {
        if (letters.getNumOfLetters() < 3) {
            letters.spawnInitialLetters(snake);

        }
        snake.moveSnake();
        letters.intersectLetter(snake, grid);
        drawSnake();
        snake.rotateHead();
        snake.rotateTail();

        if (snake.checkIfCollided()) {
            System.out.println("Game Over");
            System.exit(0);
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