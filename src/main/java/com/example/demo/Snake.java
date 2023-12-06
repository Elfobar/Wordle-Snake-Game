package com.example.demo;

import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.*;
import javafx.scene.shape.Rectangle;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Snake {
    private LinkedList<Cell> body;
    private Direction direction;
    private final int startLength;
    private final String headPath = "/images/snakeHead.png";
    private final String segmentPath = "/images/snakeSegment.png";
    private final String tailPath = "/images/snakeTail.png";
    private final Image headImg = new Image(Objects.requireNonNull(getClass().getResourceAsStream(headPath)));
    private final Image segmentImg = new Image(Objects.requireNonNull(getClass().getResourceAsStream(segmentPath)));
    private final Image tailImg = new Image(Objects.requireNonNull(getClass().getResourceAsStream(tailPath)));

    public Snake(int startLength){
        this.body = new LinkedList<>();
        this.startLength = startLength;
        this.direction = Direction.UP;
        initializeSnake();
    }

    private void initializeSnake() {
        int middleColumn = Game.COLUMNS / 2;
        int middleRow = Game.ROWS / 2;

        for (int i = 0; i < startLength; i++) {
            Cell bodyPart = new Cell(Game.CELL_SIZE, Color.BLACK, middleColumn + i, middleRow);
            if (i == 0) {
                bodyPart.setFill(new ImagePattern(headImg));
            } else if (i == startLength - 1) {
                bodyPart.setFill(new ImagePattern(tailImg));
            } else {
                bodyPart.setFill(new ImagePattern(segmentImg));
            }
            body.add(bodyPart);
        }
    }

    public void moveSnake(){
        for (int i = body.size() - 1; i > 0; i--) {
            body.get(i).getCoordinate().setX(body.get(i - 1).getCoordinate().getX());
            body.get(i).getCoordinate().setY(body.get(i - 1).getCoordinate().getY());
        }

        Coordinate head = getHead();
        head.setX(head.getX() + direction.getX());
        head.setY(head.getY() + direction.getY());

        if (head.getX() >= Game.COLUMNS) {
            head.setX(0);
        } else if (head.getX() < 0) {
            head.setX(Game.COLUMNS - 1);
        }
        if (head.getY() >= Game.ROWS) {
            head.setY(0);
        } else if (head.getY() < 0) {
            head.setY(Game.ROWS - 1);
        }
        checkIfCollided();
    }

    public void changeDirection(KeyCode keyCode) {
        Direction currentDirection = direction;
        Direction newDirection = direction.getDirectionFromKeyCode(keyCode);
        if (newDirection != currentDirection.getOpposite()) {
            this.direction = newDirection;
        }
    }

    public void rotateHead(){
        Rectangle head = body.get(0);
        if(direction == Direction.UP){
            head.setRotate(180);
        } else if(direction == Direction.DOWN){
            head.setRotate(360);
        } else if(direction == Direction.LEFT){
            head.setRotate(90);
        } else{
            head.setRotate(-90);
        }
    }

    public void rotateTail(){
        Rectangle tailRect = body.get(body.size() - 1);
        Coordinate tail = body.get(body.size() - 1).getCoordinate();
        Coordinate lastBody = body.get(body.size() - 2).getCoordinate();

        if(tail.getX() == lastBody.getX()) {
            if (tail.getY() > lastBody.getY()) {
                tailRect.setRotate(0);
            } else {
                tailRect.setRotate(180);
            }
        } else if(tail.getY() == lastBody.getY()) {
            if (tail.getX() > lastBody.getX()) {
                tailRect.setRotate(-90);
            } else {
                tailRect.setRotate(90);
            }
        }
    }

    public boolean checkIfCollided(){
        boolean isCollided = false;
        for(int i = 1; i < body.size(); i++){
            if(getHead().equals(body.get(i).getCoordinate())){
                isCollided = true;
            }
        }
        return isCollided;
    }

    public void grow(){
        Coordinate head = body.get(1).getCoordinate();
        Cell segment = new Cell(Game.CELL_SIZE, Color.BLACK, head.getX(), head.getY());
        segment.setFill(new ImagePattern(segmentImg));
        body.add(1, segment);
    }

    public LinkedList<Cell> getBody(){
        return this.body;
    }

    public Coordinate getHead(){
        return body.get(0).getCoordinate();
    }
}
