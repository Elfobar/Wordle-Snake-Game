package com.example.demo;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.LinkedList;

public class Game extends Application{

    private Timeline timeline;
    private int numOfFruits;
    private ArrayList<Cell> fruits;
    private Grid grid;
    private Snake snake;

    public static final int ROWS = 20;
    public static final int COLUMNS = 20;
    public static final int CELL_SIZE = 40;
    private Menu menu;


    public void showGame(Stage stage){
        this.grid = new Grid();
        this.snake = new Snake( 3);
        this.fruits = new ArrayList<>();

        this.numOfFruits = 0;
        Scene scene = new Scene(grid.getGrid(), ROWS*CELL_SIZE, COLUMNS*CELL_SIZE);
        scene.setOnKeyPressed(event -> handleKeyPress(event.getCode()));
        stage.setTitle("SnakeGame");
        stage.setScene(scene);
        this.timeline = new Timeline(new KeyFrame(Duration.millis(200), event -> updateGame()));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    public void showMenu(Stage stage){
        menu.setState(0);
        if (menu.getMenuContent().getScene() == null){
            stage.getScene().setRoot(menu.getMenuContent());
        } else {
            stage.setScene(menu.getMenuContent().getScene());
        }
        stage.setTitle("SnakeGameMenu");
        stage.show();
        this.timeline = new Timeline(new KeyFrame(Duration.millis(300), event -> updateScene(menu.getState(), stage)));
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
            showGameOver((Stage) grid.getGrid().getScene().getWindow());
            timeline.stop();
        }
    }

    private void intersectFruit(){
        boolean intersect = false;
        int index = 0;
        for(int i = 0; i < fruits.size(); i++) {
            if (snake.getHead().equals(fruits.get(i).getCoordinate())) {
                intersect = true;
                index = i;
            }
        }
        if(intersect) {
            grid.getGrid().getChildren().remove(fruits.get(index));
            snake.grow();
            fruits.remove(index);
            numOfFruits--;
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

    private void updateScene(int state, Stage stage){
        switch (state){
            case 1:
                this.timeline.stop();
                showGame(stage);
                break;
            case 2:
                stage.getScene().setRoot(menu.getSettingsContent());
                stage.setTitle("Settings");
                stage.show();
                break;
            case 3:
                stage.getScene().setRoot(menu.getLeaderboardContent());
                stage.setTitle("Leaderboard");
                stage.show();
                break;
            case 4:
                this.timeline.stop();
                System.exit(0);
                break;
            case 5:
                menu.setState(0);
                stage.getScene().setRoot(menu.getMenuContent());
                stage.setTitle("SnakeGameMenu");
                stage.show();

        }
    }

    public void showGameOver(Stage stage){
        this.timeline.stop();
        Pane gameOverContent = menu.createGameOverContent();
        Scene gameOverScene = new Scene(gameOverContent, 800, 800);
        gameOverScene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                showGame(stage);
            } else if (event.getCode() == KeyCode.ESCAPE) {
                showMenu(stage);
            }
        });
        stage.setScene(gameOverScene);
        stage.show();
    }

    public static void main(String[] args){
        Application.launch();
    }

    @Override
    public void start(Stage stage) {
        this.menu = new Menu();
        Scene scene = new Scene(new Pane(), 800, 800);
        stage.setScene(scene);
        showMenu(stage);
    }
}