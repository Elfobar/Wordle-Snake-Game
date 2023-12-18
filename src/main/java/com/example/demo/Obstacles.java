package com.example.demo;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.List;
/**Todo and notes
 * food shouldnt spawn on the walls.
 * snake should die when collides (or lose a live).
 *
 * image or coloring w gridpane?
 *
 *  One map for each level, but how?
 *  */
//where is the scene class, or where do they create the map? so we can create the instance of there?

public class Obstacle extends Node {    //what is Node class?
    private List<Cell> cells;
    private List<Coordinate> body;
    private List<Obstacle> obstacles;



    public Obstacle() {     //constructor
        body = new ArrayList<>();
        this.obstacles = new ArrayList<>();
    }

    public void createObstacle(){
        for (int i = 0; i < 10; i++) {
            Cell bodyPart = new Cell(Game.CELL_SIZE, Color.BLACK, 4, 2);
        }
    }

    public void addLine(Coordinate start, Coordinate end) {
        //we don't want to code every point of the obstacles, so we draw a line instead w this method.
        int startX = start.getX();
        int endX = end.getX();

        int startY = start.getY();
        int endY = end.getY();

        int distX = Math.abs(endX - startX);
        int distY = Math.abs(endY - startY);

        int dist = Math.max(distX, distY);
        double deltaX = (double) distX / dist;
        double deltaY = (double) distY / dist;

        double x = startX;
        double y = startY;

        for (int i = 0; i <= dist; i++) {
            Coordinate position = new Coordinate((int) Math.round(x), (int) Math.round(y));
            body.add(position);

            x += deltaX;    //x and y will increase w their deltas
            y += deltaY;
        }
// snake.checkIfCollided() needs to be added here?
    }
    public List<Coordinate> getBody() {
        return body;
    }

    //public Coordinate getCoordinate(){return position;}

    @Override
    public Node getStyleableNode() {
        return super.getStyleableNode();
    }
}

/*------------------*/
public class Map {
    public boolean checkIfGameOver(Rectangle snakeHead){
        if(snakeHead.getX()>300|| snakeHead.getX()<-300 || snakeHead.getY()>300 || snakeHead.getY()<-300){
            return snakeHead.isCollided = true;
        }
    }

    public List<Obstacle> getObstacles(){
        return obstacles;
    }
    public void drawObstacle(){
        //which class should all this be in
        //Obstacle obstacle = new Obstacle();
        List<Obstacle> obstacles = getObstacles();
        for(Obstacle obstacle : obstacles){
            for(Coordinate position : obstacle.getBody()){
                grid.createCell(Color.WHITE, position.getX(),position.getY());
                grid.getGrid().add(obstacle, position.getX(),position.getY());

            }
        }
// to be moved to another method
        Obstacle obstacle = new Obstacle();
        addObstacle(obstacle);
        obstacle.addLine(new Coordinate(1,1), new Coordinate(3,3));
    }


    private void addObstacle(Obstacle obstacle){
        obstacles.add(obstacle);
    }

}



