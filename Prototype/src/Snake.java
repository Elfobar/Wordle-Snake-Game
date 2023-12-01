package org.apache.maven.SnakeGame;


import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import java.util.List;
import java.util.ArrayList;

public class Snake {
    private ArrayList<Integer> currentSnakeX;
    private ArrayList<Integer> currentSnakeY;
    private GridPane gridPane;
    private List<Rectangle> oldSnake = new ArrayList<>();
    private int directionX = -1;
    private int directionY = 0;
    private Image image = new Image("file:C:/Users/Maks/Desktop/pics/Snake.png");
    private final int startLength = 2;
    private boolean isAlive = true;
    public Fruit currentEdibleFruit = null;

    public Snake(GridPane gridPane, int initialLenght){
        currentSnakeX = new ArrayList<>();
        currentSnakeY = new ArrayList<>();
        this.gridPane = gridPane;
        initializeSnake(initialLenght);
    }

    private void initializeSnake(int initialLenght) {
        // body = new int[initialLenght][2];

        int startColumn =(GameApp.GRID_SIZE - initialLenght) / 2;
        int startRow = GameApp.GRID_SIZE / 2;

        // for(int i = 0; i < initialLenght; i++){
        //     body[i][0] = startColumn + i; //x coordinate
        //     body[i][1] = startRow; //y coordinate
            
        // }
        for(int i = 0; i < startLength; i++){
            currentSnakeX.add(startColumn + i);
            currentSnakeY.add(startRow);
        }
    }

    public void drawSnake(){

        gridPane.getChildren().removeAll(oldSnake);
        oldSnake.clear();

        // for(int i = 0; i < body.length; i++){
        //     int x = body[i][0];
        //     int y = body[i][1];

        //     Rectangle square = new Rectangle(GameApp.CELL_SIZE, GameApp.CELL_SIZE);
        //     if(i==0){
                
        //         square.setFill(new ImagePattern(image));
                
        //     } else{
        //         square.setFill(Color.web("#00EAFA"));
        //     }
            
        //     oldSnake.add(square);
        //     gridPane.add(square, x, y);   
        // }
        for(int i = 0; i < currentSnakeX.size(); i++){
            int x = currentSnakeX.get(i);
            int y = currentSnakeY.get(i);

            Rectangle square = new Rectangle(GameApp.CELL_SIZE, GameApp.CELL_SIZE);
            
            if(i==0){
                
                square.setFill(new ImagePattern(image));
                
            } else{
                square.setFill(Color.web("#00EAFA"));
                square.setArcHeight(25);
                square.setArcWidth(25);
            }

            oldSnake.add(square);
            gridPane.add(square, x, y);   
        }
    }

    public void moveSnake(){
        // for(int i = body.length - 1; i > 0; i--){
        //     body[i][0] = body[i-1][0];
        //     body[i][1] = body[i-1][1];
        // }
        // int headX = body[0][0] + directionX;
        // int headY = body[0][1] + directionY;
        
        // body[0][0] = headX;
        // body[0][1] = headY;

        int headX = currentSnakeX.get(0) + directionX;
        int headY = currentSnakeY.get(0) + directionY;

        for(int i = currentSnakeX.size() - 1; i > 0; i--){
            currentSnakeX.set(i, currentSnakeX.get(i-1));
            currentSnakeY.set(i, currentSnakeY.get(i-1));
        }

        //Wrap around if the head goes beyond the right edge
        // headX = (headX + GameApp.GRID_SIZE) % GameApp.GRID_SIZE;
        // headY = (headY + GameApp.GRID_SIZE) % GameApp.GRID_SIZE;
        if(headX > GameApp.GRID_SIZE - 1){
            headX = 0;
        } else if(headY > GameApp.GRID_SIZE - 1){
            headY = 0;
        } else if(headX < 0){
            headX = GameApp.GRID_SIZE-1;
        } else if(headY < 0){
            headY = GameApp.GRID_SIZE-1;
        }
        
        if(!isCollidedIntoHerself(headX, headY)){
        currentSnakeX.remove(0);
        currentSnakeX.add(0, headX);
        currentSnakeY.remove(0);
        currentSnakeY.add(0, headY);
        } else{
            isAlive = false;
        }
        
    }

    public void handleInput(KeyCode keyCode) {
        KeyCode currentDirection = getSnakeDirection();
        switch (keyCode) {
            case UP:
                if(currentDirection != KeyCode.DOWN){
                directionX = 0;
                directionY = -1;  //UP
                }
                break;
            case DOWN:
                if(currentDirection != KeyCode.UP){
                directionX = 0;
                directionY = 1;  //DOWN
                }
                break;
            case LEFT:
                if(currentDirection != KeyCode.RIGHT){
                directionX = -1;  //LEFT
                directionY = 0;
                }
                break;
            case RIGHT:
                if(currentDirection != KeyCode.LEFT){
                directionX = 1;  //RIGHT
                directionY = 0;
                }
                break;
            case Q:
                directionX = -1;
                directionY = -1;
        }
    }

    public void rotateHead(){
        Rectangle square = oldSnake.get(0);
        if(this.directionX == 0 && this.directionY == -1){
            square.setRotate(180);
        } else if(this.directionX == 0 && directionY == 1){
            square.setRotate(360);
        } else if(this.directionX == -1 && this.directionY == 0){
            square.setRotate(90);
        } else{
            square.setRotate(-90);
        }
    }

    public void eatFruit(GridPane grid, GameApp app, ArrayList<Fruit> fruits){
            // currentEdibleFruit = null;
            for(Fruit fr : fruits){
                if(currentSnakeX.get(0) == fr.getFruitX() && currentSnakeY.get(0) == fr.getFruitY()){
                grow();
                String s = fr.getUserData();
                app.currentWord = app.currentWord + s;
                System.out.println(app.currentWord);
                fruits.remove(fr);
                grid.getChildren().remove(fr.getNode());
            }
         }
    }

    // public void setCurrentEdibleFruit(Fruit fruit) {
    //     this.currentEdibleFruit = fruit;
    // }

    public void grow(){
        int snakeSegment = oldSnake.size();
        Rectangle square = new Rectangle(GameApp.CELL_SIZE, GameApp.CELL_SIZE);
        square.setFill(Color.web("#00EAFA"));
        oldSnake.add(square);
        int startColumn =(GameApp.GRID_SIZE - startLength) / 2;
        int startRow = GameApp.GRID_SIZE / 2;

        currentSnakeX.add(oldSnake.size() - 1, startColumn+ oldSnake.size());
        currentSnakeY.add(oldSnake.size() - 1, startRow + oldSnake.size());
    }

    public boolean isCollidedIntoHerself(int headX, int headY){
        boolean isCollided = false;
        for(int i = 0; i < currentSnakeX.size(); i++){
            if(headX == currentSnakeX.get(i) && headY == currentSnakeY.get(i)){
                isCollided = true;
            }
        }
        return isCollided;
    }

    public boolean checkIfAlive(){
        return this.isAlive;
    }

    public ArrayList<Integer> getCurrentSnakeX(){
        return currentSnakeX;
    }

    public ArrayList<Integer> getCurrentSnakeY(){
        return currentSnakeY;
    }

    public KeyCode getSnakeDirection(){
        int headX = currentSnakeX.get(0);
        int headY = currentSnakeY.get(0);
        boolean down = directionX == 0 && directionY == 1;
        boolean up = directionX == 0 && directionY == -1;
        boolean left = directionX == -1 && directionY == 0;
        boolean right = directionX == 1 && directionY == 0;
        if (down) return KeyCode.DOWN;
        if (up) return KeyCode.UP;
        if (right) return KeyCode.RIGHT;
        else return KeyCode.LEFT;
    }

    public Fruit getCurrentEdibleFruit(){
        return currentEdibleFruit;
    }
}
