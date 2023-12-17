package com.example.demo;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

public class CellFactory {

    public static Cell createGridCellType1(Coordinate coordinate){
        Cell cell = new Cell(SnakeGame.CELL_SIZE, coordinate);
        Image image = new Image(AppConfig.getImagesPathType1());
        cell.setFill(new ImagePattern(image));
        return cell;
    }

    public static Cell createGridCellType2(Coordinate coordinate){
        Cell cell = new Cell(SnakeGame.CELL_SIZE, coordinate);
        Image image = new Image(AppConfig.getImagesPathType2());
        cell.setFill(new ImagePattern(image));
        return cell;
    }

    public static Cell createSnakeHead(){
        Cell cell = new Cell(SnakeGame.CELL_SIZE);
        Image image = new Image(AppConfig.getSnakeHeadPath());
        cell.setFill(new ImagePattern(image));
        return cell;
    }

    //ADD try-catch blocks
    public static Cell createSnakeSegment(){
        Cell cell = new Cell(SnakeGame.CELL_SIZE);
        Image image = new Image(AppConfig.getSnakeSegmentPath());
        cell.setFill(new ImagePattern(image));
        return cell;
    }

    public static Cell createSnakeTail(){
        Cell cell = new Cell(SnakeGame.CELL_SIZE);
        Image image = new Image(AppConfig.getSnakeTailPath());
        cell.setFill(new ImagePattern(image));
        return cell;
    }
}
