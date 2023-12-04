package com;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Game extends Application{
    
    private Grid grid;
    private Snake snake;

    public static final int ROWS = 20;
    popopop
    public static final int COLUMNS = 20;
    public static final int CELL_SIZE = 40;
    

    public void showGame(Stage stage){
        this.grid = new Grid(ROWS, COLUMNS, CELL_SIZE);
        // this.snake = new Snake(grid.getGrid());
        this.snake = new Snake(grid.getGrid(), 3);
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
        int y = (int)(Math.random() * (ROWS + 1));
        int x = (int)(Math.random() * (COLUMNS + 1));

        Cell fruit = new Cell(CELL_SIZE, CellType.FOOD, Color.RED, x, y);
        fruit.setArcHeight(40);
        fruit.setArcWidth(40);
        grid.getGrid().add(fruit, x, y);
    }

    private void updateGame() {
        // snake.moveSnake();
        // snake.updateSnake(grid.getGrid());
        // spawnFruit();
        snake.moveSnake();
        snake.drawSnake();
        snake.rotateHead();
        snake.rotateTail();
    }

    private void handleKeyPress(KeyCode keyCode) {
        // snake.updateDirection(keyCode);
        snake.changeDirection(keyCode);
    }

    public static void main(String[] args){
        Application.launch();
    }
    
}
