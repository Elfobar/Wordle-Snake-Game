package com.example.demo;

import javafx.geometry.HPos;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import java.util.ArrayList;
import java.util.LinkedList;

public class GameRenderer {


    private GameController gameController;
    private LinkedList<Cell> visualSnakeBody;
    private ArrayList<Text> visualLetters;
    private GridPane grid;


    public GameRenderer(GameController gameController){
        this.gameController = gameController;
        this.grid = new GridPane();
        this.visualSnakeBody = initializeVisualSnake();
        this.visualLetters = initializeVisualLetters();
    }

    public void drawGrid(){
        Cell gridCell;
        for(int row = 0; row < SnakeGame.ROWS; row++){
            for(int col = 0; col < SnakeGame.COLUMNS; col++){
                if((row + col) % 2 == 0){
                    gridCell = CellFactory.createGridCellType1(new Coordinate(row, col));
                } else{
                    gridCell = CellFactory.createGridCellType2(new Coordinate(row, col));
                }
                grid.add(gridCell, row, col);
            }
        }
    }

    public void renderGame(){
        drawSnake();
        rotateSnakeHead();
        rotateSnakeTail();
        drawLetters();
    }

    public void drawSnake(){
        LinkedList<Coordinate> snakeBodyPos = gameController.getSnake().getBody();

        if (snakeBodyPos.size() > visualSnakeBody.size()) {
            Cell newSnakeCell = CellFactory.createSnakeSegment();
            visualSnakeBody.add(1, newSnakeCell);
        }

        for(int i = 0; i < snakeBodyPos.size(); i++){
            Coordinate coordinate = snakeBodyPos.get(i);
            Cell cell = visualSnakeBody.get(i);

            grid.getChildren().remove(cell);
            grid.add(cell, coordinate.getX(), coordinate.getY());
        }

    }

    public void drawLetters(){
        ArrayList<Letter> letters = gameController.getLetters();

        for(int i = 0; i < letters.size(); i++){
            Coordinate coordinate = letters.get(i).getPosition();
            Text text = visualLetters.get(i);

            char letterValue = letters.get(i).getValue();
            text.setText(String.valueOf(letterValue));

            grid.getChildren().remove(text);
            grid.add(text, coordinate.getX(), coordinate.getY());
            GridPane.setHalignment(text, HPos.CENTER);
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

    public LinkedList<Cell> initializeVisualSnake() {
        LinkedList<Cell> visualSnakeBody = new LinkedList<>();
        Cell snakeCell;
        for (int i = 0; i < SnakeGame.SNAKE_LENGTH; i++) {
            if (i == 0) {
                snakeCell = CellFactory.createSnakeHead();
            } else if (i == SnakeGame.SNAKE_LENGTH - 1) {
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

    public GridPane getGrid(){
        return this.grid;
    }
}
