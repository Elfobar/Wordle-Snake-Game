package com.example.demo;


import javafx.scene.input.KeyCode;
import java.util.LinkedList;


public class Snake {
    private LinkedList<Coordinate> body;
    private Direction direction;
    private final int startLength;

    public Snake(int startLength){
        this.body = new LinkedList<>();
        this.startLength = startLength;
        this.direction = Direction.LEFT;
        initializeSnake();
    }

    private void initializeSnake() {
        int middleColumn = SnakeConfig.COLUMNS / 2;
        int middleRow = SnakeConfig.ROWS / 2;

        for (int i = 0; i < startLength; i++) {
            Coordinate bodyPart = new Coordinate(middleColumn + i, middleRow);
            body.add(bodyPart);
        }
    }

    public void moveSnake(){
        for (int i = body.size() - 1; i > 0; i--) {
            body.get(i).setX(body.get(i - 1).getX());
            body.get(i).setY(body.get(i - 1).getY());
        }

        Coordinate head = getHead();
        head.setX(head.getX() + direction.getX());
        head.setY(head.getY() + direction.getY());

        wrapAround(head);
    }

    public void wrapAround(Coordinate head){
        if (head.getX() >= SnakeConfig.COLUMNS) {
            head.setX(0);
        } else if (head.getX() < 0) {
            head.setX(SnakeConfig.COLUMNS - 1);
        }
        if (head.getY() >= SnakeConfig.ROWS) {
            head.setY(0);
        } else if (head.getY() < 0) {
            head.setY(SnakeConfig.ROWS - 1);
        }
    }

    public boolean isCollidedWithItself(){
        boolean isCollidedWithItself = false;
        Coordinate snakeHead = getHead();
        for(int i = 1; i < body.size(); i++){
            if(snakeHead.equals(body.get(i))){
                isCollidedWithItself = true;
            }
        }
        return isCollidedWithItself;
    }

    public void changeDirection(KeyCode keyCode) {
        Direction currentDirection = direction;
        Direction newDirection = direction.getDirectionFromKeyCode(keyCode, direction);
        if (newDirection != currentDirection.getOpposite()) {
            this.direction = newDirection;
        }
    }

    public int determineHeadRotation(){
        return switch (direction) {
            case UP -> 180;
            case DOWN -> 0;
            case LEFT -> 90;
            case RIGHT -> -90;
        };
    }

    public int determineTailRotation(){
        Coordinate tail = body.get(body.size() - 1);
        Coordinate lastBody = body.get(body.size() - 2);

        if(tail.getX() == lastBody.getX()) {
            if (tail.getY() > lastBody.getY()) {
                return 0;
            } else {
                return 180;
            }
        } else if(tail.getY() == lastBody.getY()) {
            if (tail.getX() > lastBody.getX()) {
                return -90;
            } else {
                return 90;
            }
        }
        return 0;
    }

    public void grow(){
        Coordinate tail = getTail();
        Coordinate segment = new Coordinate(tail.getX(), tail.getY());
        body.add(segment);
    }

    public boolean containsCoordinate(Coordinate coordinate){
        for(Coordinate cord : body){
            if(cord.equals(coordinate)){
                return true;
            }
        }
        return false;
    }

    public LinkedList<Coordinate> getBody(){
        return this.body;
    }

    public Coordinate getHead(){
        return this.body.getFirst();
    }

    public Coordinate getTail(){return this.body.getLast();}
}
